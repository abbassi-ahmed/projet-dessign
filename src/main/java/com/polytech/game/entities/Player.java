package com.polytech.game.entities;

import com.polytech.game.patterns.composite.Bullet;
import com.polytech.game.patterns.decorator.*;
import com.polytech.game.patterns.singleton.GameLogger;
import com.polytech.game.patterns.state.IdlePlayerState;
import com.polytech.game.patterns.state.PlayerState;
import javafx.scene.canvas.GraphicsContext;

import java.util.ArrayList;
import java.util.List;

/**
 * Player Entity
 * Represents the player character with state and decorator patterns
 */
public class Player {
    private GameCharacter character;
    private PlayerState state;
    private final GameLogger logger;
    private final List<PowerUpDecorator> activePowerUps;
    private final List<Bullet> bullets;
    private double x; // Player's actual position
    private double y;
    private double velocityX = 0;
    private double shootCooldown = 0;
    private static final double SHOOT_COOLDOWN_TIME = 0.3; // seconds between shots
    private static final double MAX_SPEED = 300;
    private static final double SCREEN_WIDTH = 800;

    public Player(double x, double y) {
        this.x = x;
        this.y = y;
        this.character = new BaseCharacter(x, y);
        this.state = new IdlePlayerState();
        this.logger = GameLogger.getInstance();
        this.activePowerUps = new ArrayList<>();
        this.bullets = new ArrayList<>();
        logger.info("Player created at position (" + x + ", " + y + ")");
    }

    public void update(double deltaTime) {
        // Update player state
        state.update(this, deltaTime);

        // Update shoot cooldown
        if (shootCooldown > 0) {
            shootCooldown -= deltaTime;
        }

        // Update position based on velocity
        x += velocityX * deltaTime;

        // Clamp to screen bounds
        if (x < 30) {
            x = 30;
        } else if (x > SCREEN_WIDTH - 30) {
            x = SCREEN_WIDTH - 30;
        }

        // Update the base character's position (unwrap decorators to find it)
        BaseCharacter baseChar = getBaseCharacter();
        if (baseChar != null) {
            baseChar.setX(x);
            baseChar.setY(y);
        }

        // Update bullets
        List<Bullet> bulletsToRemove = new ArrayList<>();
        for (Bullet bullet : bullets) {
            bullet.update();
            if (!bullet.isActive()) {
                bulletsToRemove.add(bullet);
            }
        }
        bullets.removeAll(bulletsToRemove);

        // Update power-ups
        List<PowerUpDecorator> expiredPowerUps = new ArrayList<>();
        for (PowerUpDecorator powerUp : activePowerUps) {
            powerUp.update(deltaTime);
            if (powerUp.isExpired()) {
                expiredPowerUps.add(powerUp);
            }
        }

        // Remove expired power-ups
        for (PowerUpDecorator powerUp : expiredPowerUps) {
            removePowerUp(powerUp);
        }
    }

    public void render(GraphicsContext gc) {
        character.draw(gc);

        // Render bullets
        for (Bullet bullet : bullets) {
            bullet.render(gc);
        }
    }

    public void moveLeft() {
        velocityX = -character.getSpeed();
        state.handleInput(this, "MOVE_LEFT");
    }

    public void moveRight() {
        velocityX = character.getSpeed();
        state.handleInput(this, "MOVE_RIGHT");
    }

    public void stopMoving() {
        velocityX = 0;
        state.handleInput(this, "STOP");
    }

    public void shoot() {
        // Check cooldown
        if (shootCooldown > 0) {
            return; // Still on cooldown
        }

        state.handleInput(this, "SHOOT");

        // Create bullet(s) based on fire power
        int firePower = character.getFirePower();

        if (firePower == 1) {
            // Single bullet
            bullets.add(new Bullet(x, y - 15, true, firePower));
        } else if (firePower == 2) {
            // Double bullets (spread)
            bullets.add(new Bullet(x - 10, y - 15, true, 1));
            bullets.add(new Bullet(x + 10, y - 15, true, 1));
        } else {
            // Triple bullets (spread)
            bullets.add(new Bullet(x - 15, y - 15, true, 1));
            bullets.add(new Bullet(x, y - 15, true, 1));
            bullets.add(new Bullet(x + 15, y - 15, true, 1));
        }

        // Reset cooldown
        shootCooldown = SHOOT_COOLDOWN_TIME;

        logger.info("Player fired weapon (Fire Power: " + firePower + ", Bullets: " + bullets.size() + ")");
    }

    /**
     * Apply a power-up to the player (Decorator Pattern)
     */
    public void applyPowerUp(PowerUpDecorator powerUp) {
        character = powerUp;
        activePowerUps.add(powerUp);
    }

    /**
     * Apply a specific power-up type by name (for testing/demo)
     */
    public void applyPowerUpByType(String type, double duration) {
        PowerUpDecorator decorator;
        switch (type.toUpperCase()) {
            case "SPEED":
                decorator = new SpeedBoostDecorator(character, duration);
                break;
            case "SHIELD":
                decorator = new ShieldDecorator(character, duration);
                break;
            case "FIREPOWER":
                decorator = new FirePowerDecorator(character, duration);
                break;
            default:
                return;
        }
        character = decorator;
        activePowerUps.add(decorator);
    }

    /**
     * Remove a power-up from the player
     */
    private void removePowerUp(PowerUpDecorator powerUp) {
        logger.logDecoratorRemoval(powerUp.getClass().getSimpleName(), "Player");
        activePowerUps.remove(powerUp);

        // Unwrap the decorator
        GameCharacter current = character;
        while (current instanceof PowerUpDecorator) {
            PowerUpDecorator decorator = (PowerUpDecorator) current;
            if (decorator == powerUp) {
                character = decorator.getDecoratedCharacter();
                break;
            }
            current = decorator.getDecoratedCharacter();
        }
    }

    public void setPlayerState(PlayerState state) {
        this.state = state;
    }

    public void setVelocityX(double velocityX) {
        this.velocityX = velocityX;
    }

    public String getActivePowerUps() {
        if (activePowerUps.isEmpty()) {
            return "None";
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < activePowerUps.size(); i++) {
            PowerUpDecorator powerUp = activePowerUps.get(i);
            String name = powerUp.getClass().getSimpleName().replace("Decorator", "");
            sb.append(name).append(String.format(" (%.1fs)", powerUp.getRemainingTime()));
            if (i < activePowerUps.size() - 1) {
                sb.append(", ");
            }
        }
        return sb.toString();
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    /**
     * Unwrap decorators to get the base character
     */
    private BaseCharacter getBaseCharacter() {
        GameCharacter current = character;
        while (current instanceof PowerUpDecorator) {
            current = ((PowerUpDecorator) current).getDecoratedCharacter();
        }
        if (current instanceof BaseCharacter) {
            return (BaseCharacter) current;
        }
        return null;
    }

    public int getFirePower() {
        return character.getFirePower();
    }

    public double getSpeed() {
        return character.getSpeed();
    }

    public int getDefense() {
        return character.getDefense();
    }

    public List<Bullet> getBullets() {
        return bullets;
    }
}

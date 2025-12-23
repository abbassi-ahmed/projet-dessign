package com.polytech.game.patterns.state;

import com.polytech.game.entities.Player;
import com.polytech.game.patterns.composite.Bullet;
import com.polytech.game.patterns.composite.Enemy;
import com.polytech.game.patterns.composite.GameComponent;
import com.polytech.game.patterns.composite.GameLevel;
import com.polytech.game.patterns.factory.EnemyFactory;
import com.polytech.game.patterns.factory.EntityFactory;
import com.polytech.game.patterns.singleton.GameContext;
import com.polytech.game.patterns.singleton.GameLogger;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

/**
 * State Pattern - PlayingState
 * Represents the active gameplay state
 */
public class PlayingState implements GameState {
    private final GameContext context;
    private final GameLogger logger;
    private final GameLevel currentLevel;
    private final Player player;
    private int score;
    private int lives;

    public PlayingState(GameContext context) {
        this.context = context;
        this.logger = GameLogger.getInstance();
        this.score = 0;
        this.lives = 3;

        // Create player
        this.player = new Player(400, 550);

        // Create level using Composite Pattern
        this.currentLevel = new GameLevel("Level 1");

        // Create enemies using Factory Pattern
        EntityFactory enemyFactory = new EnemyFactory();
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 8; col++) {
                currentLevel.add(enemyFactory.createEntity(100 + col * 80, 100 + row * 60));
            }
        }
    }

    @Override
    public void handleInput(KeyCode keyCode) {
        switch (keyCode) {
            case LEFT:
                player.moveLeft();
                break;
            case RIGHT:
                player.moveRight();
                break;
            case SPACE:
                player.shoot();
                break;
            case P:
                context.changeState(new PausedState(context, this));
                break;
            case ESCAPE:
                context.changeState(new MenuState(context));
                break;

            // TEST KEYS FOR POWER-UPS (for demonstration)
            case DIGIT1:
                // Apply Speed Boost (5 seconds)
                player.applyPowerUpByType("SPEED", 5.0);
                logger.info("✨ TEST: Speed Boost applied! (Press 1) - Move faster for 5 seconds");
                break;
            case DIGIT2:
                // Apply Shield (10 seconds)
                player.applyPowerUpByType("SHIELD", 10.0);
                logger.info("🛡️ TEST: Shield applied! (Press 2) - See cyan bubble for 10 seconds");
                break;
            case DIGIT3:
                // Apply Fire Power (8 seconds)
                player.applyPowerUpByType("FIREPOWER", 8.0);
                logger.info("🔥 TEST: Fire Power applied! (Press 3) - Shoot multiple bullets for 8 seconds");
                break;
        }
    }

    @Override
    public void update(double deltaTime) {
        player.update(deltaTime);
        currentLevel.update();

        // Check for collisions
        checkCollisions();

        // Check win/lose conditions
        if (lives <= 0) {
            context.changeState(new GameOverState(context, score));
        } else if (currentLevel.getAllEnemies().isEmpty()) {
            context.changeState(new VictoryState(context, score));
        }
    }

    @Override
    public void render(GraphicsContext gc) {
        // Clear screen
        gc.setFill(Color.BLACK);
        gc.fillRect(0, 0, gc.getCanvas().getWidth(), gc.getCanvas().getHeight());

        // Render level (Composite Pattern)
        currentLevel.render(gc);

        // Render player
        player.render(gc);

        // Render HUD
        renderHUD(gc);
    }

    private void renderHUD(GraphicsContext gc) {
        gc.setFill(Color.WHITE);
        gc.setFont(new Font("Arial", 18));
        gc.fillText("Score: " + score, 600, 25);
        gc.fillText("Lives: " + lives, 600, 50);
        gc.fillText("Active Power-ups: " + player.getActivePowerUps(), 600, 75);
    }

    private void checkCollisions() {
        // Check bullet-enemy collisions
        for (Bullet bullet : player.getBullets()) {
            if (!bullet.isActive() || !bullet.isPlayerBullet()) {
                continue;
            }

            for (GameComponent component : currentLevel.getComponents()) {
                if (component instanceof Enemy && component.isActive()) {
                    Enemy enemy = (Enemy) component;

                    // Check if bullet hits enemy
                    if (bullet.collidesWith(enemy.getX() - enemy.getWidth() / 2,
                                           enemy.getY() - enemy.getHeight() / 2,
                                           enemy.getWidth(),
                                           enemy.getHeight())) {
                        // Hit!
                        enemy.takeDamage(bullet.getDamage());
                        bullet.setActive(false);

                        // Add score
                        score += 100;
                        logger.info("Enemy destroyed! Score: " + score);
                        break;
                    }
                }
            }
        }
    }

    @Override
    public void enter() {
        logger.info("Game started - Entered Playing State");
    }

    @Override
    public void exit() {
        logger.info("Exited Playing State");
    }

    @Override
    public String getStateName() {
        return "PLAYING";
    }

    public Player getPlayer() {
        return player;
    }

    public int getScore() {
        return score;
    }

    public void addScore(int points) {
        this.score += points;
    }

    public void loseLife() {
        this.lives--;
        logger.info("Life lost. Remaining lives: " + lives);
    }
}

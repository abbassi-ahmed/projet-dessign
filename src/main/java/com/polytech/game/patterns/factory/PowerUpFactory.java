package com.polytech.game.patterns.factory;

import com.polytech.game.patterns.composite.GameComponent;
import com.polytech.game.patterns.composite.PowerUp;
import com.polytech.game.patterns.singleton.GameLogger;

import java.util.Random;

/**
 * Factory Pattern - PowerUpFactory
 * Concrete factory for creating power-up entities
 */
public class PowerUpFactory implements EntityFactory {
    private final GameLogger logger;
    private final Random random;
    private int powerUpCount = 0;

    public PowerUpFactory() {
        this.logger = GameLogger.getInstance();
        this.random = new Random();
    }

    @Override
    public GameComponent createEntity(double x, double y) {
        // Randomly select a power-up type
        PowerUp.PowerUpType[] types = PowerUp.PowerUpType.values();
        PowerUp.PowerUpType type = types[random.nextInt(types.length)];

        PowerUp powerUp = new PowerUp(x, y, type);
        powerUpCount++;
        logger.info("PowerUpFactory created " + type.name() + " #" + powerUpCount +
                   " at position (" + x + ", " + y + ")");
        return powerUp;
    }

    /**
     * Create a specific type of power-up
     */
    public GameComponent createSpecificPowerUp(double x, double y, PowerUp.PowerUpType type) {
        PowerUp powerUp = new PowerUp(x, y, type);
        powerUpCount++;
        logger.info("PowerUpFactory created " + type.name() + " #" + powerUpCount +
                   " at position (" + x + ", " + y + ")");
        return powerUp;
    }

    @Override
    public String getFactoryType() {
        return "PowerUpFactory";
    }

    public int getPowerUpCount() {
        return powerUpCount;
    }
}

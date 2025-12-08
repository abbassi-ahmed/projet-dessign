package com.polytech.game.patterns.factory;

import com.polytech.game.patterns.composite.Enemy;
import com.polytech.game.patterns.composite.GameComponent;
import com.polytech.game.patterns.singleton.GameLogger;

/**
 * Factory Pattern - EnemyFactory
 * Concrete factory for creating enemy entities
 */
public class EnemyFactory implements EntityFactory {
    private final GameLogger logger;
    private int enemyCount = 0;

    public EnemyFactory() {
        this.logger = GameLogger.getInstance();
    }

    @Override
    public GameComponent createEntity(double x, double y) {
        Enemy enemy = new Enemy(x, y);
        enemyCount++;
        logger.info("EnemyFactory created enemy #" + enemyCount + " at position (" + x + ", " + y + ")");
        return enemy;
    }

    @Override
    public String getFactoryType() {
        return "EnemyFactory";
    }

    public int getEnemyCount() {
        return enemyCount;
    }
}

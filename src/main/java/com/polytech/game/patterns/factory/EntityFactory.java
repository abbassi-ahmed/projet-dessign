package com.polytech.game.patterns.factory;

import com.polytech.game.patterns.composite.GameComponent;

/**
 * Factory Pattern - EntityFactory Interface
 * Defines the interface for creating game entities
 */
public interface EntityFactory {
    /**
     * Create a game entity at specified position
     */
    GameComponent createEntity(double x, double y);

    /**
     * Get factory name/type
     */
    String getFactoryType();
}

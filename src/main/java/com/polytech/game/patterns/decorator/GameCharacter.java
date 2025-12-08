package com.polytech.game.patterns.decorator;

import javafx.scene.canvas.GraphicsContext;

/**
 * Decorator Pattern - GameCharacter Interface
 * Base component for the Decorator Pattern
 * Represents a game character that can be decorated with power-ups
 */
public interface GameCharacter {
    /**
     * Draw the character
     */
    void draw(GraphicsContext gc);

    /**
     * Get character's current speed
     */
    double getSpeed();

    /**
     * Get character's firepower
     */
    int getFirePower();

    /**
     * Get character's defense/shield strength
     */
    int getDefense();

    /**
     * Get description of active power-ups
     */
    String getDescription();
}

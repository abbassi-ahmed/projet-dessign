package com.polytech.game.patterns.composite;

import javafx.scene.canvas.GraphicsContext;

/**
 * Composite Pattern - GameComponent Interface
 * Component interface for the Composite Pattern
 * Represents any element in the game hierarchy
 */
public interface GameComponent {
    /**
     * Update the component's logic
     */
    void update();

    /**
     * Render the component
     */
    void render(GraphicsContext gc);

    /**
     * Add a child component (for composite objects)
     */
    void add(GameComponent component);

    /**
     * Remove a child component (for composite objects)
     */
    void remove(GameComponent component);

    /**
     * Get component name/description
     */
    String getName();

    /**
     * Check if component is active
     */
    boolean isActive();

    /**
     * Set active state
     */
    void setActive(boolean active);
}

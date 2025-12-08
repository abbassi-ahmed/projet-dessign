package com.polytech.game.patterns.composite;

import javafx.scene.canvas.GraphicsContext;
import java.util.ArrayList;
import java.util.List;

/**
 * Composite Pattern - GameLevel
 * Composite class that can contain multiple game components
 * Represents a level containing enemies, obstacles, power-ups, etc.
 */
public class GameLevel implements GameComponent {
    private final String name;
    private final List<GameComponent> components;
    private boolean active;

    public GameLevel(String name) {
        this.name = name;
        this.components = new ArrayList<>();
        this.active = true;
    }

    @Override
    public void update() {
        // Update all child components
        List<GameComponent> componentsToRemove = new ArrayList<>();

        for (GameComponent component : components) {
            if (component.isActive()) {
                component.update();
            } else {
                componentsToRemove.add(component);
            }
        }

        // Remove inactive components
        components.removeAll(componentsToRemove);
    }

    @Override
    public void render(GraphicsContext gc) {
        // Render all child components
        for (GameComponent component : components) {
            if (component.isActive()) {
                component.render(gc);
            }
        }
    }

    @Override
    public void add(GameComponent component) {
        components.add(component);
    }

    @Override
    public void remove(GameComponent component) {
        components.remove(component);
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public boolean isActive() {
        return active;
    }

    @Override
    public void setActive(boolean active) {
        this.active = active;
    }

    /**
     * Get all enemies in the level
     */
    public List<GameComponent> getAllEnemies() {
        List<GameComponent> enemies = new ArrayList<>();
        for (GameComponent component : components) {
            if (component.getName().contains("Enemy")) {
                enemies.add(component);
            }
        }
        return enemies;
    }

    /**
     * Get count of components
     */
    public int getComponentCount() {
        return components.size();
    }

    /**
     * Get all components
     */
    public List<GameComponent> getComponents() {
        return new ArrayList<>(components);
    }
}

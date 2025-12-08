package com.polytech.game.patterns.composite;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * Composite Pattern - Enemy
 * Leaf class representing a single enemy
 */
public class Enemy implements GameComponent {
    private double x;
    private double y;
    private double width = 40;
    private double height = 30;
    private boolean active;
    private int health;
    private double speed = 50;

    public Enemy(double x, double y) {
        this.x = x;
        this.y = y;
        this.active = true;
        this.health = 1;
    }

    @Override
    public void update() {
        // Simple movement pattern
        x += speed * 0.016; // Assuming ~60 FPS

        // Change direction at edges
        if (x > 750 || x < 50) {
            speed = -speed;
            y += 10; // Move down when changing direction
        }
    }

    @Override
    public void render(GraphicsContext gc) {
        gc.setFill(Color.RED);
        gc.fillRect(x - width / 2, y - height / 2, width, height);

        // Draw eyes
        gc.setFill(Color.WHITE);
        gc.fillOval(x - 12, y - 8, 8, 8);
        gc.fillOval(x + 4, y - 8, 8, 8);

        gc.setFill(Color.BLACK);
        gc.fillOval(x - 10, y - 6, 4, 4);
        gc.fillOval(x + 6, y - 6, 4, 4);
    }

    @Override
    public void add(GameComponent component) {
        // Leaf node - cannot add children
        throw new UnsupportedOperationException("Cannot add component to a leaf node");
    }

    @Override
    public void remove(GameComponent component) {
        // Leaf node - cannot remove children
        throw new UnsupportedOperationException("Cannot remove component from a leaf node");
    }

    @Override
    public String getName() {
        return "Enemy";
    }

    @Override
    public boolean isActive() {
        return active && health > 0;
    }

    @Override
    public void setActive(boolean active) {
        this.active = active;
    }

    public void takeDamage(int damage) {
        health -= damage;
        if (health <= 0) {
            active = false;
        }
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }
}

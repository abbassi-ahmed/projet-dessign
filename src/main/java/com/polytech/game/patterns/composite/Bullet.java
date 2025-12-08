package com.polytech.game.patterns.composite;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * Composite Pattern - Bullet
 * Leaf class representing a bullet/projectile
 */
public class Bullet implements GameComponent {
    private double x;
    private double y;
    private double width = 4;
    private double height = 12;
    private double speed = 400; // pixels per second
    private boolean active;
    private int damage;
    private boolean playerBullet; // true for player bullets, false for enemy bullets

    public Bullet(double x, double y, boolean playerBullet, int damage) {
        this.x = x;
        this.y = y;
        this.playerBullet = playerBullet;
        this.damage = damage;
        this.active = true;
    }

    @Override
    public void update() {
        // Move bullet
        if (playerBullet) {
            y -= speed * 0.016; // Move up for player bullets
        } else {
            y += speed * 0.016; // Move down for enemy bullets
        }

        // Deactivate if off screen
        if (y < -20 || y > 620) {
            active = false;
        }
    }

    @Override
    public void render(GraphicsContext gc) {
        if (playerBullet) {
            // Player bullet - cyan/white
            gc.setFill(Color.CYAN);
            gc.fillRect(x - width / 2, y - height / 2, width, height);
            gc.setFill(Color.WHITE);
            gc.fillRect(x - width / 2 + 1, y - height / 2, width - 2, height / 2);
        } else {
            // Enemy bullet - red
            gc.setFill(Color.RED);
            gc.fillOval(x - width / 2, y - height / 2, width, height);
        }
    }

    @Override
    public void add(GameComponent component) {
        throw new UnsupportedOperationException("Cannot add component to a leaf node");
    }

    @Override
    public void remove(GameComponent component) {
        throw new UnsupportedOperationException("Cannot remove component from a leaf node");
    }

    @Override
    public String getName() {
        return playerBullet ? "PlayerBullet" : "EnemyBullet";
    }

    @Override
    public boolean isActive() {
        return active;
    }

    @Override
    public void setActive(boolean active) {
        this.active = active;
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

    public int getDamage() {
        return damage;
    }

    public boolean isPlayerBullet() {
        return playerBullet;
    }

    /**
     * Check collision with rectangular bounds
     */
    public boolean collidesWith(double ox, double oy, double ow, double oh) {
        return x < ox + ow &&
               x + width > ox &&
               y < oy + oh &&
               y + height > oy;
    }
}

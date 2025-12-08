package com.polytech.game.patterns.composite;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * Composite Pattern - PowerUp
 * Leaf class representing a power-up collectible
 */
public class PowerUp implements GameComponent {
    private double x;
    private double y;
    private double width = 30;
    private double height = 30;
    private boolean active;
    private PowerUpType type;
    private double animationTime = 0;

    public enum PowerUpType {
        SPEED_BOOST(Color.YELLOW),
        SHIELD(Color.CYAN),
        FIRE_POWER(Color.ORANGE);

        private final Color color;

        PowerUpType(Color color) {
            this.color = color;
        }

        public Color getColor() {
            return color;
        }
    }

    public PowerUp(double x, double y, PowerUpType type) {
        this.x = x;
        this.y = y;
        this.type = type;
        this.active = true;
    }

    @Override
    public void update() {
        animationTime += 0.016;
        // Slowly descend
        y += 30 * 0.016;

        // Deactivate if off screen
        if (y > 600) {
            active = false;
        }
    }

    @Override
    public void render(GraphicsContext gc) {
        // Pulsing animation
        double scale = 1.0 + 0.1 * Math.sin(animationTime * 5);

        gc.setFill(type.getColor());
        double size = width * scale;
        gc.fillOval(x - size / 2, y - size / 2, size, size);

        // Inner glow
        gc.setFill(Color.WHITE);
        gc.fillOval(x - size / 4, y - size / 4, size / 2, size / 2);

        // Draw type indicator
        gc.setFill(Color.BLACK);
        gc.setFont(new javafx.scene.text.Font("Arial", 12));
        String symbol = switch (type) {
            case SPEED_BOOST -> "S";
            case SHIELD -> "D";
            case FIRE_POWER -> "F";
        };
        gc.fillText(symbol, x - 4, y + 4);
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
        return "PowerUp-" + type.name();
    }

    @Override
    public boolean isActive() {
        return active;
    }

    @Override
    public void setActive(boolean active) {
        this.active = active;
    }

    public PowerUpType getType() {
        return type;
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

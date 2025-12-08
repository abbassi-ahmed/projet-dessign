package com.polytech.game.patterns.decorator;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * Decorator Pattern - SpeedBoostDecorator
 * Doubles the character's movement speed for a limited time
 */
public class SpeedBoostDecorator extends PowerUpDecorator {
    private static final double SPEED_MULTIPLIER = 2.0;

    public SpeedBoostDecorator(GameCharacter character, double duration) {
        super(character, duration);
    }

    @Override
    public double getSpeed() {
        return decoratedCharacter.getSpeed() * SPEED_MULTIPLIER;
    }

    @Override
    public int getFirePower() {
        return decoratedCharacter.getFirePower();
    }

    @Override
    public int getDefense() {
        return decoratedCharacter.getDefense();
    }

    @Override
    public String getDescription() {
        return decoratedCharacter.getDescription() + " + Speed Boost";
    }

    @Override
    protected void drawPowerUpEffect(GraphicsContext gc) {
        // Draw speed trail effect
        gc.setFill(Color.color(1, 1, 0, 0.3));
        if (decoratedCharacter instanceof BaseCharacter) {
            BaseCharacter base = (BaseCharacter) decoratedCharacter;
            gc.fillOval(base.getX() - 25, base.getY() - 15, 50, 30);
        }
    }
}

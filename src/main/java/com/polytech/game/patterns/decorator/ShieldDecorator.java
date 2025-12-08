package com.polytech.game.patterns.decorator;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * Decorator Pattern - ShieldDecorator
 * Adds protective shield to the character
 */
public class ShieldDecorator extends PowerUpDecorator {
    private static final int SHIELD_STRENGTH = 3;

    public ShieldDecorator(GameCharacter character, double duration) {
        super(character, duration);
    }

    @Override
    public double getSpeed() {
        return decoratedCharacter.getSpeed();
    }

    @Override
    public int getFirePower() {
        return decoratedCharacter.getFirePower();
    }

    @Override
    public int getDefense() {
        return decoratedCharacter.getDefense() + SHIELD_STRENGTH;
    }

    @Override
    public String getDescription() {
        return decoratedCharacter.getDescription() + " + Shield";
    }

    @Override
    protected void drawPowerUpEffect(GraphicsContext gc) {
        // Draw shield bubble effect
        gc.setStroke(Color.CYAN);
        gc.setLineWidth(2);
        if (decoratedCharacter instanceof BaseCharacter) {
            BaseCharacter base = (BaseCharacter) decoratedCharacter;
            gc.strokeOval(base.getX() - 30, base.getY() - 20, 60, 40);
            gc.setFill(Color.color(0, 1, 1, 0.1));
            gc.fillOval(base.getX() - 30, base.getY() - 20, 60, 40);
        }
    }
}

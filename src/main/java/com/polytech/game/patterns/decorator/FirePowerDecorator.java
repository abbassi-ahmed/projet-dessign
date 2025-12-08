package com.polytech.game.patterns.decorator;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * Decorator Pattern - FirePowerDecorator
 * Increases the character's fire power
 */
public class FirePowerDecorator extends PowerUpDecorator {
    private static final int FIRE_POWER_BONUS = 2;

    public FirePowerDecorator(GameCharacter character, double duration) {
        super(character, duration);
    }

    @Override
    public double getSpeed() {
        return decoratedCharacter.getSpeed();
    }

    @Override
    public int getFirePower() {
        return decoratedCharacter.getFirePower() + FIRE_POWER_BONUS;
    }

    @Override
    public int getDefense() {
        return decoratedCharacter.getDefense();
    }

    @Override
    public String getDescription() {
        return decoratedCharacter.getDescription() + " + Fire Power";
    }

    @Override
    protected void drawPowerUpEffect(GraphicsContext gc) {
        // Draw fire power effect (orange glow)
        gc.setFill(Color.color(1, 0.5, 0, 0.4));
        if (decoratedCharacter instanceof BaseCharacter) {
            BaseCharacter base = (BaseCharacter) decoratedCharacter;
            gc.fillRect(base.getX() - 22, base.getY() - 12, 44, 24);
        }
    }
}

package com.polytech.game.patterns.decorator;

import com.polytech.game.patterns.singleton.GameLogger;
import javafx.scene.canvas.GraphicsContext;

/**
 * Decorator Pattern - PowerUpDecorator
 * Abstract decorator class for adding power-ups to characters
 */
public abstract class PowerUpDecorator implements GameCharacter {
    protected GameCharacter decoratedCharacter;
    protected GameLogger logger;
    protected double duration;
    protected double timeActive;

    public PowerUpDecorator(GameCharacter character, double duration) {
        this.decoratedCharacter = character;
        this.logger = GameLogger.getInstance();
        this.duration = duration;
        this.timeActive = 0;

        // Log the application of the decorator
        logger.logDecorator(this.getClass().getSimpleName(),
                           character.getClass().getSimpleName());
    }

    @Override
    public void draw(GraphicsContext gc) {
        decoratedCharacter.draw(gc);
        drawPowerUpEffect(gc);
    }

    /**
     * Draw visual effect of the power-up
     */
    protected abstract void drawPowerUpEffect(GraphicsContext gc);

    /**
     * Update the power-up (for time-based effects)
     */
    public void update(double deltaTime) {
        timeActive += deltaTime;
    }

    /**
     * Check if power-up has expired
     */
    public boolean isExpired() {
        return timeActive >= duration;
    }

    public GameCharacter getDecoratedCharacter() {
        return decoratedCharacter;
    }

    public double getRemainingTime() {
        return Math.max(0, duration - timeActive);
    }
}

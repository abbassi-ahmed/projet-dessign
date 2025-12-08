package com.polytech.game.patterns.decorator;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * Decorator Pattern - BaseCharacter
 * Concrete component representing a basic character without power-ups
 */
public class BaseCharacter implements GameCharacter {
    protected double x;
    protected double y;
    protected double baseSpeed = 200.0;
    protected int baseFirePower = 1;
    protected int baseDefense = 1;

    public BaseCharacter(double x, double y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public void draw(GraphicsContext gc) {
        gc.setFill(Color.CYAN);
        gc.fillRect(x - 20, y - 10, 40, 20);
    }

    @Override
    public double getSpeed() {
        return baseSpeed;
    }

    @Override
    public int getFirePower() {
        return baseFirePower;
    }

    @Override
    public int getDefense() {
        return baseDefense;
    }

    @Override
    public String getDescription() {
        return "Base Character";
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }
}

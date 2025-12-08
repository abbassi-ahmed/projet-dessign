package com.polytech.game.patterns.state;

import com.polytech.game.patterns.singleton.GameContext;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;

/**
 * State Pattern - GameState Interface
 * Defines the interface for different game states
 */
public interface GameState {
    /**
     * Handle user input
     */
    void handleInput(KeyCode keyCode);

    /**
     * Update game logic
     */
    void update(double deltaTime);

    /**
     * Render the state
     */
    void render(GraphicsContext gc);

    /**
     * Enter this state
     */
    void enter();

    /**
     * Exit this state
     */
    void exit();

    /**
     * Get state name
     */
    String getStateName();
}

package com.polytech.game.patterns.singleton;

import com.polytech.game.patterns.state.GameState;
import com.polytech.game.patterns.state.MenuState;

/**
 * Singleton Pattern - GameContext
 * Manages the game state and provides global access to game components
 */
public class GameContext {
    private static GameContext instance;
    private GameState currentState;
    private final GameLogger logger;
    private int highScore;

    private GameContext() {
        this.logger = GameLogger.getInstance();
        this.highScore = 0;
        // Initialize with menu state
        this.currentState = new MenuState(this);
        this.currentState.enter();
    }

    /**
     * Get the single instance of GameContext (Singleton Pattern)
     */
    public static synchronized GameContext getInstance() {
        if (instance == null) {
            instance = new GameContext();
        }
        return instance;
    }

    /**
     * Change the current game state (State Pattern)
     */
    public void changeState(GameState newState) {
        if (currentState != null) {
            String oldStateName = currentState.getStateName();
            currentState.exit();
            logger.logStateChange("Game", oldStateName, newState.getStateName());
        }

        currentState = newState;
        currentState.enter();
    }

    public GameState getCurrentState() {
        return currentState;
    }

    public int getHighScore() {
        return highScore;
    }

    public void updateHighScore(int score) {
        if (score > highScore) {
            highScore = score;
            logger.info("New High Score: " + highScore);
        }
    }
}

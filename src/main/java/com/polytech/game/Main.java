package com.polytech.game;

import com.polytech.game.gui.GameWindow;
import com.polytech.game.patterns.singleton.GameLogger;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * Main Application Class
 * Entry point for the Space Invaders game
 *
 * This project demonstrates the implementation of multiple Design Patterns:
 * 1. Singleton Pattern - GameLogger, GameContext
 * 2. State Pattern - Game states (Menu, Playing, Paused, GameOver, Victory), Player states
 * 3. Decorator Pattern - Power-ups that enhance player abilities
 * 4. Composite Pattern - Hierarchical game structure (Levels containing enemies, power-ups)
 * 5. Factory Pattern - Creating enemies and power-ups
 *
 * @author Design Patterns Project 2025-2026
 * @version 1.0
 */
public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        GameLogger logger = GameLogger.getInstance();
        logger.info("=".repeat(60));
        logger.info("Space Invaders - Design Patterns Project");
        logger.info("Application starting...");
        logger.info("=".repeat(60));

        try {
            GameWindow gameWindow = new GameWindow();
            gameWindow.start(primaryStage);

            logger.info("Game window initialized successfully");
        } catch (Exception e) {
            logger.error("Failed to start game: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public void stop() {
        GameLogger logger = GameLogger.getInstance();
        logger.info("=".repeat(60));
        logger.info("Application shutting down...");
        logger.info("=".repeat(60));
        logger.close();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

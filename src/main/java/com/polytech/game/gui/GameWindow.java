package com.polytech.game.gui;

import com.polytech.game.patterns.singleton.GameContext;
import com.polytech.game.patterns.singleton.GameLogger;
import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.HashSet;
import java.util.Set;

/**
 * GameWindow
 * Manages the main game window, canvas, and game loop
 */
public class GameWindow {
    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;
    private static final String TITLE = "Space Invaders - Design Patterns Project";

    private Canvas canvas;
    private GraphicsContext gc;
    private final GameContext gameContext;
    private final GameLogger logger;
    private final Set<KeyCode> activeKeys;
    private long lastFrameTime;

    public GameWindow() {
        this.gameContext = GameContext.getInstance();
        this.logger = GameLogger.getInstance();
        this.activeKeys = new HashSet<>();
        this.lastFrameTime = System.nanoTime();
    }

    public void start(Stage primaryStage) {
        primaryStage.setTitle(TITLE);

        // Create canvas
        canvas = new Canvas(WIDTH, HEIGHT);
        gc = canvas.getGraphicsContext2D();

        // Create scene
        StackPane root = new StackPane();
        root.getChildren().add(canvas);
        Scene scene = new Scene(root, WIDTH, HEIGHT);

        // Set up input handling
        setupInputHandling(scene);

        // Set up game loop
        setupGameLoop();

        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();

        logger.info("Game window created: " + WIDTH + "x" + HEIGHT);
    }

    private void setupInputHandling(Scene scene) {
        // Key pressed
        scene.setOnKeyPressed(event -> {
            KeyCode code = event.getCode();
            activeKeys.add(code);

            // Handle state-specific input
            gameContext.getCurrentState().handleInput(code);
        });

        // Key released
        scene.setOnKeyReleased(event -> {
            activeKeys.remove(event.getCode());
        });
    }

    private void setupGameLoop() {
        AnimationTimer gameLoop = new AnimationTimer() {
            @Override
            public void handle(long currentTime) {
                // Calculate delta time in seconds
                double deltaTime = (currentTime - lastFrameTime) / 1_000_000_000.0;
                lastFrameTime = currentTime;

                // Cap delta time to prevent large jumps
                if (deltaTime > 0.1) {
                    deltaTime = 0.1;
                }

                // Update game state
                gameContext.getCurrentState().update(deltaTime);

                // Render game state
                clearScreen();
                gameContext.getCurrentState().render(gc);
            }
        };

        gameLoop.start();
        logger.info("Game loop started");
    }

    private void clearScreen() {
        gc.setFill(Color.BLACK);
        gc.fillRect(0, 0, WIDTH, HEIGHT);
    }

    public int getWidth() {
        return WIDTH;
    }

    public int getHeight() {
        return HEIGHT;
    }
}

package com.polytech.game.patterns.state;

import com.polytech.game.patterns.singleton.GameContext;
import com.polytech.game.patterns.singleton.GameLogger;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;

/**
 * State Pattern - MenuState
 * Represents the main menu state of the game
 */
public class MenuState implements GameState {
    private final GameContext context;
    private final GameLogger logger;

    public MenuState(GameContext context) {
        this.context = context;
        this.logger = GameLogger.getInstance();
    }

    @Override
    public void handleInput(KeyCode keyCode) {
        if (keyCode == KeyCode.ENTER || keyCode == KeyCode.SPACE) {
            context.changeState(new PlayingState(context));
        } else if (keyCode == KeyCode.ESCAPE) {
            logger.info("User requested to quit from menu");
            System.exit(0);
        }
    }

    @Override
    public void update(double deltaTime) {
        // No updates needed in menu
    }

    @Override
    public void render(GraphicsContext gc) {
        // Clear screen
        gc.setFill(Color.BLACK);
        gc.fillRect(0, 0, gc.getCanvas().getWidth(), gc.getCanvas().getHeight());

        // Draw title
        gc.setFill(Color.WHITE);
        gc.setFont(new Font("Arial", 48));
        gc.setTextAlign(TextAlignment.CENTER);
        gc.fillText("SPACE INVADERS", gc.getCanvas().getWidth() / 2, 150);

        // Draw instructions
        gc.setFont(new Font("Arial", 24));
        gc.fillText("Design Patterns Project", gc.getCanvas().getWidth() / 2, 220);

        gc.setFont(new Font("Arial", 20));
        gc.fillText("Press ENTER to Start", gc.getCanvas().getWidth() / 2, 350);
        gc.fillText("Press ESC to Quit", gc.getCanvas().getWidth() / 2, 390);

        // Draw controls
        gc.setFont(new Font("Arial", 16));
        gc.fillText("Controls:", gc.getCanvas().getWidth() / 2, 450);
        gc.fillText("Arrow Keys - Move", gc.getCanvas().getWidth() / 2, 475);
        gc.fillText("SPACE - Shoot", gc.getCanvas().getWidth() / 2, 500);
        gc.fillText("P - Pause", gc.getCanvas().getWidth() / 2, 525);

        // Draw power-up test keys
        gc.setFont(new Font("Arial", 14));
        gc.setFill(Color.YELLOW);
        gc.fillText("Power-ups (Test/Demo):", gc.getCanvas().getWidth() / 2, 560);
        gc.setFill(Color.WHITE);
        gc.fillText("1 - Speed Boost  |  2 - Shield  |  3 - Fire Power", gc.getCanvas().getWidth() / 2, 580);
    }

    @Override
    public void enter() {
        logger.info("Entered Menu State");
    }

    @Override
    public void exit() {
        logger.info("Exited Menu State");
    }

    @Override
    public String getStateName() {
        return "MENU";
    }
}

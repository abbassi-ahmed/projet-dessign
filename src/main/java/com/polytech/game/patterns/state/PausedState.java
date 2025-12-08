package com.polytech.game.patterns.state;

import com.polytech.game.patterns.singleton.GameContext;
import com.polytech.game.patterns.singleton.GameLogger;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;

/**
 * State Pattern - PausedState
 * Represents the paused game state
 */
public class PausedState implements GameState {
    private final GameContext context;
    private final GameLogger logger;
    private final PlayingState previousState;

    public PausedState(GameContext context, PlayingState previousState) {
        this.context = context;
        this.logger = GameLogger.getInstance();
        this.previousState = previousState;
    }

    @Override
    public void handleInput(KeyCode keyCode) {
        if (keyCode == KeyCode.P || keyCode == KeyCode.ENTER) {
            context.changeState(previousState);
        } else if (keyCode == KeyCode.ESCAPE) {
            context.changeState(new MenuState(context));
        }
    }

    @Override
    public void update(double deltaTime) {
        // No updates while paused
    }

    @Override
    public void render(GraphicsContext gc) {
        // Render the previous playing state (frozen)
        previousState.render(gc);

        // Draw pause overlay
        gc.setFill(Color.color(0, 0, 0, 0.7));
        gc.fillRect(0, 0, gc.getCanvas().getWidth(), gc.getCanvas().getHeight());

        gc.setFill(Color.WHITE);
        gc.setFont(new Font("Arial", 48));
        gc.setTextAlign(TextAlignment.CENTER);
        gc.fillText("PAUSED", gc.getCanvas().getWidth() / 2, 250);

        gc.setFont(new Font("Arial", 20));
        gc.fillText("Press P or ENTER to Resume", gc.getCanvas().getWidth() / 2, 350);
        gc.fillText("Press ESC to Return to Menu", gc.getCanvas().getWidth() / 2, 380);
    }

    @Override
    public void enter() {
        logger.info("Game paused");
    }

    @Override
    public void exit() {
        logger.info("Game resumed");
    }

    @Override
    public String getStateName() {
        return "PAUSED";
    }
}

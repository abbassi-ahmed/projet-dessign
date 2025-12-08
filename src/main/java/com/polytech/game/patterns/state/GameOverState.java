package com.polytech.game.patterns.state;

import com.polytech.game.patterns.singleton.GameContext;
import com.polytech.game.patterns.singleton.GameLogger;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;

/**
 * State Pattern - GameOverState
 * Represents the game over state
 */
public class GameOverState implements GameState {
    private final GameContext context;
    private final GameLogger logger;
    private final int finalScore;

    public GameOverState(GameContext context, int finalScore) {
        this.context = context;
        this.logger = GameLogger.getInstance();
        this.finalScore = finalScore;
    }

    @Override
    public void handleInput(KeyCode keyCode) {
        if (keyCode == KeyCode.ENTER) {
            context.changeState(new PlayingState(context));
        } else if (keyCode == KeyCode.ESCAPE) {
            context.changeState(new MenuState(context));
        }
    }

    @Override
    public void update(double deltaTime) {
        // No updates needed
    }

    @Override
    public void render(GraphicsContext gc) {
        // Clear screen with dark red tint
        gc.setFill(Color.color(0.2, 0, 0));
        gc.fillRect(0, 0, gc.getCanvas().getWidth(), gc.getCanvas().getHeight());

        // Draw Game Over text
        gc.setFill(Color.RED);
        gc.setFont(new Font("Arial", 64));
        gc.setTextAlign(TextAlignment.CENTER);
        gc.fillText("GAME OVER", gc.getCanvas().getWidth() / 2, 200);

        // Draw final score
        gc.setFill(Color.WHITE);
        gc.setFont(new Font("Arial", 32));
        gc.fillText("Final Score: " + finalScore, gc.getCanvas().getWidth() / 2, 280);

        // Draw options
        gc.setFont(new Font("Arial", 20));
        gc.fillText("Press ENTER to Try Again", gc.getCanvas().getWidth() / 2, 380);
        gc.fillText("Press ESC to Return to Menu", gc.getCanvas().getWidth() / 2, 410);
    }

    @Override
    public void enter() {
        logger.info("Game Over - Final Score: " + finalScore);
    }

    @Override
    public void exit() {
        logger.info("Exited Game Over State");
    }

    @Override
    public String getStateName() {
        return "GAME_OVER";
    }
}

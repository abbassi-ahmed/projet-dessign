package com.polytech.game.patterns.state;

import com.polytech.game.patterns.singleton.GameContext;
import com.polytech.game.patterns.singleton.GameLogger;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;

/**
 * State Pattern - VictoryState
 * Represents the victory state
 */
public class VictoryState implements GameState {
    private final GameContext context;
    private final GameLogger logger;
    private final int finalScore;

    public VictoryState(GameContext context, int finalScore) {
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
        // Clear screen with golden tint
        gc.setFill(Color.color(0.1, 0.1, 0));
        gc.fillRect(0, 0, gc.getCanvas().getWidth(), gc.getCanvas().getHeight());

        // Draw Victory text
        gc.setFill(Color.GOLD);
        gc.setFont(new Font("Arial", 64));
        gc.setTextAlign(TextAlignment.CENTER);
        gc.fillText("VICTORY!", gc.getCanvas().getWidth() / 2, 200);

        // Draw congratulations message
        gc.setFill(Color.WHITE);
        gc.setFont(new Font("Arial", 24));
        gc.fillText("All enemies defeated!", gc.getCanvas().getWidth() / 2, 250);

        // Draw final score
        gc.setFont(new Font("Arial", 32));
        gc.fillText("Final Score: " + finalScore, gc.getCanvas().getWidth() / 2, 320);

        // Draw options
        gc.setFont(new Font("Arial", 20));
        gc.fillText("Press ENTER to Play Again", gc.getCanvas().getWidth() / 2, 400);
        gc.fillText("Press ESC to Return to Menu", gc.getCanvas().getWidth() / 2, 430);
    }

    @Override
    public void enter() {
        logger.info("Victory! Final Score: " + finalScore);
    }

    @Override
    public void exit() {
        logger.info("Exited Victory State");
    }

    @Override
    public String getStateName() {
        return "VICTORY";
    }
}

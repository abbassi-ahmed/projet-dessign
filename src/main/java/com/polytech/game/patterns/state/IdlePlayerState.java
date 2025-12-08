package com.polytech.game.patterns.state;

import com.polytech.game.entities.Player;
import com.polytech.game.patterns.singleton.GameLogger;

/**
 * State Pattern - IdlePlayerState
 * Player is not moving or performing any action
 */
public class IdlePlayerState implements PlayerState {
    private final GameLogger logger = GameLogger.getInstance();

    @Override
    public void handleInput(Player player, String input) {
        switch (input) {
            case "MOVE_LEFT":
            case "MOVE_RIGHT":
                logger.logStateChange("Player", getStateName(), "MOVING");
                player.setPlayerState(new MovingPlayerState());
                break;
            case "SHOOT":
                logger.logStateChange("Player", getStateName(), "SHOOTING");
                player.setPlayerState(new ShootingPlayerState());
                break;
        }
    }

    @Override
    public void update(Player player, double deltaTime) {
        player.setVelocityX(0);
    }

    @Override
    public String getStateName() {
        return "IDLE";
    }
}

package com.polytech.game.patterns.state;

import com.polytech.game.entities.Player;
import com.polytech.game.patterns.singleton.GameLogger;

/**
 * State Pattern - MovingPlayerState
 * Player is moving left or right
 */
public class MovingPlayerState implements PlayerState {
    private final GameLogger logger = GameLogger.getInstance();
    private double stateTime = 0;

    @Override
    public void handleInput(Player player, String input) {
        switch (input) {
            case "STOP":
                logger.logStateChange("Player", getStateName(), "IDLE");
                player.setPlayerState(new IdlePlayerState());
                break;
            case "SHOOT":
                logger.logStateChange("Player", getStateName(), "SHOOTING");
                player.setPlayerState(new ShootingPlayerState());
                break;
        }
    }

    @Override
    public void update(Player player, double deltaTime) {
        stateTime += deltaTime;
        // Movement is handled by the Player class
    }

    @Override
    public String getStateName() {
        return "MOVING";
    }
}

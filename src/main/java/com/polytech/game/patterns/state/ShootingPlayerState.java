package com.polytech.game.patterns.state;

import com.polytech.game.entities.Player;
import com.polytech.game.patterns.singleton.GameLogger;

/**
 * State Pattern - ShootingPlayerState
 * Player is shooting
 */
public class ShootingPlayerState implements PlayerState {
    private final GameLogger logger = GameLogger.getInstance();
    private double cooldownTime = 0;
    private static final double SHOOT_COOLDOWN = 0.3;

    @Override
    public void handleInput(Player player, String input) {
        // Continue handling movement while shooting
        if (input.equals("MOVE_LEFT") || input.equals("MOVE_RIGHT")) {
            // Allow movement while shooting
        }
    }

    @Override
    public void update(Player player, double deltaTime) {
        cooldownTime += deltaTime;

        if (cooldownTime >= SHOOT_COOLDOWN) {
            logger.logStateChange("Player", getStateName(), "IDLE");
            player.setPlayerState(new IdlePlayerState());
        }
    }

    @Override
    public String getStateName() {
        return "SHOOTING";
    }
}

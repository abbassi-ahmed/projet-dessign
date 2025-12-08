package com.polytech.game.patterns.state;

import com.polytech.game.entities.Player;

/**
 * State Pattern - PlayerState Interface
 * Defines different states for the player character
 */
public interface PlayerState {
    /**
     * Handle player input based on current state
     */
    void handleInput(Player player, String input);

    /**
     * Update player based on current state
     */
    void update(Player player, double deltaTime);

    /**
     * Get the name of this state
     */
    String getStateName();
}

package com.example.navalbattle.model;

/**
 * This is a custom exception class for the Naval Battle game.
 * It is thrown when a position on the game board is already occupied.
 */

public class PositionOccupiedException extends Exception {

    /**
     * Constructor for the PositionOccupiedException class.
     * It calls the superclass constructor with the provided message.
     * @param message the detail message saved for later retrieval by the Throwable.getMessage() method.
     */

    public PositionOccupiedException(String message) {
        super(message);
    }
}

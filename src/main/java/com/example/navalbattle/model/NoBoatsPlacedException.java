package com.example.navalbattle.model;

/**
 * This is a custom exception class for the Naval Battle game.
 * It is thrown when no boats are placed on the user board.
 */

public class NoBoatsPlacedException extends Exception {

    /**
     * Constructor for the NoBoatsPlacedException class.
     * It calls the superclass constructor with the provided message.
     * @param message the detail message saved for later retrieval by the Throwable.getMessage() method.
     */

    public NoBoatsPlacedException(String message) {
        super(message);
    }
}

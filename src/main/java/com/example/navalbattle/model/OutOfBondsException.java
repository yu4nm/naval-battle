package com.example.navalbattle.model;

/**
 * This is a custom exception class for the Naval Battle game.
 * It is thrown when a position on the game board is out of bonds.
 */

public class OutOfBondsException extends Exception {

    /**
     * Constructor for the OutOfBondsException class.
     * It calls the superclass constructor with the provided message.
     * @param message the detail message saved for later retrieval by the Throwable.getMessage() method.
     */

    public OutOfBondsException(String message) {
        super(message);
    }
}

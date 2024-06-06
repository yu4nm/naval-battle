package com.example.navalbattle.model;

import com.example.navalbattle.view.alert.AlertBox;

import java.io.Serializable;

/**
 * The UserBoard class represents the game board for the user in the Naval Battle game.
 * It contains methods for initializing the board, placing boats, and checking if a boat can be placed at a certain position.
 * It implements the IBoard interface.
 */


import java.io.Serializable;

public class userBoard implements IBoard, Serializable {

    private int[][] userBoard = new int[10][10];

    /**
     * Constructor for the UserBoard class.
     * It initializes the board.
     */

    public userBoard(){
        initializeBoard(userBoard);

    }

    /**
     * Sets the boat's position on the user's board.
     * It checks if the boat can be placed at the specified position.
     * If the boat can be placed, it is placed on the board, otherwise it will throw an exception and display an error message.
     * @param row the row index where the boat's head is to be placed.
     * @param col the column index where the boat's head is to be placed.
     * @param boat the boat to be placed.
     * @return true if the boat was placed successfully, false otherwise.
     */

    public boolean setBoatPosition(int row, int col, Boat boat) {
        try {
            if (canPlaceBoat(row, col, boat)) {
                placeBoat(boat, row, col, userBoard);
                return true;
            }
        } catch (OutOfBondsException | PositionOccupiedException e) {
            AlertBox alertBox = new AlertBox();
            alertBox.showMessage("Error", e.getMessage());
        }
        return false;
    }

    /**
     * Checks if a boat overlaps with another boat on the board.
     * @param boat the boat to be checked.
     * @param column the column index where the boat's head is to be placed.
     * @param row the row index where the boat's head is to be placed.
     * @return true if the boat overlaps with another boat, false otherwise.
     */

    public boolean overlappedBoat(Boat boat, int column, int row) {
        userBoard = getUserBoard();
        if (boat.getIsHorizontal()) {
            for (int i = column; i < column + boat.getBoatLength(); i++) {
                if (i < userBoard[0].length && row < userBoard.length && userBoard[row][i] != 0) {
                    return true;
                }
            }
        }
        else {
            for (int i = row; i < row + boat.getBoatLength(); i++) {
                if (i < userBoard.length && column < userBoard[0].length && userBoard[i][column] != 0) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Returns the matrix associated to the user board.
     * @return the matrix of the user board.
     */

    public int[][] getUserBoard() {
        return userBoard;
    }

    /**
     * Defines if a boat can be placed at a certain position on the board.
     * It checks if the boat goes out of bounds or if the position is already occupied.
     * @param row the row index where the boat's head is to be placed.
     * @param col the column index where the boat's head is to be placed.
     * @param boat the boat to be placed.
     * @return true if the boat can be placed, false otherwise.
     * @throws OutOfBondsException if the boat goes out of bounds.
     * @throws PositionOccupiedException if the position is already occupied.
     */

    boolean canPlaceBoat(int row, int col, Boat boat) throws OutOfBondsException, PositionOccupiedException{
        int boatLength = boat.getBoatLength();
        if (boat.getIsHorizontal()) {
            if (col + boatLength > userBoard[0].length || row >= userBoard.length) {
                throw new OutOfBondsException("Boat goes out of bounds horizontally");
            }
            for (int i = col; i < col + boatLength; i++) {
                if (i < userBoard[0].length && userBoard[row][i] != 0) {
                    throw new PositionOccupiedException("Space is already occupied");
                }
            }
        }
        else {
            if (row + boatLength > userBoard.length || col >= userBoard[0].length) {
                throw new OutOfBondsException("Boat goes out of bounds vertically");
            }
            for (int j = row; j < row + boatLength; j++) {
                if (j < userBoard.length && userBoard[j][col] != 0) {
                    throw  new PositionOccupiedException("Space is already occupied");
                }
            }
        }
        return true;
    }
}

package com.example.navalbattle.model;

import java.util.ArrayList;
import java.util.List;

/**
 * The ComputerBoard class represents the game board for the computer in the Naval Battle game.
 * Contains custom methods for setting the board and checking if a boat can be placed at a certain position.
 * It implements the IBoard interface.
 */

public class ComputerBoard implements IBoard {
    int frigatesCant = 4;
    int destructorsNum = 3;
    int submarinesNum = 2;
    int aircraftCarrierNum = 1;

    List<Integer> boatsInventory = new ArrayList<>();

    private int[][] computerBoard = new int[10][10];


    /**
     * Constructor for the ComputerBoard class.
     * It initializes the board and the boats inventory.
     */

    public ComputerBoard() {

        boatsInventory.add(frigatesCant);
        boatsInventory.add(destructorsNum);
        boatsInventory.add(submarinesNum);
        boatsInventory.add(aircraftCarrierNum);

        initializeBoard(computerBoard);
    }

    /**
     * Sets the computer board by randomly placing the boats on it.
     * It iterates through the boats inventory and places each boat on the board.
     */

    public void setComputerBoard() {
        for (int i = 0; i < boatsInventory.size(); i++) {
            for (int j = 0; j < boatsInventory.get(i); j++) {
                boolean boatPlaced = false;
                while (!boatPlaced) {
                    int column = (int) (Math.random() * computerBoard.length);
                    int row = (int) (Math.random() * computerBoard.length);
                    int isHorizontal = (int) (Math.random() * 2);
                    Boat boat = new Boat(i+1);
                    boat.setIsHorizontal(isHorizontal);
                    if (canPlaceBoat(row, column, boat)) {
                        placeBoat(boat, row, column, computerBoard);
                        boatPlaced = true;
                    }
                }
            }
        }
    }

    /**
     * Defines if a boat can be placed at a certain position on the board.
     * It checks if the boat goes out of bounds or if the position is already occupied.
     * @param row the row index where the boat's head is to be placed.
     * @param col the column index where the boat's head is to be placed.
     * @param boat the boat to be placed.
     * @return true if the boat can be placed, false otherwise.
     */

    boolean canPlaceBoat(int row, int col, Boat boat){
        int boatLength = boat.getBoatLength();
        if (boat.getIsHorizontal()) {
            if (col + boatLength > computerBoard[0].length) {
                return false;
            }
            for (int i = col; i < col + boatLength; i++) {
                if (computerBoard[row][i] != 0) {
                    return false;
                }
            }
        } else {
            if (row + boatLength > computerBoard.length) {
                return false;
            }
            for (int j = row; j < row + boatLength; j++) {
                if (computerBoard[j][col] != 0) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Returns the matrix associated to the computer board.
     * @return the matrix of the computer board.
     */

    public int[][] getComputerBoard() {
        return computerBoard;
    }


}


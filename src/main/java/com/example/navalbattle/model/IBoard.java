package com.example.navalbattle.model;

/**
 * The IBoard interface defines the operations for a game board in the Naval Battle game.
 */

public interface IBoard {

    /**
     * Initializes the game board with zeros.
     * @param board the game board to be initialized.
     */

    default void initializeBoard(int[][] board){
        for(int i = 0; i < board.length; i++){
            for(int j = 0; j < board.length; j++){
                board[i][j] = 0;
            }
        }
    }

    /**
     * Prints the game board to the console.
     * @param board the game board to be printed.
     */

    default void printBoard(int[][] board){
        for(int i = 0; i < board.length; i++){
            for(int j = 0; j < board.length; j++){
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }

    /**
     * Places a boat on the game board.
     * @param boat the boat to be placed.
     * @param row the row where the boat will be placed.
     * @param col the column where the boat will be placed.
     * @param board the game board where the boat will be placed.
     */

    default void placeBoat(Boat boat, int row, int col, int[][] board){
        int typeBoat = boat.getTypeBoat();
        int boatLength = boat.getBoatLength();
        if (boat.getIsHorizontal()) {
            for (int i = col; i < col + boatLength; i++) {
                board[row][i] = typeBoat;
            }
        } else {
            for (int j = row; j < row + boatLength; j++) {
                board[j][col] = typeBoat;
            }
        }
    }


}

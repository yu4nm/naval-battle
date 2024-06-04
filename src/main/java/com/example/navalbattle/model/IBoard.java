package com.example.navalbattle.model;

public interface IBoard {

    default void initializeBoard(int[][] board){
        for(int i = 0; i < board.length; i++){
            for(int j = 0; j < board.length; j++){
                board[i][j] = 0;
            }
        }
    }


    default boolean canPlaceBoat(int row, int col, Boat boat, int[][] board) {
        //Hacer uso de excepciones en este codigo
        int boatLength = boat.getBoatLength();
        if (boat.getIsHorizontal()) {
            if (col + boatLength > board[0].length) {
                return false; // Boat goes out of bounds horizontally
            }
            for (int i = col; i < col + boatLength; i++) {
                if (board[row][i] != 0) {
                    return false; // Space is already occupied
                }
            }
        } else {
            if (row + boatLength > board.length) {
                return false; // Boat goes out of bounds vertically
            }
            for (int j = row; j < row + boatLength; j++) {
                if (board[j][col] != 0) {
                    return false; // Space is already occupied
                }
            }
        }
        return true;
    }

    default void printUserTable(int[][] userBoard){
        for(int i = 0; i < userBoard.length; i++){
            for(int j = 0; j < userBoard.length; j++){
                System.out.print(userBoard[i][j] + " ");
            }
            System.out.println();
        }
    }

    default void placeBoat(Boat boat, int row, int col, int[][] userBoard){
        int typeBoat = boat.getTypeBoat();
        int boatLength = boat.getBoatLength();
        if (boat.getIsHorizontal()) {
            for (int i = col; i < col + boatLength; i++) {
                userBoard[row][i] = typeBoat;
            }
        } else {
            for (int j = row; j < row + boatLength; j++) {
                userBoard[j][col] = typeBoat;
            }
        }
    }


}

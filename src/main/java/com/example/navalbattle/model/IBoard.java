package com.example.navalbattle.model;

public interface IBoard {

    default void initializeBoard(int[][] board){
        for(int i = 0; i < board.length; i++){
            for(int j = 0; j < board.length; j++){
                board[i][j] = 0;
            }
        }
    }

    default void printUserTable(int[][] userBoard){
        for(int i = 0; i < userBoard.length; i++){
            for(int j = 0; j < userBoard.length; j++){
                System.out.print(userBoard[i][j] + " ");
            }
            System.out.println();
        }
    }

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

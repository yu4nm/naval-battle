package com.example.navalbattle.model;

public class userBoard implements IBoard{
    private int[][] userBoard = new int[10][10];

    public userBoard(){
        initializeBoard(userBoard);

    }

    public void setBoatPosition(int row, int col, Boat boat) {
        if (canPlaceBoat(row, col, boat, userBoard)) {
            placeBoat(boat, row, col, userBoard);
        }
        else{
            System.out.println("The boat cannot be placed at that ubication"); //Aqui deberia ir algo de las excepciones
        }
    }

    public int[][] getUserBoard() {
        return userBoard;
    }
}

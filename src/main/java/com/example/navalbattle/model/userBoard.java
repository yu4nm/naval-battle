package com.example.navalbattle.model;

import com.example.navalbattle.view.alert.AlertBox;

public class userBoard implements IBoard{
    private int[][] userBoard = new int[10][10];

    public userBoard(){
        initializeBoard(userBoard);

    }

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

    public int[][] getUserBoard() {
        return userBoard;
    }

    boolean canPlaceBoat(int row, int col, Boat boat) throws OutOfBondsException, PositionOccupiedException{
        int boatLength = boat.getBoatLength();
        if (boat.getIsHorizontal()) {
            if (col + boatLength > userBoard[0].length) {
                throw new OutOfBondsException("Boat goes out of bounds horizontally");
            }
            for (int i = col; i < col + boatLength; i++) {
                if (userBoard[row][i] != 0) {
                    throw new PositionOccupiedException("Space is already occupied");
                }
            }
        } else {
            if (row + boatLength > userBoard.length) {
                throw new OutOfBondsException("Boat goes out of bounds vertically");
            }
            for (int j = row; j < row + boatLength; j++) {
                if (userBoard[j][col] != 0) {
                    throw  new PositionOccupiedException("Space is already occupied");
                }
            }
        }
        return true;
    }
}

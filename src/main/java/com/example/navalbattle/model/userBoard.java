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
                if (i < userBoard[0].length && userBoard[row][i] != 0) {
                    throw new PositionOccupiedException("Space is already occupied");
                }
            }
        } else {
            if (row + boatLength > userBoard.length) {
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

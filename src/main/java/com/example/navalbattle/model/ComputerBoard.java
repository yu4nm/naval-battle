package com.example.navalbattle.model;

import java.util.ArrayList;
import java.util.List;

public class ComputerBoard implements IBoard{
    int frigatesCant = 3;
    int submarinesNum = 1;
    int aircraftCarrierNum = 1;
    int destructorsNum = 2;

    List<Integer> boatsInventory = new ArrayList<>();

    private int[][] computerBoard = new int[10][10];

    public ComputerBoard() {

        boatsInventory.add(frigatesCant);
        boatsInventory.add(destructorsNum);
        boatsInventory.add(submarinesNum);
        boatsInventory.add(aircraftCarrierNum);

        initializeBoard(computerBoard);
    }

    public void setComputerBoard(){
    }

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


    public int[][] getComputerBoard() {
        return computerBoard;
    }
}


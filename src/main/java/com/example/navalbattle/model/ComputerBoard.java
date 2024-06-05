package com.example.navalbattle.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ComputerBoard implements IBoard, Serializable {
    int frigatesCant = 4;
    int destructorsNum = 3;
    int submarinesNum = 2;
    int aircraftCarrierNum = 1;

    List<Integer> boatsInventory = new ArrayList<>();

    private int[][] computerBoard = new int[10][10];

    public ComputerBoard() {

        boatsInventory.add(frigatesCant);
        boatsInventory.add(destructorsNum);
        boatsInventory.add(submarinesNum);
        boatsInventory.add(aircraftCarrierNum);

        initializeBoard(computerBoard);
    }

    public static void getChildren() {
    };

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


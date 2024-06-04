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



    public int[][] getComputerBoard() {
        return computerBoard;
    }


}


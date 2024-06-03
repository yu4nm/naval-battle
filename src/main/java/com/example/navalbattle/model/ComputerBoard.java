package com.example.navalbattle.model;

public class ComputerBoard {
    private int[][] computerBoard;

    public ComputerBoard() {
        this.computerBoard = new int[][]{
                {0,1,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,3,3,3,0,0},
                {0,0,0,0,0,0,0,0,0,0},
                {0,2,0,0,0,0,0,0,0,0},
                {0,2,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0}
        };
    }

//    public void setComputerBoard(){
//        int frigatesNum, submarinesNum, aircraftCarrierNum, destructorsNum;
//        frigatesNum = 3;
//        submarinesNum = 1;
//        aircraftCarrierNum = 1;
//        destructorsNum = 2;
//        int col = (int)(Math.random() * computerBoard.length);
//        int row = (int)(Math.random() * computerBoard.length);
//        for (int i = 0; i < 10; i++) {
//            for (int j = 0; j < 10; j++) {
//                if (computerBoard[row][col] == 0){
//                    int typeBoat = (int)(Math.random() * 4);
//                    Boat boat = new Boat(typeBoat);
//
//                }
//            }
//        }
//    }

}

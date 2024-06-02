package com.example.navalbattle.model;

public class userBoard{
    private int [][] userBoard;

    public userBoard(){
        this.userBoard = new int[][]{
                {0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0}
        };

    }

    public void setBoatUbication(int row, int col, Boat boat) {
        int typeBoat = boat.getTypeBoat();
        switch(typeBoat){
            case 1:
                userBoard[row][col] = typeBoat;
                break;
            case 2:
                if (boat.getIsHorizontal()){
                    for(int i = col ; i < col+3  ; i++){
                        userBoard[row][i] = typeBoat;
                    }
                }
                else{
                    for(int j=row; j < row+3  ; j++){
                        userBoard[j][col] = typeBoat;
                    }
                }
                break;
            case 3:
                if (boat.getIsHorizontal()){
                    for(int i = col ; i < col+4  ; i++){
                        userBoard[row][i] = typeBoat;
                    }
                }
                else{
                    for(int j=row; j < row+4  ; j++){
                        userBoard[j][col] = typeBoat;
                    }
                }
                break;
            case 4:
                if (boat.getIsHorizontal()){
                    for(int i = col ; i < col+5  ; i++){
                        userBoard[row][i] = typeBoat;
                    }
                }
                else{
                    for(int j=row; j < row+5  ; j++){
                        userBoard[j][col] = typeBoat;
                    }
                }
                break;
        }

    }


}

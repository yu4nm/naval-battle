package com.example.navalbattle.model;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Boat {

    Rectangle boat;
    boolean isHorizontal;
    public Boat(int typeBoat) {
        switch (typeBoat) {
            case 1: //Fragata
                this.boat = new Rectangle(23.5, 22.3);
                this.boat.setFill(Color.PALEGREEN);
                break;
            case 2: //Destructor
                this.boat = new Rectangle(47, 22.3);
                this.boat.setFill(Color.SILVER);
                break;
            case 3: //Submarino
                this.boat = new Rectangle(70.5, 22.3);
                this.boat.setFill(Color.LIGHTBLUE);
                break;
            case 4: //Portaaviones
                this.boat = new Rectangle(94, 22.3);
                this.boat.setFill(Color.GOLD);
                break;

        }
    }

    public Rectangle getBoat(){
        return this.boat;
    }

    public void rotateBoat(){
        if (isHorizontal){
            this.boat.setRotate(90);
        }
        else{
            this.boat.setRotate(0);
        }
        isHorizontal = !isHorizontal;
}}

package com.example.navalbattle.model;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Boat {

    int boatLength;
    int typeBoat;
    Rectangle boat;
    boolean isHorizontal = true;
    public Boat(int typeBoat) {
        this.boat = new Rectangle(23.5*typeBoat,22.3);
        this.typeBoat = typeBoat;
        this.boatLength = typeBoat;
        switch (typeBoat) {
            case 1: //Frigate
                this.boat.setFill(Color.PALEGREEN);
                break;
            case 2: //Destructor
                this.boat.setFill(Color.SILVER);
                break;
            case 3: //Submarine
                this.boat.setFill(Color.BLUEVIOLET);
                break;
            case 4: //Aircraft Carrier
                this.boat.setFill(Color.GOLD);
                break;

        }
    }

    public int getBoatLength() {
        return boatLength;
    }

    public Rectangle getBoat(){
        return this.boat;
    }

    public void rotateBoat() {
        if (isHorizontal) {
            this.boat.setRotate(90);
            this.boat.setTranslateX((boat.getHeight() - boat.getWidth()) / 2);
            this.boat.setTranslateY((boat.getWidth() - boat.getHeight()) / 2);
        } else {
            this.boat.setRotate(0);
            this.boat.setTranslateX(0);
            this.boat.setTranslateY(0);
        }
        isHorizontal = !isHorizontal;
    }

    public int getTypeBoat(){
        return typeBoat;
    }

    public boolean getIsHorizontal(){
        return isHorizontal;
    }

}

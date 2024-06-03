package com.example.navalbattle.model;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Boat {

    int typeBoat;
    Rectangle boat;
    boolean isHorizontal;
    public Boat(int typeBoat) {
        switch (typeBoat) {
            case 1: //Frigate
                this.boat = new Rectangle(23.5, 22.3);
                this.boat.setFill(Color.PALEGREEN);
                this.typeBoat = typeBoat;
                break;
            case 2: //Destructor
                this.boat = new Rectangle(47, 22.3);
                this.boat.setFill(Color.SILVER);
                this.typeBoat = typeBoat;
                break;
            case 3: //Submarine
                this.boat = new Rectangle(70.5, 22.3);
                this.boat.setFill(Color.BLUEVIOLET);
                this.typeBoat = typeBoat;
                break;
            case 4: //Aircraft Carrier
                this.boat = new Rectangle(94, 22.3);
                this.boat.setFill(Color.GOLD);
                this.typeBoat = typeBoat;
                break;

        }
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

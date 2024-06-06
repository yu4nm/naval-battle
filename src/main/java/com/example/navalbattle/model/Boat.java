package com.example.navalbattle.model;

import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;

/**
 * The Boat class represents a boat in the Naval Battle game.
 * It contains the boat's length, type, and orientation.
 * It also contains methods for creating different types of boats and rotating the boat.
 */

public class Boat {
    Polygon boatDrawing;
    int boatWidth;
    int boatHeight;
    int boatLength;
    int typeBoat;
    boolean isHorizontal = true;

    /**
     * Constructor for the Boat class.
     * It initializes the boat's type and length, and creates the boat based on its type.
     * @param typeBoat the type of the boat. It also determines the length of the boat.
     */

    public Boat(int typeBoat) {
        this.boatLength = typeBoat;
        this.typeBoat = typeBoat;
        this.boatHeight = (int) (23.5 * typeBoat);
        this.boatWidth = (int) 22.3;
        switch (typeBoat) {
            case 1:
                createFrigate();
                break;
            case 2:
                createDestructor();
                break;
            case 3:
                createSubmarine();
                break;
            case 4:
                createAircraftCarrier();
                break;
        }
    }

    /**
     * Creates the Java2D figure for the frigate boat.
     */

    private void createFrigate() {

        boatDrawing = new Polygon(
                50.0, 45.0,
                40.0, 45.0,
                40.0, 35.0,
                50.0, 35.0,
                60, 40);

        boatDrawing.setFill(Color.PALEGREEN);
        boatDrawing.setStroke(Color.BLACK);

    }

    /**
     * Creates the Java2D figure for the destructor boat.
     */

    private void createDestructor() {

        boatDrawing = new Polygon(
                70.0, 46.0,
                40.0, 45.0,
                40.0, 35.0,
                70.0, 33.0,
                80, 40);

        boatDrawing.setFill(Color.SILVER);
        boatDrawing.setStroke(Color.BLACK);
    }

    /**
     * Creates the Java2D figure for the submarine boat.
     */

    private void createSubmarine() {

        boatDrawing = new Polygon(

                50.0, 50.0,
                40, 43, //cola
                50.0, 35.0,
                100, 35,
                108, 40,
                108, 45,
                100, 50

        );

        boatDrawing.setFill(Color.BLUEVIOLET);
        boatDrawing.setStroke(Color.BLACK);
    }

    /**
     * Creates the Java2D figure for the aircraft carrier boat.
     */

    private void createAircraftCarrier() {
        boatDrawing = new Polygon(
                130.0, 45.0,
                130.0, 35.0, // extremo derecha
                40.0, 35.0,  // no mover
                40.0, 42.0,  // no mover
                60.0, 43.0,  // no mover
                60.0, 45.0,  // punto ajustado
                110.0, 50.0
        ); //60 40

        boatDrawing.setFill(Color.GOLD);
        boatDrawing.setStroke(Color.BLACK);
    }

    /**
     * Returns the drawing of the boat.
     * @return the drawing of the boat.
     */

    public Polygon getBoatDrawing() {
        return boatDrawing;
    }

    /**
     * Rotates the boat. If the boat is horizontal, it becomes vertical, and vice versa.
     * It also adjusts the position of the boat after rotation.
     */

    public void rotateBoat() {
        isHorizontal = !isHorizontal;
        if (isHorizontal) {
            boatDrawing.setRotate(0);
            boatDrawing.setTranslateX(0);
            boatDrawing.setTranslateY(0);
        } else {
            boatDrawing.setRotate(90);
            boatDrawing.setTranslateX((double) (boatWidth - boatHeight) / 2);
            boatDrawing.setTranslateY((double) (boatHeight - boatWidth) / 2);
        }
    }

    /**
     * Sets the orientation of the boat.
     * @param isHorizontal 1 if the boat is to be horizontal, 0 if it is to be vertical.
     */

    public void setIsHorizontal(int isHorizontal){
        if (isHorizontal == 1){
            this.isHorizontal = true;
        } else if (isHorizontal == 0) {
            this.isHorizontal = false;
        }
    }

    /**
     * Returns the length of the boat.
     * @return the length of the boat.
     */

    public int getBoatLength() {
        return boatLength;
    }

    /**
     * Returns the type of the boat.
     * @return the type of the boat.
     */

    public int getTypeBoat() {
        return typeBoat;
    }

    /**
     * Returns the color of the boat.
     * @return the color of the boat.
     */

    public Color getColorByTypeBoat() {
        return switch (typeBoat) {
            case 1 -> Color.PALEGREEN;
            case 2 -> Color.SILVER;
            case 3 -> Color.BLUEVIOLET;
            case 4 -> Color.GOLD;
            default -> Color.BLACK;
        };
    }

    /**
     * Returns the orientation of the boat.
     * @return the orientation of the boat.
     */

    public boolean getIsHorizontal() {
        return isHorizontal;
    }
}

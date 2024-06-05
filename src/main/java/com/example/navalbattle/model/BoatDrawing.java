package com.example.navalbattle.model;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polygon;

public class BoatDrawing {
    private Polygon boat;
    private int typeBoat;
    private boolean isHorizontal = true;


    public BoatDrawing(int typeBoat) {
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

        private void createFrigate() {

            Polygon frig = new Polygon(
                    50.0, 45.0,
                    40.0, 45.0,
                    40.0, 35.0,
                    50.0,35.0,
                    60,40);

            frig.setFill(Color.PALEGREEN);
            frig.setStroke(Color.BLACK);
            boat = frig;

        }

        private void createDestructor() {

            Polygon destr = new Polygon(
                    70.0, 46.0,
                    40.0, 45.0,
                    40.0, 35.0,
                    70.0,33.0,
                    80,40);

            destr.setFill(Color.SILVER);
            destr.setStroke(Color.BLACK);
            boat = destr;
        }

        private void createSubmarine() {

            Polygon submari = new Polygon(

                    50.0, 50.0,
                    40,43, //cola
                    50.0, 35.0,
                    100, 35,
                    108, 40,
                    108, 45,
                    100,50

                    );

            submari.setFill(Color.BLUEVIOLET);
            submari.setStroke(Color.BLACK);
            boat = submari;
     }

        private void createAircraftCarrier() {
            Polygon aircarrier = new Polygon(
                    130.0, 45.0,
                    130.0, 35.0, // extremo derecha
                    40.0, 35.0,  // no mover
                    40.0, 42.0,  // no mover
                    60.0, 43.0,  // no mover
                    60.0, 45.0,  // punto ajustado
                    110.0, 50.0
            ); //60 40

            aircarrier.setFill(Color.GOLD);
            aircarrier.setStroke(Color.BLACK);

            boat = aircarrier;
        }

    public Polygon getBoat() {
        return boat;
    }


    public void rotateBoat(double width, double height) {
        isHorizontal = !isHorizontal;
        if (isHorizontal) {
            boat.setRotate(0);
            boat.setTranslateX(0);
            boat.setTranslateY(0);
        } else {
            boat.setRotate(90);
            boat.setTranslateX((height - width) / 2);
            boat.setTranslateY((width - height) / 2);
        }
    }
}

package com.example.navalbattle.model;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polygon;

public class BoatDrawing {
    private Group boatGroup;
    private int typeBoat;
    private boolean isHorizontal = true;


    public BoatDrawing(int typeBoat) {
            boatGroup = new Group();
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

            boatGroup.getChildren().addAll(frig);
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

            boatGroup.getChildren().addAll(destr);
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

            boatGroup.getChildren().addAll(submari);
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

            boatGroup.getChildren().addAll(aircarrier);
        }

        public Group getBoatGroup() {
            return boatGroup;
        }
    public void rotateBoat(double width, double height) {
        isHorizontal = !isHorizontal;
        if (isHorizontal) {
            boatGroup.setRotate(0);
            boatGroup.setTranslateX(0);
            boatGroup.setTranslateY(0);
        } else {
            boatGroup.setRotate(90);
            boatGroup.setTranslateX((height - width) / 2);
            boatGroup.setTranslateY((width - height) / 2);
        }
    }
}

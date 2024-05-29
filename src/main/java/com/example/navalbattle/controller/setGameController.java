package com.example.navalbattle.controller;

import com.example.navalbattle.model.Boat;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.GridPane;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class setGameController {

    Rectangle previewBoat;

    @FXML
    private GridPane computerBoard;

    @FXML
    private TextField destructorescant;

    @FXML
    private TextField fragatascant;

    @FXML
    private TextField portaavionescant;

    @FXML
    private TextField submarinoscant;

    @FXML
    private GridPane userBoard;

    @FXML
    void onButtonPressedStartGame(ActionEvent event) {
        computerBoard.setVisible(true);

    }


    @FXML
    void PreviewFragata(ActionEvent event) {
        funcioncita(1);
    }

    @FXML
    void previewDestructor(ActionEvent event) {
        funcioncita(2);
    }

    @FXML
    void previewPortaaviones(ActionEvent event) {
        funcioncita(4);
    }

    @FXML
    void previewSubmarino(ActionEvent event) {
        funcioncita(3);
    }

    void funcioncita(int typeBoat){

        Boat boat = new Boat(typeBoat);
        Rectangle boatPreview = boat.getBoat();
        boatPreview.setOpacity(0.5);
        userBoard.add(boatPreview,4,4);

        userBoard.setOnMouseMoved(event -> {
            double mouseX = event.getX();
            double mouseY = event.getY();

            int column = (int) (mouseX / (userBoard.getWidth() / userBoard.getColumnCount()));
            int row = (int) (mouseY / (userBoard.getHeight() / userBoard.getRowCount()));

            GridPane.setColumnIndex(boatPreview, column);
            GridPane.setRowIndex(boatPreview, row);
        });

        userBoard.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.R) {
                boat.rotateBoat();
            }
            if (event.getCode() == KeyCode.ENTER) {
                //Codigo para que ponga el bote en ese lugar
            }
        });

        userBoard.requestFocus();
    }

}


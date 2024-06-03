package com.example.navalbattle.controller;

import com.example.navalbattle.model.Boat;
import com.example.navalbattle.model.ComputerBoard;
import com.example.navalbattle.model.userBoard;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.GridPane;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import static java.lang.Integer.parseInt;

public class GameController {

    userBoard userBoardM = new userBoard();
    ComputerBoard computerBoardM = new ComputerBoard();

    @FXML
    private GridPane computerBoard;

    @FXML
    private TextField destructorsNum;

    @FXML
    private TextField frigatesNum;

    @FXML
    private TextField aircraftCarrierNum;

    @FXML
    private TextField submarinesNum;

    @FXML
    private GridPane userBoard;

    @FXML
    void onButtonPressedStartGame(ActionEvent event) {
        computerBoard.setVisible(true);
//        computerBoardM.printUserTable(computerBoardM.getComputerBoard());
    }

    // General note: fix problem on double click at the buttons

    @FXML
    void PreviewFrigate(ActionEvent event) {
        previewBoat(1, frigatesNum);
    }


    @FXML
    void previewDestructor(ActionEvent event) {
        previewBoat(2, destructorsNum);
    }

    @FXML
    void previewAircraftCarrier(ActionEvent event) {
        previewBoat(4, aircraftCarrierNum);
    }

    @FXML
    void previewSubmarine(ActionEvent event) {
        previewBoat(3, submarinesNum);
    }

    void setUserBoats(int typeBoat){

        Boat boat = new Boat(typeBoat);
        Rectangle boatPreview = boat.getBoat();
        boatPreview.setOpacity(0.5);
        userBoard.add(boatPreview,4,4);

        userBoard.setOnMouseMoved(event -> {
            double mouseX = event.getX();
            double mouseY = event.getY  ();

            int column = (int) (mouseX / (userBoard.getWidth() / userBoard.getColumnCount()));
            int row = (int) (mouseY / (userBoard.getHeight() / userBoard.getRowCount()));


            GridPane.setColumnIndex(boatPreview, column);
            GridPane.setRowIndex(boatPreview, row);

            if (userBoardM.getUserBoard()[row][column] != 0){
                boatPreview.setFill(Color.RED);
                //Codigo para que no lo deje ubicar
            }

            onKeyPressed(userBoard, column, row, boat);
        });

        userBoard.requestFocus();
    }

    private void onKeyPressed(GridPane userBoard, int column, int row, Boat boat){
        userBoard.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.R) {
                boat.rotateBoat();
            }
            if (event.getCode() == KeyCode.ENTER) {
                userBoardM.setBoatPosition(row, column, boat);
                userBoardM.printUserTable(userBoardM.getUserBoard());
                boat.getBoat().setOpacity(1.0);
                userBoard.setOnMouseMoved(null);
                userBoard.setOnKeyPressed(null);
                decreaseBoatCount(boat.getTypeBoat(), userBoard);
            }
        });

    }

    public void previewBoat(int typeBoat, TextField boatCountField){
        if(parseInt(boatCountField.getText()) > 0) {
            setUserBoats(typeBoat);
        }
        else{
            System.out.println("No tienes mas barcos que ubicar");
        }
    }

    public void decreaseBoatCount(int typeBoat, GridPane gridPane){
        TextField textField = switch (typeBoat) {
            case 1 -> frigatesNum;
            case 2 -> destructorsNum;
            case 3 -> submarinesNum;
            case 4 -> aircraftCarrierNum;
            default -> null;
        };
        int currentCount = parseInt(textField.getText());
        if (currentCount > 0) {
            currentCount--;
            textField.setText(Integer.toString(currentCount));
        }
    }
}


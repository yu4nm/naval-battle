package com.example.navalbattle.controller;

import com.example.navalbattle.model.Boat;
import com.example.navalbattle.model.ComputerBoard;
import com.example.navalbattle.model.userBoard;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.GridPane;
import javafx.scene.control.TextField;
import javafx.scene.shape.Rectangle;

import static java.lang.Integer.parseInt;

public class setGameController {

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
        // Codigo para que ponga los barcos de la computadora y que active el metodo de jugar si
        // el metodo que determina si se gano o no aun no halla dado un resultado

    }


    @FXML
    void PreviewFragata(ActionEvent event) {
        if(parseInt(frigatesNum.getText()) > 0) {
            setUserBoats(1);
        }
        else{
            System.out.println("No tienes mas barcos que ubicar");
        }
    }


    @FXML
    void previewDestructor(ActionEvent event) {
        if(parseInt(destructorsNum.getText()) > 0) {
            setUserBoats(2);
        }
        else{
            System.out.println("No tienes mas barcos que ubicar");
        }    }

    @FXML
    void previewPortaaviones(ActionEvent event) {
        if(parseInt(aircraftCarrierNum.getText()) > 0) {
            setUserBoats(4);
        }
        else{
            System.out.println("No tienes mas barcos que ubicar");
        }
    }

    @FXML
    void previewSubmarino(ActionEvent event) {
        if(parseInt(submarinesNum.getText()) > 0) {
            setUserBoats(3);
        }
        else{
            System.out.println("No tienes mas barcos que ubicar");
        }
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

            // Poner con un if que si esta pasando el mouse por encima de otro
            // barco ya ubicado se ponga en rojo y no lo deje ubicar ademas de no poder ubicarlo

            onKeyPressed(userBoard, row, column, boat);
        });

        userBoard.requestFocus();
    }

    private void onKeyPressed(GridPane userBoard, int column, int row, Boat boat){
        userBoard.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.R) {
                boat.rotateBoat();
            }
            if (event.getCode() == KeyCode.ENTER) {
                userBoard userBoardM = new userBoard();
                userBoardM.setBoatUbication(row, column, boat);

                boat.getBoat().setOpacity(1.0);
                userBoard.setOnMouseMoved(null);
                userBoard.setOnKeyPressed(null);
                decreaseBoatCount(boat.getTypeBoat(), userBoard);
            }
        });

    }
    void playerAttack(){
        Rectangle attackv;
        attackv = new Rectangle(23.5, 22.3);
        attackv.setFill(Color.RED);
        attackv.setOpacity(0.5);
        computerBoard.add(attackv,4,4);

        computerBoard.setOnMouseMoved(event -> {
            double mouseX = event.getX();
            double mouseY = event.getY();

            int column = (int) (mouseX / (computerBoard.getWidth() / computerBoard.getColumnCount()));
            int row = (int) (mouseY / (computerBoard.getHeight() / computerBoard.getRowCount()));

            GridPane.setColumnIndex(attackv, column);
            GridPane.setRowIndex(attackv, row);
        });
        computerBoard.setOnMouseClicked(event -> {
            double mouseX = event.getX();
            double mouseY = event.getY();

            int column = (int) (mouseX / (computerBoard.getWidth() / computerBoard.getColumnCount()));
            int row = (int) (mouseY / (computerBoard.getHeight() / computerBoard.getRowCount()));

            Image image = new Image("/com/example/navalbattle/images/equis.jpg");
            ImageView imageView = new ImageView(image);

            computerBoard.add(imageView, column, row);

            System.out.println(column + row);

        });
    }
    void computerAttack(){

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


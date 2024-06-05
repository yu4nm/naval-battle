package com.example.navalbattle.controller;

import com.example.navalbattle.model.Boat;
import com.example.navalbattle.model.ComputerBoard;
import com.example.navalbattle.model.userBoard;
import com.example.navalbattle.view.alert.AlertBox;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.GridPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;
import java.util.Collection;

import static java.lang.Integer.parseInt;

public class GameController {

    Collection<Node> elementsToKeep = new ArrayList<>();

    userBoard userBoardM = new userBoard();
    ComputerBoard computerBoardM = new ComputerBoard();


    @FXML
    private Pane mainWindow;

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
    private Button atackButton;

    private Image hitImage, fireImage;

    @FXML
    void initialize() {
        fireImage = new Image(getClass().getResource("/com/example/navalbattle/images/fire.png").toExternalForm());
        hitImage = new Image(getClass().getResource("/com/example/navalbattle/images/hit.png").toExternalForm());
    }

    @FXML
    void onButtonPressedStartGame(ActionEvent event) {
        elementsToKeep.add(userBoard);
        elementsToKeep.add(computerBoard);
        elementsToKeep.add(atackButton);
        mainWindow.getChildren().retainAll(elementsToKeep);
        computerBoard.setVisible(true);
        computerBoardM.setComputerBoard();
        computerBoardM.printUserTable(computerBoardM.getComputerBoard());
        atackButton.setVisible(true);


    }

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

    @FXML
    void onButtonPressedAttack(ActionEvent event) {
        playerAttack();

    }

    void setUserBoats(int typeBoat) {

        Boat boat = new Boat(typeBoat);
        Rectangle boatPreview = boat.getBoat();
        boatPreview.setOpacity(0.5);
        userBoard.add(boatPreview, 4, 4);

        userBoard.setOnMouseExited(event -> {
            userBoard.getChildren().remove(boatPreview);
        });

        userBoard.setOnMouseMoved(event -> {
            double mouseX = event.getX();
            double mouseY = event.getY();

            int column = (int) (mouseX / (userBoard.getWidth() / userBoard.getColumnCount()));
            int row = (int) (mouseY / (userBoard.getHeight() / userBoard.getRowCount()));

            GridPane.setColumnIndex(boatPreview, column);
            GridPane.setRowIndex(boatPreview, row);

            if (userBoardM.overlappedBoat(boat, column, row)) {
                boatPreview.setFill(Color.RED);
            } else {
                boatPreview.setFill(boat.getColorByTypeBoat(typeBoat));
            }

            onKeyPressed(userBoard, column, row, boat);
        });

        userBoard.requestFocus();
    }

    private void onKeyPressed(GridPane userBoard, int column, int row, Boat boat) {

        userBoard.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.R) {
                boat.rotateBoat();
            }
            if (event.getCode() == KeyCode.ENTER) {
                if (userBoardM.setBoatPosition(row, column, boat)) {
                    userBoardM.printUserTable(userBoardM.getUserBoard());
                    boat.getBoat().setOpacity(1.0);
                    userBoard.setOnMouseMoved(null);
                    userBoard.setOnKeyPressed(null);
                    decreaseBoatCount(boat.getTypeBoat());
                }
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




            computerBoard.setOnMouseMoved(null);
            computerBoard.setOnMouseClicked(null);
            int player = 1;


            stateOfShots(column, row, computerBoardM.getComputerBoard(), player,userBoardM.getUserBoard());



        });
    }
    void computerAttack(){
        int player = 2;

        int randomAttackC = (int) (Math.random() * 10) ;
        int column = randomAttackC;

        int randomAttackR = (int) (Math.random() * 10) ;
        int row = randomAttackR;

        while(userBoardM.getUserBoard()[row][column] == 5){
            randomAttackC = (int) (Math.random() * 10);
            column = randomAttackC;

            randomAttackR = (int) (Math.random() * 10);
            row = randomAttackR;
        }
        stateOfShots(column, row, computerBoardM.getComputerBoard(), player, userBoardM.getUserBoard());
        showHitImage(column, row, player);
    }

    public void previewBoat(int typeBoat, TextField boatCountField){
        if(parseInt(boatCountField.getText()) > 0) {
            setUserBoats(typeBoat);
        }
        else{
            AlertBox alertBox = new AlertBox();
            alertBox.showMessage("Error", "No more boats of this type");
        }
    }

    public void decreaseBoatCount(int typeBoat){
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
    public void stateOfShots(int column, int row, int[][] computerBoard, int player, int[][] userBoard) {
        switch (player){
            case 1:
                if((computerBoardM.getComputerBoard()[row][column]) == 0){
                    computerBoard[row][column] = 5;
                    showHitImage(column,row, player);
                    computerAttack();


                }else if((computerBoardM.getComputerBoard()[row][column]) > 0 && computerBoardM.getComputerBoard()[row][column] < 5){
                    showFireImage(column,row,player);
                    computerAttack();
                }
                else{
                    System.out.println("ya disparaste ahi");
                }

            case 2:
                if((userBoardM.getUserBoard()[row][column]) == 0){
                    userBoard[row][column] = 5;
                    showHitImage(column,row, player);



                }else if((userBoardM.getUserBoard()[row][column]) > 0 && userBoardM.getUserBoard()[row][column] < 5){
                    showFireImage(column,row,player);
                }


        }

    }
    private void showHitImage(int column, int row, int player) {
        ImageView hitView = new ImageView(hitImage);
        hitView.setFitWidth(23.5);
        hitView.setFitHeight(22.3);
        if (player == 1) {
            computerBoard.add(hitView, column, row);
        } else {
            userBoard.add(hitView, column, row);
        }
    }
    private void showFireImage(int column, int row, int player) {
        ImageView fireView = new ImageView(fireImage);
        fireView.setFitWidth(23.5);
        fireView.setFitHeight(22.3);
        if (player == 1) {
            computerBoard.add(fireView, column, row);
        } else {
            userBoard.add(fireView, column, row);
        }
    }
}



package com.example.navalbattle.controller;

import com.example.navalbattle.model.*;
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
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import java.util.ArrayList;
import java.util.Collection;
import static java.lang.Integer.parseInt;

/**
 * The GameController class is responsible for handling user interactions and updating the game state.
 * It contains methods for initializing the game, handling button clicks, and updating the game board.
 * It is used by the GameStage class to control the game logic.
 */

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

    /**
     * Initializes the game controller.
     * It loads the images used for the game.
     */

    @FXML
    void initialize() {
        fireImage = new Image(getClass().getResource("/com/example/navalbattle/images/fire.png").toExternalForm());
        hitImage = new Image(getClass().getResource("/com/example/navalbattle/images/hit.png").toExternalForm());
    }

    /**
     * Handles the event when the start game button is pressed.
     * Clears the main window and retains the elements to play the game.
     * It initializes the computer board and displays it to the user.
     * @param event the action event.
     * @throws NoBoatsPlacedException if no boats are placed on the user board.
     */

    @FXML
    void onButtonPressedStartGame(ActionEvent event) throws NoBoatsPlacedException {
        try {
            if (frigatesNum.getText() != "0" || destructorsNum.getText() != "0" || submarinesNum.getText() != "0" || aircraftCarrierNum.getText() != "0") {
                throw new NoBoatsPlacedException("You must place all boats before starting the game");
            }
            elementsToKeep.add(userBoard);
            elementsToKeep.add(computerBoard);
            elementsToKeep.add(atackButton);
            mainWindow.getChildren().retainAll(elementsToKeep);
            computerBoard.setVisible(true);
            computerBoardM.setComputerBoard();
            computerBoardM.printBoard(computerBoardM.getComputerBoard());
            atackButton.setVisible(true);

        } catch (NoBoatsPlacedException e) {
            AlertBox alertBox = new AlertBox();
            alertBox.showMessage("Error", e.getMessage());
        }
    }

    /**
     * Handles the event when the preview frigate button is pressed.
     * It activates the preview boat method with the frigate type.
     * @param event the action event.
     */

    @FXML
    void PreviewFrigate(ActionEvent event) {
        previewBoat(1, frigatesNum);
    }

    /**
     * Handles the event when the preview destructor button is pressed.
     * It activates the preview boat method with the destructor type.
     * @param event the action event.
     */

    @FXML
    void previewDestructor(ActionEvent event) {
        previewBoat(2, destructorsNum);
    }

    /**
     * Handles the event when the preview aircraft carrier button is pressed.
     * It activates the preview boat method with the aircraft carrier type.
     * @param event the action event.
     */

    @FXML
    void previewAircraftCarrier(ActionEvent event) {
        previewBoat(4, aircraftCarrierNum);
    }

    /**
     * Handles the event when the preview submarine button is pressed.
     * It activates the preview boat method with the submarine type.
     * @param event the action event.
     */

    @FXML
    void previewSubmarine(ActionEvent event) {
        previewBoat(3, submarinesNum);
    }

    /**
     * Handles the event when the attack button is pressed.
     * It activates the player attack method.
     * @param event the action event.
     */

    @FXML
    void onButtonPressedAttack(ActionEvent event) {
        playerAttack();

    }

    /**
     * Sets the user boats on the user board.
     * It creates a boat object and displays it on the user board.
     * It handles the mouse movement and key press events to move the boat and rotate it.
     * @param typeBoat the type of boat to be placed.
     */

    void setUserBoats(int typeBoat) {

        Boat boat = new Boat(typeBoat);
        Polygon boatPreview = boat.getBoatDrawing();
        boatPreview.setOpacity(0.5);
        userBoard.add(boatPreview, 4, 4);

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
                boatPreview.setFill(boat.getColorByTypeBoat());
            }

            onKeyPressed(userBoard, column, row, boat);
        });

        userBoard.requestFocus();
    }

    /**
     * Handles the key press event when the user is placing a boat.
     * It rotates the boat when the 'R' key is pressed.
     * It places the boat on the user board when the 'Enter' key is pressed.
     * It updates the user board and decreases the boat count.
     * @param userBoard the user board.
     * @param column the column where the boat is placed.
     * @param row the row where the boat is placed.
     * @param boat the boat to be placed.
     */

    private void onKeyPressed(GridPane userBoard, int column, int row, Boat boat){
        userBoard.setOnKeyPressed(event -> {
            try {
                if (row == 9 || column == 9) {
                    throw new OutOfBondsException("The boat can not be rotated");
                } else {
                    if (event.getCode() == KeyCode.R) {
                        boat.rotateBoat();
                    }
                }
            } catch (OutOfBondsException e) {
                AlertBox alertBox = new AlertBox();
                alertBox.showMessage("Error", e.getMessage());
            }

            if (event.getCode() == KeyCode.ENTER) {
                if (userBoardM.setBoatPosition(row, column, boat)) {
                    userBoardM.printBoard(userBoardM.getUserBoard());
                    boat.getBoatDrawing().setOpacity(1.0);
                    userBoard.setOnMouseMoved(null);
                    userBoard.setOnKeyPressed(null);
                    decreaseBoatCount(boat.getTypeBoat());
                }
            }
        });

    }

    /**
     * Handles the player attack.
     * It creates a red rectangle to represent the attack.
     * It handles the mouse movement and click events to place the attack on the computer board.
     */

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
            attackv.setOpacity(0);

        });
    }

    /**
     * Handles the computer attack.
     * It generates random attacks on the user board.
     * It updates the user board and calls the player attack method.
     */

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
    }

    /**
     * It checks if there are boats of the selected type available.
     * It sets the user boats on the user board if there are boats available, otherwise it shows an error message.
     * @param typeBoat the type of boat to be placed.
     * @param boatCountField the boat count field.
     */

    public void previewBoat(int typeBoat, TextField boatCountField){
        if(parseInt(boatCountField.getText()) > 0) {
            setUserBoats(typeBoat);
        }
        else{
            AlertBox alertBox = new AlertBox();
            alertBox.showMessage("Error", "No more boats of this type");
        }
    }

    /**
     * Decreases the boat count of the selected type.
     * Changes the text field value to the new count.
     * @param typeBoat the type of boat to decrease.
     */

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

    /**
     * Handles the state of the shots.
     * It updates the computer board and the user board according to the result of the attack.
     * It shows the hit image if the attack hits a boat, the fire image if the attack misses, and an error message if the attack is repeated.
     * @param column the column where the attack is placed.
     * @param row the row where the attack is placed.
     * @param computerBoard the computer board.
     * @param player the player that placed the attack.
     * @param userBoard the user board.
     */

    public void stateOfShots(int column, int row, int[][] computerBoard, int player, int[][] userBoard) {
        switch (player) {
            case 1:
                if (computerBoard[row][column] == 0) {
                    computerBoard[row][column] = 5;
                    showHitImage(column, row, player);
                    computerAttack();
                } else if (computerBoard[row][column] > 0 && computerBoard[row][column] < 5) {
                    computerBoard[row][column] = 5;
                    showFireImage(column, row, player);
                    playerAttack();

                } else {
                    AlertBox alertBox = new AlertBox();
                    alertBox.showMessage("Error", "You have already attacked this position");
                }
                break;
            case 2:
                if (userBoard[row][column] == 0) {
                    userBoard[row][column] = 5;
                    showHitImage(column, row, player);
                } else if (userBoard[row][column] > 0 && userBoard[row][column] < 5) {
                    userBoard[row][column] = 5;
                    showFireImage(column, row, player);
                    computerAttack();
                }
                break;
        }

    }

    /**
     * Shows the hit image on the board.
     * It displays the hit image on the board where the player placed the attack.
     * @param column the column where the fire image is placed.
     * @param row the row where the fire image is placed.
     * @param player the player that placed the fire image.
     */

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

    /**
     * Shows the fire image on the board.
     * It displays the fire image on the board where the player placed the attack.
     * @param column the column where the fire image is placed.
     * @param row the row where the fire image is placed.
     * @param player the player that placed the fire image.
     */

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



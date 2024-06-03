package com.example.navalbattle.view.alert;

import javafx.scene.control.Alert;

public class AlertBox implements IAlertBox {

    @Override
    public void showMessage(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}

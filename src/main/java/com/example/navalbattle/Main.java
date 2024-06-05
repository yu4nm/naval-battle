package com.example.navalbattle;
import com.example.navalbattle.view.GameStage;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;

public class

Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        GameStage.getInstance();
    }
}

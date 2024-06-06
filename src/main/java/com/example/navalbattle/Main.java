package com.example.navalbattle;
import com.example.navalbattle.view.GameStage;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * The Main class extends the Application class from JavaFX.
 * It is the entry point for the Naval Battle application.
 */

public class Main extends Application {

    /**
     * The main method of the application.
     * It calls the launch method inherited from the Application class.
     * @param args command line arguments
     */

    public static void main(String[] args) {
        launch(args);
    }

    /**
     * The start method is the main entry point for all JavaFX applications.
     * It is called after the init method has returned, and after the system is ready for the application to begin running.
     * @param primaryStage the primary stage for this application, onto which the application scene can be set.
     * @throws IOException if an I/O error occurs.
     */

    @Override
    public void start(Stage primaryStage) throws IOException {
        GameStage.getInstance();
    }
}

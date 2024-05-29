package com.example.navalbattle.view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class setGameStage extends Stage {
    public setGameStage() throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/navalbattle/game-view.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        setTitle("Naval battle game");
        setResizable(false);
        setScene(scene);
        show();
    }

    public static setGameStage getInstance() throws IOException{
        return setGameStageHolder.INSTANCE = new setGameStage();
    }

    public static void deleteInstance() {
        setGameStageHolder.INSTANCE.close();
        setGameStageHolder.INSTANCE = null;
    }

    private static class setGameStageHolder {
        private static setGameStage INSTANCE;
    }

}

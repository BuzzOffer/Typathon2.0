package com.example.typathon2;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class App extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        // Load the main UI from the FXML file
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.initStyle(StageStyle.UNDECORATED);
        
        // Set up the scene and display the stage
        primaryStage.setScene(new Scene(root, 780, 550));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

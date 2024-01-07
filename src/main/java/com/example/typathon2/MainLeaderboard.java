package com.example.typathon2;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.net.URL;

public class MainLeaderboard extends Application{

    @Override
    public void start(Stage stage) throws Exception {
        // Load the FXML file using FXMLLoader
        URL resource = getClass().getResource("leaderboardsss.fxml");
        if (resource == null) {
            System.err.println("FXML file not found");
        } else {
            FXMLLoader loader = new FXMLLoader(resource);
            // rest of your code
            Parent root = loader.load();

            // Get the controller instance from the FXMLLoader
//        LeaderboardController controller = loader.getController();

            // You can perform additional operations on the controller if needed
            // For example, you might have initialization methods in your controller
//        controller.initialize();

            // Set up the scene
            Scene scene = new Scene(root);

            // Set the stage properties
            stage.setTitle("Typing Speed Test Leaderboard");
            stage.setScene(scene);
            stage.show();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}

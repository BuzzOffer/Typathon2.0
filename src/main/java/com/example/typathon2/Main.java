package com.example.typathon2;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("/com/example/Views/NewGame.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Typ-a-thon");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    public void changeScene(String string) {
    }

    
    
}

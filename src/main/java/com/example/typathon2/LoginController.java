package com.example.typathon2;

import javafx.animation.TranslateTransition;
import javafx.event.*;
import javafx.fxml.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.stage.*;
import javafx.util.Duration;

import java.io.IOException;

public class LoginController {
    @FXML
    private TextField emailField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Label invalidMessage;

    @FXML
    private Button loginButton;

    @FXML
    private void handleLoginButtonAction(ActionEvent event) {
        String email = emailField.getText();
        String password = passwordField.getText();

        // Validate username and password (implement your logic)

        // If validation is successful, change the scene
        if (!email.isEmpty() && !password.isEmpty() && isValid(email, password)) {
            changeScene("sample.fxml", event);
            ProfilePage.setUsername(email);
        } else {
            // Handle invalid login
            invalidMessage.setVisible(true);

            if (invalidMessage.isVisible()) {
                TranslateTransition tt = new TranslateTransition(Duration.millis(100), invalidMessage);
                tt.setByX(10f);
                tt.setCycleCount(4);
                tt.setAutoReverse(true);

                tt.play();
            }
        }
    }

    @FXML
    private void createAccountButton(ActionEvent event) {
        changeScene("registerpage.fxml", event);
    }


    private boolean isValid(String email, String password) {
        return password.equals(UserInfo.getPassword(email));
    }

    @FXML
    public void changeScene(String fxmlFile, ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource(fxmlFile));
            Scene scene = new Scene(root);

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            // Handle exception
            e.printStackTrace();
        }
    }
}

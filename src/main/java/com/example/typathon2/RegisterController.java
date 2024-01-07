package com.example.typathon2;

import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;

public class RegisterController {
    @FXML
    private TextField usernameReg, emailReg;
    @FXML
    private PasswordField passReg, confirmPass;
    @FXML
    private Label invalidPass, invalidEmail, invalidUsername;

    @FXML
    private void registerButton(ActionEvent event) {
        String username = usernameReg.getText();
        String email = emailReg.getText();
        String password = passReg.getText();
        String confirm_pass = confirmPass.getText();

        if (confirm_pass.equals(password) && !UserInfo.emailInUse(email) && !UserInfo.usernameInUse(username)) {    //add condition check for email in use and username in use
            Database.newUser(username); //placeholder, add store user info method to write into text file
            User user = new User(username, password, email);
            user.storeUserInfo();
            ProfilePage.setUsername(email);
            changeScene("sample.fxml", event);
        }
        else if (!confirm_pass.equals(password)) {  //do else ifs for different invalidities
            invalidPass.setVisible(true);

            if (invalidPass.isVisible()) {
                TranslateTransition tt = new TranslateTransition(Duration.millis(100), invalidPass);
                tt.setByX(10f);
                tt.setCycleCount(4);
                tt.setAutoReverse(true);

                tt.play();
            }
        }
        else if (UserInfo.emailInUse(email)) {  //do else ifs for different invalidities
            invalidEmail.setVisible(true);

            if (invalidEmail.isVisible()) {
                TranslateTransition tt = new TranslateTransition(Duration.millis(100), invalidPass);
                tt.setByX(10f);
                tt.setCycleCount(4);
                tt.setAutoReverse(true);

                tt.play();
            }
        }
        else if (UserInfo.usernameInUse(username)) {  //do else ifs for different invalidities
            invalidUsername.setVisible(true);

            if (invalidUsername.isVisible()) {
                TranslateTransition tt = new TranslateTransition(Duration.millis(100), invalidPass);
                tt.setByX(10f);
                tt.setCycleCount(4);
                tt.setAutoReverse(true);

                tt.play();
            }
        }
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

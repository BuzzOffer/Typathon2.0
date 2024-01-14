package com.example.typathon2.Controllers;

import com.example.typathon2.Models.ProfilePage;
import com.example.typathon2.dao.UserInfo;
import javafx.animation.TranslateTransition;
import javafx.event.*;
import javafx.fxml.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.stage.*;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {
    @FXML
    private TextField emailField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Label invalidMessage;

    @FXML
    private Label loggedInAs;


    @FXML
    private void handleLoginButtonAction(ActionEvent event) {
        String email = emailField.getText();
        String password = passwordField.getText();

        // Validate username and password (implement your logic)

        // If validation is successful, change the scene
        if (!email.isEmpty() && !password.isEmpty() && isValid(email, password)) {
            changeScene("sample.fxml", event);
            ProfilePage.setUsername(email);
            ProfilePage.setProfUsername(ProfilePage.getCurrentUser());
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
    @FXML
    public void displayLoggedInAs(String username) {
        loggedInAs.setText("as " + username);
    }


    private boolean isValid(String email, String password) {
        return password.equals(UserInfo.getPassword(email));
    }

    @FXML
    private void goBack(ActionEvent event) {
        changeScene("sample.fxml", event);
    }
    @FXML
    private void logout(ActionEvent event) {
        ProfilePage.setProfUsername(null);
        ProfilePage.setNullUser();
        changeScene("loginpage.fxml", event);
    }

    @FXML
    public void changeScene(String fxmlFile, ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/com/example/Views/" +fxmlFile));
            Scene scene = new Scene(root);

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            // Handle exception
            e.printStackTrace();
        }
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}

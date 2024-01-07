package com.example.typathon2;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Date;

public class ProfileController {
    @FXML
    private Label playerName, wpm_10, wpm_all, acc_10, acc_all, dateJoined, gamesCompleted, deathScore;
    @FXML
    public void exitButton(ActionEvent event) throws IOException {
        String fxmlFile;
        ProfilePage.setTempUser(ProfilePage.getCurrentUser());
        Parent root = FXMLLoader.load(getClass().getResource("leaderboardsss.fxml"));
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    } // program exit button to exit profile pages back to leaderboard or home.
    @FXML
    public void setUsername() {
        playerName.setText(ProfilePage.getUsername());
    }
    @FXML
    public void setWpm10() {
        wpm_10.setText(Integer.toString(ProfilePage.wpm10()));
    }
    @FXML
    public void setWpmAll() {
        wpm_all.setText(Integer.toString(ProfilePage.wpmAll()));
    }
    @FXML
    public void setAcc10() {
        acc_10.setText(Integer.toString(ProfilePage.acc10()));
    }
    @FXML
    public void setAccAll() {
        acc_all.setText(Integer.toString(ProfilePage.accAll()));
    }
    @FXML
    public void setDateJoined() {
        dateJoined.setText(ProfilePage.dateJoined().toString());
    }
    @FXML
    public void setGamesCompleted() {
        gamesCompleted.setText(Integer.toString(ProfilePage.testsCompleted()));
    }
    @FXML
    public void setDeathScore() {
        deathScore.setText(""); //get sudden death score once database is linked
    }
    @FXML
    public void setNotLoggedIn() {
        playerName.setText("You are not logged in");
    }
    @FXML
    public void loadProfileData() {
        if (!ProfilePage.isLoggedIn()) {
            setNotLoggedIn();
        }
        setUsername();
        setWpm10();
        setWpmAll();
        setAcc10();
        setAccAll();
        setDateJoined();
        setDeathScore();
        setGamesCompleted();
    }
}

package com.example.typathon2.Controllers;

import com.example.typathon2.Models.ProfilePage;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class ProfileController {
    @FXML
    private Label playerName, wpm_10, wpm_all, acc_10, acc_all, dateJoined, gamesCompleted, deathScore;
    @FXML
    public void exitButton(ActionEvent event) throws IOException {
        ProfilePage.setProfUsername(ProfilePage.getCurrentUser());
        Parent root = FXMLLoader.load(getClass().getResource("/com/example/Views/homepage.fxml"));
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    } // program exit button to exit profile pages back to leaderboard or home.
    @FXML
    public void setUsername() {
        if (!ProfilePage.isLoggedIn()) {
            setNotLoggedIn();
        }
        else {
            playerName.setText(ProfilePage.getProfUsername());
        }
    }
    @FXML
    public void setWpm10() {
        if (!ProfilePage.isLoggedIn()) {
            wpm_10.setText("0");
        }
        else {
            wpm_10.setText(Integer.toString(ProfilePage.wpm10()));
        }

    }
    @FXML
    public void setWpmAll() {
        if (!ProfilePage.isLoggedIn()) {
            wpm_all.setText("0");
        }
        else {
            wpm_all.setText(Integer.toString(ProfilePage.wpmAll()));
        }
    }
    @FXML
    public void setAcc10() {
        if (!ProfilePage.isLoggedIn()) {
            acc_10.setText("0");
        }
        else {
            acc_10.setText(Integer.toString(ProfilePage.acc10()));
        }
    }
    @FXML
    public void setAccAll() {
        if (!ProfilePage.isLoggedIn()) {
            acc_all.setText("0");
        }
        else {
            acc_all.setText(Integer.toString(ProfilePage.accAll()));
        }
    }
    @FXML
    public void setDateJoined() {
        if (!ProfilePage.isLoggedIn()) {
            dateJoined.setText("-");
        }
        else {
            dateJoined.setText(ProfilePage.dateJoined().toString());
        }
    }
    @FXML
    public void setGamesCompleted() {
        if (!ProfilePage.isLoggedIn()) {
            gamesCompleted.setText("0");
        }
        else {
            gamesCompleted.setText(Integer.toString(ProfilePage.testsCompleted()));
        }
    }
    @FXML
    public void setDeathScore() {
        if (!ProfilePage.isLoggedIn()) {
            deathScore.setText("0");
        }
        else {
            deathScore.setText(Integer.toString(ProfilePage.deathScore()));
        }
    }
    @FXML
    public void setNotLoggedIn() {
        playerName.setText("You are not logged in");
    }
    @FXML
    public void loadProfileData() {
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

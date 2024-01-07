package com.example.typathon2;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.sql.Date;

public class ProfileController {
    @FXML
    private Label playerName, wpm_10, wpm_all, acc_10, acc_all, dateJoined, gamesCompleted, deathScore;
    @FXML
    public void exitButton() {
        ProfilePage.setTempUser(ProfilePage.getCurrentUser());

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
}

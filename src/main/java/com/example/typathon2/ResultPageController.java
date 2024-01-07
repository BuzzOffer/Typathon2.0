package com.example.typathon2;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

public class ResultPageController implements Initializable {

    @FXML
    private Text wpmText;

    @FXML
    private Text accuracyText;

    @FXML
    private Label gameModeDetailsLabel;

    @FXML
    private Text scoreText;

    @FXML
    private Text timeUsedText; // Only visible for Sudden Death mode

    @FXML
    private Label scoreLabel; // Label for "Score" or "Sudden Death Score" based on the mode

    private int wpm;
    private int accuracy;
    private boolean isSuddenDeath;
    private int timeUsed; // Only for Sudden Death mode
    private int score;

    public ResultPageController(int wpm, int accuracy, boolean isSuddenDeath, int timeUsed, int score) {
        this.wpm = wpm;
        this.accuracy = accuracy;
        this.isSuddenDeath = isSuddenDeath;
        this.timeUsed = timeUsed;
        this.score = score;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        wpmText.setText(String.valueOf(wpm));
        accuracyText.setText(String.valueOf(accuracy));

        if (isSuddenDeath) {
            gameModeDetailsLabel.setText("Sudden Death Mode"); // Customize this based on the mode
            timeUsedText.setVisible(true);
            timeUsedText.setText("Time Used: " + timeUsed);
            scoreLabel.setText("Sudden Death Score:");
        } else {
            gameModeDetailsLabel.setText("Normal Mode"); // Customize this based on the mode
            timeUsedText.setVisible(false);
            scoreLabel.setText("Score:");
        }

        scoreText.setText(String.valueOf(score));
    }
}

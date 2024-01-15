package com.example.typathon2.Controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.text.Text;

public class ResultPageController implements Initializable {

    @FXML
    private Text accuracyText;

    @FXML
    private LineChart<?, ?> wpmChart;

    @FXML
    private LineChart<?, ?> accuracyChart;

    @FXML
    private Text gameModeDetailsText;

    @FXML
    private Text scoreText;

    @FXML
    private Text timeusedText; 

    @FXML
    private Text wpmText;

    private int wpm;
    private int accuracy;
    private boolean isSuddenDeath;
    private boolean timeTaken;
    private int timeUsed; // Only for Sudden Death mode
    private int score;
    private int[] wpmArray;
    private int[] accuracyArray;

    // Method for setting results including chart data
    public void setResults(int wpm, int accuracy, boolean isSuddenDeath, int timeUsed, int score, int[] wpmArray, int[] accuracyArray) {
        this.wpm = wpm;
        this.accuracy = accuracy;
        this.isSuddenDeath = isSuddenDeath;
        this.timeUsed = timeUsed;
        this.score = score;
        this.wpmArray = wpmArray;
        this.accuracyArray = accuracyArray;

        // Call initialize to update UI
        initialize(null, null);
        updateWpmChart();
        updateAccuracyChart();
    }

     private void updateAccuracyChart() {
        XYChart.Series series = new XYChart.Series();
        series.setName("Accuracy");

        // Assuming accuracyArray is an array of integers containing accuracy values
        for (int i = 0; i < accuracyArray.length; i++) {
            series.getData().add(new XYChart.Data<>(i + 1, accuracyArray[i]));
        }

        accuracyChart.getData().clear(); // Clear previous data
        accuracyChart.getData().add(series);

        // Customize the X-axis label
        NumberAxis xAxis = (NumberAxis) accuracyChart.getXAxis();
        xAxis.setLabel("Seconds");
        throw new UnsupportedOperationException("Unimplemented method 'updateAccuracyChart'");
    }

    private void updateWpmChart() {
        XYChart.Series series = new XYChart.Series();
        series.setName("WPM");

        // Assuming wpmArray is an array of integers containing WPM values
        for (int i = 0; i < wpmArray.length; i++) {
            series.getData().add(new XYChart.Data<>(i + 1, wpmArray[i]));
        }

        wpmChart.getData().clear(); // Clear previous data
        wpmChart.getData().add(series);

        // Customize the X-axis label
        NumberAxis xAxis = (NumberAxis) wpmChart.getXAxis();
        xAxis.setLabel("Seconds");
        throw new UnsupportedOperationException("Unimplemented method 'updateWpmChart'");
    }

    // Constructor for setting initial values without chart data
    public ResultPageController(int wpm, int accuracy, boolean isSuddenDeath, int timeUsed, int score) {
        this.wpm = wpm;
        this.accuracy = accuracy;
        this.isSuddenDeath = isSuddenDeath;
        this.timeUsed = timeUsed;
        this.score = score;
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        XYChart.Series series = new XYChart.Series<>();


        wpmText.setText(String.valueOf(wpm));
        accuracyText.setText(String.valueOf(accuracy));

        if (isSuddenDeath) {
            gameModeDetailsText.setText("Sudden Death Mode"); // Customize this based on the mode
            timeusedText.setVisible(true);
            timeusedText.setText("Time Used: " + timeUsed);
            scoreText.setText("Sudden Death Score:");
        } else {
            gameModeDetailsText.setText("Normal Mode"); // Customize this based on the mode
            timeusedText.setVisible(false);
            scoreText.setText("Score:");
        }

        scoreText.setText(String.valueOf(score));
    
        throw new UnsupportedOperationException("Unimplemented method 'initialize'");
    }
}
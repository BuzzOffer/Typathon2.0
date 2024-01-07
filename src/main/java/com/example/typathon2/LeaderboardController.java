package com.example.typathon2;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.time.LocalDateTime;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class LeaderboardController {

    @FXML
    private TableView<LeaderboardEntry> leaderboardTable;

    @FXML
    private TableColumn<LeaderboardEntry, Integer> rankColumn;

    @FXML
    private TableColumn<LeaderboardEntry, String> playerNameColumn;

    @FXML
    private TableColumn<LeaderboardEntry, Integer> wordsPerMinuteColumn;

    @FXML
    private TableColumn<LeaderboardEntry, Double> accuracyColumn;

    @FXML
    private TableColumn<LeaderboardEntry, Date> dateColumn;

    @FXML
    private TableColumn<LeaderboardEntry, LocalDateTime> timeColumn;

    // Initialization method
    @FXML
    public void initialize() {
        System.out.println("LeaderboardController initialized!");

        // Set up columns
        rankColumn.setCellValueFactory(new PropertyValueFactory<>("rank"));
        playerNameColumn.setCellValueFactory(new PropertyValueFactory<>("playerName"));
        wordsPerMinuteColumn.setCellValueFactory(new PropertyValueFactory<>("wordsPerMinute"));
        accuracyColumn.setCellValueFactory(new PropertyValueFactory<>("accuracy"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
        timeColumn.setCellValueFactory(new PropertyValueFactory<>("time"));

        loadDataForMode("30 seconds");
    }
    // Handle mode selection
    @FXML
    void handleTimeSelection(ActionEvent event) {
        // Assume you have a menu or buttons for different modes
        MenuItem selectedModeLabel = (MenuItem) event.getSource();
        String selectedMode = selectedModeLabel.getText();

        loadDataForMode(selectedMode);
    }
    @FXML
    void handleModeSelection(ActionEvent event) {
        // Assume you have a menu or buttons for different modes
        MenuItem selectedModeLabel = (MenuItem) event.getSource();
        String selectedMode = selectedModeLabel.getText();

        loadDataForMode(selectedMode);
    }
    // Load data for a specific mode
    private void loadDataForMode(String mode) {
        // Clear existing data
        leaderboardTable.getItems().clear();

        // Load predefined data for the selected mode
        List<LeaderboardEntry> leaderboardData = getPredefinedDataForMode(mode);

        // Populate the table
        leaderboardTable.getItems().addAll(leaderboardData);
    }
    // Load predefined data for a specific mode
    private List<LeaderboardEntry> getPredefinedDataForMode(String mode) {
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        List<LeaderboardEntry> data = new ArrayList<>();

        switch (mode) {
            case "15 seconds":
                data.add(createLeaderboardEntry(1, UserInfo.getUsernameFromData("Simon"), UserInfo.getWpm10("Simon"), 99.0, LocalDate.now(), LocalDateTime.now().format(timeFormatter)));
                break;
            case "30 seconds":
                data.add(createLeaderboardEntry(1, "win10", 57, 99.0, LocalDate.now(), LocalDateTime.now().format(timeFormatter)));
                break;
            case "45 seconds":
                data.add(createLeaderboardEntry(1, "syivv", 57, 99.0, LocalDate.now(), LocalDateTime.now().format(timeFormatter)));
                break;
            case "60 seconds":
                data.add(createLeaderboardEntry(1, "jun", 57, 99.0, LocalDate.now(), LocalDateTime.now().format(timeFormatter)));
                    break;
            default: // for errors
                System.out.println("Error");
                break;
        }
        return data;
    }
    private LeaderboardEntry createLeaderboardEntry(int rank, String playerName, int wordsPerMinute, double accuracy, LocalDate date, String time) {
        Label playerNameLabel = new Label(playerName);
        playerNameLabel.setOnMouseClicked(event -> openPlayerProfile(playerName, event));

        return new LeaderboardEntry(rank, playerNameLabel, wordsPerMinute, accuracy, date, time);
    }

    private void openPlayerProfile(String player_name, MouseEvent event) {
        // Implement logic to open the player's profile when player's name is clicked
        ProfilePage.setCurrentUser(ProfilePage.getUsername());
        ProfilePage.setTempUser(player_name);
        accessProfile("profilepage.fxml", event);
    }
    @FXML
    private void accessProfile(String fxmlFile, MouseEvent event) {
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

    // Class representing a leaderboard entry
    public static class LeaderboardEntry {
        private final int rank;
        private final Label playerName;
        private final int wordsPerMinute;
        private final double accuracy;
        private final String time;
        private final LocalDate date;

        public LeaderboardEntry(int rank, Label playerName, int wordsPerMinute, double accuracy, LocalDate date, String time) {
            this.rank = rank;
            this.playerName = playerName;
            this.wordsPerMinute = wordsPerMinute;
            this.accuracy = accuracy;
            this.date = date;
            this.time = time;
        }

        public int getRank() {
            return rank;
        }

        public Label getPlayerName() {
            return playerName;
        }

        public int getWordsPerMinute() {
            return wordsPerMinute;
        }

        public double getAccuracy() {
            return accuracy;
        }

        public LocalDate getDate() {
            return date;
        }

        public String getTime() {
            return time;
        }
    }
}

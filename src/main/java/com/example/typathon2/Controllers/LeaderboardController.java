package com.example.typathon2.Controllers;

import com.example.typathon2.dao.Database;
import com.example.typathon2.Models.ProfilePage;
import com.example.typathon2.dao.UserInfo;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.util.Collections;
import java.util.ArrayList;
import java.time.LocalDateTime;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class LeaderboardController {
    @FXML
    private Button backButton;
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

            // Sort the leaderboardData based on WPM (Words Per Minute)
            Collections.sort(leaderboardData, (entry1, entry2) -> Integer.compare(entry2.getWordsPerMinute(), entry1.getWordsPerMinute()));

            // Assign ranks based on the sorted order
            for (int i = 0; i < leaderboardData.size(); i++) {
                leaderboardData.get(i).setRank(i + 1);
            }

            // Populate the table
            leaderboardTable.getItems().addAll(leaderboardData);
        }

    // Load predefined data for a specific mode
    private List<LeaderboardEntry> getPredefinedDataForMode(String mode) {
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        List<LeaderboardEntry> data = new ArrayList<>();

        switch (mode) {
            case "15 seconds":
                for (int i = 0; i < 9; i++) {
                    if (getFor15(i) == null)
                        break;
                    else {
                        data.add(createLeaderboardEntry(getFor15(i)[0].toString(), Integer.parseInt(getFor15(i)[1].toString()),
                                Integer.parseInt(getFor15(i)[2].toString()), ((LocalDate) getFor15(i)[3]), getFor15(i)[4].toString()));
                    }
                }
                break;
            case "30 seconds":
                for (int i = 0; i < 9; i++) {
                    if (getFor30(i) == null)
                        break;
                    else {
                        data.add(createLeaderboardEntry(getFor30(i)[0].toString(), Integer.parseInt(getFor30(i)[1].toString()),
                                Integer.parseInt(getFor30(i)[2].toString()), ((LocalDate) getFor30(i)[3]), getFor30(i)[4].toString()));
                    }
                }
                break;
            case "45 seconds":
                for (int i = 0; i < 9; i++) {
                    if (getFor45(i) == null)
                        break;
                    else {
                        data.add(createLeaderboardEntry(getFor45(i)[0].toString(), Integer.parseInt(getFor45(i)[1].toString()),
                                Integer.parseInt(getFor45(i)[2].toString()), ((LocalDate) getFor45(i)[3]), getFor45(i)[4].toString()));
                    }
                }
                break;
            case "60 seconds":
                for (int i = 0; i < 9; i++) {
                    if (getFor60(i) == null)
                        break;
                    else {
                        data.add(createLeaderboardEntry(getFor60(i)[0].toString(), Integer.parseInt(getFor60(i)[1].toString()),
                                Integer.parseInt(getFor60(i)[2].toString()), ((LocalDate) getFor60(i)[3]), getFor60(i)[4].toString()));
                    }
                }
                break;
            default: // for errors
                System.out.println("Error");
                break;
        }
        // Assign ranks based on the position in the TableView
        for (int i = 0; i < data.size(); i++) {
            data.get(i).setRank(i + 1);
        }
        return data;
    }
    private LeaderboardEntry createLeaderboardEntry(String playerName, int wordsPerMinute, double accuracy, LocalDate date, String time) {
        Label playerNameLabel = new Label(playerName);
        playerNameLabel.setOnMouseClicked(event -> openPlayerProfile(playerName, event));

        return new LeaderboardEntry(playerNameLabel, wordsPerMinute, accuracy, date, time);
    }

    private void openPlayerProfile(String player_name, MouseEvent event) {
        // Implement logic to open the player's profile when player's name is clicked
        // makes sure the player_name is in the database, else won't do anything
        if (!player_name.equals(ProfilePage.getProfUsername()) && player_name.equals(UserInfo.getUsernameFromData(player_name))) {
            ProfilePage.setProfUsername(player_name);
            accessProfile("profilepage.fxml", event);
        }
        else if (!player_name.equals(UserInfo.getUsernameFromData(player_name))) {
            System.out.println("Player not found in database!");
        }
        else {
            accessProfile("profilepage.fxml", event);
        }

    }
    @FXML
    private void accessProfile(String fxmlFile, MouseEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/Views/profilepage.fxml"));
            Parent root = loader.load();
            ProfileController prof = loader.getController();
            prof.loadProfileData();
            Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            // Handle exception
            e.printStackTrace();
        }
    }
    @FXML
    public void goBack(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/com/example/Views/homepage.fxml"));
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            // Handle exception
            e.printStackTrace();
        }
    }
    //methods to get values to leaderboard
    public static Object[] getFor15(int number) {
        return Database.getForLB(15, number);
    }
    public static Object[] getFor30(int number) {
        return Database.getForLB(30, number);
    }
    public static Object[] getFor45(int number) {
        return Database.getForLB(45, number);
    }
    public static Object[] getFor60(int number) {
        return Database.getForLB(60, number);
    }

    // Class representing a leaderboard entry
    public static class LeaderboardEntry {
        private int rank;
        private final Label playerName;
        private final int wordsPerMinute;
        private final double accuracy;
        private final String time;
        private final LocalDate date;

        public LeaderboardEntry(Label playerName, int wordsPerMinute, double accuracy, LocalDate date, String time) {
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

        public void setRank(int rank) {
            this.rank = rank;
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

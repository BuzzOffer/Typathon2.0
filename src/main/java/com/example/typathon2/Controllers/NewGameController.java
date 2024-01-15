package com.example.typathon2.Controllers;

import com.example.typathon2.dao.DataWriter;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.text.Text;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import javafx.application.Platform;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SplitMenuButton;
import javafx.stage.Stage;

public class NewGameController implements Initializable {

    @FXML
    private TextField someTextField; // Assuming this is the text field where words are displayed
    @FXML
    private TextField userTextField; // User types the words that are displayed on the screen
    @FXML
    private Text wpmText; // Text to display WPM
    @FXML
    private Text timerText; // Text to display the countdown timer
    @FXML
    private Text mistakesText; // Text to display the number of mistakes
    @FXML
    private Text accuracyText; // Text to display the accuracy percentage
    @FXML
    private SplitMenuButton mainGameButton; // The 'Main Game' button
    @FXML
    private SplitMenuButton wordsButton;
    @FXML
    private SplitMenuButton quotesButton; // 'Quotes' SplitMenuButton
    @FXML
    private MenuItem mainGamefifteenSecondsItem;
    @FXML
    private MenuItem mainGamethirtySecondsItem;
    @FXML
    private MenuItem mainGamefortyFiveSecondsItem;
    @FXML
    private MenuItem mainGamesixtySecondsItem;
    @FXML
    private MenuItem mainGamesuddenDeathItem;
    @FXML
    private MenuItem mainGamewithPunctuationItem;
    @FXML
    private MenuItem tenWordsItem;
    @FXML
    private MenuItem twentyFiveWordsItem;
    @FXML
    private MenuItem fiftyWordsItem;
    @FXML
    private MenuItem hundredWordsItem;
    @FXML
    private MenuItem quotesfifteenSecondsItem;
    @FXML
    private MenuItem quotesthirtySecondsItem;
    @FXML
    private MenuItem quotesfortyFiveSecondsItem;
    @FXML
    private MenuItem quotessixtySecondsItem;
    @FXML
    private MenuItem quotessuddenDeathItem;

    private List<String> words; // List to store words from 'words.txt'
    private List<String> quotes; //LIst to store quotes from 'quotes.txt'
    private Timer gameTimer;
    private int timeLeft; // 30 seconds for the game
    private int totalCharactersTypedCorrectly = 0;
    private int totalMistakes = 0;
    private boolean isGameActive = false;
    private String currentWord; // Current word to be typed by the user
    private boolean suddenDeathMode = false;
    private boolean wordsMode = false;
    private long startTime;
    private long endTime;
    private Timer resultsUpdateTimer;
    private int updateInterval = 1000; // 1 second update interval
    private boolean gameOver = false; //Add a flag to track whether the game is over
    private boolean quotesMode = false;
    private long lastResultsUpdateTime = 0;

    private int wpm, accuracy, score, wordCount;

    // Method to initialize the game
    public void initialize(URL url, ResourceBundle rb) {
        words = new ArrayList<>();
        quotes = new ArrayList<>();
        loadWords();
        loadQuotes();
        setupTypingListener();
        setupMenuActions();
    }


    // Method to load words from 'words.txt'
    private void loadWords() {
        try (BufferedReader reader = new BufferedReader(new FileReader("words.txt"))) {

            words = new ArrayList<>();
            String line;
            while ((line = reader.readLine()) != null) {
                words.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
            // Handle the exception (e.g., file not found)
        }

//        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream("words.txt")) {
//            if (inputStream != null) {
//                try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
//                    words = new ArrayList<>();
//                    String line;
//                    while ((line = reader.readLine()) != null) {
//                        words.add(line);
//                    }
//                }
//            } else {
//                System.err.println("File not found: words.txt");
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }

    // Method to load quotes from 'quotes.txt'
    private void loadQuotes() {
        try (BufferedReader reader = new BufferedReader(new FileReader("quotes.txt"))) {
            quotes = new ArrayList<>();
            String line;
            while ((line = reader.readLine()) != null) {
                quotes.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
            // Handle exception (e.g., file not found)
        }

//        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream("quotes.txt")) {
//            if (inputStream != null) {
//                try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
//                    quotes = new ArrayList<>();
//                    String line;
//                    while ((line = reader.readLine()) != null) {
//                        quotes.add(line);
//                    }
//                }
//            } else {
//                System.err.println("File not found: words.txt");
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }

    private void setupTypingListener() {
        userTextField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!isGameActive) {
                    return; // Ignore typing if the game is not active
                }
                checkTyping(newValue);
            }
        });
    }

    private void checkTyping(String typedText) {
        if (!isGameActive) {
          return;
       }

        if (quotesMode && typedText.equals(currentWord) && typedText.endsWith("\n")) {
           stopStopwatch();
           endGame();
           return; // Add return satement to exit the method
        }

        if (wordsMode && typedText.equals(currentWord) && typedText.endsWith("\n")) {
           stopStopwatch();
           endGame();
           return; // Add return satement to exit the method
        }

        if (suddenDeathMode && !typedText.equals(currentWord.substring(0, typedText.length()))) {
            // In Sudden Death mode, allow only one mistake
            if (totalMistakes == 1) {
                endGame();
                return;
            }
        }

        if (typedText.endsWith("\n") && typedText.trim().equals(currentWord.trim())) {
            totalCharactersTypedCorrectly += currentWord.length();
            generateRandomWord(); // Generate a new word after correctly typing the current word
            userTextField.clear(); // Clear the TextField for the new word
        } else if (typedText.endsWith("\n")) {
            // Only increment totalMistakes if the user pressed Enter, and the word is incorrect
            totalMistakes++;
        }

        // Check if the lengths match before changing the color
        if (typedText.length() == currentWord.length()) {
            changeColor(typedText.equals(currentWord));
        }
    }

    @FXML
    private void handleEnterKeyPressed(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            userTextField.clear();
            generateRandomWord();
        }
    }

    // Event handler for 'Main Game' button
//    @FXML
//    private void handleMainGameAction() {
//        if (isGameActive) {
//            // If a game is already in progress, you might want to reset or ignore the
//            // action
//            return;
//        }
//
//        startGame(30);
//    }
//    @FXML
//    private void handleMainGameAction() {
//        if (isGameActive) {
//            // If a game is already in progress, you might want to reset or ignore the action
//            return;
//        }
//
//        String selectedMenuItemText = mainGameButton.getText();
//        int selectedTime = extractTimeFromMenuItemText(selectedMenuItemText);
//
//        startGameWithTimer(selectedTime);
//    }
//
//    private int extractTimeFromMenuItemText(String menuItemText) {
//        // Parse the time mode from the menu item text
//        // You might need to adjust this parsing logic based on your actual menu item text format
//        String[] parts = menuItemText.split(" ");
//        if (parts.length >= 2) {
//            try {
//                return Integer.parseInt(parts[0]);
//            } catch (NumberFormatException e) {
//                e.printStackTrace(); // Handle the exception appropriately
//            }
//        }
//        return 0; // Return a default value if parsing fails
//    }
    @FXML
    private void handleMainGameAction(ActionEvent event) {
        //todo: comment
        System.out.println("event " + event);
//        MenuItem selectedItem = mainGameButton.getSelectionModel().getSelectedItem();
//        if (selectedItem != null) {
//            String selectedTimeText = selectedItem.getText();
//            setSelectedTime(selectedTimeText);
//            startGame(); // Start the game with the selected time
//        } else {
//            // Handle the case where no item is selected
//            System.out.println("handleMainGameAction null");
//        }
    }

    //todo: new method
    @FXML
    private void handlePlayAction(ActionEvent event) {
        System.out.println("play");
        if (quotesMode) {
            startQuotesGame(timeLeft);
        }
        else
            startGame(timeLeft);
    }

    private void setSelectedTime(String timeText) {
        int selectedTime = extractTimeFromMenuItemText(timeText);
        timeLeft = selectedTime;
    }

    private int extractTimeFromMenuItemText(String menuItemText) {
        String[] parts = menuItemText.split(" ");
        if (parts.length >= 2) {
            try {
                return Integer.parseInt(parts[0]);
            } catch (NumberFormatException e) {
                e.printStackTrace(); // Handle the exception appropriately
            }
        }
        return 0; // Return a default value if parsing fails
    }



    // Method to start the game
    private void startGame(int selectedTime) {
        isGameActive = true;
        resetGameOverFlag(); // Reset the game over flag
        resetGameStats();
        resetTime(selectedTime);
        startTimer();
        generateRandomWord();
        startResultsUpdateTimer(); // Start the timer for updating results every second
    }

    private void startResultsUpdateTimer() {
        resultsUpdateTimer = new Timer();
        resultsUpdateTimer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                updateResults();
            }
        }, 0,1000);
    }

    private void updateResults() {
        calculateResults(); // To calculate results
        if (!isGameActive && !gameOver) {
            resultsUpdateTimer.cancel();
        }
    }
    // Method to reset tge game over flag
    private void resetGameOverFlag() {
        gameOver = false;
    }

    private void changeColor(boolean correct) {
        if (correct) {
            // if correct
            userTextField.setStyle("-fx-text-fill: green;");
        } else {
            // if incorrect
            userTextField.setStyle("-fx-text-fill: red;");
        }
    }

    // Method to reset game statistics
    private void resetGameStats() {
        totalCharactersTypedCorrectly = 0;
        totalMistakes = 0;
//        timeLeft = initialTime;
        updateGUI();
    }
    private void resetTime(int initialTime){
        timeLeft = initialTime;
        updateGUI();
    }


    private void startTimer() {
        if (gameTimer != null) {
            gameTimer.cancel();
        }
        gameTimer = new Timer();
        gameTimer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                if (timeLeft > 0) {
                    timeLeft--;
                    Platform.runLater(() -> {
                        updateTimerLabel();
                        updateResults(); // Added to update results every second
                    });
                } else {
                    Platform.runLater(() -> endGame());
                    gameOver = true;
                    cancel();
                }
            }
        }, 1000, 1000);
    }
//    private void startTimer(int seconds) {
//        if (gameTimer != null) {
//            gameTimer.cancel();
//        }
//        timeLeft = seconds; // Set the time based on user selection
//        gameTimer = new Timer();
//        gameTimer.scheduleAtFixedRate(new TimerTask() {
//            @Override
//            public void run() {
//                if (timeLeft > 0) {
//                    timeLeft--;
//                    Platform.runLater(() -> {
//                        updateTimerLabel();
//                        updateResults(); // Added to update results every second
//                    });
//                } else {
//                    Platform.runLater(() -> endGame());
//                    gameOver = true;
//                    cancel();
//                }
//            }
//        }, 1000, 1000);
//    }


    // Method to generate a random word
    private void generateRandomWord() {
        loadWords();
        Random r = new Random();
        currentWord = words.get(r.nextInt(words.size()));
        someTextField.setText(currentWord);
    }

    private void generateRandomWordwithPunctuation() {

        loadWords();
        Random random = new Random();

        // Array of punctuation marks to randomly choose from
        String[] punctuations = { ",", ".", "!", "?", "'", "\"" }; // Add more as needed

        List<String> wordsWithPunctuation = new ArrayList<>();

        for (String word : words) {
            int punctuationIndex = random.nextInt(punctuations.length); // Random punctuation index
            String punctuation = punctuations[punctuationIndex]; // Random punctuation mark

            int insertPosition = random.nextInt(word.length()); // Random position in the word
            String wordWithPunctuation = new StringBuilder(word).insert(insertPosition, punctuation).toString();

            wordsWithPunctuation.add(wordWithPunctuation); // Add modified word to the list
        }

        currentWord = String.join(" ", wordsWithPunctuation);

        someTextField.setText(currentWord);

    }

    private void startGameWithPunctuation(int seconds) {
        isGameActive = true;
        resetGameStats();
        resetTime(seconds);
        startTimer();
        generateRandomWordwithPunctuation();
        startResultsUpdateTimer(); // Start the timer for updating results every second
    }

    @FXML
    private void handlemainGameWithPunctuationAction() {
        if (isGameActive) {
            // If a game is already in progress, you might want to reset or ignore the
            // action
            return;
        }

        //todo: comment
//        startGameWithPunctuation();
    }

    private void endGame() {
        stopTimer(); // Stop the timer when the game ends
        if (suddenDeathMode) {
            calculateSuddenDeathScore();
            DataWriter.storeResultsDeath(wpm, accuracy, score, (int) (endTime - startTime));
            DataWriter.storeGameHistory("Sudden Death", wpm, accuracy, score);
        } else if (quotesMode){
            calculateResults(); // Regular game mode calculations
            DataWriter.storeResultsQuotes(wpm, accuracy, score, (int) (endTime - startTime));
            DataWriter.storeGameHistory("Quotes", wpm, accuracy, score);
        } else if (wordsMode) {
            calculateResults();
            DataWriter.storeResultsWords(wordCount, wpm, accuracy, score, (int) (endTime - startTime));
            DataWriter.storeGameHistory("Words", wpm, accuracy, score);
        } else {
            calculateResults();
            DataWriter.storeResultsNormal(wpm, accuracy, score, (int) (endTime - startTime));
            DataWriter.storeGameHistory("Timed", wpm, accuracy, score);
        }

        isGameActive = false;
        suddenDeathMode = false; // Reset the sudden death flag
        updateGUI(); // Update GUI after the games ends


    }

    // Method to stop the timer
    private void stopTimer(){
        if(gameTimer != null){
            gameTimer.cancel();
        }
    }

    // Method to calculate WPM, mistakes, and accuracy
    private void calculateResults() {
        int wpm = totalCharactersTypedCorrectly / 5; // WPM calculation
        int accuracy = (int) ((double) totalCharactersTypedCorrectly / (totalCharactersTypedCorrectly + totalMistakes)
                * 100);
        int score = wpm * accuracy * 100;

        this.wpm = wpm;
        this.accuracy = accuracy;
        this.score = score;

        // Update the displayed result
        Platform.runLater(() -> {
            wpmText.setText(String.valueOf(wpm));
            mistakesText.setText(String.valueOf(totalMistakes));
            accuracyText.setText(String.valueOf(accuracy)+"%");
        });

    }

    // Method to update GUI elements (timer, WPM, etc.)
    private void updateGUI() {
        updateTimerLabel();
        if(gameOver){
            someTextField.setText("Game Over"); // Display a message 'Game Over' in someTextField when the game has ended
            userTextField.setEditable(false); // Disable further typing in userTextField
            calculateResults(); // Calculate adn display results
        }
    }

    // Method to update the timer label
    private void updateTimerLabel() {
//        timerText.setText(timeLeft+"s");
        Platform.runLater(() -> timerText.setText(timeLeft + "s"));
    }

    private void setupMenuActions() {
        mainGamefifteenSecondsItem.setOnAction(e -> handlemainGameFifteenSecondsAction());
        mainGamethirtySecondsItem.setOnAction(e -> handlemainGameThirtySecondsAction());
        mainGamefortyFiveSecondsItem.setOnAction(e -> handlemainGameFortyFiveSecondsAction());
        mainGamesixtySecondsItem.setOnAction(e -> handlemainGameSixtySecondsAction());
        mainGamesuddenDeathItem.setOnAction(e -> handlemainGameSuddenDeathAction());
        wordsButton.setOnAction(e -> handleWordsAction());
        tenWordsItem.setOnAction(e -> handleTenWordsAction());
        twentyFiveWordsItem.setOnAction(e -> handleTwentyFiveWordsAction());
        fiftyWordsItem.setOnAction(e -> handleFiftyWordsAction());
        hundredWordsItem.setOnAction(e -> handleHundredWordsAction());
        quotesButton.setOnAction(e -> handleQuotesAction());
    }

    @FXML
    private void handlemainGameFifteenSecondsAction() {
        startGameWithTimer(15);
    }

    @FXML
    private void handlemainGameThirtySecondsAction() {
        startGameWithTimer(30);
    }

    @FXML
    private void handlemainGameFortyFiveSecondsAction() {
        startGameWithTimer(45);
    }

    @FXML
    private void handlemainGameSixtySecondsAction() {
        startGameWithTimer(60);
    }

    @FXML
    private void handlemainGameSuddenDeathAction() {
        suddenDeathMode = true;
        startGameWithTimer(30); // Assuming 30 seconds timer for Sudden Death mode
    }
    @FXML
    private void handleQuotesFifteenSecondsAction() {
        startQuotesGamewithTimer(15);
    }

    @FXML
    private void handleQuotesThirtySecondsAction() {
        startQuotesGamewithTimer(30);
    }

    @FXML
    private void handleQuotesFortyFiveSecondsAction() {
        startQuotesGamewithTimer(45);
    }

    @FXML
    private void handleQuotesSixtySecondsAction() {
        startQuotesGamewithTimer(60);
    }

    @FXML
    private void handleQuotesSuddenDeathAction() {
        suddenDeathMode = true;
        startGameWithTimer(30); // Assuming 30 seconds timer for Sudden Death mode
    }

    private void startGameWithTimer(int seconds) {
        //todo: comment
//        if (isGameActive) {
//            // Optionally reset the game or inform the player that the game is in progress
//            return;
//        }

        isGameActive = false;
        timeLeft = seconds;
        resetTime(timeLeft);
        stopTimer();
        //todo: comment
//        startGame(seconds);
    }

    // Other methods and logic related to your game
    private void calculateSuddenDeathScore() {
        // Calculate the number of words typed correctly
        int wordsTypedCorrectly = totalCharactersTypedCorrectly / 5; // assuming an average of 5 characters per word

        // Calculate the time taken. If 30 seconds is the total time, then timeTaken =
        // 30 - timeLeft
        int timeTaken = 30 - timeLeft;

        // Scoring logic: you can adjust this formula according to your game's design
        // For example, scoring could be higher for more words typed in less time
        int suddenDeathScore = wordsTypedCorrectly * 100 / (timeTaken + 1); // +1 to avoid division by zero
    }

    //todo: comment
    @FXML
    private void handleWordsAction() {
        wordsMode = true;
//        startWordsGame(10);
    }

    //todo: comment
    private void startWordsGame(int wordCount) {
//        if (isGameActive) {
//            // Optionally reset the game or inform the player that the game is in progress
//            return;
//        }
//        isGameActive = true;
        this.wordCount = wordCount;
        resetGameStats();
        generateRandomWords(wordCount);
//        startStopwatch();
    }

    private void generateRandomWords(int count) {
        loadWords(); // Load words if the list is null or empty
        Random random = new Random();
        List<String> selectedWords = IntStream.range(0, count)
            .mapToObj(i -> words.get(random.nextInt(words.size())))
            .collect(Collectors.toList());
            currentWord = String.join(" ", selectedWords);
        someTextField.setText(currentWord);
    }

    private void startStopwatch() {
        startTime = System.currentTimeMillis();
    }

    private void stopStopwatch() {
        endTime = System.currentTimeMillis();
    }

    @FXML
    private void handleTenWordsAction() {
        wordsMode = true;
        startWordsGame(10);
    }

    @FXML
    private void handleTwentyFiveWordsAction() {
        wordsMode = true;
        startWordsGame(25);
    }

    @FXML
    private void handleFiftyWordsAction() {
        wordsMode = true;
        startWordsGame(50);
    }

    @FXML
    private void handleHundredWordsAction() {
        wordsMode = true;
        startWordsGame(100);
    }

    @FXML
    private void handleQuotesAction() {
        if (isGameActive) {
            // Handle an already active game (e.g., show a message or reset the game)
            return;
        }
        quotesMode = true;
        //todo: comment
//        startQuotesGame();
    }

    private void startQuotesGame(int seconds) {
        isGameActive = true;
        resetGameOverFlag(); // Reset the game over flag
        resetGameStats();
        resetTime(seconds);
        startTimer();
        generateRandomQuote();
        startResultsUpdateTimer(); // Start the timer for updating results every second
    }

    private void startQuotesGamewithTimer(int seconds) {
//        if (isGameActive) {
//            // Optionally reset the game or inform the player that the game is in progress
//            return;
//        }

        quotesMode = true;
        isGameActive = false;
        timeLeft = seconds;
        resetTime(timeLeft);
        stopTimer();

//        startQuotesGame(seconds);
    }

    private void generateRandomQuote() {

        loadQuotes(); // Load quotes if the list is null or empty
        Random random = new Random();
        String randomQuote = quotes.get(random.nextInt(quotes.size()));
        someTextField.setText(randomQuote);
        currentWord = randomQuote;
    }
}
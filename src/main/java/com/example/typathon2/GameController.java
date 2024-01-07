package com.example.typathon2;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import com.example.typathon2.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioMenuItem;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.control.TextField;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

public class GameController implements Initializable {

    private int wordCounter = 0;

    private File saveData;

    private int first = 1;

    ArrayList<String> wordsList;

    ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);

    @FXML
    private Button Guest;

    @FXML
    private Button Home;

    @FXML
    private Button Leaderboard;

    @FXML
    private Button SignIn;

    @FXML
    private Button Start;

    @FXML
    private Text accuracy;

    @FXML
    private AnchorPane an;

    @FXML
    private ImageView logo;

    @FXML
    private Text mistakes;

    @FXML
    private Button playAgain;

    @FXML
    private Text seconds;

    @FXML
    private TextField userWord;

    @FXML
    private Label word;

    @FXML
    private Text wpm;

    @FXML
    private RadioMenuItem quoteOption;

    @FXML
    private RadioMenuItem wordsOption; // Declare the new game mode option

    @FXML
    private RadioMenuItem mainGameOption;

    @FXML
    private SplitMenuButton timedMenuButton;

    @FXML
    private Text normalScoreText;

    @FXML
    private Text suddenDeathScoreText;

    @FXML
    private ComboBox<String> wordCountDropdown; // Declare the dropdown

    @FXML
    private CheckBox includePunctuationCheckBox;

    @FXML
    void guest(MouseEvent event) {

    }

    @FXML
    void home(MouseEvent event) {

    }

    @FXML
    void leaderboard(MouseEvent event) {

    }

    @FXML
    void signin(MouseEvent event) {

    }

    @FXML
    void start(MouseEvent event) {

    }

    private int timer = 30;

    Runnable r = new Runnable() {
        @Override
        public void run() {
            if (suddenDeathMode || wordsMode) {
                seconds.setText(String.valueOf(timer));
                timer += 1;
            } else {
                if (timer > -1) {
                    seconds.setText(String.valueOf(timer));
                    timer -= 1;
                }

                else {
                    if (timer == -1) {
                        userWord.setDisable(true);
                        userWord.setText("Game over");

                        try {
                            FileWriter myWriter = new FileWriter(saveData);
                            myWriter.write(countAll + ";");
                            myWriter.write(counter + ";");
                            myWriter.write(String.valueOf(countAll - counter));
                            myWriter.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                    if (timer == -4) {
                        playAgain.setVisible(true);
                        playAgain.setDisable(false);
                        executor.shutdown();
                    }

                    timer -= 1;
                }
            }
        }
    };

    private int countAll = 0;
    private int counter = 0;
    private boolean suddenDeathMode = false;
    private boolean wordsMode = false;

    @FXML
    void startGame(KeyEvent event) {
        if (event.getCode().equals(KeyCode.ENTER)) {
            String userInput = userWord.getText();
            String correctWord = word.getText();

            if (userInput.equals(correctWord)) {
                totalCorrectChars += correctWord.length(); // Update the count of correct characters
                // only gets called once
                if (first == 1) {
                    first = 0;
                    if (!suddenDeathMode) {
                        executor.scheduleAtFixedRate(r, 0, 1, TimeUnit.SECONDS);
                    }
                }

                if (event.getCode().equals(KeyCode.ENTER)) {

                    String s = userWord.getText();
                    String real = word.getText();
                    countAll++;

                    // if correct
                    if (s.equals(real)) {
                        counter++;

                        // Check for sudden death mode and end game on mistake
                        if (suddenDeathMode) {
                            if (wordCounter < words.size()) {
                                word.setText(words.get(wordCounter));
                                wordCounter++;
                            } else {
                                endGame();
                            }
                        } else {
                            word.setText(words.get(wordCounter));
                            wordCounter++;
                        }
                    } else {
                        if (suddenDeathMode) {
                            endGame();
                        }
                    }

                    userWord.setText("");
                    accuracy.setText(String.valueOf(Math.round((counter * 1.0 / countAll) * 100)));

                    calculateAndDisplayScores(counter, (int) Math.round((counter * 1.0 / countAll) * 100),suddenDeathMode);
                }
            }
            calculateWPM();
        }
    }

    private int totalCorrectChars = 0; // New variable to keep track of correct characters

    private void calculateWPM() {
        // Assuming 'timer' holds the elapsed time in seconds
        if (timer > 0) {
            double wpmc = (totalCorrectChars / 5.0) / (timer / 60.0);
            wpm.setText(String.format("%.2f", wpmc)); // Display the WPM with two decimal places
        }
    }

    private void endGame() {
        userWord.setDisable(true);
        userWord.setText("Game over");

        try {
            FileWriter myWriter = new FileWriter(saveData);
            myWriter.write(countAll + ";");
            myWriter.write(counter + ";");
            myWriter.write(String.valueOf(countAll - counter));
            myWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        playAgain.setVisible(true);
        playAgain.setDisable(false);
        executor.shutdown();
    }

    private void setSuddenDeathMode(boolean mode) {
        suddenDeathMode = mode;
        timer = 0; // Reset timer for Sudden Death mode
    }

    @FXML
    void suddenDeathSelected(ActionEvent event) {
        setSuddenDeathMode(true);
        // Start the game immediately for Sudden Death mode
        startGame(new KeyEvent(KeyEvent.KEY_PRESSED, "", "", KeyCode.ENTER, false, false, false, false));
    }

    ArrayList<String> words = new ArrayList<>();

    // add words to array list
    public void addToList() {
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader("words.txt"));
            String line = reader.readLine();
            while (line != null) {
                words.add(line);
                // read next line
                line = reader.readLine();
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void toMainMenu(ActionEvent ae) throws IOException {
        Main m = new Main();
        m.changeScene("sample.fxml");
    }

    private void checkInput() {
        String s = userWord.getText();
        String real = word.getText();

        if (s.equals(real)) {
            // if correct
            changeColor(true);
        } else {
            // if incorrect
            changeColor(false);
        }
    }

    private void changeColor(boolean correct) {
        if (correct) {
            // if correct
            userWord.setStyle("-fx-text-fill: green;");
        } else {
            // if incorrect
            userWord.setStyle("-fx-text-fill: red;");
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        seconds.setText("30");
        addToList();
        Collections.shuffle(words);
        word.setText(words.get(wordCounter));
        wordCounter++;

        userWord.setOnKeyReleased(e -> checkInput());

        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH-mm-ss");
        saveData = new File("src/data/" + formatter.format(date).strip() + ".txt");

        try {
            if (saveData.createNewFile()) {
                System.out.println("File created: " + saveData.getName());
            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        wordsOption = new RadioMenuItem("Words"); // Initialize the new game mode
        mainGameOption = new RadioMenuItem("Main Game");
        quoteOption = new RadioMenuItem("Quotes");

        ToggleGroup toggleGroup = new ToggleGroup();
        ((Toggle) wordsOption).setToggleGroup(toggleGroup);
        ((Toggle) mainGameOption).setToggleGroup(toggleGroup);
        ((Toggle) quoteOption).setToggleGroup(toggleGroup);

        mainGameOption.setSelected(true);

        wordsOption.setOnAction(this::optionSelected);
        mainGameOption.setOnAction(this::optionSelected);
        quoteOption.setOnAction(this::optionSelected);

        setupTimedMenu();
        setupWordCountDropdown();

        throw new UnsupportedOperationException("Unimplemented method 'initialize'");
    }

    private void optionSelected(ActionEvent event) {
        if (event.getSource() == wordsOption) {
            int wordCount = Integer.parseInt(wordCountDropdown.getValue().split(" ")[0]);
            loadWords("words.txt", wordCount);
            wordsMode = true; // Set wordsMode to true for "Words" mode
            suddenDeathMode = false; // Reset suddenDeathMode
            // Start the game
            startGame(null);
        } else if (event.getSource() == mainGameOption) {
            loadWords("words.txt");
            wordsMode = false; // Reset wordsMode
            suddenDeathMode = false; // Reset suddenDeathMode
            // Start the game for "Main Game" mode
            startGame(null);
        } else if (event.getSource() == quoteOption) {
            String fileName = "quotes.txt";
            if (timedModeSelected() && !includePunctuationCheckBox.isSelected()) {
                fileName = "quotes_nopunctuation.txt";
            }
            loadWords(fileName);
            wordsMode = false; // Reset wordsMode
            suddenDeathMode = false; // Reset suddenDeathMode
            // Start the game for "Quotes" mode
            startGame(null);
        }
    }

    private void loadWords(String fileName) {
        try {
            File file = new File(fileName);
            Scanner scanner = new Scanner(file);

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] words = line.split(" ");

                for (String word : words) {

                    wordsList.add(word);
                }
            }

            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadWords(String fileName, int wordCount) {
        wordsList = new ArrayList<>();
        try {
            File file = new File(fileName);
            Scanner scanner = new Scanner(file);

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                Collections.addAll(wordsList, line.split(" "));
            }

            scanner.close();

            // Randomize and limit the list to the specified word count
            Collections.shuffle(wordsList);
            if (wordsList.size() > wordCount) {
                wordsList = new ArrayList<>(wordsList.subList(0, wordCount));
            }

        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + fileName);
            e.printStackTrace();
        }
    }

    // Inside initialize method or any setup method
    private void setupTimedMenu() {
        MenuItem time15 = new MenuItem("15 seconds");
        MenuItem time30 = new MenuItem("30 seconds");
        MenuItem time45 = new MenuItem("45 seconds");
        MenuItem time60 = new MenuItem("60 seconds");

        time15.setOnAction(event -> setTimer(15));
        time30.setOnAction(event -> setTimer(30));
        time45.setOnAction(event -> setTimer(45));
        time60.setOnAction(event -> setTimer(60));

        timedMenuButton.getItems().addAll(time15, time30, time45, time60);
    }

    private void setTimer(int seconds) {
        timer = seconds;
    }

    private boolean timedModeSelected() {
        return timedMenuButton.isShowing(); // Check if the timed menu is visible (i.e., "Timed" mode selected)
    }

    private int calculateScore(int wpm, int accuracy) {
        int score;
        score = wpm * accuracy * 100;
        return score;
    }

    private int calculateSuddenDeathScore(String input, String target) {
        int suddendeath_score = 0;
        int minLength = Math.min(input.length(), target.length());
        for (int i = 0; i < minLength; i++) {
            if (input.charAt(i) == target.charAt(i)) {
                suddendeath_score++;
            }
        }
        return suddendeath_score;
    }

    private void calculateAndDisplayScores(int wpm, int accuracy, boolean isSuddenDeath) {
        int score;
        int suddendeath_score;
        if (!isSuddenDeath) {
            score = calculateScore(wpm, accuracy);
            normalScoreText.setText("Normal Score: " + score); // Update normal score display
        } else {
            String userInput = userWord.getText();
            String currentWord = word.getText();
            suddendeath_score = calculateSuddenDeathScore(userInput, currentWord);
            suddenDeathScoreText.setText("Sudden Death Score: " + suddendeath_score); // Update sudden death score
                                                                                      // display
        }
    }

    private void setupWordCountDropdown() {
        wordCountDropdown.getItems().addAll("10 words", "25 words", "50 words", "100 words");
        wordCountDropdown.setValue("10 words"); // Set default value
    }
}

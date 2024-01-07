package com.example.typathon2;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.RadioMenuItem;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

public class Controller implements Initializable {

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
    private AnchorPane an;

    @FXML
    private ImageView logo;

    @FXML
    void guest(MouseEvent event) {

        loadPage("Play As Guest");

    }

    @FXML
    void home(MouseEvent event) {

       loadPage("HomePage");

    }

    @FXML
    void leaderboard(MouseEvent event) {

        loadPage("Leaderboard");

    }

    @FXML
    void signin(MouseEvent event) {

        loadPage("Sign In / Register");

    }

    @FXML
    void start(MouseEvent event) {

        loadPage("Start Playing");

    }

    private void loadPage (String page){
   
        try {
            FXMLLoader.load(getClass().getResource(page+".fxml"));
        } catch (IOException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        

    }

    public Button getGuest() {
        return Guest;
    }

    public void setGuest(Button guest) {
        Guest = guest;
    }

    public Button getHome() {
        return Home;
    }

    public void setHome(Button home) {
        Home = home;
    }

    public Button getLeaderboard() {
        return Leaderboard;
    }

    public void setLeaderboard(Button leaderboard) {
        Leaderboard = leaderboard;
    }

    public Button getSignIn() {
        return SignIn;
    }

    public void setSignIn(Button signIn) {
        SignIn = signIn;
    }

    public Button getStart() {
        return Start;
    }

    public void setStart(Button start) {
        Start = start;
    }

    public AnchorPane getAn() {
        return an;
    }

    public void setAn(AnchorPane an) {
        this.an = an;
    }

    public ImageView getLogo() {
        return logo;
    }

    public void setLogo(ImageView logo) {
        this.logo = logo;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

}


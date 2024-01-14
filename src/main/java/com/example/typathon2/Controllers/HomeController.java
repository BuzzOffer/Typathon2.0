package com.example.typathon2.Controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.example.typathon2.Models.ProfilePage;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class HomeController implements Initializable {

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
    void guest(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/Views/profilepage.fxml"));
        Parent root = loader.load();
        ProfileController prof = loader.getController();
        prof.loadProfileData();
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void home(MouseEvent event) {

       Parent root = loadPage("sample");
       Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
       Scene scene = new Scene(root);
       stage.setScene(scene);
       stage.show();

    }

    @FXML
    void leaderboard(MouseEvent event) {

        Parent root = loadPage("leaderboardsss");
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    @FXML
    void signin(MouseEvent event) throws IOException {
        if (ProfilePage.isLoggedIn()) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/Views/isloggedin.fxml"));
            Parent root = loader.load();
            LoginController login = loader.getController();
            login.displayLoggedInAs(ProfilePage.getCurrentUser());
            Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
        else {
            Parent root = loadPage("loginpage");
            Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
    }

    @FXML
    void start(MouseEvent event) {

        Parent root = loadPage("NewGame");
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    private Parent loadPage (String page){
   
        try {
            return FXMLLoader.load(getClass().getResource("/com/example/Views/" + page+".fxml"));
        } catch (IOException ex) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
            return null;
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


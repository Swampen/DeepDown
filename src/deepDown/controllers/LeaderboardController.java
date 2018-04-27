package deepDown.controllers;

import deepDown.Leaderboard;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

public class LeaderboardController {

    @FXML
    private AnchorPane anchor;
    @FXML
    private Button newGameButton;
    @FXML
    private Text nr1;
    @FXML
    private Text nr2;
    @FXML
    private Text nr3;
    @FXML
    private Text nr4;
    @FXML
    private Text nr5;

    @FXML
    public void initialize() {
        Leaderboard lb = new Leaderboard();
        lb.loadScores();
        nr1.setText("1. " + lb.getNames(0) + "................... " + Integer.toString(lb.getHighScore(0)));
        nr2.setText("2. " + lb.getNames(1) + "................... " + Integer.toString(lb.getHighScore(1)));
        nr3.setText("3. " + lb.getNames(2) + "................... " + Integer.toString(lb.getHighScore(2)));
        nr4.setText("4. " + lb.getNames(3) + "................... " + Integer.toString(lb.getHighScore(3)));
        nr5.setText("5. " + lb.getNames(4) + "................... " + Integer.toString(lb.getHighScore(4)));

    }
}

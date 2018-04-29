package deepDown.controllers;

import deepDown.Leaderboard;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

import java.util.concurrent.TimeUnit;

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
        fillScores(nr1, 1, lb.getNames(0).length(), Integer.toString(lb.getHighScore(0)).length(), lb.getNames(0), Integer.toString(lb.getHighScore(0)));
        fillScores(nr2, 2, lb.getNames(1).length(), Integer.toString(lb.getHighScore(1)).length(), lb.getNames(1), Integer.toString(lb.getHighScore(1)));
        fillScores(nr3, 3, lb.getNames(2).length(), Integer.toString(lb.getHighScore(2)).length(), lb.getNames(2), Integer.toString(lb.getHighScore(2)));
        fillScores(nr4, 4, lb.getNames(3).length(), Integer.toString(lb.getHighScore(3)).length(), lb.getNames(3), Integer.toString(lb.getHighScore(3)));
        fillScores(nr5, 5, lb.getNames(4).length(), Integer.toString(lb.getHighScore(4)).length(), lb.getNames(4), Integer.toString(lb.getHighScore(4)));
    }

    public void fillScores(Text nr, int x, int nLength, int sLength, String name, String score) {
        String dot = ".";
        for(double i = 0; i < 80; i++){
            if(nr.getLayoutBounds().getWidth() < (1280-15.39)){
                dot += "." ;
                System.out.println(dot.length());
                nr.setText(x +". " + name + dot + score);
            }
        }
    }
}

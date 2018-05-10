package deepDown.controllers;

import deepDown.Leaderboard;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

import java.io.IOException;


/**
 * @author Michael Mobæk Thoresen
 */
public class LeaderboardController {

    @FXML    private AnchorPane anchor;
    @FXML    private Button backButton;
    @FXML    private Text nr1;
    @FXML    private Text nr2;
    @FXML    private Text nr3;
    @FXML    private Text nr4;
    @FXML    private Text nr5;
    @FXML    private Text leaderTitle;

    /**
     * Method which runs when the fxml is loaded
     * Loads the scores file and sets each text to the appropriate score and name
     */
    @FXML
    public void initialize() {
        Leaderboard lb = new Leaderboard();
        lb.loadScores();
        fillScores(nr1, 1, lb.getNames(0), Integer.toString(lb.getHighScore(0)));
        fillScores(nr2, 2, lb.getNames(1), Integer.toString(lb.getHighScore(1)));
        fillScores(nr3, 3, lb.getNames(2), Integer.toString(lb.getHighScore(2)));
        fillScores(nr4, 4, lb.getNames(3), Integer.toString(lb.getHighScore(3)));
        fillScores(nr5, 5, lb.getNames(4), Integer.toString(lb.getHighScore(4)));
        Image button = new Image(getClass().getResourceAsStream("/deepDown/resource/images/BackArrow.png"), 100, 75, false, false);
        backButton.setGraphic(new ImageView(button));

        anchor.getStylesheets().add("deepDown/resource/stylesheet.css");

    }

    /**
     * Fills in scores and names plus checks for how much room you have left
     * from the left edge and adds a punctuation mark until the page is filled
     * @param nr which text you wish to change
     * @param x what number place i.e. 1. , 2. , 3.
     * @param name Name of the player who achieved that score
     * @param score The score the player achieved
     */
    private void fillScores(Text nr, int x, String name, String score) {
        StringBuilder dot = new StringBuilder(".");
        for(double i = 0; i < 80; i++){
            if(nr.getLayoutBounds().getWidth() < (1280-32)){
                dot.append(".");
                nr.setText(x +". " + name + dot + score);
            }
        }
    }

    /**
     * Method for getting back to the main menu when you press the back button
     */
    public void backButtonPressed(){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/deepDown/resource/FXML/startMenu.fxml"));
            StartMenuController controller = new StartMenuController();
            loader.setController(controller);
            Parent root = loader.load();
            anchor.getChildren().setAll(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

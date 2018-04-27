package deepDown.controllers;

import deepDown.Leaderboard;
import javafx.animation.FadeTransition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.util.Duration;

import java.io.IOException;

public class EndScreenController {

    @FXML private ImageView imageView;
    @FXML private VBox vBox;
    @FXML private HBox nameHBox;
    @FXML private HBox buttonsHBox;
    @FXML private Text totScoreText;
    @FXML private AnchorPane anchor;
    @FXML private TextField nameInput;
    private int totscore = 0;

    public EndScreenController(int totscore) {
        this.totscore = totscore;
    }

    @FXML
    public void initialize(){
        FadeTransition ft = new FadeTransition(Duration.millis(1500), vBox);
        ft.setToValue(1.0);
        ft.play();

        Image img = new Image(getClass().getResourceAsStream("/deepDown/resource/images/GameOver.png"));
        imageView.setImage(img);

        totScoreText.setFill(Color.WHITE);
        totScoreText.setStroke(Color.BLACK);
        totScoreText.setStrokeWidth(1.0);
        totScoreText.setText("Total score: " + Integer.toString(totscore));

        Leaderboard lb = new Leaderboard();
        lb.loadScores();
        lb.addScore(totscore);
        lb.saveScores();
    }

    public void backToMenuPressed()throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/deepDown/resource/FXML/startMenu.fxml"));
        StartMenuController controller = new StartMenuController();
        loader.setController(controller);
        Parent root = loader.load();
        anchor.getChildren().setAll(root);
    }

    public void quitGamePressed(){
        Platform.exit();
        System.exit(0);
    }

    public void okButtonPressed(){
        nameHBox.setOpacity(0.0);
        nameHBox.setDisable(true);
        buttonsHBox.opacityProperty().setValue(1.0);
        buttonsHBox.setDisable(false);
        String name = nameInput.getText();
    }
}

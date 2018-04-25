package deepDown.controllers;

import javafx.animation.FadeTransition;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.util.Duration;

public class EndScreenController {

    @FXML private ImageView imageView;
    @FXML private VBox vBox;
    @FXML private Text totScoreText;
    @FXML private AnchorPane anchor;
    private int totscore = 0;

    public EndScreenController(int totscore) {
        this.totscore = totscore;
    }

    @FXML
    public void initialize(){

        Image img = new Image(getClass().getResourceAsStream("/deepDown/resource/images/GameOver.png"));
        imageView.setImage(img);

        totScoreText.setFill(Color.WHITE);
        totScoreText.setStroke(Color.BLACK);
        totScoreText.setStrokeWidth(1.0);
        totScoreText.setText("Total score: " + Integer.toString(totscore));

        FadeTransition ft = new FadeTransition(Duration.millis(1500), vBox);
        ft.setFromValue(0.0);
        ft.setToValue(1.0);
        ft.play();
    }

    public void playAgainPressed(){
        System.out.println("again");
        anchor.getChildren().remove(anchor);
    }

    public void quitGamePressed(){
        //TODO
        System.out.println("quit");
    }
}

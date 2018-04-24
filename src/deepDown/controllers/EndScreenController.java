package deepDown.controllers;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class EndScreenController {

    @FXML private ImageView imageView;
    @FXML private VBox vBox;
    @FXML private Text totScoreText;
    private int totscore = 0;

    public EndScreenController(int totscore) {
        this.totscore = totscore;
    }

    @FXML
    public void initialize(){
        Image img = new Image(getClass().getResourceAsStream("/deepDown/resource/images/NewGame.png"));
        imageView.setImage(img);

        totScoreText.setText("Total score: " + Integer.toString(totscore));






    }
}

package deepDown.controllers;

import deepDown.Leaderboard;
import deepDown.level.Animation;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.io.IOException;

/**
 * @author Ole-Martin Heggen
 */
public class EndScreenController {

    @FXML private ImageView imageView;
    @FXML private VBox vBox;
    @FXML private HBox nameHBox;
    @FXML private HBox buttonsHBox;
    @FXML private Text totScoreText;
    @FXML private Text nameText;
    @FXML private AnchorPane anchor;
    @FXML private TextField nameInput;
    @FXML private Button backToMenuButton;
    @FXML private Button quitGameButton;
    private int totscore = 0;
    private boolean gameCompleted;

    public EndScreenController(int totscore, boolean gameCompleted) {
        this.totscore = totscore;
        this.gameCompleted = gameCompleted;
    }

    /**
     * Method which runs when the fxml is loaded.
     * Enables the possibility to use the Enter key on buttons
     * and plays fade transition.
     */
    @FXML
    public void initialize(){
        anchor.addEventHandler(KeyEvent.KEY_PRESSED, e -> {
            if (backToMenuButton.isFocused() && e.getCode() == KeyCode.ENTER) {
                backToMenuPressed();
                e.consume();
            }
            if (quitGameButton.isFocused() && e.getCode() == KeyCode.ENTER) {
                quitGamePressed();
                e.consume();
            }
        });

        Animation.endScreenAnimation(vBox);

        if (gameCompleted){
            Image img = new Image(getClass().getResourceAsStream("/deepDown/resource/images/Victory.png"));
            imageView.setImage(img);
        }else{
            Image img = new Image(getClass().getResourceAsStream("/deepDown/resource/images/GameOver.png"));
            imageView.setImage(img);
        }

        anchor.getStylesheets().add("deepDown/resource/stylesheet.css");

        totScoreText.setText("Total score: " + Integer.toString(totscore));
    }


    /**
     * The action when the "Back to Menu" button is pressed.
     * Resets the background music and loads the start menu with FXML.
     */
    public void backToMenuPressed(){
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

    /**
     * The action when the "Quit Game" button is pressed.
     * Stops and closes the javaFX application.
     */
    public void quitGamePressed(){
        Platform.exit();
        System.exit(0);
    }

    /**
     * The action when the "OK" button next to the name field is pressed.
     * Confirms the {@code String} the user has typed in the input field
     * and adds it to the leaderboard.
     */
    public void okButtonPressed(){
        nameHBox.setVisible(false);
        nameHBox.setDisable(true);
        buttonsHBox.opacityProperty().setValue(1.0);
        buttonsHBox.setVisible(true);
        buttonsHBox.setDisable(false);
        String name = nameInput.getText();
        Leaderboard lb = new Leaderboard();
        lb.loadScores();
        lb.addScore(totscore, name);
        lb.saveScores();
    }
}

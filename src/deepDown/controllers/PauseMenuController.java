package deepDown.controllers;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class PauseMenuController {

    private final Stage stage;
    @FXML private Button resumeGameButton;
    @FXML private Button backToMenuButton;
    @FXML private Button saveGameButton;
    @FXML private Button quitGameButton;
    @FXML private AnchorPane anchor;
    private final AnchorPane parentAnchor;
    private int levelProgression;
    private int totScore;
    private int avatarLives;

    /**
     * Constructor for PauseMenuController
     * @param stage The specified stage 'Add More'
     * @param levelProgression What level is loaded which is used for saving
     * @param totScore the players total score which is used for saving
     * @param avatarLives the players life count which is used for saving
     */
    public PauseMenuController(Stage stage, AnchorPane parentAnchor, int levelProgression, int totScore, int avatarLives){
        this.stage = stage;
        this.parentAnchor = parentAnchor;
        this.levelProgression = levelProgression;
        this.totScore = totScore;
        this.avatarLives = avatarLives;
    }

    @FXML
    public void initialize(){
        anchor.addEventHandler(KeyEvent.KEY_PRESSED, e -> {
            if (resumeGameButton.isFocused() && e.getCode() == KeyCode.ENTER) {
                resumeGamePressed();
                e.consume();
            }
            if (backToMenuButton.isFocused() && e.getCode() == KeyCode.ENTER) {
                backToMenuPressed();
                e.consume();
            }
            if (saveGameButton.isFocused() && e.getCode() == KeyCode.ENTER) {
                saveGamePressed();
                e.consume();
            }
            if (quitGameButton.isFocused() && e.getCode() == KeyCode.ENTER) {
                quitGamePressed();
                e.consume();
            }
        });
    }

    /**
     * Exits the Pause menu and resumes the animationTimer when the Resume Game button is pressed
     */
    public void resumeGamePressed(){
        stage.close();
    }

    /**
     * Returns to the main menu
     * @throws IOException throws an IOException when a file is missing
     */
    public void backToMenuPressed(){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/deepDown/resource/FXML/startMenu.fxml"));
            StartMenuController startMenuController = new StartMenuController();
            loader.setController(startMenuController);
            Parent root = loader.load();
            stage.close();
            parentAnchor.getChildren().setAll(root);
        }  catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Saves the Avatar's level progress, score and life total to an external file
     * @throws IOException throws an IOException when a file is missing
     */
    public void saveGamePressed(){
        try {
            File save = new File("Files/save");
            FileOutputStream fos = new FileOutputStream(save);
            DataOutputStream dos = new DataOutputStream(fos);
            dos.writeInt(levelProgression);
            dos.writeInt(totScore);
            dos.writeInt(avatarLives);
            Alert conf = new Alert(Alert.AlertType.CONFIRMATION, "Game saved");
            conf.showAndWait();
        }  catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Quit the application
     */
    public void quitGamePressed(){
        Platform.exit();
        System.exit(0);
    }
}

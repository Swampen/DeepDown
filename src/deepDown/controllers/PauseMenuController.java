package deepDown.controllers;

import deepDown.Alerts;
import deepDown.Loader;
import javafx.application.Platform;
import javafx.fxml.FXML;
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
    @FXML private Button backToLEButton;
    @FXML private Button saveGameButton;
    @FXML private Button quitGameButton;
    @FXML private AnchorPane anchor;
    private final AnchorPane parentAnchor;
    private int levelProgression;
    private int totScore;
    private int avatarLives;

    /**
     * Constructor.
     * @param stage The specified stage 'Add More'.
     * @param levelProgression What level is loaded which is used for saving.
     * @param totScore the players total score which is used for saving.
     * @param avatarLives the players life count which is used for saving.
     */
    public PauseMenuController(Stage stage, AnchorPane parentAnchor, int levelProgression, int totScore, int avatarLives){
        this.stage = stage;
        this.parentAnchor = parentAnchor;
        this.levelProgression = levelProgression;
        this.totScore = totScore;
        this.avatarLives = avatarLives;
    }

    /**
     * Method which runs when the fxml is loaded.
     * Enables the possibility to use Enter key on the buttons.
     */
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
            if (backToLEButton.isFocused() && e.getCode() == KeyCode.ENTER) {
                backToLEButtonPressed();
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
     * The action performed when the "Resume Game" button is pressed.
     * Exits the Pause menu and starts the {@code AnimationTimer}.
     */
    public void resumeGamePressed(){
        stage.close();
    }

    /**
     * The action performed when the "Back to Menu" button is pressed.
     * Loads the start menu with FXML.
     */
    public void backToMenuPressed(){
        Loader.loadStartMenuFromPause(parentAnchor, stage);
    }

    public void backToLEButtonPressed(){
        Loader.loadLevelEditorFromPause(parentAnchor, stage);
    }

    /**
     * The action performed when the "Save Game" button is pressed.
     * Saves the player's level progress, score and life total to an external file.
     */
    public void saveGamePressed(){
        try {
            File save = new File("Files/save");
            FileOutputStream fos = new FileOutputStream(save);
            DataOutputStream dos = new DataOutputStream(fos);
            dos.writeInt(levelProgression);
            dos.writeInt(totScore);
            dos.writeInt(avatarLives);
            Alerts.gameSaved();
        }  catch (IOException e) {
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
}

package deepDown.controllers;

import deepDown.Alerts;
import deepDown.ButtonProperty;
import deepDown.Loader;
import javafx.animation.AnimationTimer;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class PauseMenuController {

    private final Stage stage;
    @FXML private ImageView pauseImageView;
    @FXML private Button resumeGameButton;
    @FXML private Button backToMenuButton;
    @FXML private Button backToLEButton;
    @FXML private Button saveGameButton;
    @FXML private Button quitGameButton;
    @FXML private AnchorPane anchor;
    @FXML private VBox buttonVBox;

    private final AnchorPane parentAnchor;
    private int levelProgression;
    private int totScore;
    private int avatarLives;
    private AnimationTimer animationTimer;

    private ButtonProperty resumeGame;
    private ButtonProperty backToMenu;
    private ButtonProperty backToLevelEditor;
    private ButtonProperty saveGame;
    private ButtonProperty quit;

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
        stage.initStyle(StageStyle.UNDECORATED);

        Image pauseImage = new Image(getClass().getResourceAsStream("/deepDown/resource/images/pauseMenu/Pause.png"));
        pauseImageView.setImage(pauseImage);

        resumeGame = new ButtonProperty(resumeGameButton);
        backToMenu = new ButtonProperty(backToMenuButton);
        if (levelProgression < 9) {
            saveGame = new ButtonProperty(saveGameButton);
        }else {
            backToLevelEditor = new ButtonProperty(backToLEButton);
        }
        quit = new ButtonProperty(quitGameButton);

        anchor.setOnMouseMoved(e -> buttonVBox.setMouseTransparent(false));

        anchor.addEventHandler(KeyEvent.KEY_PRESSED, e -> {
            if (e.getCode() == KeyCode.UP || e.getCode() == KeyCode.DOWN){
                buttonVBox.setMouseTransparent(true);
            }
            if ((resumeGameButton.isFocused() && e.getCode() == KeyCode.ENTER) || e.getCode() == KeyCode.ESCAPE) {
                resumeGamePressed();
                e.consume();
            }
            if (backToMenuButton.isFocused() && e.getCode() == KeyCode.ENTER) {
                backToMenuPressed();
                e.consume();
            }
            if (levelProgression < 9) {
                if (saveGameButton.isFocused() && e.getCode() == KeyCode.ENTER) {
                    saveGamePressed();
                    e.consume();
                }
            }else {
                if (backToLEButton.isFocused() && e.getCode() == KeyCode.ENTER) {
                    backToLEButtonPressed();
                    e.consume();
                }

            }
            if (quitGameButton.isFocused() && e.getCode() == KeyCode.ENTER) {
                quitGamePressed();
                e.consume();
            }
        });

        animationTimer = new AnimationTimer(){
            public void handle(long now){

                if((resumeGameButton.isHover() || resumeGameButton.isFocused()) && resumeGame.isNotHovering()){
                    resumeGame.setButtonImage("/deepDown/resource/images/pauseMenu/ResumeGame2.png");
                    resumeGameButton = resumeGame.getButton();
                    resumeGame.setHovering(true);
                    resumeGameButton.requestFocus();

                }else if (!resumeGameButton.isHover() && !resumeGameButton.isFocused()){
                    resumeGame.setButtonImage("/deepDown/resource/images/pauseMenu/ResumeGame.png");
                    resumeGameButton = resumeGame.getButton();
                    resumeGame.setHovering(false);
                }
                if ((backToMenuButton.isHover() || backToMenuButton.isFocused()) && backToMenu.isNotHovering()) {
                    backToMenu.setButtonImage("/deepDown/resource/images/pauseMenu/BackToMenu2.png");
                    backToMenuButton = backToMenu.getButton();
                    backToMenu.setHovering(true);
                    backToMenuButton.requestFocus();
                } else if (!backToMenuButton.isHover() && !backToMenuButton.isFocused()) {
                    backToMenu.setButtonImage("/deepDown/resource/images/pauseMenu/BackToMenu.png");
                    backToMenuButton = backToMenu.getButton();
                    backToMenu.setHovering(false);
                }

                if (levelProgression < 9) {
                    if((saveGameButton.isHover() || saveGameButton.isFocused()) && saveGame.isNotHovering()){
                        saveGame.setButtonImage("/deepDown/resource/images/pauseMenu/SaveGame2.png");
                        saveGameButton = saveGame.getButton();
                        saveGame.setHovering(true);
                        saveGameButton.requestFocus();

                    }else if (!saveGameButton.isHover() && !saveGameButton.isFocused()){
                        saveGame.setButtonImage("/deepDown/resource/images/pauseMenu/SaveGame.png");
                        saveGameButton = saveGame.getButton();
                        saveGame.setHovering(false);
                    }
                }else {
                    if ((backToLEButton.isHover() || backToLEButton.isFocused()) && backToLevelEditor.isNotHovering()) {
                        backToLevelEditor.setButtonImage("/deepDown/resource/images/pauseMenu/BackToLevelEditor2.png");
                        backToLEButton = backToLevelEditor.getButton();
                        backToLevelEditor.setHovering(true);
                        backToLEButton.requestFocus();
                    } else if (!backToLEButton.isHover() && !backToLEButton.isFocused()) {
                        backToLevelEditor.setButtonImage("/deepDown/resource/images/pauseMenu/BackToLevelEditor.png");
                        backToLEButton = backToLevelEditor.getButton();
                        backToLevelEditor.setHovering(false);
                    }
                }

                if((quitGameButton.isHover() || quitGameButton.isFocused()) && quit.isNotHovering()){
                    quit.setButtonImage("/deepDown/resource/images/pauseMenu/Quit2.png");
                    quitGameButton = quit.getButton();
                    quit.setHovering(true);
                    quitGameButton.requestFocus();

                }else if (!quitGameButton.isHover() && !quitGameButton.isFocused()){
                    quit.setButtonImage("/deepDown/resource/images/pauseMenu/Quit.png");
                    quitGameButton = quit.getButton();
                    quit.setHovering(false);
                }
            }
        };
        animationTimer.start();
    }

    /**
     * The action performed when the "Resume Game" button is pressed.
     * Exits the Pause menu and starts the {@code AnimationTimer}.
     */
    public void resumeGamePressed(){
        animationTimer.stop();
        stage.close();
    }

    /**
     * The action performed when the "Back to Menu" button is pressed.
     * Loads the start menu with FXML.
     */
    public void backToMenuPressed(){
        animationTimer.stop();
        Loader.loadStartMenuFromPause(parentAnchor, stage);
    }

    /**
     * The action performed when the "Back To Level Editor" button is pressed.
     * Loads the level editor with FXML.
     */
    public void backToLEButtonPressed(){
        animationTimer.stop();
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
        if (Alerts.quitGameConfirmation()) {
            animationTimer.stop();
            Platform.exit();
            System.exit(0);
        }
    }
}

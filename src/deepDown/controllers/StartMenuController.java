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
import javafx.scene.layout.*;


import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * @author Michael Mobæk Thoresen and Ole-Martin Heggen
 */
public class StartMenuController {

    @FXML private AnchorPane anchor;
    @FXML private ImageView logo;
    @FXML private Button newGameButton;
    @FXML private Button loadGameButton;
    @FXML private Button leaderboardButton;
    @FXML private Button quitButton;
    @FXML private Button helpButton;
    @FXML private Button levelEditorButton;
    @FXML private VBox buttonVBox;

    private ButtonProperty newGame;
    private ButtonProperty loadGame;
    private ButtonProperty levelEditor;
    private ButtonProperty leaderboard;
    private ButtonProperty quit;
    private ButtonProperty help;

    private AnimationTimer animationTimer;

    /**
     * Method which runs when the fxml is loaded.
     * Loads the initial resources for the start menu, enables
     * the possibility to use the Enter key on buttons and starts {@code AnimationTimer}
     */
    @FXML
    public void initialize() {
        Image logoImage = new Image(getClass().getResourceAsStream("/deepDown/resource/images/DeepDownLogo.png"));
        logo.setImage(logoImage);

        newGame = new ButtonProperty(newGameButton);
        loadGame = new ButtonProperty(loadGameButton);
        levelEditor = new ButtonProperty(levelEditorButton);
        leaderboard = new ButtonProperty(leaderboardButton);
        quit = new ButtonProperty(quitButton);
        help = new ButtonProperty(helpButton);

        anchor.requestFocus();

        anchor.setOnMouseMoved(e -> buttonVBox.setMouseTransparent(false));

        anchor.addEventHandler(KeyEvent.KEY_PRESSED, e -> {
            if (e.getCode() == KeyCode.UP || e.getCode() == KeyCode.DOWN){
                buttonVBox.setMouseTransparent(true);
            }
            if (newGameButton.isFocused() && e.getCode() == KeyCode.ENTER){
                newGamePressed();
                e.consume();
            }
            if (loadGameButton.isFocused() && e.getCode() == KeyCode.ENTER){
                loadGamePressed();
                e.consume();
            }
            if (levelEditorButton.isFocused() && e.getCode() == KeyCode.ENTER){
                levelEditorButtonPressed();
                e.consume();
            }
            if (leaderboardButton.isFocused() && e.getCode() == KeyCode.ENTER){
                leaderboardButtonPressed();
               e.consume();
            }
            if (quitButton.isFocused() && e.getCode() == KeyCode.ENTER){
                quitButtonPressed();
                e.consume();
            }
            if (helpButton.isFocused() && e.getCode() == KeyCode.ENTER){
                helpButtonPressed();
                e.consume();
            }
        });

        menuAnimation();
    }

    /**
     * Method for changing button animation when the button is in focus
     * or when the the user hover over a button with the mouse.
     */
    private void menuAnimation() {

        animationTimer = new AnimationTimer(){
            public void handle(long now){

                if((newGameButton.isHover() || newGameButton.isFocused()) && newGame.isNotHovering()){
                    newGame.setButtonImage("/deepDown/resource/images/startMenu/NewGame.gif");
                    newGameButton = newGame.getButton();
                    newGame.setHovering(true);
                    newGameButton.requestFocus();

                }else if (!newGameButton.isHover() && !newGameButton.isFocused()){
                    newGame.setButtonImage("/deepDown/resource/images/startMenu/NewGame.png");
                    newGameButton = newGame.getButton();
                    newGame.setHovering(false);
                }

                if((loadGameButton.isHover() || loadGameButton.isFocused()) && loadGame.isNotHovering()){
                    loadGame.setButtonImage("/deepDown/resource/images/startMenu/LoadGame.gif");
                    loadGameButton = loadGame.getButton();
                    loadGame.setHovering(true);
                    loadGameButton.requestFocus();
                }else if (!loadGameButton.isHover() && !loadGameButton.isFocused()){
                    loadGame.setButtonImage("/deepDown/resource/images/startMenu/LoadGame.png");
                    loadGameButton = loadGame.getButton();
                    loadGame.setHovering(false);
                }

                if((levelEditorButton.isHover() || levelEditorButton.isFocused()) && levelEditor.isNotHovering()){
                    levelEditor.setButtonImage("/deepDown/resource/images/startMenu/LevelEditor.gif");
                    levelEditorButton = levelEditor.getButton();
                    levelEditor.setHovering(true);
                    levelEditorButton.requestFocus();
                }else if (!levelEditorButton.isHover() && !levelEditorButton.isFocused()){
                    levelEditor.setButtonImage("/deepDown/resource/images/startMenu/LevelEditor.png");
                    levelEditorButton = levelEditor.getButton();
                    levelEditor.setHovering(false);
                }

                if((leaderboardButton.isHover() || leaderboardButton.isFocused()) && leaderboard.isNotHovering()){
                    leaderboard.setButtonImage("/deepDown/resource/images/startMenu/Leaderboard.gif");
                    leaderboardButton = leaderboard.getButton();
                    leaderboard.setHovering(true);
                    leaderboardButton.requestFocus();

                }else if (!leaderboardButton.isHover() && !leaderboardButton.isFocused()){
                    leaderboard.setButtonImage("/deepDown/resource/images/startMenu/Leaderboard.png");
                    leaderboardButton = leaderboard.getButton();
                    leaderboard.setHovering(false);
                }

                if((quitButton.isHover() || quitButton.isFocused()) && quit.isNotHovering()){
                    quit.setButtonImage("/deepDown/resource/images/startMenu/QuitGame.gif");
                    quitButton = quit.getButton();
                    quit.setHovering(true);
                    quitButton.requestFocus();

                }else if (!quitButton.isHover() && !quitButton.isFocused()){
                    quit.setButtonImage("/deepDown/resource/images/startMenu/QuitGame.png");
                    quitButton = quit.getButton();
                    quit.setHovering(false);
                }

                if((helpButton.isHover() || helpButton.isFocused()) && help.isNotHovering()){
                    help.setButtonImage("/deepDown/resource/images/startMenu/Help.gif");
                    helpButton = help.getButton();
                    help.setHovering(true);
                    helpButton.requestFocus();

                }else if (!helpButton.isHover() && !helpButton.isFocused()){
                    help.setButtonImage("/deepDown/resource/images/startMenu/Help.png");
                    helpButton = help.getButton();
                    help.setHovering(false);
                }
            }
        };
        animationTimer.start();
    }

    /**
     * The action when the "New Game" button is pressed.
     * Method for loading a new game.
     */
    public void newGamePressed(){
        animationTimer.stop();
        Loader.loadLevel(anchor, 1, 0, 3);
    }

    /**
     * The action performed when the "Load Game" button is pressed.
     * Method for loading a previously saved game with the player's
     * stored score and lives.
     */
    public void loadGamePressed(){
        animationTimer.stop();
        try {
            FileInputStream fis = new FileInputStream("Files/save");
            DataInputStream dis = new DataInputStream(fis);
            int levelProgression = dis.readInt();
            int totScore = dis.readInt();
            int avatarLives = dis.readInt();

            Loader.loadLevel(anchor, levelProgression, totScore, avatarLives);
        } catch (IOException e ){
            Alerts.noSaveFile();
            e.printStackTrace();
        }
    }

    /**
     * The action performed when the "Leaderboard" button is pressed.
     * Method for loading the leaderboard screen where the player
     * can check the top scores achieved.
     */
    public void leaderboardButtonPressed(){
        animationTimer.stop();
        Loader.loadLeaderboard(anchor);
    }

    /**
     * The action performed when the "Level Editor" button is pressed.
     * Method for loading the Level editor where the player
     * can make their own custom levels.
     */
    public void levelEditorButtonPressed(){
        animationTimer.stop();
        Loader.loadLevelEditor(anchor);
    }

    /**
     * The action performed when the "Quit Game" button is pressed.
     * Stops and closes the javaFX application.
     */
    public void quitButtonPressed(){
        if(Alerts.quitGameConfirmation()) {
            animationTimer.stop();
            Platform.exit();
            System.exit(0);
        }
    }

    /**
     * The action performed when the "Help?" button is pressed.
     * Loads the help page where the player gets instructed on what to do in the game
     */

    public void helpButtonPressed(){
        animationTimer.stop();
        Loader.loadHelp(anchor);
    }
}
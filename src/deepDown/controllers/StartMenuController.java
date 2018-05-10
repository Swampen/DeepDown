package deepDown.controllers;

import deepDown.Alerts;
import deepDown.Loader;
import deepDown.MenuAnimation;
import javafx.animation.AnimationTimer;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;


import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.logging.Level;

/**
 * @author Michael Mobæk Thoresen and Ole-Martin Heggen
 */
public class StartMenuController {

    @FXML private AnchorPane anchor;
    @FXML private Button newGameButton;
    @FXML private Button loadGameButton;
    @FXML private Button leaderboardButton;
    @FXML private Button quitButton;
    @FXML private Button helpButton;
    @FXML private Button levelEditorButton;

    private MenuAnimation newGame;
    private MenuAnimation loadGame;
    private MenuAnimation levelEditor;
    private MenuAnimation leaderboard;
    private MenuAnimation quit;
    private MenuAnimation help;

    private AnimationTimer animationTimer;

    /**
     * Method which runs when the fxml is loaded.
     * Loads the initial resources for the start menu, enables
     * the possibility to use the Enter key on buttons and starts {@code AnimationTimer}
     */
    @FXML
    public void initialize() {
        newGame = new MenuAnimation(newGameButton);
        loadGame = new MenuAnimation(loadGameButton);
        levelEditor = new MenuAnimation(levelEditorButton);
        leaderboard = new MenuAnimation(leaderboardButton);
        quit = new MenuAnimation(quitButton);
        help = new MenuAnimation(helpButton);

        anchor.requestFocus();

        anchor.addEventHandler(KeyEvent.KEY_PRESSED, e -> {
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
                    Image image = new Image(getClass().getResourceAsStream("/deepDown/resource/images/NewGame.gif"));
                    newGame.setButtonImage(image);
                    newGameButton = newGame.getButton();
                    newGame.setHovering(true);
                    newGameButton.requestFocus();

                }else if (!newGameButton.isHover() && !newGameButton.isFocused()){
                    Image image = new Image(getClass().getResourceAsStream("/deepDown/resource/images/NewGame.png"));
                    newGame.setButtonImage(image);
                    newGameButton = newGame.getButton();
                    newGame.setHovering(false);
                }

                if((loadGameButton.isHover() || loadGameButton.isFocused()) && loadGame.isNotHovering()){
                    Image image = new Image(getClass().getResourceAsStream("/deepDown/resource/images/LoadGame.gif"));
                    loadGame.setButtonImage(image);
                    loadGameButton = loadGame.getButton();
                    loadGame.setHovering(true);
                    loadGameButton.requestFocus();
                }else if (!loadGameButton.isHover() && !loadGameButton.isFocused()){
                    Image image = new Image(getClass().getResourceAsStream("/deepDown/resource/images/LoadGame.png"));
                    loadGame.setButtonImage(image);
                    loadGameButton = loadGame.getButton();
                    loadGame.setHovering(false);
                }

                if((levelEditorButton.isHover() || levelEditorButton.isFocused()) && levelEditor.isNotHovering()){
                    Image image = new Image(getClass().getResourceAsStream("/deepDown/resource/images/LevelEditor.gif"));
                    levelEditor.setButtonImage(image);
                    levelEditorButton = levelEditor.getButton();
                    levelEditor.setHovering(true);
                    levelEditorButton.requestFocus();
                }else if (!levelEditorButton.isHover() && !levelEditorButton.isFocused()){
                    Image image = new Image(getClass().getResourceAsStream("/deepDown/resource/images/LevelEditor.png"));
                    levelEditor.setButtonImage(image);
                    levelEditorButton = levelEditor.getButton();
                    levelEditor.setHovering(false);
                }

                if((leaderboardButton.isHover() || leaderboardButton.isFocused()) && leaderboard.isNotHovering()){
                    Image image = new Image(getClass().getResourceAsStream("/deepDown/resource/images/Leaderboard.gif"));
                    leaderboard.setButtonImage(image);
                    leaderboardButton = leaderboard.getButton();
                    leaderboard.setHovering(true);
                    leaderboardButton.requestFocus();

                }else if (!leaderboardButton.isHover() && !leaderboardButton.isFocused()){
                    Image image = new Image(getClass().getResourceAsStream("/deepDown/resource/images/Leaderboard.png"));
                    leaderboard.setButtonImage(image);
                    leaderboardButton = leaderboard.getButton();
                    leaderboard.setHovering(false);
                }

                if((quitButton.isHover() || quitButton.isFocused()) && quit.isNotHovering()){
                    Image image = new Image(getClass().getResourceAsStream("/deepDown/resource/images/QuitGame.gif"));
                    quit.setButtonImage(image);
                    quitButton = quit.getButton();
                    quit.setHovering(true);
                    quitButton.requestFocus();

                }else if (!quitButton.isHover() && !quitButton.isFocused()){
                    Image image = new Image(getClass().getResourceAsStream("/deepDown/resource/images/QuitGame.png"));
                    quit.setButtonImage(image);
                    quitButton = quit.getButton();
                    quit.setHovering(false);
                }

                if((helpButton.isHover() || helpButton.isFocused()) && help.isNotHovering()){
                    Image image = new Image(getClass().getResourceAsStream("/deepDown/resource/images/Help.gif"));
                    help.setButtonImage(image);
                    helpButton = help.getButton();
                    help.setHovering(true);
                    helpButton.requestFocus();

                }else if (!helpButton.isHover() && !helpButton.isFocused()){
                    Image image = new Image(getClass().getResourceAsStream("/deepDown/resource/images/Help.png"));
                    help.setButtonImage(image);
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
     * Method for loading a previously saved game with the players
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

    public void levelEditorButtonPressed(){
        animationTimer.stop();
        Loader.loadLevelEditor(anchor);
    }

    /**
     * The action performed when the "Quit Game" button is pressed.
     * Stops and closes the javaFX application.
     */
    public void quitButtonPressed(){
        animationTimer.stop();
        Platform.exit();
        System.exit(0);
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
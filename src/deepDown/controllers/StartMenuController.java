package deepDown.controllers;

import deepDown.MenuAnimation;
import javafx.animation.AnimationTimer;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;


import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;

public class StartMenuController {

    @FXML private AnchorPane anchor;
    @FXML private Button newGameButton;
    @FXML private Button loadGameButton;
    @FXML private Button leaderboardButton;
    @FXML private Button quitGameButton;
    @FXML private Button helpButton;
    @FXML private Button levelSelect;

    private boolean newGameHover = false;
    private boolean loadGameHover = false;
    private boolean leaderboardHover = false;
    private boolean quitGameHover = false;
    private boolean helpHover = false;

    private MenuAnimation newGame;
    private MenuAnimation loadGame;
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
        leaderboard = new MenuAnimation(leaderboardButton);
        quit = new MenuAnimation(quitGameButton);
        help = new MenuAnimation(helpButton);

        anchor.requestFocus();

        anchor.addEventHandler(KeyEvent.KEY_PRESSED, e -> {
            if (newGameButton.isFocused() && e.getCode() == KeyCode.ENTER){
                newGameClicked();
                e.consume();
            }
            if (loadGameButton.isFocused() && e.getCode() == KeyCode.ENTER){
                loadGameClicked();
                e.consume();
            }
            if (leaderboardButton.isFocused() && e.getCode() == KeyCode.ENTER){
               leaderboardClicked();
               e.consume();
            }
            if (quitGameButton.isFocused() && e.getCode() == KeyCode.ENTER){
                quitClicked();
                e.consume();
            }
            if (helpButton.isFocused() && e.getCode() == KeyCode.ENTER){
                helpButtonClicked();
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

                if((newGameButton.isHover() || newGameButton.isFocused()) && !newGame.isHovering()){
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

                if((loadGameButton.isHover() || loadGameButton.isFocused()) && !loadGame.isHovering()){
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

                if((leaderboardButton.isHover() || leaderboardButton.isFocused()) && !leaderboard.isHovering()){
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

                if((quitGameButton.isHover() || quitGameButton.isFocused()) && !quit.isHovering()){
                    Image image = new Image(getClass().getResourceAsStream("/deepDown/resource/images/QuitGame.gif"));
                    quit.setButtonImage(image);
                    quitGameButton = quit.getButton();
                    quit.setHovering(true);
                    quitGameButton.requestFocus();

                }else if (!quitGameButton.isHover() && !quitGameButton.isFocused()){
                    Image image = new Image(getClass().getResourceAsStream("/deepDown/resource/images/QuitGame.png"));
                    quit.setButtonImage(image);
                    quitGameButton = quit.getButton();
                    quit.setHovering(false);
                }

                if((helpButton.isHover() || helpButton.isFocused()) && !help.isHovering()){
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
     * Loads the FXML for the first level with score of 0 and 3 lives.
     */
    public void newGameClicked(){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/deepDown/resource/FXML/level.fxml"));
            LevelController level = new LevelController(1, 0, 3);
            loader.setController(level);
            Parent root = loader.load();
            anchor.getChildren().setAll(root);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * The action when the "Load Game" button is pressed.
     * Loads the FXML for the level that the player saved, with the
     * stored score and lives from the save file.
     */
    public void loadGameClicked(){
        try {
            FileInputStream fis = new FileInputStream("Files/save");
            DataInputStream dis = new DataInputStream(fis);
            int i = dis.readInt();
            int ts = dis.readInt();
            int l = dis.readInt();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/deepDown/resource/FXML/level.fxml"));
            LevelController level = new LevelController(i, ts, l);
            loader.setController(level);
            Parent root = loader.load();
            anchor.getChildren().setAll(root);
        } catch (IOException e ){
            Alert a = new Alert(Alert.AlertType.ERROR, "Save file missing or corrputed");
            a.showAndWait();
        }
    }

    /**
     * The action when the "Leaderboard" button is pressed.
     * Loads the FXML for the leaderboard where the player
     * can check the top scores achieved.
     */
    public void leaderboardClicked(){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/deepDown/resource/FXML/leaderboard.fxml"));
            LeaderboardController controller = new LeaderboardController();
            loader.setController(controller);
            Parent root = loader.load();
            anchor.getChildren().setAll(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void levelSelectClicked(){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/deepDown/resource/FXML/levelSelect.fxml"));
            LevelSelectController levelSelect = new LevelSelectController();
            loader.setController(levelSelect);
            Parent root = loader.load();
            anchor.getChildren().setAll(root);
        }  catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * The action when the "Quit Game" button is pressed.
     * Stops and closes the javaFX application.
     */
    public void quitClicked(){
        animationTimer.stop();
        Platform.exit();
        System.exit(0);
    }

    /**
     * The action when the "Help?" button is pressed.
     * Loads the FXML for the help screen for instructions on how to play the game.
     */

    public void helpButtonClicked(){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/deepDown/resource/FXML/help.fxml"));
            HelpController help = new HelpController();
            loader.setController(help);
            Parent root = loader.load();
            anchor.getChildren().setAll(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
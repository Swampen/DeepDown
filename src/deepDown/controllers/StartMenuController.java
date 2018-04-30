package deepDown.controllers;

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

    private boolean newGameHover = false;
    private boolean loadGameHover = false;
    private boolean leaderboardHover = false;
    private boolean quitGameHover = false;
    private boolean helpHover = false;

    private AnimationTimer animationTimer;

    /**
     * Loads the initial resources for the start menu and starts the animationTimer
     */
    @FXML
    public void initialize() {
        anchor.requestFocus();

        anchor.addEventHandler(KeyEvent.KEY_PRESSED, e -> {
            if (newGameButton.isFocused() && e.getCode() == KeyCode.ENTER){
                try {
                    newGameClicked();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                e.consume();
            }
            if (loadGameButton.isFocused() && e.getCode() == KeyCode.ENTER){
                try {
                    loadGameClicked();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                e.consume();
            }
            if (leaderboardButton.isFocused() && e.getCode() == KeyCode.ENTER){
                try {
                    leaderboardClicked();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                e.consume();
            }
            if (quitGameButton.isFocused() && e.getCode() == KeyCode.ENTER){
                quitClicked();
                e.consume();
            }
            if (helpButton.isFocused() && e.getCode() == KeyCode.ENTER){
                try {
                    helpButtonClicked();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                e.consume();
            }
        });

        menuAnimation();
    }

    /**
     * Method for changing button animation when the button is in focus
     * or when the the user hover over a button with the mouse
     */
    private void menuAnimation() {
        animationTimer = new AnimationTimer(){
            public void handle(long now){

                if((newGameButton.isHover() || newGameButton.isFocused()) && !newGameHover ){
                    Image image = new Image(getClass().getResourceAsStream("/deepDown/resource/images/NewGame.gif"));
                    MenuAnimation newGame = new MenuAnimation(image, newGameButton, true);
                    newGameButton = newGame.setButtonImage();
                    newGameHover = newGame.isHovering();
                    newGameButton.requestFocus();

                }else if (!newGameButton.isHover() && !newGameButton.isFocused()){
                    Image image = new Image(getClass().getResourceAsStream("/deepDown/resource/images/NewGame.png"));
                    MenuAnimation newGame = new MenuAnimation(image, newGameButton, false);
                    newGameButton = newGame.setButtonImage();
                    newGameHover = newGame.isHovering();
                }

                if((loadGameButton.isHover() || loadGameButton.isFocused()) && !loadGameHover){
                    Image image = new Image(getClass().getResourceAsStream("/deepDown/resource/images/LoadGame.gif"));
                    MenuAnimation loadGame = new MenuAnimation(image, loadGameButton, true);
                    loadGameButton = loadGame.setButtonImage();
                    loadGameHover = loadGame.isHovering();
                    loadGameButton.requestFocus();

                }else if (!loadGameButton.isHover() && !loadGameButton.isFocused()){
                    Image image = new Image(getClass().getResourceAsStream("/deepDown/resource/images/LoadGame.png"));
                    MenuAnimation loadGame = new MenuAnimation(image, loadGameButton, false);
                    loadGameButton = loadGame.setButtonImage();
                    loadGameHover = loadGame.isHovering();
                }

                if((leaderboardButton.isHover() || leaderboardButton.isFocused()) && !leaderboardHover){
                    Image image = new Image(getClass().getResourceAsStream("/deepDown/resource/images/Leaderboard.gif"));
                    MenuAnimation leaderboard = new MenuAnimation(image, leaderboardButton, true);
                    leaderboardButton = leaderboard.setButtonImage();
                    leaderboardHover = leaderboard.isHovering();
                    leaderboardButton.requestFocus();

                }else if (!leaderboardButton.isHover() && !leaderboardButton.isFocused()){
                    Image image = new Image(getClass().getResourceAsStream("/deepDown/resource/images/Leaderboard.png"));
                    MenuAnimation leaderboard = new MenuAnimation(image, leaderboardButton, false);
                    leaderboardButton = leaderboard.setButtonImage();
                    leaderboardHover = leaderboard.isHovering();
                }

                if((quitGameButton.isHover() || quitGameButton.isFocused()) && !quitGameHover){
                    Image image = new Image(getClass().getResourceAsStream("/deepDown/resource/images/QuitGame.gif"));
                    MenuAnimation quitGame = new MenuAnimation(image, quitGameButton, true);
                    quitGameButton = quitGame.setButtonImage();
                    quitGameHover = quitGame.isHovering();
                    quitGameButton.requestFocus();

                }else if (!quitGameButton.isHover() && !quitGameButton.isFocused()){
                    Image image = new Image(getClass().getResourceAsStream("/deepDown/resource/images/QuitGame.png"));
                    MenuAnimation quitGame = new MenuAnimation(image, quitGameButton, false);
                    quitGameButton = quitGame.setButtonImage();
                    quitGameHover = quitGame.isHovering();
                }

                if((helpButton.isHover() || helpButton.isFocused()) && !helpHover){
                    Image image = new Image(getClass().getResourceAsStream("/deepDown/resource/images/Help.gif"));
                    MenuAnimation help = new MenuAnimation(image, helpButton, true);
                    helpButton = help.setButtonImage();
                    helpHover = help.isHovering();
                    helpButton.requestFocus();

                }else if (!helpButton.isHover() && !helpButton.isFocused()){
                    Image image = new Image(getClass().getResourceAsStream("/deepDown/resource/images/Help.png"));
                    MenuAnimation help = new MenuAnimation(image, helpButton, false);
                    helpButton = help.setButtonImage();
                    helpHover = help.isHovering();
                }
            }
        };
        animationTimer.start();
    }

    /**
     * Opens the first level with 0 score and 3 lives when the New Game button is pressed
     * @throws IOException throws an IOException when a file is missing
     */
    public void newGameClicked() throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/deepDown/resource/FXML/level.fxml"));
        LevelController level = new LevelController(1, 0, 3);
        loader.setController(level);
        Parent root = loader.load();
        anchor.getChildren().setAll(root);
    }

    /**
     * Loads the level the player has come to, setting his score and lives to what it was before saving
     * from an external file
     * @throws IOException throws an IOException when a file is missing
     */
    public void loadGameClicked() throws IOException{
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
    }

    //Opens up the level select for testing of levels
    public void helpButtonClicked() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/deepDown/resource/FXML/levelSelect.fxml"));
        LevelSelectController levelSelect = new LevelSelectController();
        loader.setController(levelSelect);
        Parent root = loader.load();
        anchor.getChildren().setAll(root);
    }

    /**
     * Opens the leaderboard where the player can check top scores achieved
     * @throws IOException throws an IOException when a file is missing
     */
    public void leaderboardClicked() throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/deepDown/resource/FXML/leaderboard.fxml"));
        LeaderboardController controller = new LeaderboardController();
        loader.setController(controller);
        Parent root = loader.load();
        anchor.getChildren().setAll(root);
    }

    /**
     * quits the application
     */
    public void quitClicked(){
        animationTimer.stop();
        Platform.exit();
        System.exit(0);
    }
}
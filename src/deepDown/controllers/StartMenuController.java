package deepDown.controllers;

import deepDown.MenuAnimation;
import javafx.animation.AnimationTimer;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;

public class StartMenuController {

    @FXML
    private AnchorPane anchor;
    @FXML
    private Button newGameButton;
    @FXML
    private Button loadGameButton;
    @FXML
    private Button leaderboardButton;
    @FXML
    private Button quitGameButton;

    private boolean newGameHover = false;
    private boolean loadGameHover = false;
    private boolean leaderboardHover = false;
    private boolean quitGameHover = false;

    private AnimationTimer animationTimer;

    /**
     * Loads the initial resources for the start menu and starts the animationTimer
     */
    @FXML
    public void initialize() {
        animationTimer = new AnimationTimer(){
                public void handle(long now){

                    if(newGameButton.isHover() && !newGameHover){
                        Image image = new Image(getClass().getResourceAsStream("/deepDown/resource/images/NewGame.gif"));
                        MenuAnimation newGame = new MenuAnimation(image, newGameButton, true);
                        newGameButton = newGame.setButtonImage();
                        newGameHover = newGame.isHovering();

                    }else if (!newGameButton.isHover()){
                        Image image = new Image(getClass().getResourceAsStream("/deepDown/resource/images/NewGame.png"));
                        MenuAnimation newGame = new MenuAnimation(image, newGameButton, false);
                        newGameButton = newGame.setButtonImage();
                        newGameHover = newGame.isHovering();
                    }

                    if(loadGameButton.isHover() && !loadGameHover){
                        Image image = new Image(getClass().getResourceAsStream("/deepDown/resource/images/LoadGame.gif"));
                        MenuAnimation loadGame = new MenuAnimation(image, loadGameButton, true);
                        loadGameButton = loadGame.setButtonImage();
                        loadGameHover = loadGame.isHovering();

                    }else if (!loadGameButton.isHover()){
                        Image image = new Image(getClass().getResourceAsStream("/deepDown/resource/images/LoadGame.png"));
                        MenuAnimation loadGame = new MenuAnimation(image, loadGameButton, false);
                        loadGameButton = loadGame.setButtonImage();
                        loadGameHover = loadGame.isHovering();
                    }

                    if(leaderboardButton.isHover() && !leaderboardHover){
                        Image image = new Image(getClass().getResourceAsStream("/deepDown/resource/images/Leaderboard.gif"));
                        MenuAnimation leaderboard = new MenuAnimation(image, leaderboardButton, true);
                        leaderboardButton = leaderboard.setButtonImage();
                        leaderboardHover = leaderboard.isHovering();

                    }else if (!leaderboardButton.isHover()){
                        Image image = new Image(getClass().getResourceAsStream("/deepDown/resource/images/Leaderboard.png"));
                        MenuAnimation leaderboard = new MenuAnimation(image, leaderboardButton, false);
                        leaderboardButton = leaderboard.setButtonImage();
                        leaderboardHover = leaderboard.isHovering();
                    }

                    if(quitGameButton.isHover() && !quitGameHover){
                        Image image = new Image(getClass().getResourceAsStream("/deepDown/resource/images/QuitGame.gif"));
                        MenuAnimation quitGame = new MenuAnimation(image, quitGameButton, true);
                        quitGameButton = quitGame.setButtonImage();
                        quitGameHover = quitGame.isHovering();

                    }else if (!quitGameButton.isHover()){
                        Image image = new Image(getClass().getResourceAsStream("/deepDown/resource/images/QuitGame.png"));
                        MenuAnimation quitGame = new MenuAnimation(image, quitGameButton, false);
                        quitGameButton = quitGame.setButtonImage();
                        quitGameHover = quitGame.isHovering();
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
        FileInputStream fis = new FileInputStream("Files/save.txt");
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
    public void trainingModeClicked() throws IOException {
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
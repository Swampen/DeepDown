package deepDown.controllers;

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
    private Image image;
    private boolean newGameButtonHover = false;

    private AnimationTimer animationTimer;

    /**
     * Loads the initial resources for the start menu and starts the animationTimer
     */
    @FXML
    public void initialize() {

        image = new Image(getClass().getResourceAsStream("/deepDown/resource/images/NewGame.png"));
        newGameButton.setGraphic(new ImageView(image));

        animationTimer = new AnimationTimer(){
                public void handle(long now){

                    if(newGameButton.isHover() && !newGameButtonHover){
                        image = new Image(getClass().getResourceAsStream("/deepDown/resource/images/NewGame.gif"));

                        newGameButton.setGraphic(new ImageView(image));
                        newGameButtonHover = true;
                    }else if (!newGameButton.isHover()){
                        image = new Image(getClass().getResourceAsStream("/deepDown/resource/images/NewGame.png"));

                        newGameButton.setGraphic(new ImageView(image));
                        newGameButtonHover = false;
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
     * Opens the leaderboards where the player can check top scores achieved
     * @throws IOException throws an IOException when a file is missing
     */
    public void leaderboardsClicked() throws IOException{
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
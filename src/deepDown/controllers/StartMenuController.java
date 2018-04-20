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
import deepDown.Main;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;

public class StartMenuController {

    @FXML
    protected AnchorPane anchor;
    @FXML
    private Button newGameButton;
    private Main main;
    private Image image;
    private boolean newGameButtonHover = false;

    private AnimationTimer animationTimer;

    @FXML
    public void initialize() {
        main = new Main();

        image = new Image(getClass().getResourceAsStream("/deepDown/resource/test.png"));
        newGameButton.setGraphic(new ImageView(image));

        animationTimer = new AnimationTimer(){
                public void handle(long now){

                    if(newGameButton.isHover() && !newGameButtonHover){
                        image = new Image(getClass().getResourceAsStream("/deepDown/resource/Menu.gif"));

                        newGameButton.setGraphic(new ImageView(image));
                        newGameButtonHover = true;
                    }else if (!newGameButton.isHover()){
                        image = new Image(getClass().getResourceAsStream("/deepDown/resource/test.png"));

                        newGameButton.setGraphic(new ImageView(image));
                        newGameButtonHover = false;
                    }
                }
        };
        animationTimer.start();
    }

    //Opens the first level when New game is clicked
    public void newGameClicked() throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/deepDown/resource/FXML/level.fxml"));
        LevelController level = new LevelController(1, 0, 3);
        loader.setController(level);
        Parent root = main.getRoot();
        root = loader.load();
        anchor.getChildren().setAll(root);
    }

    //Loads the level the player has come to before quitting play
    public void loadGameClicked() throws IOException{
        FileInputStream fis = new FileInputStream("Files/save.txt");
        DataInputStream dis = new DataInputStream(fis);
        int i = dis.readInt();
        int ts = dis.readInt();
        int l = dis.readInt();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/deepDown/resource/FXML/level.fxml"));
        LevelController level = new LevelController(i, ts, l);
        loader.setController(level);
        Parent root = main.getRoot();
        root = loader.load();
        anchor.getChildren().setAll(root);
    }

    //Opens up the level select for testing of levels
    public void trainingModeClicked() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/deepDown/resource/FXML/levelSelect.fxml"));
        LevelSelectController levelSelect = new LevelSelectController();
        loader.setController(levelSelect);
        Parent root = main.getRoot();
        root = loader.load();
        anchor.getChildren().setAll(root);
    }

    //Opens the leaderboards to see the top scores
    public void leaderboardsClicked() throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/deepDown/resource/FXML/leaderboard.fxml"));
        LeaderboardController controller = new LeaderboardController();
        loader.setController(controller);
        Parent root = main.getRoot();
        root = loader.load();
        anchor.getChildren().setAll(root);
    }

    //Quits the game
    public void quitClicked(){
        animationTimer.stop();
        Platform.exit();
        System.exit(0);
    }
}
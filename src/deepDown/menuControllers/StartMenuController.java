package deepDown.menuControllers;

import deepDown.LevelController;
import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import deepDown.Main;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class StartMenuController {

    @FXML
    protected AnchorPane anchor;
    @FXML private Button newGameButton;
    private Main main;

    @FXML
    public void initialize() {
        main = new Main();

        Image image = null;
        try {
            image = new Image(new FileInputStream("src/deepDown/resource/test.png"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        newGameButton.setGraphic(new ImageView(image));

        new AnimationTimer(){
                public void handle(long now){

                    if(newGameButton.isHover()){
                        Image image = null;
                        try {
                            image = new Image(new FileInputStream("src/deepDown/resource/test1.png"));
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        }
                        newGameButton.setGraphic(new ImageView(image));
                    }else {
                        Image image = null;
                        try {
                            image = new Image(new FileInputStream("src/deepDown/resource/test.png"));
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        }
                        newGameButton.setGraphic(new ImageView(image));
                    }
                }
        }.start();
    }

    //Opens the first level when New game is clicked
    public void newGameClicked() throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/deepDown/resource/FXML/level.fxml"));
        LevelController level = new LevelController(1);
        loader.setController(level);
        Parent root = main.getRoot();
        root = loader.load();
        anchor.getChildren().setAll(root);
    }

    //Loads the level the player has come to before quitting play
    public void loadGameClicked(){

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
    public void leaderboardsClicked() {

    }

    //Quits the game
    public void quitClicked(){
        System.exit(0);
    }
}
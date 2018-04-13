package deepDown.menuControllers;

import deepDown.LevelController;
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

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class StartMenuController {

    @FXML
    protected AnchorPane anchor;
    @FXML
    private Button newGameButton;
    private Main main;

    @FXML
    public void initialize() {
        main = new Main();

        Image image = new Image(getClass().getResourceAsStream("/deepDown/resource/test.png"));
        newGameButton.setGraphic(new ImageView(image));

        new AnimationTimer(){
                public void handle(long now){

                    if(newGameButton.isHover()){
                        Image image = new Image(getClass().getResourceAsStream("/deepDown/resource/test1.png"));

                        newGameButton.setGraphic(new ImageView(image));
                    }else {
                        Image image = new Image(getClass().getResourceAsStream("/deepDown/resource/test.png"));

                        newGameButton.setGraphic(new ImageView(image));
                    }
                }
        }.start();
    }

    public void newGameClicked() throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/deepDown/resource/FXML/level.fxml"));
        LevelController level = new LevelController(1);
        loader.setController(level);
        Parent root = main.getRoot();
        root = loader.load();
        anchor.getChildren().setAll(root);
    }

    public void loadGameClicked(){

    }

    public void trainingModeClicked() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/deepDown/resource/FXML/levelSelect.fxml"));
        LevelSelectController levelSelect = new LevelSelectController();
        loader.setController(levelSelect);
        Parent root = main.getRoot();
        root = loader.load();
        anchor.getChildren().setAll(root);
    }

    public void leaderboardsClicked() throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/deepDown/resource/FXML/leaderboard.fxml"));
        LeaderboardController controller = new LeaderboardController();
        loader.setController(controller);
        Parent root = main.getRoot();
        root = loader.load();
        anchor.getChildren().setAll(root);
    }

    public void quitClicked(){
        Platform.exit();
    }
}
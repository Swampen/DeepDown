package deepDown.menuControllers;

import deepDown.Main;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.awt.*;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;

public class PauseMenuController {

    @FXML
    private AnchorPane anchor;
    private Main main;
    private Stage stage;

    int levelProgression;
    int totScore;
    int avatarLives;


    public PauseMenuController(Stage stage, int levelProgression, int totScore, int avatarLives){
        this.levelProgression = levelProgression;
        this.totScore = totScore;
        this.avatarLives = avatarLives;
    }

    public void resumeGamePressed(){
        stage.close();
    }

    public void backToMenuPressed(){

    }

    public void saveGamePressed() throws Exception{
        File f = new File("save.txt");
        FileOutputStream fos = new FileOutputStream(f);
        DataOutputStream dos = new DataOutputStream(fos);
        dos.writeInt(levelProgression);
        dos.writeInt(totScore);
        dos.writeInt(avatarLives);

    }

    public void quitGamePressed(){
        Platform.exit();
        System.exit(0);
    }
}

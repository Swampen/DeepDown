package deepDown.controllers;

import deepDown.Main;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class PauseMenuController {

    private Main main = new Main();
    private Stage stage;

    private AnchorPane anchor;
    private int levelProgression;
    private int totScore;
    private int avatarLives;


    public PauseMenuController(Stage stage, AnchorPane anchor, int levelProgression, int totScore, int avatarLives){
        this.stage = stage;
        this.anchor = anchor;
        this.levelProgression = levelProgression;
        this.totScore = totScore;
        this.avatarLives = avatarLives;
    }

    public void resumeGamePressed(){
        stage.close();
    }

    public void backToMenuPressed() throws IOException{
        stage.close();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/deepDown/resource/FXML/startMenu.fxml"));
        StartMenuController startMenuController = new StartMenuController();
        loader.setController(startMenuController);
        Parent root = main.getRoot();
        root = loader.load();
        anchor.getChildren().setAll(root);
    }

    public void saveGamePressed() throws Exception{
        File f = new File("Files/save.txt");
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

package deepDown.menuControllers;

import deepDown.Main;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;

public class PauseMenuController {

    int levelProgression;
    int totScore;
    int avatarLives;

    public PauseMenuController(int levelProgression, int totScore, int avatarLives){
        this.levelProgression = levelProgression;
        this.totScore = totScore;
        this.avatarLives = avatarLives;
    }

    @FXML
    protected AnchorPane anchor;
    private Main main;

    public void resumeGamePressed(){

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

    public void quitGamePressed() {


    }
}

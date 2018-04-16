package deepDown.menuControllers;

import deepDown.Main;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.awt.*;

public class PauseMenuController {

    @FXML
    private AnchorPane anchor;
    private Main main;
    private Stage stage;

    public PauseMenuController(Stage stage){
        this.stage = stage;
    }

    public void resumeGamePressed(){
        stage.close();
    }

    public void backToMenuPressed(){

    }

    public void saveGamePressed(){

    }

    public void quitGamePressed(){
        Platform.exit();
        System.exit(0);
    }
}

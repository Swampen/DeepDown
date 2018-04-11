package deepDown.menuControllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;
import deepDown.LevelController;
import deepDown.Main;

import java.io.IOException;

public class LevelSelectController {

    @FXML
    protected AnchorPane anchor;
    private Main main;

    @FXML
    public void initialize() {
        main = new Main();
    }

    public void loadLevel(int level)throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/deepDown/resource/FXML/level.fxml"));
        LevelController controller = new LevelController(level);
        loader.setController(controller);
        Parent root = main.getRoot();
        root = loader.load();
        anchor.getChildren().setAll(root);
    }

    public void level1Selected()throws IOException{
        loadLevel(1);
    }

    public void level2Selected()throws IOException{
        loadLevel(2);
    }

    public void level3Selected()throws IOException{
        loadLevel(3);
    }
}
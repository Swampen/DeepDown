package deepDown;

import deepDown.menuControllers.LevelSelectController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class FxmlLoader {
    private String fxmlPath;

    public FxmlLoader(String fxmlPath){
        this.fxmlPath = fxmlPath;
    }

    public void loadFXML(AnchorPane anchor, Main main)throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource(this.fxmlPath));
        LevelSelectController levelSelect = new LevelSelectController();
        loader.setController(levelSelect);
        Parent root = main.getRoot();
        root = loader.load();
        anchor.getChildren().setAll(root);
    }
}

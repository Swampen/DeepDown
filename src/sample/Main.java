package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sample.menu.LevelSelectController;
import sample.menu.StartMenuController;

import java.io.IOException;
import java.net.URL;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    private Scene scene;
    private Parent root;

    @Override
    public void start(Stage stage) throws IOException{

        LevelSelectController levelSelect = new LevelSelectController();
        URL url = getClass().getResource("/sample/resource/levelSelect.fxml");
        FXMLLoader loader = new FXMLLoader(url);
        loader.setController(levelSelect);
        root = loader.load();

        scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Deep Down");
        stage.show();
    }

    public Parent getRoot(){
        return root;
    }
}

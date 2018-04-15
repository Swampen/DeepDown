package deepDown;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import deepDown.menuControllers.StartMenuController;

import java.io.IOException;
import java.net.URL;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    private Scene scene;
    private Parent root;
    private Stage stage = new Stage();

    @Override
    public void start(Stage stage) throws IOException{

        this.stage = stage;
        StartMenuController startMenu = new StartMenuController();
        URL url = getClass().getResource("/deepDown/resource/FXML/startMenu.fxml");
        FXMLLoader loader = new FXMLLoader(url);
        loader.setController(startMenu);
        root = loader.load();

        scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Deep Down");
        stage.show();
    }

    public Parent getRoot(){
        return root;
    }

    public Stage getStage(){
        return this.stage;
    }
}
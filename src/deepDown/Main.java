package deepDown;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import deepDown.controllers.StartMenuController;

import java.io.IOException;
import java.net.URL;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws IOException{

        StartMenuController startMenu = new StartMenuController();
        URL url = getClass().getResource("/deepDown/resource/FXML/startMenu.fxml");
        FXMLLoader loader = new FXMLLoader(url);
        loader.setController(startMenu);
        Parent root = loader.load();

        stage.setOnCloseRequest(event -> {
            Platform.exit();
            System.exit(0);
        });

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Deep Down");
        stage.show();
    }
}
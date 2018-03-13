package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URL;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    protected Scene scene;
    protected Parent root;

    @Override
    public void start(Stage stage) {
        Level1Controller en = new Level1Controller();
        try {
            URL url = getClass().getResource("levelSelect.fxml");
            root = FXMLLoader.load(url);

            scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("Deep Down");
            stage.show();
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }
}

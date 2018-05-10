package deepDown;

import deepDown.controllers.LevelEditorController;
import deepDown.controllers.PauseMenuController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class Loader {

    public static void loadLevelEditor(AnchorPane anchor){
        try {
            FXMLLoader loader = new FXMLLoader(Loader.class.getResource("/deepDown/resource/FXML/levelEditor.fxml"));
            LevelEditorController levelSelect = new LevelEditorController();
            loader.setController(levelSelect);
            Parent root = loader.load();
            anchor.getChildren().setAll(root);
        }  catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void loadLevelEditorPause(AnchorPane anchor, Stage stage){
        try {
            FXMLLoader loader = new FXMLLoader(Loader.class.getResource("/deepDown/resource/FXML/levelEditor.fxml"));
            LevelEditorController levelSelect = new LevelEditorController();
            loader.setController(levelSelect);
            Parent root = loader.load();
            stage.close();
            anchor.getChildren().setAll(root);
        }  catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void loadPauseMenu(AnchorPane anchor, int levelProgression, int totScore, int avatarLives){
        Rectangle r = new Rectangle(0, 0, 1280, 720);
        r.setFill(Color.rgb(0, 0, 0, 0.6));
        anchor.getChildren().add(r);
        try {
            Stage stage = new Stage();
            PauseMenuController controller = new PauseMenuController(stage, anchor, levelProgression, totScore, avatarLives);
            FXMLLoader loader = new FXMLLoader(Loader.class.getResource("/deepDown/resource/FXML/pauseMenu.fxml"));
            loader.setController(controller);
            Parent root = loader.load();

            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(new Scene(root));
            stage.showAndWait();
            anchor.getChildren().remove(r);
        } catch(IOException e){
            e.printStackTrace();
        }
    }

    public static void loadPauseMenuCustomLevel(AnchorPane anchor, int levelProgression, int totScore, int avatarLives){
        Rectangle r = new Rectangle(0, 0, 1280, 720);
        r.setFill(Color.rgb(0, 0, 0, 0.6));
        anchor.getChildren().add(r);
        try {
            Stage stage = new Stage();
            PauseMenuController controller = new PauseMenuController(stage, anchor, levelProgression, totScore, avatarLives);
            FXMLLoader loader = new FXMLLoader(Loader.class.getResource("/deepDown/resource/FXML/pauseMenuCustomLevel.fxml"));
            loader.setController(controller);
            Parent root = loader.load();

            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(new Scene(root));
            stage.showAndWait();
            anchor.getChildren().remove(r);
        } catch(IOException e){
            e.printStackTrace();
        }
    }
}

package deepDown;

import deepDown.controllers.*;
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

    /**
     * Loads FXML for the Level Editor from the main menu using an FXMLLoaser
     * @param anchor Which {@code AnchorPane} the scene should be attached to.
     */
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

    /**
     * Loads FXML for the Level Editor from the pause menu using an FXMLLoader
     * @param anchor Which {@code AnchorPane} the scene should be attached to.
     * @param stage The window for the pause menu
     */
    public static void loadLevelEditorFromPause(AnchorPane anchor, Stage stage){
        try {
            FXMLLoader loader = new FXMLLoader(Loader.class.getResource("/deepDown/resource/FXML/levelEditor.fxml"));
            LevelEditorController levelSelect = new LevelEditorController();
            loader.setController(levelSelect);
            Parent root = loader.load();
            anchor.getChildren().setAll(root);
            stage.close();
        }  catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Loads the main menu from the pause menu using an FXMLLoader
     * @param anchor Which {@code AnchorPane} the scene should be attached to.
     * @param stage The window for the pause menu
     */
    public static void loadStartMenuFromPause(AnchorPane anchor, Stage stage){
        try {
            FXMLLoader loader = new FXMLLoader(Loader.class.getResource("/deepDown/resource/FXML/startMenu.fxml"));
            StartMenuController levelSelect = new StartMenuController();
            loader.setController(levelSelect);
            Parent root = loader.load();
            anchor.getChildren().setAll(root);
            stage.close();
        }  catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Loads the start menu when you start the game using an FXMLLoader
     * @param anchor Which {@code AnchorPane} the scene should be attached to.
     */
    public static void loadStartMenu(AnchorPane anchor){
        try {
            FXMLLoader loader = new FXMLLoader(Loader.class.getResource("/deepDown/resource/FXML/startMenu.fxml"));
            StartMenuController levelSelect = new StartMenuController();
            loader.setController(levelSelect);
            Parent root = loader.load();
            anchor.getChildren().setAll(root);
        }  catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Loads the Pause menu when the player hits escape during a level using an FXMLLoader
     * @param anchor The main pane.
     * @param levelProgression Which level the player is at, used for saving.
     * @param totScore What score the player has, used for saving.
     * @param avatarLives How many lives the player has, used for saving.
     */
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

    /**
     * Loads the Pause menu when the player hits escape during a run of the custom level using an FXMLLoader.
     * @param anchor The main pane.
     * @param levelProgression Which level the player is at, used for saving.
     * @param totScore What score the player has, used for saving.
     * @param avatarLives How many lives the player has, used for saving.
     */
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

    /**
     * Loads the end screen, using an FXMLLoader, when the player runs out of lives or wins the game.
     * @param anchor The main pane.
     * @param totScore The total score of that game.
     * @param gameCompleted Whether the player has completed the game or not.
     */
    public static void loadEndScreen(AnchorPane anchor, int totScore, boolean gameCompleted){
        try {
            FXMLLoader loader = new FXMLLoader(Loader.class.getResource("/deepDown/resource/FXML/endScreen.fxml"));
            EndScreenController endScreen = new EndScreenController(totScore, gameCompleted);
            loader.setController(endScreen);
            Parent root = loader.load();
            anchor.getChildren().setAll(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Loads a level when the player hits new game, load game, plays their custom level or proceeds to the next level.
     * @param anchor The main pane.
     * @param levelProgression Which level it is supposed to load.
     * @param totScore Which Score it is supposed to give the player.
     * @param avatarLives What amount of lives the player is supposed to have.
     */
    public static void loadLevel(AnchorPane anchor, int levelProgression, int totScore, int avatarLives){
        try {
            FXMLLoader loader = new FXMLLoader(Loader.class.getResource("/deepDown/resource/FXML/level.fxml"));
            LevelController level = new LevelController(levelProgression, totScore, avatarLives);
            loader.setController(level);
            Parent root = loader.load();
            anchor.getChildren().setAll(root);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Loads the Leaderboard when press the button on the main menu.
     * @param anchor The main pane.
     */
    public static void loadLeaderboard(AnchorPane anchor){
        try {
            FXMLLoader loader = new FXMLLoader(Loader.class.getResource("/deepDown/resource/FXML/leaderboard.fxml"));
            LeaderboardController controller = new LeaderboardController();
            loader.setController(controller);
            Parent root = loader.load();
            anchor.getChildren().setAll(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Loads the help page when press the button on the main menu.
     * @param anchor The main pane.
     */
    public static void loadHelp(AnchorPane anchor){
        try {
            FXMLLoader loader = new FXMLLoader(Loader.class.getResource("/deepDown/resource/FXML/help.fxml"));
            HelpController help = new HelpController();
            loader.setController(help);
            Parent root = loader.load();
            anchor.getChildren().setAll(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}

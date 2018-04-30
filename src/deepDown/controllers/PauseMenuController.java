package deepDown.controllers;

import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class PauseMenuController {

    private final Stage stage;

    private final AnchorPane anchor;
    private int levelProgression;
    private int totScore;
    private int avatarLives;


    /**
     * Constructor for PauseMenuController
     * @param stage The specified stage 'Add More'
     * @param anchor The specified anchor 'Add more'
     * @param levelProgression What level is loaded which is used for saving
     * @param totScore the players total score which is used for saving
     * @param avatarLives the players life count which is used for saving
     */
    public PauseMenuController(Stage stage, AnchorPane anchor, int levelProgression, int totScore, int avatarLives){
        this.stage = stage;
        this.anchor = anchor;
        this.levelProgression = levelProgression;
        this.totScore = totScore;
        this.avatarLives = avatarLives;
    }

    /**
     * Exits the Pause menu and resumes the animationTimer when the Resume Game button is pressed
     */
    public void resumeGamePressed(){
        stage.close();
    }

    /**
     * Returns to the main menu
     * @throws IOException throws an IOException when a file is missing
     */
    public void backToMenuPressed() throws IOException{
        stage.close();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/deepDown/resource/FXML/startMenu.fxml"));
        StartMenuController startMenuController = new StartMenuController();
        loader.setController(startMenuController);
        Parent root = loader.load();
        anchor.getChildren().setAll(root);
    }

    /**
     * Saves the Avatar's level progress, score and life total to an external file
     * @throws IOException throws an IOException when a file is missing
     */
    public void saveGamePressed() throws IOException{
        File save = new File("Files/save");
        FileOutputStream fos = new FileOutputStream(save);
        DataOutputStream dos = new DataOutputStream(fos);
        dos.writeInt(levelProgression);
        dos.writeInt(totScore);
        dos.writeInt(avatarLives);

    }

    /**
     * Quit the application
     */
    public void quitGamePressed(){
        Platform.exit();
        System.exit(0);
    }
}

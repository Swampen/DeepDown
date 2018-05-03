package deepDown;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.media.AudioClip;
import javafx.stage.Stage;
import deepDown.controllers.StartMenuController;
import sun.audio.AudioData;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;
import sun.audio.ContinuousAudioDataStream;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;

public class Main extends Application {
    private AudioClip backgroundMusic;

    public static void main(String[] args) {
        File filesFolder = new File("Files");
        try{
            if (!filesFolder.exists()){
               filesFolder.mkdir();
            }
            else if (filesFolder.exists()){
                System.out.println("Files folder already exists");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        launch(args);
    }

    @Override
    public void start(Stage stage) throws IOException{

        AudioPlayer ap = AudioPlayer.player;
        AudioStream BGM;
        AudioData ad;
        ContinuousAudioDataStream loop = null;
        try{
            BGM = new AudioStream(getClass().getResourceAsStream("/deepDown/resource/sounds/Untitled.wav"));
            ad = BGM.getData();
            loop = new ContinuousAudioDataStream(ad);
        }catch (Exception e){
            e.printStackTrace();
        }


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
        ap.start(loop);
    }
}
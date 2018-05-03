package deepDown;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import deepDown.controllers.StartMenuController;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;

public class Main extends Application {
    private static Mixer mixer;
    private static Clip clip;
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

        playBackgroundMusic();
    }

    private void playBackgroundMusic() {
        Mixer.Info[] mixerInfo = AudioSystem.getMixerInfo();
        mixer = AudioSystem.getMixer(mixerInfo[0]);

        DataLine.Info dataInfo = new DataLine.Info(Clip.class, null);
        try {
            clip = (Clip)mixer.getLine(dataInfo);
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }


        try {
            URL bgmURL = getClass().getResource("/deepDown/resource/sounds/Untitled.wav");
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(bgmURL);
            clip.open(audioInputStream);
        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }
        clip.loop(Clip.LOOP_CONTINUOUSLY);
        clip.start();

    }
}
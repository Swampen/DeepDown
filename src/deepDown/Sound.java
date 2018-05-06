package deepDown;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;

/**
 * @author Ole-Martin Heggen
 */
public class Sound {
    private static MediaPlayer mediaPlayer;

    /**
     *
     */
    public static void playBackgroundMedia(){
        String path = "src/deepDown/resource/sounds/backgroundMusic.mp3";
        Media backgroundMedia = new Media(new File(path).toURI().toString());
        mediaPlayer = new MediaPlayer(backgroundMedia);
        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        mediaPlayer.play();
    }

    public static void playCoinMedia(){
        String path = "src/deepDown/resource/sounds/coin.mp3";
        Media coinMedia = new Media(new File(path).toURI().toString());
        mediaPlayer = new MediaPlayer(coinMedia);
        mediaPlayer.play();
    }

    public static void playEnemyMedia(){
        String path = "src/deepDown/resource/sounds/enemy.mp3";
        Media enemyMedia = new Media(new File(path).toURI().toString());
        mediaPlayer = new MediaPlayer(enemyMedia);
        mediaPlayer.play();
    }

    public static void playDoorMedia(){
        String path = "src/deepDown/resource/sounds/door.mp3";
        Media doorMedia = new Media(new File(path).toURI().toString());
        mediaPlayer = new MediaPlayer(doorMedia);
        mediaPlayer.play();
    }


    /**
     * Stops what the {@code MediaPlayer} is playing.
     */
    public static void stopMedia(){
        mediaPlayer.stop();
    }
}

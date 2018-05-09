package deepDown;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

/**
 * @author Ole-Martin Heggen
 */
public class Sound {
    private static MediaPlayer mediaPlayer;

    /**
     * Reads the path to a MP3 file, then the {@code MediaPlayer}
     * plays it on an infinite loop.
     */
    public static void playBackgroundMedia(){
        String mediaString = Sound.class.getResource("/deepDown/resource/sounds/backgroundMusic.mp3").toExternalForm();
        Media backgroundMedia = new Media(mediaString);
        mediaPlayer = new MediaPlayer(backgroundMedia);
        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        mediaPlayer.play();
    }

    /**
     * Reads the path to a MP3 file, then the {@code MediaPlayer}
     * plays it once.
     */
    public static void playCoinMedia(){
        String mediaString = Sound.class.getResource("/deepDown/resource/sounds/coin.mp3").toExternalForm();
        Media coinMedia = new Media(mediaString);
        mediaPlayer = new MediaPlayer(coinMedia);
        mediaPlayer.play();
    }

    /**
     * Reads the path to a MP3 file, then the {@code MediaPlayer}
     * plays it once.
     */
    public static void playEnemyMedia(){
        String mediaString = Sound.class.getResource("/deepDown/resource/sounds/enemy.mp3").toExternalForm();
        Media enemyMedia = new Media(mediaString);
        mediaPlayer = new MediaPlayer(enemyMedia);
        mediaPlayer.play();
    }

    /**
     * Reads the path to a MP3 file, then the {@code MediaPlayer}
     * plays it once.
     */
    public static void playDoorMedia(){
        String mediaString = Sound.class.getResource("/deepDown/resource/sounds/backgroundMusic.mp3").toExternalForm();
        Media doorMedia = new Media(mediaString);
        mediaPlayer = new MediaPlayer(doorMedia);
        mediaPlayer.play();
    }

    /**
     * Stops everything the {@code MediaPlayer} is playing.
     */
    public static void stopMedia(){
        mediaPlayer.stop();
    }
}

package deepDown;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

/**
 * @author Ole-Martin Heggen
 */
@SuppressWarnings("FieldCanBeLocal")
public class Sound {
    private static MediaPlayer bgMusicMediaPlayer;
    private static MediaPlayer gameSoundMediaPlayer;


    /**
     * Reads the path to a MP3 file, then the {@code MediaPlayer}
     * plays it on an infinite loop.
     */
    public static void playBackgroundMedia(){
        String mediaString = Sound.class.getResource("/deepDown/resource/sounds/backgroundMusic.mp3").toExternalForm();
        Media backgroundMedia = new Media(mediaString);
        bgMusicMediaPlayer = new MediaPlayer(backgroundMedia);
        bgMusicMediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        bgMusicMediaPlayer.play();
    }

    /**
     * Reads the path to a MP3 file, then the {@code MediaPlayer}
     * plays it once.
     */
    public static void playCoinMedia(){
        String mediaString = Sound.class.getResource("/deepDown/resource/sounds/coin.mp3").toExternalForm();
        Media coinMedia = new Media(mediaString);
        gameSoundMediaPlayer = new MediaPlayer(coinMedia);
        gameSoundMediaPlayer.play();
    }

    /**
     * Reads the path to a MP3 file, then the {@code MediaPlayer}
     * plays it once.
     */
    public static void playEnemyMedia(){
        String mediaString = Sound.class.getResource("/deepDown/resource/sounds/enemy.mp3").toExternalForm();
        Media enemyMedia = new Media(mediaString);
        gameSoundMediaPlayer = new MediaPlayer(enemyMedia);
        gameSoundMediaPlayer.play();
    }

    /**
     * Reads the path to a MP3 file, then the {@code MediaPlayer}
     * plays it once.
     */
    public static void playDoorMedia(){
        String mediaString = Sound.class.getResource("/deepDown/resource/sounds/door.mp3").toExternalForm();
        Media doorMedia = new Media(mediaString);
        gameSoundMediaPlayer = new MediaPlayer(doorMedia);
        gameSoundMediaPlayer.play();
    }
}

package deepDown;

import javax.sound.sampled.*;
import java.io.IOException;
import java.net.URL;

public class Sound {
    private static Mixer mixer;
    private static Clip clip;

    public void playBackgroundMusic() {
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
        } catch (UnsupportedAudioFileException | LineUnavailableException | IOException e) {
            e.printStackTrace();
        }
        clip.loop(Clip.LOOP_CONTINUOUSLY);
        clip.start();

    }

    public static Clip getClip() {
        return clip;
    }
}

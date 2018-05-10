package deepDown.level;


import javafx.animation.FadeTransition;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

public class Animation {

    /**
     * Makes a {@code FadeTransition} from value 0 to 1
     * over a duration of 1.5 seconds.
     * @param vBox  The node to be affected by the transition.
     */
    public static void endScreenAnimation(VBox vBox){
        FadeTransition ft = new FadeTransition(Duration.millis(1500), vBox);
        ft.setFromValue(0.0);
        ft.setToValue(1.0);
        ft.play();
    }
}

package deepDown.level;


import javafx.animation.FadeTransition;
import javafx.animation.TranslateTransition;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.util.Duration;

public class Animation {

    /**
     * Makes a {@code FadeTransition} from value 0 to 1
     * over a duration of 1500 milliseconds.
     * @param vBox  The node to be affected by the transition.
     */
    public static void endScreenAnimation(VBox vBox){
        FadeTransition ft = new FadeTransition(Duration.millis(1500), vBox);
        ft.setFromValue(0.0);
        ft.setToValue(1.0);
        ft.play();
    }

    /**
     * Makes a {@code TranslateTransition} from y value 0 to 100,
     * a {@code FadeTransition} from value 0 to 1 and
     * a {@code FadeTransition} from value 1 to 0.
     * Plays the transitions over a duration of 1000 milliseconds.
     * @param text  The node to be affected by the transitions.
     */
    public static void deathAnimation(Text text){

        TranslateTransition tt = new TranslateTransition(Duration.millis(1000), text);
        tt.setFromY(0);
        tt.setToY(100);

        FadeTransition ft = new FadeTransition(Duration.millis(500), text);
        ft.setFromValue(0.0);
        ft.setToValue(1.0);

        FadeTransition ft2 = new FadeTransition(Duration.millis(500), text);
        ft2.setFromValue(1.0);
        ft2.setToValue(0.0);

        tt.play();
        ft.play();
        ft.setOnFinished(event -> ft2.play());
    }
}

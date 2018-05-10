package deepDown;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.util.Optional;

/**
 * @author Ole-Martin Heggen
 */
public class Alerts {

    /**
     * Shows and waits for an alert box containing an error.
     */
    public static void noCustomLevel(){
        Alert a = new Alert(Alert.AlertType.ERROR, "Corrupted or no custom level");
        a.setHeaderText("Custom level error");
        a.showAndWait();
    }

    /**
     * Shows and waits for an alert box containing a message.
     */
    public static void gameSaved(){
        Alert a = new Alert(Alert.AlertType.INFORMATION, "Game saved");
        a.setHeaderText(null);
        a.showAndWait();
    }

    /**
     * Shows and waits for an alert box containing an error.
     */
    public static void noSaveFile(){
        Alert a = new Alert(Alert.AlertType.ERROR, "Save file missing or corrupted");
        a.setHeaderText("Save file error");
        a.showAndWait();
    }

    /**
     * Shows and waits for an alert box containing an error.
     */
    public static void notValidLevel(){
        Alert a = new Alert(Alert.AlertType.ERROR);
        a.setHeaderText("The level is not valid");
        a.setContentText("Please make sure you have the following: \n" +
                            "  - At least one Coin\n" +
                            "  - At least one Enemy\n" +
                            "  - One Key\n" +
                            "  - One Door\n" +
                            "  - One Avatar\n");
        a.showAndWait();
    }

    /**
     * Shows and waits for an alert box containing a message.
     * @return {@code true} if the user pressed the OK button, {@code false} otherwise.
     */
    public static Boolean discardCustomLevel(){
        Alert a = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to discard your level?");
        Optional<ButtonType> option = a.showAndWait();
        return option.isPresent() && option.get() == ButtonType.OK;
    }
}

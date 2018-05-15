package deepDown;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.util.Optional;

/**
 * @author Ole-Martin Heggen
 */
public class Alerts {

    /**
     * Shows and waits for an alert box when no customLevel is present.
     */
    public static void noCustomLevel(){
        Alert a = new Alert(Alert.AlertType.ERROR, "Corrupted or no custom level");
        a.setHeaderText("Custom level error");
        a.showAndWait();
    }

    /**
     * Shows and waits for an alert box when you successfully save the game.
     */
    public static void gameSaved(){
        Alert a = new Alert(Alert.AlertType.INFORMATION, "Game saved");
        a.setHeaderText(null);
        a.showAndWait();
    }

    /**
     * Shows and waits an alert when you successfully save a level.
     */
    public static void levelSaved() {
        Alert a = new Alert(Alert.AlertType.INFORMATION, "Level Saved");
        a.setHeaderText(null);
        a.showAndWait();
    }

    /**
     * Shows and waits for an alert box when there is no save file present.
     */
    public static void noSaveFile(){
        Alert a = new Alert(Alert.AlertType.ERROR, "Save file missing or corrupted");
        a.setHeaderText("Save file error");
        a.showAndWait();
    }

    /**
     * Shows and waits for an alert box when you make a custom level without
     * the required sprites.
     */
    public static void notValidLevel(){
        Alert a = new Alert(Alert.AlertType.ERROR);
        a.setHeaderText("The level is not valid");
        a.setContentText("Please make sure you have the following: \n" +
                            "  - One Key\n" +
                            "  - One Door\n" +
                            "  - One Avatar\n");
        a.showAndWait();
    }

    /**
     * Shows and waits for an alert box when the player is about to discard
     * the custom level.
     * @return {@code true} if the user pressed the OK button, {@code false} otherwise.
     */
    public static Boolean discardCustomLevel(){
        Alert a = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to discard your level?");
        Optional<ButtonType> option = a.showAndWait();
        return option.isPresent() && option.get() == ButtonType.OK;
    }

    /**
     * Shows and waits for an alert box when the player is about to quit the game
     * @return {@code true} if the user press the OK button, {@code false} otherwise.
     */
    public static Boolean quitGameConfirmation(){
        Alert a = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want quit the game?");
        Optional<ButtonType> option = a.showAndWait();
        return option.isPresent() && option.get() == ButtonType.OK;
    }
}

package deepDown.controllers;

import deepDown.Loader;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;


/**
 * @author Michael Mobæk Thoresen
 */
public class HelpController {

    @FXML private AnchorPane anchor;
    @FXML private Label titleHelp;
    @FXML private Label movePic;
    @FXML private Label moveText;
    @FXML private Label enemyPic;
    @FXML private Label enemyText;
    @FXML private Label pickupPic;
    @FXML private Label pickupText;
    @FXML private Button backButton;


    /**
     * Method which runs when the controller is loaded and sets all labels.
     */
    public void initialize(){
        anchor.getStylesheets().add("deepDown/resource/stylesheet.css");
        titleHelp.setText("Help");
        moveText.setText("Use the arrow keys to move around");
        enemyText.setText("Avoid enemies, if you hit one the level gets reset");
        pickupText.setText("Coins increase score and the key opens the trapdoor");
        Image moveImage = new Image(getClass().getResourceAsStream("/deepDown/resource/images/help/1.png"), 125, 125, false, false);
        Image enemyImage = new Image(getClass().getResourceAsStream("/deepDown/resource/images/help/2.png"), 125, 125, false, false);
        Image pickupImage = new Image(getClass().getResourceAsStream("/deepDown/resource/images/help/3.png"), 125, 125, false, false);
        movePic.setGraphic(new ImageView(moveImage));
        enemyPic.setGraphic(new ImageView(enemyImage));
        pickupPic.setGraphic(new ImageView(pickupImage));
        Image button = new Image(getClass().getResourceAsStream("/deepDown/resource/images/BackArrow.png"), 100, 75, false, false);
        backButton.setGraphic(new ImageView(button));

    }

    /**
     * Returns to the main menu.
     */
    public void backButtonPressed() {
        Loader.loadStartMenu(anchor);
    }
}

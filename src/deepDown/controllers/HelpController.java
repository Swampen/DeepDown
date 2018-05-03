package deepDown.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;


public class HelpController {

    @FXML private AnchorPane anchor;
    @FXML private Label titleHelp;
    @FXML private Label movePic;
    @FXML private Label moveText;
    @FXML private Label enemyPic;
    @FXML private Label enemyText;
    @FXML private Label pickupPic;
    @FXML private Label pickupText;


    public void initialize(){
        anchor.getStylesheets().add("deepDown/resource/stylesheet.css");
        titleHelp.setText("Help");
        moveText.setText("Use the arrow keys to move around");
        enemyText.setText("Avoid enemies, if you hit one the level gets reset");
        pickupText.setText("Coins increase score and the key opens the trapdoor");

    }
}

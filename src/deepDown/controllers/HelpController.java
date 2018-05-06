package deepDown.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;


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


    public void initialize(){
        anchor.getStylesheets().add("deepDown/resource/stylesheet.css");
        titleHelp.setText("Help");
        moveText.setText("Use the arrow keys to move around");
        enemyText.setText("Avoid enemies, if you hit one the level gets reset");
        pickupText.setText("Coins increase score and the key opens the trapdoor");
        Image moveImage = new Image(getClass().getResourceAsStream("/deepDown/resource/images/help/1.png"), 150, 150, false, false);
        Image enemyImage = new Image(getClass().getResourceAsStream("/deepDown/resource/images/help/2.png"), 150, 150, false, false);
        Image pickupImage = new Image(getClass().getResourceAsStream("/deepDown/resource/images/help/3.png"), 150, 150, false, false);
        movePic.setGraphic(new ImageView(moveImage));
        enemyPic.setGraphic(new ImageView(enemyImage));
        pickupPic.setGraphic(new ImageView(pickupImage));

    }

    public void backButtonPressed() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/deepDown/resource/FXML/startMenu.fxml"));
            StartMenuController controller = new StartMenuController();
            loader.setController(controller);
            Parent root = loader.load();
            anchor.getChildren().setAll(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

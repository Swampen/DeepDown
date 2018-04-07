package sample.menu;

import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import sample.Main;

public class StartMenuController {

    @FXML
    protected AnchorPane anchor;
    @FXML private Button newGameButton;
    private Main main;

    @FXML
    public void initialize() {
        main = new Main();




        final long startTime = System.nanoTime();
        new AnimationTimer(){
            public void handle(long now){
                if (newGameButton.isFocused()){
                    System.out.println("focus");
                }
            }
        }.start();

    }

    public void newGameClicked(){
        System.out.println("clicked");
        if (newGameButton.isFocused()){
            System.out.println("focus");
        }
    }
}

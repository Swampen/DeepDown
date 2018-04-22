package deepDown;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class MenuAnimation {

    private Image image;
    private Button button;
    private boolean hovering;

    public MenuAnimation(Image image, Button button, boolean hovering) {
        this.image = image;
        this.button = button;
        this.hovering = hovering;
    }

    public Button setButtonImage(){
        button.setGraphic(new ImageView(this.image));
        return button;
    }

    public boolean isHovering() {
        return hovering;
    }
}

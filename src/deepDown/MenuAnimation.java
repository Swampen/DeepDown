package deepDown;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class MenuAnimation {

    private Image image;
    private Button button;
    private boolean isHovering;

    /**
     * Constructor for MenuAnimation
     * @param image Specified image
     * @param button Specified button
     * @param isHovering Specified hovering
     */
    public MenuAnimation(Image image, Button button, boolean isHovering) {
        this.image = image;
        this.button = button;
        this.isHovering = isHovering;
    }

    /**
     * Setter for this button's image
     * @return button
     */
    public Button setButtonImage(){
        button.setGraphic(new ImageView(this.image));
        return button;
    }

    /**
     * Getter for the isHovering boolean
     * @return isHovering boolean
     */
    public boolean isHovering() {
        return isHovering;
    }
}

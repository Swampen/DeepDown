package deepDown;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class MenuAnimation {

    private final Button button;
    private boolean isHovering = false;

    /**
     * Constructor.
     * @param button Specified button.
     */
    public MenuAnimation(Button button) {
        this.button = button;
    }

    /**
     * Sets the value of the property image.
     * @param image Assign the image to this {@code Button}.
     */
    public void setButtonImage(Image image){
        button.setGraphic(new ImageView(image));
    }

    /**
     * Gets the {@code Button} for this {@code MenuAnimation}.
     * @return the button with a image assigned to it.
     */
    public Button getButton() {
        return button;
    }

    /**
     * Sets the value of the property isHovering.
     * @param isHovering Defines if the {@code Button} is being hovered by the mouse.
     */
    public void setHovering(boolean isHovering){
        this.isHovering = isHovering;
    }

    /**
     * Gets the value of the property isHovering.
     * @return {@code true} if the mouse is hovering over the {@code Button}, {@code false} otherwise.
     */
    public boolean isHovering() {
        return isHovering;
    }
}

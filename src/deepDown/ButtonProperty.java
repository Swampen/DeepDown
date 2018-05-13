package deepDown;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * @author Ole-Martin Heggen
 */
public class ButtonProperty {

    private final Button button;
    private boolean isHovering = false;

    /**
     * Constructor.
     * @param button Specified button.
     */
    public ButtonProperty(Button button) {
        this.button = button;
    }

    /**
     * Sets the image for the button.
     * @param path The the image to this {@code Button}.
     */
    public void setButtonImage(String path){
        Image image = new Image(getClass().getResourceAsStream(path));
        button.setGraphic(new ImageView(image));
    }

    /**
     * Sets the image for the button without the need of an object.
     * @param path The the image to this {@code Button}.
     * @param button The {@code Button} the image is applied to.
     */
    public static Button setStaticButtonImage(String path, Button button){
        Image image = new Image(ButtonProperty.class.getResourceAsStream(path));
        button.setGraphic(new ImageView(image));
        return button;
    }

    /**
     * Gets the {@code Button} for this {@code ButtonProperty}.
     * @return the button with a image assigned to it.
     */
    public Button getButton() {
        return button;
    }

    /**
     * Sets the value of the property isNotHovering.
     * @param isHovering Defines if the {@code Button} is being hovered by the mouse.
     */
    public void setHovering(boolean isHovering){
        this.isHovering = isHovering;
    }

    /**
     * Gets the value of the property isNotHovering.
     * @return {@code true} if the mouse is hovering over the {@code Button}, {@code false} otherwise.
     */
    public boolean isNotHovering() {
        return !isHovering;
    }
}

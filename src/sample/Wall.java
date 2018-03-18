package sample;


import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Wall extends GameObject{

    private Image image;
    private double width;
    private double height;
    private double xPos;
    private double yPos;

    public Wall (double x, double y, int height, int width){
        super(x, y, height, width);
    }

    

}

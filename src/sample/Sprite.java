package sample;

import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.shape.Rectangle;

public class Sprite {

    private Image image;
    private double positionX;
    private double positionY;
    private double width;
    private double height;

    public Sprite (Image image, GameObject go){
        this.image = image;
        this.positionX = go.getX();
        this.positionY = go.getY();
        this.width = go.getW();
        this.height = go.getH();
    }

    public void render (GraphicsContext gc, double sx, double sy){
        gc.drawImage(image, sx, sy, width, height, positionX, positionY, 40, 40);
    }

    public Rectangle2D getBoundary(){
        return new Rectangle2D(positionX, positionY, width,height);
    }





    public boolean collision(Sprite sprite){

        if( sprite.getBoundary().intersects(this.getBoundary())){
            System.out.println(sprite);
        }
        return sprite.getBoundary().intersects(this.getBoundary());
    }
}
package deepDown.gameObjects;

public class Key extends GameObject {

    private boolean isPickedUp;

    public Key(double x, double y, int height, int width, boolean isPickedUp) {
        super(x, y, height, width);
        this.isPickedUp = isPickedUp;
    }

    public boolean isPickedUp(){
        return isPickedUp;
    }

    public void setPickedUp(boolean isPickedUp){
        this.isPickedUp = isPickedUp;
    }
}

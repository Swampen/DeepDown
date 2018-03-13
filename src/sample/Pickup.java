package sample;

public class Pickup extends GameObject{

    public boolean isPickedUp;

    public Pickup(double x, double y, int height, int width, boolean isPickedUp) {
        super(x, y, height, width);
        this.isPickedUp = isPickedUp;
    }

    public void setPickedUp(boolean pickedUp) {
        isPickedUp = pickedUp;
    }
    public boolean isPickedUp() {
        return isPickedUp;
    }
}

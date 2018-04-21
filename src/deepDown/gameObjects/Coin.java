package deepDown.gameObjects;

public class Coin extends GameObject{

    private boolean isPickedUp;

    public Coin(double x, double y, int height, int width, boolean isPickedUp) {
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

package sample;

public class Key extends Pickup {

    private boolean exitOpen;

    public Key(double x, double y, int height, int width, boolean isPickedUp, boolean exitOpen) {
        super(x, y, height, width, isPickedUp);
        this.exitOpen = exitOpen;
    }


    public boolean isExitOpen() {
        return exitOpen;
    }
    public void setExitOpen(boolean exitOpen) {
        this.exitOpen = exitOpen;
    }

    @Override
    public boolean isPickedUp() {
        return super.isPickedUp();
    }
    @Override
    public void setPickedUp(boolean pickedUp) {
        super.setPickedUp(pickedUp);
    }

}

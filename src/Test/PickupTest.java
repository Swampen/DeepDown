import org.junit.Test;
import deepDown.gameObjects.Pickup;

import static org.junit.Assert.*;

public class PickupTest {
    private Pickup testPickup = new Pickup(0,0,40,40,false);
    @Test
    public void setPickedUp() throws Exception {
        testPickup.setPickedUp(true);
        assertTrue(testPickup.isPickedUp());
    }

    @Test
    public void isPickedUp() throws Exception {
        assertFalse(testPickup.isPickedUp());
    }

}
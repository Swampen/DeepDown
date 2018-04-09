import org.junit.Test;
import deepDown.gameObjects.Coin;

import static org.junit.Assert.*;

public class PickupTest {
    private Coin testPickup = new Coin(0,0,40,40,false);
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
import org.junit.Test;
import deepDown.gameObjects.Key;

import static org.junit.Assert.*;

public class KeyTest {
    private Key testKey = new Key(0,0,40,40,false,false);
    @Test
    public void isExitOpen() throws Exception {
        assertFalse(testKey.isExitOpen());
    }

    @Test
    public void setExitOpen() throws Exception {
        testKey.setExitOpen(true);
        assertTrue(testKey.isExitOpen());
    }

    @Test
    public void isPickedUp() throws Exception {
        assertFalse(testKey.isPickedUp());
    }

    @Test
    public void setPickedUp() throws Exception {
        testKey.setPickedUp(true);
        assertTrue(testKey.isPickedUp());
    }

}
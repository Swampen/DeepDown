package deepDown.gameObjects;

import deepDown.Sprite;
import org.junit.Test;
import static org.junit.Assert.*;

public class DoorTest {
    private Sprite sprite;

    Door test = new Door(0, 0, 40, 40, sprite);
    @Test
    public void isOpen() throws Exception {
        assertFalse(test.isOpen());
    }

    @Test
    public void setOpen() throws Exception {
        test.setOpen(true);
        assertTrue(test.isOpen());
    }
}

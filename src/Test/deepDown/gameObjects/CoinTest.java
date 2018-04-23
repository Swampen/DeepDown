package deepDown.gameObjects;

import deepDown.Sprite;
import org.junit.Test;
import deepDown.gameObjects.Coin;

import static org.junit.Assert.*;

public class CoinTest {
    private Sprite sprite;

    private Coin testCoin = new Coin(0,0,40,40, sprite, false);
    @Test
    public void setPickedUp() throws Exception {
        testCoin.setPickedUp(true);
        assertTrue(testCoin.isPickedUp());
    }

    @Test
    public void isPickedUp() throws Exception {
        assertFalse(testCoin.isPickedUp());
    }

}
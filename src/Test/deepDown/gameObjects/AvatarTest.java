package deepDown.gameObjects;

import org.junit.Test;

import static org.junit.Assert.*;

public class AvatarTest {
    Avatar testAvatar = new Avatar(0, 0, 30, 30, 0, 0);
    @Test
    public void setMovementState() throws Exception {
        testAvatar.setMovementState(false);
        assertFalse(testAvatar.getCanMoveDown());
        assertFalse(testAvatar.getCanMoveRight());
        assertFalse(testAvatar.getCanMoveLeft());
        assertFalse(testAvatar.getCanMoveUp());
    }

    @Test
    public void setCanMoveUp() throws Exception {
        testAvatar.setCanMoveUp(false);
        assertFalse(testAvatar.getCanMoveUp());
    }

    @Test
    public void setCanMoveDown() throws Exception {
        testAvatar.setCanMoveDown(false);
        assertFalse(testAvatar.getCanMoveDown());
    }

    @Test
    public void setCanMoveLeft() throws Exception {
        testAvatar.setCanMoveLeft(false);
        assertFalse(testAvatar.getCanMoveLeft());
    }

    @Test
    public void setCanMoveRight() throws Exception {
        testAvatar.setCanMoveRight(false);
        assertFalse(testAvatar.getCanMoveRight());
    }

    @Test
    public void getCanMoveUp() throws Exception {
        assertTrue(testAvatar.getCanMoveUp());
    }

    @Test
    public void getCanMoveDown() throws Exception {
        assertTrue(testAvatar.getCanMoveDown());
    }

    @Test
    public void getCanMoveLeft() throws Exception {
        assertTrue(testAvatar.getCanMoveLeft());
    }

    @Test
    public void getCanMoveRight() throws Exception {
        assertTrue(testAvatar.getCanMoveRight());
    }

}
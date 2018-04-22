package deepDown.gameObjects;



import org.junit.Test;
import static org.junit.Assert.*;

import javafx.scene.image.Image;

public class SpriteTest {
    private Image image;
    private GameObject go;

    Sprite test = new Sprite(image, go, Type.HENEMY, 40, 40);
    @Test
    public void render() throws Exception {
    }

    @Test
    public void changeSprite() throws Exception {
    }

    @Test
    public void getType() throws Exception {
        assertEquals(Type.HENEMY, test.getType());
    }



    @Test
    public void getGo(){
        assertEquals(go, test.getGo());
    }
}

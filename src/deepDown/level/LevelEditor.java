package deepDown.level;

import deepDown.Alerts;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * @author Michael Mobæk Thoresen and Ole-Martin Heggen
 */
public class LevelEditor {
    private final Image image;
    private final GraphicsContext gc;
    private final int gridSize = 32;

    /**
     * Constructor.
     * @param gc Specified {@code GraphicsContext} from the {@code LevelEditorController}.
     */
    public LevelEditor(GraphicsContext gc){
        this.gc = gc;
        image = new Image(getClass().getResourceAsStream("/deepDown/resource/images/DeepDownTileSet.png"));
    }

    /**
     * Makes a new {@code int[18][32]}  with an integer
     * of 1 around the edges of the 2D array.
     * @return 2D int array containing the clean level.
     */
    public int[][] getLevelArray(){
        int[][] levelArray = new int[18][32];
        for (int i = 0; i < levelArray.length; i++) {
            for (int j = 0; j < levelArray[i].length; j++) {
                if ((i == 0) || (i == 17) || (j == 0) || (j == 31)){
                    levelArray[i][j] = 1;
                    gc.drawImage(image, 0, 0, 40, 40, j * 32, i * 32, 32, 32);
                } else {
                    levelArray[i][j] = 0;
                    gc.fillRect(j * 32, i * 32, 32, 32);
                }
            }
        }
        return levelArray;
    }

    /**
     * Draws an 2D int array onto the level editor's {@code Canvas}.
     * @param levelArray The 2D int array to be drawn onto the {@code Canvas}.
     */
    public void updateEditorCanvas(int[][] levelArray){
        for (int i = 0; i < levelArray.length; i++) {
            for (int j = 0; j < levelArray[i].length; j++) {
                int arrayInteger = levelArray[i][j];
                if (arrayInteger == 0){
                    gc.fillRect(j * gridSize, i * gridSize, gridSize, gridSize);
                }else if (arrayInteger == 1) {
                    gc.fillRect(j * gridSize, i * gridSize, gridSize, gridSize);
                    gc.drawImage(image, 0, 0, 40, 40, j * gridSize, i * gridSize, gridSize, gridSize);
                }else if (arrayInteger == 2) {
                    gc.fillRect(j * gridSize, i * gridSize, gridSize, gridSize);
                    gc.drawImage(image, 40, 0, 40, 40, j * gridSize, i * gridSize, gridSize, gridSize);
                }else if (arrayInteger == 3) {
                    gc.fillRect(j * 32, i * gridSize, gridSize, gridSize);
                    gc.drawImage(image, 80, 0, 40, 40, j * gridSize, i * gridSize, gridSize, gridSize);
                }else if (arrayInteger == 4) {
                    gc.fillRect(j * gridSize, i * gridSize, gridSize, gridSize);
                    gc.drawImage(image, 80, 0, 40, 40, j * 32, i * gridSize, gridSize, gridSize);
                }else if (arrayInteger == 5 && !LevelRequirements.isKeyLimit()) {
                    gc.fillRect(j * gridSize, i * gridSize, gridSize, gridSize);
                    gc.drawImage(image, 120, 0, 40, 40, j * gridSize, i * gridSize, gridSize, gridSize);
                    LevelRequirements.setKeyLimit(true);
                }else if (arrayInteger == 6 && !LevelRequirements.isDoorLimit()) {
                    gc.fillRect(j * gridSize, i * gridSize, gridSize, gridSize);
                    gc.drawImage(image, 160, 0, 40, 40, j * gridSize, i * gridSize, gridSize, gridSize);
                    LevelRequirements.setDoorLimit(true);
                }else if (arrayInteger == 7 && !LevelRequirements.isAvatarLimit()) {
                    gc.fillRect(j * gridSize, i * gridSize, gridSize, gridSize);
                    gc.drawImage(image, 0, 40, 30, 30, j * gridSize + 4, i * gridSize + 4, 24, 24);
                    LevelRequirements.setAvatarLimit(true);
                }
            }
        }
    }

    /**
     * Saves the custom level to the Files folder.
     * @param array The 2D int array to be saved.
     * @param f The custom file path.
     */
    public void saveCustomLevel(int[][] array, File f) {
        try {
            StringBuilder builder = new StringBuilder();
            for (int i = 0; i < 18; i++) {
                for (int j = 0; j < 32; j++) {
                    builder.append(array[i][j]);
                    if (j < 31) {
                        builder.append(" ");
                    }
                }
                builder.append(System.getProperty("line.separator"));
            }
            BufferedWriter writer = new BufferedWriter(new FileWriter(f));
            writer.write(builder.toString());
            writer.close();
            Alerts.levelSaved();
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    /**
     * Gets the value of the property gridSize.
     * @return Returns the Level Editor's gridSize value.
     */
    public int getGridSize(){
        return gridSize;
    }

}

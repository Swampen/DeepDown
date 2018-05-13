package deepDown.level;

import deepDown.Alerts;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * @author Ole-Martin Heggen
 */
public class LevelReader {

    /**
     * Reads a .txt file and makes and 2D int array from the context.
     * @param path The path to the level.txt file.
     * @return The 2D int array made from the .txt file.
     */
    public static int[][] readLevel(String path){
        Scanner input = new Scanner(LevelReader.class.getResourceAsStream(path));

        int rows = 18;
        int columns = 32;
        int[][] level = new int[rows][columns];

        for (int i=0; i<rows; i++){
            for(int j=0; j<columns; j++){
                if(input.hasNextInt()){
                    level[i][j] = input.nextInt();
                }
            }
        }
        return level;
    }

    /**
     * Reads the custom .txt file from the "Files" folder
     * and makes and 2D int array from the context.
     * Shows an error if the file does not exist.
     * @return The 2D int array made from the .txt file.
     */
    public static int[][] readCustomLevel(){
        LevelRequirements.setKeyLimit(false);
        LevelRequirements.setDoorLimit(false);
        LevelRequirements.setAvatarLimit(false);

        int rows = 18;
        int columns = 32;
        int[][] level = new int[rows][columns];

        try {
            String filepath = new File("Files").getAbsolutePath();
            String file = "customLevel.txt";
            File f = new File(filepath, file);
            Scanner input = new Scanner(f);
            for (int i=0; i<rows; i++){
                for(int j=0; j<columns; j++){
                    if(input.hasNextInt()){
                        level[i][j] = input.nextInt();
                    }
                }
            }
        } catch (FileNotFoundException e) {
            Alerts.noCustomLevel();
            e.printStackTrace();
        }
        return level;
    }
}

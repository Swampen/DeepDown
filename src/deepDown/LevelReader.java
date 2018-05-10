package deepDown;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class LevelReader {
    private Scanner input;

    public int[][] readCustomlevel(){
        try {
            String filepath = new File("Files").getAbsolutePath();
            String file = "customLevel.txt";
            File f = new File(filepath, file);
            input = new Scanner(f);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        int rows = 18;
        int columns = 32;
        int[][] board = new int[rows][columns];

        for (int i=0; i<rows; i++){
            for(int j=0; j<columns; j++){
                if(input.hasNextInt()){
                    board[i][j] = input.nextInt();
                }
            }
        }
        return board;
    }
}

package sample;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class GameBoard {

    private int level;

    public GameBoard (int level){
        this.level = level;
    }

    public int[][] board() throws IOException{
        Scanner levelSelected = readFile(this.level);
        int[][] levelToDraw = initiateLevel(levelSelected);
        return levelToDraw;
}

    private int[][] initiateLevel(Scanner levelSelected) throws IOException{
        int rows = 18;
        int colums = 32;

        int[][] board = new int[rows][colums];
        //levelSelected.close();

        levelSelected = new Scanner(new File("src/sample/level1.txt"));
        for (int i=0; i<rows; i++){
            for(int j=0; j<colums; j++){
                if(levelSelected.hasNextInt()){
                    board[i][j] = levelSelected.nextInt();
                }
            }
        }
        return board;
    }

    public Scanner readFile(int selectedLevel) throws IOException{
        switch (selectedLevel) {
            case 1:
                Scanner input = new Scanner(new File("src/sample/level1.txt"));
                return input;
            case 2:
                break;
            case 3:
                break;
            case 4:
                break;
            case 5:
                break;
            case 6:
                break;
            case 7:
                break;
            case 8:
                break;
            case 9:
                break;
            case 10:
                break;
            default:
                break;
        }
        return null;
    }

}

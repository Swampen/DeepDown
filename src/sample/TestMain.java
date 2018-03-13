package sample;

import java.io.IOException;

public class TestMain {
    public static void main(String[] args) throws IOException{
        GameBoard level1 = new GameBoard(1);
        int [][] level = level1.board();

       for(int i = 0; i<level.length; i++){
            for(int j = 0; j<level[i].length; j++){
                System.out.print(level[i][j] + " ");
            }
           System.out.println();
        }

    }
}

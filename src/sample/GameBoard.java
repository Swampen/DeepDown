package sample;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Scanner;

public class GameBoard {

    private int level;

    public GameBoard (int level){
        this.level = level;
    }


    public void drawBoard(GraphicsContext gc)throws IOException{
        Scanner input = null;
        Image image = new Image(new FileInputStream("src/sample/TestSprite2.png"));


        //Select case for every possible selected level
        switch (this.level) {
            case 1:
                input = new Scanner(new File("src/sample/level1.txt"));
                break;
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

        //Initiates rows and colums for the board
        int rows = 18;
        int colums = 32;
        int[][] board = new int[rows][colums];

        //reads the txt file to board 2D array
        for (int i=0; i<rows; i++){
            for(int j=0; j<colums; j++){
                if(input.hasNextInt()){
                    board[i][j] = input.nextInt();
                }
            }
        }

        //draws the objects into pane
        for (int i=0; i<rows; i++){
            for(int j=0; j<colums; j++){

                switch (board[i][j]){
                    case 1:
                        Wall wall = new Wall(j*40, i*40, 40, 40);
                        gc.drawImage(image, 0, 0, 40, 40, wall.x,wall.y,40,40);
                        break;
                    case 2:
                        Pickup pickup = new Pickup(j*40, i*40, 40, 40, false);
                        gc.drawImage(image, 40, 0,40,40, pickup.x, pickup.y, 40, 40);
                        break;
                    case 3:
                        break;
                    case 4:
                        break;
                    case 5:
                        break;
                    case 6:
                        break;
                    default:
                        break;
                }
            }
        }
    }

}

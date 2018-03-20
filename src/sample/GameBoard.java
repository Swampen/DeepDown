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


    public Avatar drawBoard(GraphicsContext gc)throws IOException{
        Scanner input = null;
        Image image = new Image(new FileInputStream("src/sample/TestSprite2.png"));


        //Select case for every possible selected level
        switch (this.level) {
            case 1:
                input = new Scanner(new File("src/sample/level1.txt"));
                break;
            case 2:
                //input = new Scanner(new File("src/sample/level2.txt"));
                break;
            case 3:
                //input = new Scanner(new File("src/sample/level3.txt"));
                break;
            case 4:
                //input = new Scanner(new File("src/sample/level4.txt"));
                break;
            case 5:
                //input = new Scanner(new File("src/sample/level5.txt"));
                break;
            case 6:
                //input = new Scanner(new File("src/sample/level6.txt"));
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

        Avatar player = null;
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
                        Enemy enemy = new Enemy(j*40, i*40, 40, 40);
                        //gc.drawImage(image, 80, 0, 40, 40, enemy.x,enemy.y,40,40);
                        break;
                    case 4:
                        player = new Avatar(j*40, i*40, 40, 40, 3, true, 0, 0);
                        //gc.drawImage(image, 0, 0, 40, 40, player.x,player.y,40,40);
                        break;
                    case 5:
                        Key key = new Key(j*40,i*40,40,40, false, false);
                        //gc.drawImage(image, 40, 40, 40, 40, key.x,key.y,40,40);
                        break;
                    case 6:
                        //Exit exit = new Exit(j*40, i*40, 40, 40, false)
                        //gc.drawImage(image, 40, 80, 40, 40, exit.x,exit.y,40,40);
                        break;
                    default:
                        break;
                }
            }
        }
        return player;
    }

}

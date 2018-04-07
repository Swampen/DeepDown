package sample;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

public class GameBoard {

    private int level;

    public GameBoard (int level){
        this.level = level;
    }


    public ArrayList<Sprite> drawBoard(GraphicsContext gc)throws IOException{
        Scanner input = null;
        Image image = new Image(new FileInputStream("src/sample/DeepDownTileSet.png"));


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

        ArrayList<Sprite> sprites = new ArrayList<Sprite>();
        //draws the objects into canvas
        for (int i=0; i<rows; i++){
            for(int j=0; j<colums; j++){

                switch (board[i][j]){
                    case 1:
                        Wall wall = new Wall(j*40, i*40, 40, 40);
                        Sprite wallSprite = new Sprite(image, wall, 1, 0, 0);
                        wallSprite.render(gc);
                        sprites.add(wallSprite);
                        break;
                    case 2:
                        Pickup pickup = new Pickup(j*40, i*40, 40, 40, false);
                        Sprite pickupSprite = new Sprite(image, pickup, 2, 40, 0);
                        pickupSprite.render(gc);
                        sprites.add(pickupSprite);
                        break;
                    case 3:
                        Enemy enemy = new Enemy(j*40, i*40, 40, 40);
                        Sprite enemySprite = new Sprite(image, enemy, 3, 80, 0);
                        enemySprite.render(gc);
                        sprites.add(enemySprite);
                        break;
                    case 4:
                        //player = new Avatar(j*40, i*40, 40, 40, 3, true, 0, 0);
                        //Sprite avatarSprite = new Sprite(image, Avatar);
                        //avatarSprite.render(gc, 80, 0);
                        break;
                    case 5:
                        Key key = new Key(j*40,i*40,40,40, false, false);
                        Sprite keySprite = new Sprite(image, key, 5, 120, 0);
                        keySprite.render(gc);
                        sprites.add(keySprite);
                        break;
                    case 6:
                        Exit exit = new Exit(j*40,i*40,40,40, false);
                        Sprite exitSprite = new Sprite(image, exit, 6, 160, 0);
                        exitSprite.render(gc);
                        sprites.add(exitSprite);
                        break;
                    default:
                        break;
                }
            }
        }
        return sprites;
    }

    public void renderSprite(ArrayList<Sprite> sprites, GraphicsContext gc){
        for (int i = 0; i < sprites.size(); i++){
            Sprite sprite = sprites.get(i);
            sprite.render(gc);
        }
    }
}

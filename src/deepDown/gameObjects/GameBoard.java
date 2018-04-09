package deepDown.gameObjects;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class GameBoard {

    private int level;
    Scanner input;

    public GameBoard (int level){
        this.level = level;
    }


    public ArrayList<Sprite> drawBoard(GraphicsContext gc)throws IOException{

        Image image = new Image(new FileInputStream("src/deepDown/resource/DeepDownTileSet.png"));


        //Select case for every possible selected level
        switch (this.level) {
            case 1:
                input = new Scanner(new File("src/deepDown/levels/level1.txt"));
                break;
            case 2:
                input = new Scanner(new File("src/deepDown/levels/level2.txt"));
                break;
            case 3:
                //input = new Scanner(new File("src/deepDown/level3.txt"));
                break;
            case 4:
                //input = new Scanner(new File("src/deepDown/level4.txt"));
                break;
            case 5:
                //input = new Scanner(new File("src/deepDown/level5.txt"));
                break;
            case 6:
                //input = new Scanner(new File("src/deepDown/level6.txt"));
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
                        Sprite wallSprite = new Sprite(image, wall, Type.WALL, 0, 0);
                        wallSprite.render(gc);
                        sprites.add(wallSprite);
                        break;
                    case 2:
                        Coin coin = new Coin(j*40, i*40, 40, 40, false);
                        Sprite coinSprite = new Sprite(image, coin, Type.COIN, 40, 0);
                        coinSprite.render(gc);
                        sprites.add(coinSprite);
                        break;
                    case 3:
                        Enemy enemy = new Enemy(j*40, i*40, 40, 40);
                        Sprite enemySprite = new Sprite(image, enemy, Type.ENEMY, 80, 0);
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
                        Sprite keySprite = new Sprite(image, key, Type.KEY, 120, 0);
                        keySprite.render(gc);
                        sprites.add(keySprite);
                        break;
                    case 6:
                        Door door = new Door(j*40,i*40,40,40, false);
                        Sprite doorSprite = new Sprite(image, door, Type.DOOR, 160, 0);
                        doorSprite.render(gc);
                        sprites.add(doorSprite);
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
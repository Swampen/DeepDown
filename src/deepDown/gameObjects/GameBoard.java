package deepDown.gameObjects;

import deepDown.gameObjects.Enemy.Enemy;
import deepDown.gameObjects.Enemy.HorisontalEnemy;
import deepDown.gameObjects.Enemy.VerticalEnemy;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class GameBoard {

    private int level;
    private Scanner input;
    private Image image;
    private Avatar avatar;
    private Key key;
    private Door door;
    private VerticalEnemy vEnemy;
    private HorisontalEnemy hEnemy;

    private Sprite avatarSprite;
    private Sprite keySprite;
    private Sprite doorSprite;

    private ArrayList<Sprite> wallSprites = new ArrayList<Sprite>();
    private ArrayList<Sprite> coinSprites = new ArrayList<Sprite>();
    private ArrayList<Sprite> hEnemySprites = new ArrayList<Sprite>();
    private ArrayList<Sprite> vEnemySprites = new ArrayList<Sprite>();

    public GameBoard (int level){
        this.level = level;
    }

    public void iniitalizeGameBoard(GraphicsContext gc)throws IOException{
        readImage();
        readLevel();
        drawGameBoard(gc);
    }

    public void readImage() throws IOException{
        image = new Image(new FileInputStream("src/deepDown/resource/DeepDownTileSet.png"));
    }

    private void readLevel() throws IOException{
        switch (this.level) {
            case 1:
                input = new Scanner(new File("src/deepDown/levels/level1.txt"));
                break;
            case 2:
                input = new Scanner(new File("src/deepDown/levels/level2.txt"));
                break;
            case 3:
                input = new Scanner(new File("src/deepDown/levels/level3.txt"));
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
    }

    public void drawGameBoard(GraphicsContext gc){
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

        //draws the objects into canvas
        for (int i=0; i<rows; i++){
            for(int j=0; j<colums; j++){

                switch (board[i][j]){
                    case 1:
                        Wall wall = new Wall(j*40, i*40, 40, 40);
                        Sprite wallSprite = new Sprite(image, wall, Type.WALL, 0, 0);
                        wallSprite.render(gc);
                        wallSprites.add(wallSprite);
                        break;
                    case 2:
                        Coin coin = new Coin(j*40, i*40, 40, 40, false);
                        Sprite coinSprite = new Sprite(image, coin, Type.COIN, 40, 0);
                        coinSprite.render(gc);
                        coinSprites.add(coinSprite);
                        break;
                    case 3:
                        Enemy hEnemy = new HorisontalEnemy(j*40, i*40, 40, 40, 0, 0);
                        Sprite hEnemySprite = new Sprite(image, hEnemy, Type.HENEMY, 80, 0);
                        hEnemySprite.render(gc);
                        hEnemySprites.add(hEnemySprite);
                        break;
                    case 4:
                        Enemy vEnemy = new VerticalEnemy(j*40, i*40, 40, 40, 0, 0);
                        Sprite vEnemySprite = new Sprite(image, vEnemy, Type.VENEMY, 80, 0);
                        vEnemySprite.render(gc);
                        vEnemySprites.add(vEnemySprite);
                        break;
                    case 5:
                        key = new Key(j*40,i*40,40,40, false);
                        keySprite = new Sprite(image, key, Type.KEY, 120, 0);
                        keySprite.render(gc);
                        break;
                    case 6:
                        door = new Door(j*40,i*40,40,40, false);
                        doorSprite = new Sprite(image, door, Type.DOOR, 160, 0);
                        doorSprite.render(gc);
                        break;
                    case 7:
                        avatar = new Avatar(j*40, i*40, 30, 30, 3, true, 0, 0);
                        avatarSprite = new Sprite(image, avatar, Type.AVATAR, 0, 40);
                        avatarSprite.render(gc);
                        break;
                    default:
                        break;
                }
            }
        }
    }


    public Avatar getAvatar() {
        return this.avatar;
    }

    public Key getKey(){
        return this.key;
    }

    public Door getDoor() {
        return door;
    }

    public VerticalEnemy getVEnemy() { return this.vEnemy;}

    public HorisontalEnemy getHEnemy() { return this.hEnemy;}

    public ArrayList<Sprite> getWallSprites() {
        return this.wallSprites;
    }

    public ArrayList<Sprite> getCoinSprites() {
        return this.coinSprites;
    }

    public ArrayList<Sprite> getHEnemieSprites() {
        return this.hEnemySprites;
    }

    public ArrayList<Sprite> getVEnemieSprites() {
        return this.vEnemySprites;
    }

    public Sprite getAvatarSprite() {
        return this.avatarSprite;
    }

    public Sprite getKeySprite() {
        return this.keySprite;
    }

    public Sprite getDoorSprite() {
        return doorSprite;
    }
}
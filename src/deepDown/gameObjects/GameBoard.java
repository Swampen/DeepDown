package deepDown.gameObjects;

import deepDown.gameObjects.Enemy.Enemy;
import deepDown.gameObjects.Enemy.HorizontalEnemy;
import deepDown.gameObjects.Enemy.VerticalEnemy;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

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
    private HorizontalEnemy hEnemy;

    private Sprite avatarSprite;
    private Sprite keySprite;
    private Sprite doorSprite;

    private ArrayList<Sprite> wallSprites = new ArrayList<Sprite>();
    private ArrayList<Sprite> coinSprites = new ArrayList<Sprite>();
    private ArrayList<Sprite> hEnemySprites = new ArrayList<Sprite>();
    private ArrayList<Sprite> vEnemySprites = new ArrayList<Sprite>();

    /**
     * Constructor for the GameBoard
     * @param level What level to load
     */
    public GameBoard (int level){
        this.level = level;
    }

    /**
     * Starts loading of the level
     * @param gc
     */
    public void initializeGameBoard(GraphicsContext gc){
        readImage();
        readLevel();
        drawGameBoard(gc);
    }

    /**
     * reads the tileset to use
     */
    public void readImage(){
        image = new Image(getClass().getResourceAsStream("/deepDown/resource/images/DeepDownTileSet.png"));
    }

    /**
     * reads what level to load depending on the level variable specified in the constructor
     */
    private void readLevel(){
        switch (this.level) {
            case 1:
                input = new Scanner(getClass().getResourceAsStream("/deepDown/levels/level1.txt"));
                break;
            case 2:
                input = new Scanner(getClass().getResourceAsStream("/deepDown/levels/level2.txt"));
                break;
            case 3:
                input = new Scanner(getClass().getResourceAsStream("/deepDown/levels/level3.txt"));
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

    /**
     * Draws the GameBoard using the file read by the readLevel() method and turns that into an array
     * which then gets translated into what kind of sprite gets created and placed in each coordinate
     * @param gc the specified GraphicContext
     */
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
                        Enemy hEnemy = new HorizontalEnemy(j*40, i*40, 40, 40, 0, 0);
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
                        avatar = new Avatar(j*40, i*40, 30, 30,  0, 0);
                        avatarSprite = new Sprite(image, avatar, Type.AVATAR, 0, 40);
                        avatarSprite.render(gc);
                        break;
                    default:
                        break;
                }
            }
        }
    }

    /**
     * Getter for Avatar
     * @return avatar
     */
    public Avatar getAvatar() {
        return this.avatar;
    }

    /**
     * Getter for Key
     * @return key
     */
    public Key getKey(){
        return this.key;
    }

    /**
     * Getter for Door
     * @return door
     */
    public Door getDoor() {
        return door;
    }

<<<<<<< HEAD
=======
    /**
     * Getter for vEnemy
     * @return vEnemy
     */
    public VerticalEnemy getVEnemy() { return this.vEnemy;}

    /**
     * Getter for hEnemy
     * @return hEnemy
     */
    public HorizontalEnemy getHEnemy() { return this.hEnemy;}

    /**
     * Getter for WallSprites
     * @return wallSprites
     */
>>>>>>> 86bc73ea33d5824ba945c188abfb98ae09284328
    public ArrayList<Sprite> getWallSprites() {
        return this.wallSprites;
    }

    /**
     * Getter for CoinSprites
     * @return coinSprites
     */
    public ArrayList<Sprite> getCoinSprites() {
        return this.coinSprites;
    }

    /**
     * Getter for hEnemySprites
     * @return hEnemySprites
     */
    public ArrayList<Sprite> getHEnemieSprites() {
        return this.hEnemySprites;
    }

    /**
     * Getter for vEnemySprites
     * @return vEnemySprites
     */
    public ArrayList<Sprite> getVEnemieSprites() {
        return this.vEnemySprites;
    }

    /**
     * Getter for AvatarSprites
     * @return avatarSprites
     */
    public Sprite getAvatarSprite() {
        return this.avatarSprite;
    }

    /**
     * Getter for KeySprites
     * @return keySprites
     */
    public Sprite getKeySprite() {
        return this.keySprite;
    }

    /**
     * Getter for DoorSprites
     * @return doorSprites
     */
    public Sprite getDoorSprite() {
        return doorSprite;
    }
}

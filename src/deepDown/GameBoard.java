package deepDown;

import deepDown.gameObjects.*;
import deepDown.gameObjects.enemy.Enemy;
import deepDown.gameObjects.enemy.HorizontalEnemy;
import deepDown.gameObjects.enemy.VerticalEnemy;
import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * @author Ole-Martin Heggen
 */
public class GameBoard {

    private final int level;
    private double enemyVel;
    private Scanner input;
    private Image image;
    private Avatar avatar;
    private Key key;
    private Door door;

    private ArrayList<Wall> walls = new ArrayList<>();
    private ArrayList<Coin> coins = new ArrayList<>();
    private ArrayList<Enemy> enemies = new ArrayList<>();

    /**
     * Constructor.
     * @param level What level to load.
     * @param enemyVel The enemiy's velocity on the level.
     */
    public GameBoard (int level , double enemyVel){
        this.level = level;
        this.enemyVel = enemyVel;
    }

    /**
     * Runs the methods necessary to initialize the gameboard.
     */
    public void initializeGameBoard(){
        readImage();
        readLevel();
        drawGameBoard();
    }

    /**
     * Reads the tileset to use for sprites.
     */
    private void readImage(){
        image = new Image(getClass().getResourceAsStream("/deepDown/resource/images/DeepDownTileSet.png"));
    }

    /**
     * Reads the level array for this level.
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
                input = new Scanner(getClass().getResourceAsStream("/deepDown/levels/level4.txt"));
                break;
            case 5:
                input = new Scanner(getClass().getResourceAsStream("/deepDown/levels/level5.txt"));
                break;
            case 6:
                input = new Scanner(getClass().getResourceAsStream("/deepDown/levels/level6.txt"));
                break;
            case 7:
                input = new Scanner(getClass().getResourceAsStream("/deepDown/levels/level7.txt"));
                break;
            case 8:
                input = new Scanner(getClass().getResourceAsStream("/deepDown/levels/level8.txt"));
                break;
            case 9:
                break;
            case 10:
                break;
            default:
                break;
        }
    }

    private void drawGameBoard(){
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

        for (int i=0; i<rows; i++){
            for(int j=0; j<columns; j++){

                switch (board[i][j]){
                    case 1:
                        Sprite wallSprite = new Sprite(image, 0, 0);
                        Wall wall = new Wall(j*40, i*40, 40, 40, wallSprite);
                        walls.add(wall);
                        break;
                    case 2:
                        Sprite coinSprite = new Sprite(image, 40, 0);
                        Coin coin = new Coin(j*40, i*40, 40, 40, coinSprite);
                        coins.add(coin);
                        break;
                    case 3:
                        Sprite hEnemySprite = new Sprite(image, 80, 0);
                        Enemy hEnemy = new HorizontalEnemy(j*40, i*40, 40, 40, hEnemySprite, enemyVel, 0);
                        enemies.add(hEnemy);
                        break;
                    case 4:
                        Sprite vEnemySprite = new Sprite(image, 80, 0);
                        Enemy vEnemy = new VerticalEnemy(j*40, i*40, 40, 40, vEnemySprite, 0, enemyVel);
                        enemies.add(vEnemy);
                        break;
                    case 5:
                        Sprite keySprite = new Sprite(image, 120, 0);
                        key = new Key(j*40,i*40,40,40, keySprite);
                        break;
                    case 6:
                        Sprite doorSprite = new Sprite(image, 160, 0);
                        door = new Door(j*40,i*40,40,40, doorSprite);
                        break;
                    case 7:
                        Sprite avatarSprite = new Sprite(image, 0, 40);
                        avatar = new Avatar(j*40, i*40, 30, 30, avatarSprite, 0, 0);
                        break;
                    default:
                        break;
                }
            }
        }
    }

    /**
     * Gets the {@code Avatar} associated with this level.
     */
    public Avatar getAvatar() {
        return this.avatar;
    }

    /**
     * Gets the {@code Key} associated with this level.
     */
    public Key getKey(){
        return this.key;
    }

    /**
     * Gets the {@code Door} associated with this level.
     */
    public Door getDoor() {
        return door;
    }

    /**
     * Gets an {@code ArrayList<>} containing the walls associated with this level.
     */
    public ArrayList<Wall> getWalls() {
        return this.walls;
    }

    /**
     * Gets an {@code ArrayList<>} containing the coins associated with this level.
     */
    public ArrayList<Coin> getCoins() {
        return this.coins;
    }

    /**
     * Gets an {@code ArrayList<>} containing the enemies associated with this level.
     */
    public ArrayList<Enemy> getEnemies() {
        return this.enemies;
    }
}

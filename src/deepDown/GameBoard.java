package deepDown;

import deepDown.gameObjects.*;
import deepDown.gameObjects.enemy.Enemy;
import deepDown.gameObjects.enemy.HorizontalEnemy;
import deepDown.gameObjects.enemy.VerticalEnemy;
import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.Scanner;

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
     * Constructor for the GameBoard
     * @param level What level to load
     * @param enemyVel The enemiy's velocity on the level
     */
    public GameBoard (int level , double enemyVel){
        this.level = level;
        this.enemyVel = enemyVel;
    }

    public void initializeGameBoard(){
        readImage();
        readLevel();
        drawGameBoard();
    }

    /**
     * Reads the tileset to use
     */
    private void readImage(){
        image = new Image(getClass().getResourceAsStream("/deepDown/resource/images/DeepDownTileSet.png"));
    }

    /**
     * Reads what level to load depending on the level variable specified in the constructor
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
                        Coin coin = new Coin(j*40, i*40, 40, 40, coinSprite, false);
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
                        key = new Key(j*40,i*40,40,40, keySprite, false);
                        break;
                    case 6:
                        Sprite doorSprite = new Sprite(image, 160, 0);
                        door = new Door(j*40,i*40,40,40, doorSprite, false);
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
     * Getter for the {@code Avatar} for the level
     * @return avatar
     */
    public Avatar getAvatar() {
        return this.avatar;
    }

    /**
     * Getter for the {@code Key} for the level
     * @return key
     */
    public Key getKey(){
        return this.key;
    }

    /**
     * Getter for the {@code Door} for the level
     * @return door
     */
    public Door getDoor() {
        return door;
    }

    /**
     * Getter for the {@code ArrayList<Wall>} containing all the
     * walls on the level
     * @return walls
     */
    public ArrayList<Wall> getWalls() {
        return this.walls;
    }

    /**
     * Getter for the {@code ArrayList<Coins>} containing all the
     * coins on the level
     * @return coins
     */
    public ArrayList<Coin> getCoins() {
        return this.coins;
    }

    /**
     * Getter for the {@code ArrayList<Enemy>} containing all the
     * enemies on the level
     * @return enemies
     */
    public ArrayList<Enemy> getEnemies() {
        return this.enemies;
    }
}

package deepDown.level;

import deepDown.gameObjects.*;
import deepDown.gameObjects.enemy.Enemy;
import deepDown.gameObjects.enemy.HorizontalEnemy;
import deepDown.gameObjects.enemy.VerticalEnemy;
import javafx.scene.image.Image;

import java.util.ArrayList;

/**
 * @author Michael Mobæk Thoresen and Ole-Martin Heggen
 */
public class GameBoard {

    private final int level;
    private final double enemyVel;
    private final Image image;
    private Avatar avatar;
    private Key key;
    private Door door;
    private int[][] levelArray;

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
        image = new Image(getClass().getResourceAsStream("/deepDown/resource/images/DeepDownTileSet.png"));
    }

    /**
     * Runs the methods necessary to initialize the gameboard.
     */
    public void initializeGameBoard(){
        if (level < 9){
            String path = "/deepDown/level/levels/level" + Integer.toString(level) + ".txt";
            levelArray = LevelReader.readLevel(path);

        }else {
            levelArray = LevelReader.readCustomLevel();
        }
        drawGameBoard();
    }

    /**
     * Goes through the an {@code int[][]} and makes
     * an object for each {@code Integer} inside
     * the array that is not a 0.
     */
    private void drawGameBoard(){
        for (int i=0; i<levelArray.length; i++){
            for(int j=0; j<levelArray[i].length; j++){

                switch (levelArray[i][j]){
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
     * @return the avatar
     */
    public Avatar getAvatar() {
        return this.avatar;
    }

    /**
     * Gets the {@code Key} associated with this level.
     * @return the key
     */
    public Key getKey(){
        return this.key;
    }

    /**
     * Gets the {@code Door} associated with this level.
     * @return the door
     */
    public Door getDoor() {
        return door;
    }

    /**
     * Gets an {@code ArrayList<>} containing the walls associated with this level.
     * @return the walls arraylist
     */
    public ArrayList<Wall> getWalls() {
        return this.walls;
    }

    /**
     * Gets an {@code ArrayList<>} containing the coins associated with this level.
     * @return the coin array list
     */
    public ArrayList<Coin> getCoins() {
        return this.coins;
    }

    /**
     * Gets an {@code ArrayList<>} containing the enemies associated with this level.
     * @return the enemy array list
     */
    public ArrayList<Enemy> getEnemies() {
        return this.enemies;
    }
}

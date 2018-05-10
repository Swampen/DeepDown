package deepDown.level;

/**
 * @author Ole-Martin Heggen
 */
public class LevelRequirements {

    private static boolean keyLimit = false;
    private static boolean doorLimit = false;
    private static boolean avatarLimit = false;


    public static boolean isKeyLimit() {
        return keyLimit;
    }

    public static void setKeyLimit(boolean keyLimit) {
        LevelRequirements.keyLimit = keyLimit;
    }

    public static boolean isDoorLimit() {
        return doorLimit;
    }

    public static void setDoorLimit(boolean doorLimit) {
        LevelRequirements.doorLimit = doorLimit;
    }

    public static boolean isAvatarLimit() {
        return avatarLimit;
    }

    public static void setAvatarLimit(boolean avatarLimit) {
        LevelRequirements.avatarLimit = avatarLimit;
    }

    public static Boolean isValidLevel(int[][] levelArray){
        int coinCount = 0;
        int enemyCount = 0;
        for (int i = 1; i < levelArray.length - 1; i++) {
            for (int j = 1; j < levelArray[i].length - 1; j++) {
                if (levelArray[i][j] == 2){
                    coinCount++;
                }else if (levelArray[i][j] == 3 || levelArray[i][j] == 4){
                    enemyCount++;
                }
            }
        }
        return coinCount > 0 && enemyCount > 0 && keyLimit && doorLimit && avatarLimit;
    }
}

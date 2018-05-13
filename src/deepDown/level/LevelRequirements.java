package deepDown.level;

/**
 * @author Ole-Martin Heggen
 */
public class LevelRequirements {

    private static boolean keyLimit = false;
    private static boolean doorLimit = false;
    private static boolean avatarLimit = false;

    /**
     * Gets the value of the property keyLimit.
     * @return {@code true} if the key limit is reached, {@code false} otherwise.
     */
    public static boolean isKeyLimit() {
        return keyLimit;
    }


    /**
     * Sets the value of the property keyLimit.
     * @param keyLimit Defines if the key limit has been reached.
     */
    public static void setKeyLimit(boolean keyLimit) {
        LevelRequirements.keyLimit = keyLimit;
    }

    /**
     * Gets the value of the property doorLimit.
     * @return {@code true} if the dor limit is reached, {@code false} otherwise.
     */
    public static boolean isDoorLimit() {
        return doorLimit;
    }

    /**
     * Sets the value of the property doorLimit.
     * @param doorLimit Defines if the door limit has been reached.
     */
    public static void setDoorLimit(boolean doorLimit) {
        LevelRequirements.doorLimit = doorLimit;
    }

    /**
     * Gets the value of the property avatarLimit.
     * @return {@code true} if the avatar limit is reached,
     * {@code false} by default.
     */
    public static boolean isAvatarLimit() {
        return avatarLimit;
    }

    /**
     * Sets the value of the property avatarLimit.
     * @param avatarLimit Defines if the avatar limit has been reached.
     */
    public static void setAvatarLimit(boolean avatarLimit) {
        LevelRequirements.avatarLimit = avatarLimit;
    }

    public static Boolean isValidLevel(int[][] levelArray){
        return keyLimit && doorLimit && avatarLimit;
    }
}

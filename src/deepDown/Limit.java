package deepDown;


public class Limit {

    private static boolean keyLimit = false;
    private static boolean doorLimit = false;
    private static boolean avatarLimit = false;


    public static boolean isKeyLimit() {
        return keyLimit;
    }

    public static void setKeyLimit(boolean keyLimit) {
        Limit.keyLimit = keyLimit;
    }

    public static boolean isDoorLimit() {
        return doorLimit;
    }

    public static void setDoorLimit(boolean doorLimit) {
        Limit.doorLimit = doorLimit;
    }

    public static boolean isAvatarLimit() {
        return avatarLimit;
    }

    public static void setAvatarLimit(boolean avatarLimit) {
        Limit.avatarLimit = avatarLimit;
    }
}

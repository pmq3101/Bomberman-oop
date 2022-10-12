package constant;

public final class Size {
    public static final double screenWidth = 1350;
    public static final double screenHeight = 750;
    public static final double entitySize = 50;
    public static final int matrixHeight = (int) (Size.screenHeight / Size.entitySize);
    public static final int matrixWidth = (int) (Size.screenWidth / Size.entitySize);
    public static final int maxNumberOfCrackedWall = 80;
    public static final int secondCounter = 60;
}

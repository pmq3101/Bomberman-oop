package bomber_man;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.media.Media;

public class Bomber extends Entity{
    private double speed;
    private int maxNumberOfBombs;
    private int movementAdd;
    private char dir;
    private int level;
    private int life;
    private int points;
    private Media movementSound;
    private Media deathSound;
    private Media placeBombSound;

    public Bomber() {}

    public Bomber(double x, double y, double speed, String fileName, int maxNumberOfBombs, int movementAdd, int level, int life, int points) {
        super(x, y, fileName);
        this.speed = speed;
        this.maxNumberOfBombs = maxNumberOfBombs;
        this.movementAdd = movementAdd;
        this.level = level;
        this.life = life;
        this.points = points;
    }

    public char getDir() {
        return dir;
    }

    public void setDir(char dir) {
        this.dir = dir;
    }

    public int getMovementAdd() {
        return movementAdd;
    }

    public void setMovementAdd(int movementAdd) {
        this.movementAdd = movementAdd;
    }

    public double getSpeed() {
        return speed;
    }

    public int getMaxNumberOfBombs() {
        return maxNumberOfBombs;
    }

    public void setMaxNumberOfBombs(int maxNumberOfBombs) {
        this.maxNumberOfBombs = maxNumberOfBombs;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getLife() {
        return life;
    }

    public void setLife(int life) {
        this.life = life;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }
}

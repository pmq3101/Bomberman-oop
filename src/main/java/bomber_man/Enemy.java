package bomber_man;

import constant.FileName;
import constant.Size;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicReferenceArray;

public class Enemy extends Entity {
    private double speed;
    private Entity radius;
    private Entity checkBombRadius;
    private char dir;
    private int second;

    public Enemy() {}

    public Enemy(double x, double y, double speed, String filename, char dir, String radiusFilename) {
        super(x, y, filename);
        this.speed = speed;
        this.dir = dir;
        Image image = new Image(radiusFilename);
        this.radius = new Entity(x - (image.getWidth() - Size.entitySize) / 2, y - (image.getHeight() - Size.entitySize) / 2, radiusFilename);
        if(radiusFilename.equals(FileName.ovapiRadiusFileName)) {
            Image checkBombImage = new Image(FileName.checkBombRadiusFileName);
            this.checkBombRadius = new Entity(x - (checkBombImage.getWidth() - Size.entitySize) / 2, y - (checkBombImage.getHeight() - Size.entitySize) / 2, FileName.checkBombRadiusFileName);
        }
    }
    public void setRadius() {
        radius.setX(super.getX() - (radius.getWidth() - Size.entitySize) / 2);
        radius.setY(super.getY() - (radius.getHeight() - Size.entitySize) / 2);
    }
    public void setCheckBombRadius() {
        checkBombRadius.setX(super.getX() - (checkBombRadius.getWidth() - Size.entitySize) / 2);
        checkBombRadius.setY(super.getY() - (checkBombRadius.getHeight() - Size.entitySize) / 2);
    }
    public Entity getCheckBombRadius() {
        return checkBombRadius;
    }
    public Entity getRadius() {
        return radius;
    }
    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public char getDir() {
        return dir;
    }

    public void setDir(char dir) {
        this.dir = dir;
    }

    public int getSecond() {
        return second;
    }

    public void setSecond(int second) {
        this.second = second;
    }

    public void status(String[] fileName) {
        second++;
        if(second == 10) super.setImage(fileName[1]);
        else if(second == 10 * 2) super.setImage(fileName[2]);
        else if(second == 10 * 3) super.setImage(fileName[3]);
        else if(second == 10 * 4) {
            super.setImage(fileName[0]);
            second = 1;
        }
    }
}


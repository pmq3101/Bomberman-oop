package bomber_man;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Entity {
    private double x;
    private double y;
    private double width;
    private double height;
    private Image image;

    public Entity() {}

    public Entity(double x, double y, String fileName) {
        image = new Image(fileName);
        this.x = x;
        this.y = y;
        this.width = image.getWidth();
        this.height = image.getHeight();
    }

//    public Entity(double x, double y, Image image) {
//        this.image = image;
//        this.x = x;
//        this.y = y;
//        this.width = image.getWidth();
//        this.height = image.getHeight();
//    }

    public boolean isCollide(Entity other) {
        return !((this.x + this.width <= other.x) ||
                (other.x + other.width <= this.x) ||
                (this.y + this.height <= other.y) ||
                (other.y + other.height <= this.y));
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(String fileName) {
        this.image = new Image(fileName);
        this.width = image.getWidth();
        this.height = image.getHeight();
    }

    public void render(GraphicsContext context) {
        context.drawImage(image, x, y);
    }
}

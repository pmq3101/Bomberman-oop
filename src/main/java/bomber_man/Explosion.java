package bomber_man;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Explosion extends Entity {
    private int second;

    public Explosion() {}

    public Explosion(double x, double y, String fileName, int second) {
        super(x, y, fileName);
        this.second = second;
    }

    public boolean disappear() {
        second++;
        if(second == 20) {
            return true;
        }
        return false;
    }
}

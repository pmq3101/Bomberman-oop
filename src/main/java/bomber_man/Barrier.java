package bomber_man;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Barrier extends Entity {
    private char type; /* 'N' - normal   'C' - cracked 'B' - bombed  'A' - after bomb */

    public Barrier() {}

    public Barrier(double x, double y, String fileName, char type) {
        super(x, y, fileName);
        this.type = type;
    }

    public char getType() {
        return type;
    }

    public void setType(char type) {
        this.type = type;
    }
}

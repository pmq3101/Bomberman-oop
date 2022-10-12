package bomber_man;

import javafx.scene.media.Media;

public class PowerUp extends Entity {
    private int type;
    private Media powerUpSound;

    public PowerUp() {}

    public PowerUp(double x, double y, String fileName, int type) {
        super(x, y, fileName);
        this.type = type;
    }

    public int getType() {
        return type;
    }
}

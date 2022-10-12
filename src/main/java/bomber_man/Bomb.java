package bomber_man;

import constant.FileName;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.media.Media;

public class Bomb extends Entity{
    private int second;
    private Media explosionSound;

    public Bomb() {}

    public Bomb(double x, double y, String fileName, int second) {
        super(x, y, fileName);
        this.second = second;
    }

    public boolean checkStatus() {
        second++;
        if(second >= 160) {
            return true;
        } else {
            if(second % 20 == 0) {
                super.setImage(FileName.bombFileName[second / 20]);
            }
            return false;
        }
    }
}

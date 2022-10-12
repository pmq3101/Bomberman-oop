package event;

import bomber_man.Bomber;
import bomber_man.Disappearance;
import bomber_man.Enemy;
import bomber_man.Explosion;
import constant.FileName;
import constant.GameMedia;

import java.util.ArrayList;

public final class Death {
    public static final void handleBombDeath(Bomber bomber, ArrayList<Explosion> explosions, Disappearance bomberDisappearance) {
        for(Explosion i : explosions) {
            if(bomber.isCollide(i)) {
                GameMedia.setDeathSound();
                GameMedia.getDeathSound().play();
                bomber.setLife(bomber.getLife() - 1);
                bomberDisappearance.setX(bomber.getX());
                bomberDisappearance.setY(bomber.getY());
                bomberDisappearance.setImage(FileName.bomberDeathFileName[0]);
                bomber.setX(0);
                bomber.setY(0);
            }
        }
    }

    public static final void handleTouchEnemyDeath(Bomber bomber, ArrayList<Enemy> enemies, Disappearance bomberDisappearance) {
        for(Enemy i : enemies) {
            if(bomber.isCollide(i)) {
                GameMedia.setDeathSound();
                GameMedia.getDeathSound().play();
                bomber.setLife(bomber.getLife() - 1);
                bomberDisappearance.setX(bomber.getX());
                bomberDisappearance.setY(bomber.getY());
                bomberDisappearance.setImage(FileName.bomberDeathFileName[0]);
                bomber.setX(0);
                bomber.setY(0);
            }
        }
    }
}

package event;

import bomber_man.Bomber;
import bomber_man.PowerUp;
import constant.GameMedia;

import java.util.ArrayList;

public final class PowerUpEvent {
    public static final void getPowerUp(Bomber bomber, ArrayList<PowerUp> powerUps) {
        for(int i = 0; i < powerUps.size(); i++) {
            if(bomber.isCollide(powerUps.get(i))) {
                GameMedia.setGetPowerUpSound();
                GameMedia.getGetPowerUpSound().play();
                if(powerUps.get(i).getType() == 0) {
                    bomber.setSpeed(3);
                } else if(powerUps.get(i).getType() == 1) {
                    bomber.setMaxNumberOfBombs(2);
                }
                powerUps.remove(i);
                i--;
            }
        }
    }
}

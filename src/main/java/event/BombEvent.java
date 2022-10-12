package event;

import bomber_man.*;
import constant.FileName;
import constant.GameMedia;
import constant.Size;
import javafx.scene.image.Image;
import javafx.scene.media.Media;

import java.util.ArrayList;

public final class BombEvent {
    public static final void handleBombEvent(ArrayList<Bomb> bombs, ArrayList<Explosion> explosions, ArrayList<Barrier> barriers, ArrayList<Disappearance> wallDisappearances, ArrayList<Disappearance> explosionDisappearances) {
        for(int i = 0; i < bombs.size(); i++) {
            if(bombs.get(i).checkStatus()) { /* check explosion */
                GameMedia.setBombExplosionSound();
                GameMedia.getBombExplosionSound().play();
                explosions.add(new Explosion(bombs.get(i).getX(), bombs.get(i).getY(), FileName.invisible, 0));
                explosionDisappearances.add(new Disappearance(bombs.get(i).getX(), bombs.get(i).getY(), FileName.middleExplosionFileName[0], 0, 'M'));

                explosions.add(new Explosion(bombs.get(i).getX() + 15, bombs.get(i).getY() + 50 + 15, FileName.invisibleExplosion, 0));
                explosionDisappearances.add(new Disappearance(bombs.get(i).getX(), bombs.get(i).getY() + 50, FileName.topDownExplosionFileName[0], 0, 'D'));

                explosions.add(new Explosion(bombs.get(i).getX() + 15, bombs.get(i).getY() - 50 + 15, FileName.invisibleExplosion, 0));
                explosionDisappearances.add(new Disappearance(bombs.get(i).getX(), bombs.get(i).getY() - 50, FileName.topUpExplosionFileName[0], 0, 'U'));

                explosions.add(new Explosion(bombs.get(i).getX() + 50 + 15, bombs.get(i).getY() + 15, FileName.invisibleExplosion, 0));
                explosionDisappearances.add(new Disappearance(bombs.get(i).getX() + 50, bombs.get(i).getY(), FileName.topRightExplosionFileName[0], 0, 'R'));

                explosions.add(new Explosion(bombs.get(i).getX() - 50 + 15, bombs.get(i).getY() + 15, FileName.invisibleExplosion, 0));
                explosionDisappearances.add(new Disappearance(bombs.get(i).getX() - 50, bombs.get(i).getY(), FileName.topLeftExplosionFileName[0], 0, 'L'));

                Main.map[(int) (bombs.get(i).getY() / Size.entitySize)][(int) (bombs.get(i).getX() / Size.entitySize)] = 0;
                barriers.remove(barriers.size() - bombs.size());
                bombs.remove(i);
                i--;
            }
        }
        for(int i = 0; i < explosionDisappearances.size(); i++) {
            for(int j = 0; j < barriers.size(); j++) {
                if(barriers.get(j).getType() != 'A' && barriers.get(j).isCollide(explosionDisappearances.get(i))) {
                    explosionDisappearances.remove(i);
                    i--;
                    break;
                }
            }
        }
        for(int i = 0; i < explosions.size(); i++) {
            for(int j = 0; j < barriers.size(); j++) {
                if(barriers.get(j).getType() != 'A' && barriers.get(j).isCollide(explosions.get(i))) {
                    if(barriers.get(j).getType() == 'C') {
                        Main.map[(int) (barriers.get(j).getY() / Size.entitySize)][(int) (barriers.get(j).getX() / Size.entitySize)] = 0;
                        wallDisappearances.add(new Disappearance(barriers.get(j).getX(), barriers.get(j).getY(), FileName.wallDisappearanceFileName[0], 0, '0'));
                        barriers.remove(j);
                        j--;
                    }
                    explosions.remove(i);
                    i--;
                }
            }
            if(explosions.get(i).disappear()) {
                explosions.remove(i);
                i--;
            }
        }
    }
}

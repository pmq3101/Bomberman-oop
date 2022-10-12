package event;

import bomber_man.Bomber;
import bomber_man.Disappearance;
import bomber_man.Menu;
import constant.FileName;

import java.util.ArrayList;

public final class DisappearanceEvent {
    public static final void handleBarrierDisappearance(ArrayList<Disappearance> wallDisappearances) {
        for(int i = 0; i < wallDisappearances.size(); i++) {
            int j = wallDisappearances.get(i).getStatus();
            if(j == 30) {
                wallDisappearances.remove(i);
                i--;
            } else if(j % 5 == 0) {
                wallDisappearances.get(i).setImage(FileName.wallDisappearanceFileName[j / 5]);
            }
        }
    }

    public static final void handleExplosionDisappearance(ArrayList<Disappearance> explosionDisappearances) {
        for(int i = 0; i < explosionDisappearances.size(); i++) {
            int j = explosionDisappearances.get(i).getStatus();
            if(j == 35) {
                explosionDisappearances.remove(i);
                i--;
            } else if(j % 5 == 0) {
                if(explosionDisappearances.get(i).getDir() == 'U') {
                    explosionDisappearances.get(i).setImage(FileName.topUpExplosionFileName[j / 5]);
                } else if(explosionDisappearances.get(i).getDir() == 'D') {
                    explosionDisappearances.get(i).setImage(FileName.topDownExplosionFileName[j / 5]);
                } else if(explosionDisappearances.get(i).getDir() == 'L') {
                    explosionDisappearances.get(i).setImage(FileName.topLeftExplosionFileName[j / 5]);
                } else if(explosionDisappearances.get(i).getDir() == 'R') {
                    explosionDisappearances.get(i).setImage(FileName.topRightExplosionFileName[j / 5]);
                } else if(explosionDisappearances.get(i).getDir() == 'M') {
                    explosionDisappearances.get(i).setImage(FileName.middleExplosionFileName[j / 5]);
                }
            }
        }
    }

    public static final void handleEnemyDisappearance(ArrayList<Disappearance> enemyDisappearances) {
        for(int i = 0; i < enemyDisappearances.size(); i++) {
            int j = enemyDisappearances.get(i).getStatus();
            if(j == 70) {
                enemyDisappearances.remove(i);
                i--;
            } else if(j % 10 == 0) {
                if(j >= 30) enemyDisappearances.get(i).setImage(FileName.balloonDeathFileName[(j - 20) / 10]);
            }
        }
    }

    public static final void handleBomberDisappearance(Disappearance bomberDisappearance, ArrayList<Menu> stageMenu, ArrayList<Menu> mainMenu, Bomber bomber) {
        if(bomberDisappearance.getX() != 0) {
            int i = bomberDisappearance.getStatus();
            if (i == 90) {
                bomberDisappearance.setImage(FileName.invisible);
                bomberDisappearance.setY(0);
                bomberDisappearance.setX(0);
                bomberDisappearance.setSecond(0);
                if(bomber.getLife() <= 0) {
                    MenuEvent.setCheckStageMenu(false);
                    MenuEvent.setCount(0);
                    stageMenu.add((new Menu(0, 0, FileName.loseFileName, 0)));
                } else {
                    MenuEvent.setCheckStageMenu(false);
                    MenuEvent.setCount(0);
                    stageMenu.add((new Menu(0, 0, FileName.levelFileName[bomber.getLevel() - 1], 2)));
                }
            } else if (i % 10 == 0) {
                if (i >= 30) bomberDisappearance.setImage(FileName.bomberDeathFileName[(i - 20) / 10]);
            }
        }
    }
}

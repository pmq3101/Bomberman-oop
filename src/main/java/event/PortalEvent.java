package event;

import bomber_man.*;
import constant.FileName;
import constant.GameMedia;

import java.util.ArrayList;

public final class PortalEvent {
    static int second = 0;
    public static void handlePortalEvent(Portal portal, ArrayList<Explosion> explosions, ArrayList<Enemy> oneals, ArrayList<Enemy> balloons, ArrayList<String> input, Bomber bomber, ArrayList<Menu> stageMenu) {
        if (portal.isCovered() < 3) {
            for (int i = 0; i < explosions.size(); i++) {
                if (portal.isCollide(explosions.get(i))) {
                    if (portal.isCovered() == 0) portal.setCovered(1);
                    else {
                        portal.setCovered(2);
                    }
                }
            }
            if(portal.isCovered() == 2) {
                second++;
                if(second == 20) {
                    oneals.add(new Enemy(portal.getX(), portal.getY(), 1, FileName.movingLeftOnealFileName[0], '0', FileName.onealRadiusFileName));
                    oneals.add(new Enemy(portal.getX(), portal.getY(), 1, FileName.movingLeftOnealFileName[0], '0', FileName.onealRadiusFileName));
                    oneals.add(new Enemy(portal.getX(), portal.getY(), 1, FileName.movingLeftOnealFileName[0], '0', FileName.onealRadiusFileName));
                    portal.setCovered(3);
                    second = 0;
                }
            }
        }
        if(bomber.isCollide(portal)) {
//            if(balloons.isEmpty() && oneals.isEmpty()) {
                if(input.contains("ENTER")) {
                    if(bomber.getLevel() == 1) {
                        bomber.setLevel(bomber.getLevel() + 1);
                        MenuEvent.setCheckStageMenu(false);
                        MenuEvent.setCount(0);
                        stageMenu.add((new Menu(0, 0, FileName.levelFileName[1], 2)));
                    } else if(bomber.getLevel() == 2) {
//                        GameMedia.setNextLevelSound();
//                        GameMedia.getNextLevelSound().play();
                        MenuEvent.setCheckStageMenu(false);
                        MenuEvent.setCount(0);
                        stageMenu.add((new Menu(0, 0, FileName.winFileName, 1)));
                    }
                }
//            }
        }
    }
}

package event;

import bomber_man.Bomber;
import bomber_man.Counter;
import bomber_man.Enemy;
import constant.FileName;
import constant.Size;

import java.util.ArrayList;

public final class CounterEvent {
    private static boolean counterIsStop = false;
    public static final void handleTimerEvent(Counter timer, ArrayList<Enemy> ovapi) {
        if(counterIsStop == false) timer.setComputerCounter(timer.getComputerCounter() - 1);
        if(timer.getComputerCounter() < 0) counterIsStop = true;
        else if(timer.getComputerCounter() > 0) {
            counterIsStop = false;
            if (timer.getComputerCounter() % Size.secondCounter == 0) {
                timer.setConvertedCounter(timer.getComputerCounter() / Size.secondCounter);
                timer.updateUnits();
            }
        } else {
            spawnOvapi(ovapi);
            timer.setConvertedCounter(0);
            timer.updateUnits();
        }
    }

    private static void spawnOvapi(ArrayList<Enemy> ovapi) {
        for(int i = 1; i <= 3; i++) {
            ovapi.add(new Enemy((i * 5) * Size.entitySize + (i - 1) * Size.entitySize, Size.entitySize, 2, FileName.movingLeftOvapiFileName[0], 'D', FileName.ovapiRadiusFileName));
        }
        for(int i = 1; i <= 3; i++) {
            ovapi.add(new Enemy((i * 5) * Size.entitySize + (i - 1) * Size.entitySize, Size.screenHeight - 2 * Size.entitySize, 2, FileName.movingLeftOvapiFileName[0], 'A', FileName.ovapiRadiusFileName));
        }
    }

    public static final void handlePointsEvent(Counter points, Bomber bomber) {
        points.setConvertedCounter(bomber.getPoints());
        points.updateUnits();
    }
}

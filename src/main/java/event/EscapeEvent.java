package event;

import bomber_man.Bomber;
import bomber_man.Menu;
import constant.FileName;
import constant.GameMedia;

import java.util.ArrayList;

public final class EscapeEvent {
    public static final void handleEscapeEvent(ArrayList<String> input, Bomber bomber, ArrayList<Menu> mainMenu, ArrayList<Menu> stageMenu) {
        if(input.contains("R")) {
            GameMedia.getLevelStartSound().stop();
            MenuEvent.setCheckStageMenu(false);
            MenuEvent.setCount(0);
            stageMenu.add((new Menu(0, 0, FileName.restartFileName, 0)));
        }
    }
}

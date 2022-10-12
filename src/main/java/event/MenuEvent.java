package event;

import bomber_man.*;
import constant.FileName;
import constant.GameMedia;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import set_up.SetUp;

import java.util.ArrayList;

public final class MenuEvent {
    private static boolean checkMainMenu = false;
    private static boolean checkStageMenu = false;
    private static int count = 0;
    public static final boolean handleMainMenuEvent(ArrayList<Menu> mainMenu, ArrayList<Menu> stageMenu, ArrayList<String> input, Stage stage) {
        if(GameMedia.getMainMenuSound() == null) {
            if(GameMedia.getThemeSound() != null) {
                GameMedia.getThemeSound().stop();
                GameMedia.setThemeSoundToNull();
            }
            GameMedia.setMainMenuSound();
            GameMedia.getMainMenuSound().play();
        } else {
            if(GameMedia.getMainMenuSound().getCurrentTime().equals(GameMedia.getMainMenuSound().getStopTime())) {
                GameMedia.setMainMenuSoundToNull();
            }
        }
        if(input.contains("ENTER")) {
            checkMainMenu = true;
        }
        if(checkMainMenu == false) {
            if (input.contains("ESCAPE")) {
                stage.close();
            }
        }
        if(checkMainMenu == true) {
            if(!mainMenu.isEmpty()) {
                mainMenu.remove(0);
                GameMedia.getMainMenuSound().stop();
                checkStageMenu = false;
                count = 0;
                stageMenu.add((new Menu(0, 0, FileName.levelFileName[0], 2)));
            }
            return false;
        }
        return true;
    }

    public static final boolean handleStage(ArrayList<Menu> stageMenu, ArrayList<Barrier> barriers, ArrayList<Enemy> balloons,
                                            ArrayList<Enemy> oneals, ArrayList<Enemy> ovapis, Portal portal, Bomber bomber, ArrayList<Bomb> bombs,
                                            ArrayList<Menu> mainMenu, Counter timer, Counter points, ArrayList<PowerUp> powerUps,
                                            ArrayList<Explosion> explosions, ArrayList<Disappearance> enemyDisappearances,
                                            ArrayList<Disappearance> explosionDisappearances, ArrayList<Disappearance> wallDisappearances) {
        if(count == 0) {
            if(GameMedia.getThemeSound() != null) {
                GameMedia.getThemeSound().stop();
                GameMedia.setThemeSoundToNull();
            }
            if(stageMenu.get(0).getImage().getUrl().equals(new Image(FileName.winFileName).getUrl())) {
                GameMedia.setWinSound();
                GameMedia.getWinSound().play();
            } else if(stageMenu.get(0).getImage().getUrl().equals(new Image(FileName.loseFileName).getUrl())) {
                GameMedia.setGameOverSound();
                GameMedia.getGameOverSound().play();
            } else if(stageMenu.get(0).getImage().getUrl().equals(new Image(FileName.restartFileName).getUrl())) {
                GameMedia.setRestartSound();
                GameMedia.getRestartSound().play();
            } else {
                GameMedia.setLevelStartSound();
                GameMedia.getLevelStartSound().play();
            }
        }
        count++;
        if (count == 140) {
            if (stageMenu.get(0).getWin() == 0 || stageMenu.get(0).getWin() == 1) {
                GameMedia.setMainMenuSoundToNull();
                checkStageMenu = true;
                checkMainMenu = false;
                mainMenu.add(new Menu(0, 0, FileName.mainMenuFileName, 2));
                bomber.setLife(3);
                bomber.setLevel(1);
                bomber.setX(50);
                bomber.setY(50);
                bomber.setSpeed(2);
                bomber.setMaxNumberOfBombs(1);
                bomber.setPoints(0);
                CounterEvent.handlePointsEvent(points, bomber);
                SetUp.cleanUp(barriers, balloons, oneals, ovapis, bombs, powerUps, explosions, enemyDisappearances, explosionDisappearances, wallDisappearances);
                SetUp.gameSetUpLevel1(barriers, balloons, portal, timer, points, powerUps);
            } else {
                checkStageMenu = true;
                if (bomber.getLevel() == 1) {
                    SetUp.cleanUp(barriers, balloons, oneals, ovapis, bombs, powerUps, explosions, enemyDisappearances, explosionDisappearances, wallDisappearances);
                    SetUp.gameSetUpLevel1(barriers, balloons, portal, timer, points, powerUps);
                } else {
                    SetUp.cleanUp(barriers, balloons, oneals, ovapis, bombs, powerUps, explosions, enemyDisappearances, explosionDisappearances, wallDisappearances);
                    SetUp.gameSetUpLevel2(barriers, balloons, oneals, portal, timer, points, powerUps);
                }
                bomber.setX(50);
                bomber.setY(50);
                bomber.setSpeed(2);
                bomber.setMaxNumberOfBombs(1);
                bomber.setImage(FileName.movingLeftFileName[0]);
            }
        }
        if (checkStageMenu == true) {
            if (!stageMenu.isEmpty()) {
                stageMenu.remove(0);
            }
            return false;
        }
        return true;
    }

    public static void setCheckStageMenu(boolean checkStageMenu) {
        MenuEvent.checkStageMenu = checkStageMenu;
    }

    public static void setCount(int count) {
        MenuEvent.count = count;
    }
}

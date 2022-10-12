package constant;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;

public final class GameMedia {

    private static MediaPlayer placeBombSound;
    public static MediaPlayer getPlaceBombSound() {
        return placeBombSound;
    }
    public static void setPlaceBombSound() {
        placeBombSound = null;
        placeBombSound = new MediaPlayer(new Media(new File(FileName.placeBombSoundFileName).toURI().toString()));
    }

    private static MediaPlayer bombExplosionSound;
    public static MediaPlayer getBombExplosionSound() {
        return bombExplosionSound;
    }
    public static void setBombExplosionSound() {
        bombExplosionSound = null;
        bombExplosionSound = new MediaPlayer(new Media(new File(FileName.bombExplosionSoundFileName).toURI().toString()));
    }

    private static MediaPlayer getPowerUpSound;
    public static MediaPlayer getGetPowerUpSound() {
        return getPowerUpSound;
    }
    public static void setGetPowerUpSound() {
        getPowerUpSound = null;
        getPowerUpSound = new MediaPlayer(new Media(new File(FileName.getPowerUpSoundFileName).toURI().toString()));
    }

    private static MediaPlayer winSound;
    public static MediaPlayer getWinSound() {
        return winSound;
    }
    public static void setWinSound() {
        winSound = null;
        winSound = new MediaPlayer(new Media(new File(FileName.winSoundFileName).toURI().toString()));
    }


    private static MediaPlayer levelStartSound;
    public static MediaPlayer getLevelStartSound() {
        return levelStartSound;
    }
    public static void setLevelStartSound() {
        levelStartSound = null;
        levelStartSound = new MediaPlayer(new Media(new File(FileName.levelStartSoundFileName).toURI().toString()));
    }

    private static MediaPlayer leftRightWalkSound;
    public static MediaPlayer getLeftRightWalkSound() {
        return leftRightWalkSound;
    }
    public static void setLeftRightWalkSound() {
        leftRightWalkSound = null;
        leftRightWalkSound = new MediaPlayer(new Media(new File(FileName.leftRightWalkSoundFileName).toURI().toString()));
    }

    private static MediaPlayer upDownWalkSound;
    public static MediaPlayer getUpDownWalkSound() {
        return upDownWalkSound;
    }
    public static void setUpDownWalkSound() {
        upDownWalkSound = null;
        upDownWalkSound = new MediaPlayer(new Media(new File(FileName.upDownWalkSoundFileName).toURI().toString()));
        upDownWalkSound.setVolume(upDownWalkSound.getVolume() + 1);
    }

    private static MediaPlayer deathSound;
    public static MediaPlayer getDeathSound() {
        return deathSound;
    }
    public static void setDeathSound() {
        deathSound = null;
        deathSound = new MediaPlayer(new Media(new File(FileName.deathSoundFileName).toURI().toString()));
    }

    private static MediaPlayer gameOverSound;
    public static MediaPlayer getGameOverSound() {
        return gameOverSound;
    }
    public static void setGameOverSound() {
        gameOverSound = null;
        gameOverSound = new MediaPlayer(new Media(new File(FileName.gameOverSoundFileName).toURI().toString()));
    }

    private static MediaPlayer restartSound;
    public static MediaPlayer getRestartSound() {
        return restartSound;
    }
    public static void setRestartSound() {
        restartSound = null;
        restartSound = new MediaPlayer(new Media(new File(FileName.restartSoundFileName).toURI().toString()));
    }

    private static MediaPlayer mainMenuSound;
    public static MediaPlayer getMainMenuSound() {
        return mainMenuSound;
    }
    public static void setMainMenuSound() {
        mainMenuSound = null;
        mainMenuSound = new MediaPlayer(new Media(new File(FileName.mainMenuSoundFileName).toURI().toString()));
    }
    public static void setMainMenuSoundToNull() {
        mainMenuSound = null;
    }

    private static MediaPlayer themeSound;
    public static MediaPlayer getThemeSound() {
        return themeSound;
    }
    public static void setThemeSound() {
        themeSound = null;
        themeSound = new MediaPlayer(new Media(new File(FileName.themeSoundFileName).toURI().toString()));
        themeSound.setVolume(themeSound.getVolume() - 0.7);
    }
    public static void setThemeSoundToNull() {
        themeSound = null;
    }
}

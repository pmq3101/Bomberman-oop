package bomber_man;

import constant.*;
import event.*;
import set_up.*;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import java.util.ArrayList;

public class Main extends Application {
    public static int[][] map = new int[Size.matrixHeight][Size.matrixWidth];

    public static void main(String[] args) {
        try{
            launch(args);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.exit(0);
        }
    }

    @Override
    public void start(Stage stage) throws Exception {
        /***** SET UP SCREEN *****/
        stage.setTitle("Bomber man");
        BorderPane root = new BorderPane();
        Scene scene = new Scene(root, Size.screenWidth, Size.screenHeight);
        Canvas canvas = new Canvas(Size.screenWidth, Size.screenHeight);
        GraphicsContext context = canvas.getGraphicsContext2D();
        root.setCenter(canvas);
        Image gameIcon = new Image(FileName.iconFileName);
        Image backGround = new Image(FileName.backGroundFileName);
        stage.getIcons().add(gameIcon);
        stage.setResizable(false);
        /**************************/

        /***** INITIALIZE *****/
        Bomber bomber = new Bomber(50, 50, 2, FileName.movingLeftFileName[0], 1, 0, 1, 3, 0);
        Counter timer = new Counter();
        Counter points = new Counter();
        Portal portal = new Portal(0, 0, FileName.invisible, 0);
        ArrayList<PowerUp> powerUps = new ArrayList<>();
        ArrayList<Menu> mainMenu = new ArrayList<>();
            mainMenu.add(new Menu(0, 0, FileName.mainMenuFileName, 2));
        ArrayList<Menu> stageMenu = new ArrayList<>();
        ArrayList<Enemy> balloons = new ArrayList<>();
        ArrayList<Enemy> oneals = new ArrayList<>();
        ArrayList<Enemy> ovapis = new ArrayList<>();
        ArrayList<Barrier> barriers = new ArrayList<>();
        ArrayList<Bomb> bombs = new ArrayList<>();
        ArrayList<Explosion> explosions = new ArrayList<>();
        ArrayList<String> input = new ArrayList<>();
        ArrayList<Disappearance> wallDisappearances = new ArrayList<>();
        ArrayList<Disappearance> explosionDisappearances = new ArrayList<>();
        ArrayList<Disappearance> enemyDisappearances = new ArrayList<>();
        Disappearance bomberDisappearance = new Disappearance(0, 0, FileName.invisible, 0, '0');
        /**********************/

        /***** SET UP GAME *****/
        SetUp.inputSetUp(input, scene);
        /***********************/

        AnimationTimer gameLoop = new AnimationTimer() {
            @Override
            public void handle(long nanoTime) {
                /***** HANDLE EVENT *****/
                if(!MenuEvent.handleMainMenuEvent(mainMenu, stageMenu, input, stage)) {
                    if(!MenuEvent.handleStage(stageMenu, barriers, balloons, oneals, ovapis, portal, bomber, bombs, mainMenu, timer, points, powerUps, explosions, enemyDisappearances, explosionDisappearances, wallDisappearances)) {
                        if(GameMedia.getThemeSound() == null) {
                            GameMedia.setThemeSound();
                            GameMedia.getThemeSound().play();
                        } else {
                            if(GameMedia.getThemeSound().getCurrentTime().equals(GameMedia.getThemeSound().getStopTime())) {
                                GameMedia.setThemeSoundToNull();
                            }
                        }
                        BombEvent.handleBombEvent(bombs, explosions, barriers, wallDisappearances, explosionDisappearances);
                        EscapeEvent.handleEscapeEvent(input, bomber, mainMenu, stageMenu);
                        PortalEvent.handlePortalEvent(portal, explosions, oneals, balloons, input, bomber, stageMenu);
                        PowerUpEvent.getPowerUp(bomber, powerUps);
                        if (bomber.getX() != 0) {
                            Movement.handleMovement(input, bomber, barriers, bombs);
                        }
                        EnemyEvent.handleEnemyEvent(balloons, barriers, explosions, enemyDisappearances, bombs, bomber, FileName.movingLeftBalloonFileName, FileName.movingRightBalloonFileName, FileName.balloonDeathFileName, points);
                        EnemyEvent.handleEnemyEvent(oneals, barriers, explosions, enemyDisappearances, bombs, bomber, FileName.movingLeftOnealFileName, FileName.movingRightOnealFileName, FileName.onealDeathFileName, points);
                        EnemyEvent.handleEnemyEvent(ovapis, barriers, explosions, enemyDisappearances, bombs, bomber, FileName.movingLeftOvapiFileName, FileName.movingRightOvapiFileName, FileName.ovapiDeathFileName, points);
                        DisappearanceEvent.handleBarrierDisappearance(wallDisappearances);
                        DisappearanceEvent.handleExplosionDisappearance(explosionDisappearances);
                        DisappearanceEvent.handleEnemyDisappearance(enemyDisappearances);
                        DisappearanceEvent.handleBomberDisappearance(bomberDisappearance, stageMenu, mainMenu, bomber);
                        Death.handleBombDeath(bomber, explosions, bomberDisappearance);
                        Death.handleTouchEnemyDeath(bomber, balloons, bomberDisappearance);
                        Death.handleTouchEnemyDeath(bomber, oneals, bomberDisappearance);
                        Death.handleTouchEnemyDeath(bomber, ovapis, bomberDisappearance);
                        CounterEvent.handleTimerEvent(timer, ovapis);
                        /************************/

                        /***** RENDER ENTITY *****/
                        context.drawImage(backGround, 0, 0);
                        portal.render(context);
                        for(PowerUp i : powerUps) {
                            i.render(context);
                        }
                        bomber.render(context);
                        for (Enemy i : balloons) {
                            i.render(context);
                        }
                        for (Enemy i : oneals) {
                            i.render(context);
                        }
                        for (Explosion i : explosions) {
                            i.render(context);
                        }
                        for (Barrier i : barriers) {
                            i.render(context);
                        }
                        for (Enemy i : ovapis) {
                            i.render(context);
                        }
                        bomberDisappearance.render(context);
                        for (Disappearance i : wallDisappearances) {
                            i.render(context);
                        }
                        for (Disappearance i : explosionDisappearances) {
                            i.render(context);
                        }
                        for (Disappearance i : enemyDisappearances) {
                            i.render(context);
                        }
                        for (Bomb i : bombs) {
                            i.render(context);
                        }
                        timer.render(context);
                        points.render(context);
                        /*************************/
                    } else {
                        for (Menu i : stageMenu) {
                            i.render(context);
                        }
                    }
                } else {
                    for (Menu i : mainMenu) {
                        i.render(context);
                    }
                }
            }
        };

        gameLoop.start();
        stage.setScene(scene);
        stage.show();
    }
}
package set_up;

import bomber_man.*;
import constant.*;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;

import java.util.ArrayList;
import java.util.Random;

public final class SetUp {

    public static final void cleanUp(ArrayList<Barrier> barriers, ArrayList<Enemy> balloons, ArrayList<Enemy> oneals,
                                     ArrayList<Enemy> ovapis, ArrayList<Bomb> bombs, ArrayList<PowerUp> powerUps,
                                     ArrayList<Explosion> explosions, ArrayList<Disappearance> enemyDisappearances,
                                     ArrayList<Disappearance> explosionDisappearances, ArrayList<Disappearance> wallDisappearances) {
        barriers.clear();
        balloons.clear();
        oneals.clear();
        ovapis.clear();
        bombs.clear();
        powerUps.clear();
        explosions.clear();
        enemyDisappearances.clear();
        wallDisappearances.clear();
        explosionDisappearances.clear();
    }

    public static final void inputSetUp(ArrayList<String> input, Scene scene) {
        scene.setOnKeyPressed(
                (KeyEvent event) ->
                {
                    String keyName = event.getCode().toString();
                    if(!input.contains(keyName)) input.add(keyName);
                }
        );
        scene.setOnKeyReleased(
                (KeyEvent event) ->
                {
                    String keyName = event.getCode().toString();
                    input.remove(keyName);
                }
        );
    }

    public static final void gameSetUpLevel2(ArrayList<Barrier> barriers, ArrayList<Enemy> balloons, ArrayList<Enemy> oneals,
                                             Portal portal, Counter timer, Counter points, ArrayList<PowerUp> powerUps) {
        int initialNumberOfCrackedWall = 0;
        boolean checkPortal = false;
        for(int i = 0; i < Size.matrixHeight; i++) {
            for(int j = 0; j < Size.matrixWidth; j++) {
                if(i == 0) {
                    if(j == 17) {
                        barriers.add(new Barrier(j * Size.entitySize, i * Size.entitySize, FileName.leftMenuWallFileName, 'N'));
                        timer.setMaxNumberOfUnits(3);
                        timer.setInitialUnits(j * Size.entitySize + 5, i * Size.entitySize + 5, 200);
                    }
                    else if(j > 17 && j < 25) {
                        if(j == 22) {
                            points.setMaxNumberOfUnits(4);
                            points.setInitialUnits(j * Size.entitySize + 5, i * Size.entitySize + 5, points.getConvertedCounter());
                        }
                        barriers.add(new Barrier(j * Size.entitySize, i * Size.entitySize, FileName.midMenuWallFileName, 'N'));
                    }
                    else if(j == 25) barriers.add(new Barrier(j * Size.entitySize, i * Size.entitySize, FileName.rightMenuWallFileName, 'N'));
                    else barriers.add(new Barrier(j * Size.entitySize, i * Size.entitySize, FileName.normalWallFileName, 'N'));
                    Main.map[i][j] = 2;
                } else if (i == Size.matrixHeight - 1) {
                    barriers.add(new Barrier(j * Size.entitySize, i * Size.entitySize, FileName.normalWallFileName, 'N'));
                    Main.map[i][j] = 2;
                } else if(j == 0 || j == Size.matrixWidth - 1) {
                    barriers.add(new Barrier(j * Size.entitySize, i * Size.entitySize, FileName.normalWallFileName, 'N'));
                    Main.map[i][j] = 2;
                } else if(i % 2 == 0 && j % 2 == 0) {
                    barriers.add(new Barrier(j * Size.entitySize, i * Size.entitySize, FileName.normalWallFileName, 'N'));
                    Main.map[i][j] = 2;
                } else if(initialNumberOfCrackedWall <= Size.maxNumberOfCrackedWall && (i > 3 || j > 3)) {
                    /* 25% cracked-wall spawn rate */
                    double randomDouble = Math.random();
                    randomDouble = randomDouble * 100 + 1;
                    int randomInt = (int) randomDouble;
                    if(randomInt < 25) {
                        barriers.add(new Barrier(j * Size.entitySize, i * Size.entitySize, FileName.crackedWallFileName, 'C'));
                        initialNumberOfCrackedWall++;
                        if(checkPortal == false && initialNumberOfCrackedWall > 20) {
                            portal.setX(j * Size.entitySize);
                            portal.setY(i * Size.entitySize);
                            portal.setImage(FileName.portalFileName);
                            portal.setCovered(0);
                            System.out.println(j * Size.entitySize + " " + i * Size.entitySize);
                            checkPortal = true;
                        }
                        if(powerUps.size() == 0 && initialNumberOfCrackedWall >= 30) {
                            Random rand = new Random();
                            int ranPowerUp = rand.nextInt(FileName.powerUpFileName.length);
                            powerUps.add(new PowerUp(j * Size.entitySize, i * Size.entitySize, FileName.powerUpFileName[ranPowerUp], ranPowerUp));
                            System.out.println(powerUps.get(0).getX() + " " + powerUps.get(0).getY() + " " + powerUps.get(0).getType());
                        }
                        Main.map[i][j] = 1;
                    } else {
                        Main.map[i][j] = 0;
                        /* Spawn enemies */
                        if(i > 5 && j > 5) {
                            if (balloons.size() < 7) {
                                double randomEnemy = Math.random();
                                randomEnemy = randomEnemy * 100 + 1;
                                int randomEnemyInt = (int) randomEnemy;
                                if (randomEnemyInt < 25) {
                                    balloons.add(new Enemy(j * Size.entitySize, i * Size.entitySize, 1, FileName.movingLeftBalloonFileName[0], '0', FileName.balloonRadiusFileName));
                                }
                            }
                        }
                        if(i > 8 && j > 8) {
                            if (oneals.size() < 3) {
                                double randomEnemy = Math.random();
                                randomEnemy = randomEnemy * 100 + 1;
                                int randomEnemyInt = (int) randomEnemy;
                                if (randomEnemyInt < 20) {
                                    oneals.add(new Enemy(j * Size.entitySize, i * Size.entitySize, 1, FileName.movingLeftOnealFileName[0], '0', FileName.onealRadiusFileName));
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    public static final void gameSetUpLevel1(ArrayList<Barrier> barriers, ArrayList<Enemy> balloons, Portal portal,
                                             Counter timer, Counter points , ArrayList<PowerUp> powerUps) {
        int initialNumberOfCrackedWall = 0;
        boolean checkPortal = false;
        for(int i = 0; i < Size.matrixHeight; i++) {
            for(int j = 0; j < Size.matrixWidth; j++) {
                if(i == 0) {
                    if(j == 17) {
                        barriers.add(new Barrier(j * Size.entitySize, i * Size.entitySize, FileName.leftMenuWallFileName, 'N'));
                        timer.setMaxNumberOfUnits(3);
                        timer.setInitialUnits(j * Size.entitySize + 5, i * Size.entitySize + 5, 200);
                    }
                    else if(j > 17 && j < 25) {
                        if(j == 22) {
                            points.setMaxNumberOfUnits(4);
                            points.setInitialUnits(j * Size.entitySize + 5, i * Size.entitySize + 5, points.getConvertedCounter());
                        }
                        barriers.add(new Barrier(j * Size.entitySize, i * Size.entitySize, FileName.midMenuWallFileName, 'N'));
                    }
                    else if(j == 25) barriers.add(new Barrier(j * Size.entitySize, i * Size.entitySize, FileName.rightMenuWallFileName, 'N'));
                    else barriers.add(new Barrier(j * Size.entitySize, i * Size.entitySize, FileName.normalWallFileName, 'N'));
                    Main.map[i][j] = 2;
                } else if (i == Size.matrixHeight - 1) {
                    barriers.add(new Barrier(j * Size.entitySize, i * Size.entitySize, FileName.normalWallFileName, 'N'));
                    Main.map[i][j] = 2;
                } else if(j == 0 || j == Size.matrixWidth - 1) {
                    barriers.add(new Barrier(j * Size.entitySize, i * Size.entitySize, FileName.normalWallFileName, 'N'));
                    Main.map[i][j] = 2;
                } else if(i % 2 == 0 && j % 2 == 0) {
                    barriers.add(new Barrier(j * Size.entitySize, i * Size.entitySize, FileName.normalWallFileName, 'N'));
                    Main.map[i][j] = 2;
                } else if(initialNumberOfCrackedWall <= Size.maxNumberOfCrackedWall && (i > 3 || j > 3)) {
                    /* 30% cracked-wall spawn rate */
                    double randomDouble = Math.random();
                    randomDouble = randomDouble * 100 + 1;
                    int randomInt = (int) randomDouble;
                    if(randomInt < 30) {
                        barriers.add(new Barrier(j * Size.entitySize, i * Size.entitySize, FileName.crackedWallFileName, 'C'));
                        initialNumberOfCrackedWall++;
                        if(checkPortal == false && initialNumberOfCrackedWall > 20) {
                            portal.setX(j * Size.entitySize);
                            portal.setY(i * Size.entitySize);
                            portal.setImage(FileName.portalFileName);
                            portal.setCovered(0);
                            System.out.println(j * Size.entitySize + " " + i * Size.entitySize);
                            checkPortal = true;
                        }
                        if(powerUps.size() == 0 && initialNumberOfCrackedWall >= 30) {
                            Random rand = new Random();
                            int ranPowerUp = rand.nextInt(FileName.powerUpFileName.length);
                            powerUps.add(new PowerUp(j * Size.entitySize, i * Size.entitySize, FileName.powerUpFileName[ranPowerUp], ranPowerUp));
                            System.out.println(powerUps.get(0).getX() + " " + powerUps.get(0).getY() + " " + powerUps.get(0).getType());
                        }
                        Main.map[i][j] = 1;
                    } else {
                        Main.map[i][j] = 0;
                        /* Spawn enemies */
                        if(i > 6 && j > 6) {
                            if (balloons.size() < 5) {
                                double randomEnemy = Math.random();
                                randomEnemy = randomEnemy * 100 + 1;
                                int randomEnemyInt = (int) randomEnemy;
                                if (randomEnemyInt < 20) {
                                    balloons.add(new Enemy(j * Size.entitySize, i * Size.entitySize, 1, FileName.movingLeftBalloonFileName[0], '0', FileName.balloonRadiusFileName));
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

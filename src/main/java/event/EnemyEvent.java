package event;

import bomber_man.*;
import constant.FileName;
import constant.Size;

import java.util.ArrayList;
import java.util.Random;

public final class EnemyEvent {

    private static void chooseDir(Enemy enemy, String enemyIdentification) {
        ArrayList<Character> availableDir = new ArrayList<>();
        if(enemyIdentification.equals(FileName.ovapiDeathFileName[0])) {
            if (Main.map[(int) ((enemy.getY() - Size.entitySize) / Size.entitySize)][(int) (enemy.getX() / Size.entitySize)] != 2) {
                availableDir.add('W');
            }
            if (Main.map[(int) ((enemy.getY() + Size.entitySize) / Size.entitySize)][(int) (enemy.getX() / Size.entitySize)] != 2) {
                availableDir.add('S');
            }
            if (Main.map[(int) (enemy.getY() / Size.entitySize)][(int) ((enemy.getX() - Size.entitySize) / Size.entitySize)] != 2) {
                availableDir.add('A');
            }
            if (Main.map[(int) (enemy.getY() / Size.entitySize)][(int) ((enemy.getX() + Size.entitySize) / Size.entitySize)] != 2) {
                availableDir.add('D');
            }
        } else {
            if (Main.map[(int) ((enemy.getY() - Size.entitySize) / Size.entitySize)][(int) (enemy.getX() / Size.entitySize)] == 0) {
                availableDir.add('W');
            }
            if (Main.map[(int) ((enemy.getY() + Size.entitySize) / Size.entitySize)][(int) (enemy.getX() / Size.entitySize)] == 0) {
                availableDir.add('S');
            }
            if (Main.map[(int) (enemy.getY() / Size.entitySize)][(int) ((enemy.getX() - Size.entitySize) / Size.entitySize)] == 0) {
                availableDir.add('A');
            }
            if (Main.map[(int) (enemy.getY() / Size.entitySize)][(int) ((enemy.getX() + Size.entitySize) / Size.entitySize)] == 0) {
                availableDir.add('D');
            }
        }
        if(availableDir.isEmpty()) {
            enemy.setDir('0');
        } else {
            Random rand = new Random();
            int ranDir = rand.nextInt(availableDir.size());
            enemy.setDir(availableDir.get(ranDir));
        }
    }

    private static ArrayList<Character> unionOfTwoList(ArrayList<Character> availableDir, ArrayList<Character> wayToBomber) {
        ArrayList<Character> finalDir = new ArrayList<>();
        for(int i = 0; i < availableDir.size(); i++) {
            for(int j = 0; j < wayToBomber.size(); j++) {
                if(availableDir.get(i) == wayToBomber.get(j)) {
                    finalDir.add(availableDir.get(i));
                    break;
                }
            }
        }
        return finalDir;
    }

    private static void chooseWayToBomber(Enemy enemy, Bomber bomber, String enemyIdentification) {
        ArrayList<Character> availableDir = new ArrayList<>();
        if((enemy.getY() - Size.entitySize) / Size.entitySize == (int) ((enemy.getY() - Size.entitySize) / Size.entitySize)
                && (enemy.getX() / Size.entitySize) == (int) (enemy.getX() / Size.entitySize)) {
            if(enemyIdentification.equals(FileName.ovapiDeathFileName[0])) {
                if(Main.map[(int) ((enemy.getY() - Size.entitySize) / Size.entitySize)][(int) (enemy.getX() / Size.entitySize)] != 2) {
                    availableDir.add('W');
                }
            } else {
                if (Main.map[(int) ((enemy.getY() - Size.entitySize) / Size.entitySize)][(int) (enemy.getX() / Size.entitySize)] == 0) {
                    availableDir.add('W');
                }
            }
        }
        if((enemy.getY() + Size.entitySize) / Size.entitySize == (int) ((enemy.getY() + Size.entitySize) / Size.entitySize)
                && (enemy.getX() / Size.entitySize) == (int) (enemy.getX() / Size.entitySize)) {
            if(enemyIdentification.equals(FileName.ovapiDeathFileName[0])) {
                if (Main.map[(int) ((enemy.getY() + Size.entitySize) / Size.entitySize)][(int) (enemy.getX() / Size.entitySize)] != 2) {
                    availableDir.add('S');
                }
            } else {
                if (Main.map[(int) ((enemy.getY() + Size.entitySize) / Size.entitySize)][(int) (enemy.getX() / Size.entitySize)] == 0) {
                    availableDir.add('S');
                }
            }
        }
        if(enemy.getY() / Size.entitySize == (int) (enemy.getY() / Size.entitySize)
                && ((enemy.getX() - Size.entitySize) / Size.entitySize) == (int) ((enemy.getX() - Size.entitySize) / Size.entitySize)) {
            if(enemyIdentification.equals(FileName.ovapiDeathFileName[0])) {
                if (Main.map[(int) (enemy.getY() / Size.entitySize)][(int) ((enemy.getX() - Size.entitySize) / Size.entitySize)] != 2) {
                    availableDir.add('A');
                }
            } else {
                if (Main.map[(int) (enemy.getY() / Size.entitySize)][(int) ((enemy.getX() - Size.entitySize) / Size.entitySize)] == 0) {
                    availableDir.add('A');
                }
            }
        }
        if(enemy.getY() / Size.entitySize == (int) (enemy.getY() / Size.entitySize)
                && ((enemy.getX() + Size.entitySize) / Size.entitySize) == (int) ((enemy.getX() + Size.entitySize) / Size.entitySize)) {
            if(enemyIdentification.equals(FileName.ovapiDeathFileName[0])) {
                if (Main.map[(int) (enemy.getY() / Size.entitySize)][(int) ((enemy.getX() + Size.entitySize) / Size.entitySize)] != 2) {
                    availableDir.add('D');
                }
            } else {
                if (Main.map[(int) (enemy.getY() / Size.entitySize)][(int) ((enemy.getX() + Size.entitySize) / Size.entitySize)] == 0) {
                    availableDir.add('D');
                }
            }
        }
        if(availableDir.isEmpty()) {
            return;
        } else {
            ArrayList<Character> wayToBomber = new ArrayList<>();
            if(enemy.getX() + enemy.getWidth() > bomber.getX() + bomber.getWidth()) {
                wayToBomber.add('A');
            } else {
                wayToBomber.add('D');
            }
            if(enemy.getY() + enemy.getHeight() > bomber.getY() + bomber.getHeight()) {
                wayToBomber.add('W');
            } else {
                wayToBomber.add('S');
            }
            ArrayList<Character> finalDir = unionOfTwoList(availableDir, wayToBomber);
            if(finalDir.isEmpty()) return;
            else {
                if(!finalDir.contains(enemy.getDir())) {
                    Random rand = new Random();
                    int ranDir = rand.nextInt(finalDir.size());
                    enemy.setDir(finalDir.get(ranDir));
                }
            }
        }
    }

    public static final void handleEnemyEvent(ArrayList<Enemy> enemies, ArrayList<Barrier> barriers, ArrayList<Explosion> explosions,
                                              ArrayList<Disappearance> disappearances, ArrayList<Bomb> bombs, Bomber bomber, String[] movingLeftFileName,
                                              String[] movingRightFileName, String[] deathFileName, Counter points) {
        for(Enemy enemy : enemies) {
            boolean check = true;
            for(Explosion explosion : explosions) {
                if(enemy.isCollide(explosion)) {
                    disappearances.add(new Disappearance(enemy.getX(), enemy.getY(), deathFileName[0], 0, '0'));
                    if(deathFileName[0] == FileName.balloonDeathFileName[0]) {
                        bomber.setPoints(bomber.getPoints() + 10);
                        CounterEvent.handlePointsEvent(points, bomber);
                    } else if (deathFileName[0] == FileName.onealDeathFileName[0]) {
                        bomber.setPoints(bomber.getPoints() + 20);
                        CounterEvent.handlePointsEvent(points, bomber);
                    } else if (deathFileName[0] == FileName.ovapiDeathFileName[0]) {
                        bomber.setPoints(bomber.getPoints() + 100);
                        CounterEvent.handlePointsEvent(points, bomber);
                    }
                    enemies.remove(enemy);
                    check = false;
                    break;
                }
            }
            if(!check) break;
            if(enemy.getRadius().isCollide(bomber)) {
                chooseWayToBomber(enemy, bomber, deathFileName[0]);
                if(deathFileName[0] == FileName.onealDeathFileName[0]) {
                    enemy.setSpeed(2);
                }
            } else {
                if(deathFileName[0] == FileName.onealDeathFileName[0]) {
                    enemy.setSpeed(1);
                }
            }
            if(deathFileName[0] == FileName.ovapiDeathFileName[0]) {
                for (Bomb bomb : bombs) {
                    if (bomb.isCollide(enemy.getCheckBombRadius())) {
                        enemy.setSpeed(3);
                    } else {
                        enemy.setSpeed(2);
                    }
                }
            }
            if (enemy.getDir() == 'D') {
                enemy.setX(enemy.getX() + enemy.getSpeed());
                enemy.status(movingRightFileName);
                if(movingLeftFileName[0] == FileName.movingLeftOvapiFileName[0]) {
                    for (Barrier i : barriers) {
                        if(i.getType() != 'C') {
                            if (enemy.isCollide(i)) {
                                enemy.setX(enemy.getX() - enemy.getSpeed());
                                chooseDir(enemy, deathFileName[0]);
                            }
                        }
                    }
                } else {
                    for (Barrier i : barriers) {
                        if (enemy.isCollide(i)) {
                            enemy.setX(enemy.getX() - enemy.getSpeed());
                            chooseDir(enemy, deathFileName[0]);
                        }
                    }
                }
            } else if (enemy.getDir() == 'A') {
                enemy.setX(enemy.getX() - enemy.getSpeed());
                enemy.status(movingLeftFileName);
                if(movingLeftFileName[0] == FileName.movingLeftOvapiFileName[0]) {
                    for (Barrier i : barriers) {
                        if (i.getType() != 'C') {
                            if (enemy.isCollide(i)) {
                                enemy.setX(enemy.getX() + enemy.getSpeed());
                                chooseDir(enemy, deathFileName[0]);
                            }
                        }
                    }
                } else {
                    for (Barrier i : barriers) {
                        if (enemy.isCollide(i)) {
                            enemy.setX(enemy.getX() + enemy.getSpeed());
                            chooseDir(enemy, deathFileName[0]);
                        }
                    }
                }
            } else if (enemy.getDir() == 'S') {
                enemy.setY(enemy.getY() + enemy.getSpeed());
                enemy.status(movingRightFileName);
                if(movingLeftFileName[0] == FileName.movingLeftOvapiFileName[0]) {
                    for (Barrier i : barriers) {
                        if (i.getType() != 'C') {
                            if (enemy.isCollide(i)) {
                                enemy.setY(enemy.getY() - enemy.getSpeed());
                                chooseDir(enemy, deathFileName[0]);
                            }
                        }
                    }
                } else {
                    for (Barrier i : barriers) {
                        if (enemy.isCollide(i)) {
                            enemy.setY(enemy.getY() - enemy.getSpeed());
                            chooseDir(enemy, deathFileName[0]);
                        }
                    }
                }
            } else if (enemy.getDir() == 'W') {
                enemy.setY(enemy.getY() - enemy.getSpeed());
                enemy.status(movingLeftFileName);
                if(movingLeftFileName[0] == FileName.movingLeftOvapiFileName[0]) {
                    for (Barrier i : barriers) {
                        if (i.getType() != 'C') {
                            if (enemy.isCollide(i)) {
                                enemy.setY(enemy.getY() + enemy.getSpeed());
                                chooseDir(enemy, deathFileName[0]);
                            }
                        }
                    }
                } else {
                    for (Barrier i : barriers) {
                        if (enemy.isCollide(i)) {
                            enemy.setY(enemy.getY() + enemy.getSpeed());
                            chooseDir(enemy, deathFileName[0]);
                        }
                    }
                }
            } else if (enemy.getDir() == '0') {
                enemy.status(movingLeftFileName);
                ArrayList<Character> availableDir = new ArrayList<>();
                if (Main.map[(int) ((enemy.getY() + Size.entitySize) / Size.entitySize)][(int) (enemy.getX() / Size.entitySize)] == 0) {
                    availableDir.add('S');
                }
                if (Main.map[(int) (enemy.getY() / Size.entitySize)][(int) ((enemy.getX() + Size.entitySize) / Size.entitySize)] == 0) {
                    availableDir.add('D');
                }
                if (Main.map[(int) (enemy.getY() / Size.entitySize)][(int) ((enemy.getX() - Size.entitySize) / Size.entitySize)] == 0) {
                    availableDir.add('A');
                }
                if (Main.map[(int) ((enemy.getY() - Size.entitySize) / Size.entitySize)][(int) (enemy.getX() / Size.entitySize)] == 0) {
                    availableDir.add('W');
                }
                if(!availableDir.isEmpty()) {
                    Random rand = new Random();
                    int ranDir = rand.nextInt(availableDir.size());
                    enemy.setDir(availableDir.get(ranDir));
                }
            }
            enemy.setRadius();
            if(deathFileName[0].equals(FileName.ovapiDeathFileName[0])) enemy.setCheckBombRadius();
        }
    }
}

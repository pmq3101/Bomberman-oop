package event;

import bomber_man.*;
import constant.FileName;
import constant.GameMedia;
import constant.Size;
import javafx.scene.media.MediaPlayer;

import java.util.ArrayList;

public final class Movement {

    private static boolean enable2Bombs = false;
    public static final void handleMovement(ArrayList<String> input, Bomber bomber, ArrayList<Barrier> barriers, ArrayList<Bomb> bombs) {
        if (input.contains("A")) {
            bomber.setX(bomber.getX() - bomber.getSpeed());
            if(bomber.getDir() == 'A') {
                bomber.setMovementAdd(bomber.getMovementAdd() + 1);
                if (bomber.getMovementAdd() == 5) {
                    bomber.setImage(FileName.movingLeftFileName[1]);
                    GameMedia.setLeftRightWalkSound();
                    GameMedia.getLeftRightWalkSound().play();
                }
                else if (bomber.getMovementAdd() == 10) bomber.setImage(FileName.movingLeftFileName[2]);
                else if (bomber.getMovementAdd() == 15) bomber.setImage(FileName.movingLeftFileName[1]);
                else if (bomber.getMovementAdd() == 20) {
                    bomber.setImage(FileName.movingLeftFileName[0]);
                    bomber.setMovementAdd(1);
                }
            } else {
                bomber.setDir('A');
                bomber.setMovementAdd(0);
            }
            for(Barrier i : barriers) {
                if(i.getType() != 'B' && bomber.isCollide(i)) {
                    if(bomber.getY() + bomber.getHeight() >= i.getY() && bomber.getY() + bomber.getHeight() <= i.getY() + 20 && !input.contains("W") && !input.contains("S")) { //delete S
                        bomber.setY(bomber.getY() - bomber.getSpeed());
                    }
                    else if(bomber.getY() <= i.getY() + i.getHeight() && bomber.getY() >= i.getY() + i.getHeight() - 20 && !input.contains("S") && !input.contains("W")) {
                        bomber.setY(bomber.getY() + bomber.getSpeed());
                    }
                    else bomber.setX(bomber.getX() + bomber.getSpeed());
                }
                if(i.getType() == 'B') {
                    if(!bomber.isCollide(i)) {
                        i.setType('A');
                        if(bomber.getMaxNumberOfBombs() == 2) enable2Bombs = true;
                    }
                }
            }
        }
        if (input.contains("D")) {
            bomber.setX(bomber.getX() + bomber.getSpeed());
            if(bomber.getDir() == 'D') {
                bomber.setMovementAdd(bomber.getMovementAdd() + 1);
                if (bomber.getMovementAdd() == 5) {
                    bomber.setImage(FileName.movingRightFileName[1]);
                    GameMedia.setLeftRightWalkSound();
                    GameMedia.getLeftRightWalkSound().play();
                }
                else if (bomber.getMovementAdd() == 10) bomber.setImage(FileName.movingRightFileName[2]);
                else if (bomber.getMovementAdd() == 15) bomber.setImage(FileName.movingRightFileName[1]);
                else if (bomber.getMovementAdd() == 20) {
                    bomber.setImage(FileName.movingRightFileName[0]);
                    bomber.setMovementAdd(1);
                }
            } else {
                bomber.setDir('D');
                bomber.setMovementAdd(0);
            }
            for(Barrier i : barriers) {
                if(i.getType() != 'B' && bomber.isCollide(i)) {
                    if(bomber.getY() + bomber.getHeight() >= i.getY() && bomber.getY() + bomber.getHeight() <= i.getY() + 20 && !input.contains("W") && !input.contains("S")) { //delete S
                        bomber.setY(bomber.getY() - bomber.getSpeed());
                    }
                    else if(bomber.getY() <= i.getY() + i.getHeight() && bomber.getY() >= i.getY() + i.getHeight() - 20 && !input.contains("S") && !input.contains("W")) {
                        bomber.setY(bomber.getY() + bomber.getSpeed());
                    }
                    else bomber.setX(bomber.getX() - bomber.getSpeed());
                }
                if(i.getType() == 'B') {
                    if(!bomber.isCollide(i)) {
                        i.setType('A');
                        if(bomber.getMaxNumberOfBombs() == 2) enable2Bombs = true;
                    }
                }
            }
        }
        if (input.contains("W")) {
            bomber.setY(bomber.getY() - bomber.getSpeed());
            if(bomber.getDir() == 'W') {
                bomber.setMovementAdd(bomber.getMovementAdd() + 1);
                if (bomber.getMovementAdd() == 5) {
                    bomber.setImage(FileName.movingUpFileName[1]);
                    GameMedia.setUpDownWalkSound();
                    GameMedia.getUpDownWalkSound().play();
                }
                else if (bomber.getMovementAdd() == 10) bomber.setImage(FileName.movingUpFileName[2]);
                else if (bomber.getMovementAdd() == 15) bomber.setImage(FileName.movingUpFileName[1]);
                else if (bomber.getMovementAdd() == 20) {
                    bomber.setImage(FileName.movingUpFileName[0]);
                    bomber.setMovementAdd(1);
                }
            } else {
                bomber.setDir('W');
                bomber.setMovementAdd(0);
            }
            for(Barrier i : barriers) {
                if(i.getType() != 'B' && bomber.isCollide(i)) {
                    if(bomber.getX() >= i.getX() + i.getWidth() - 20 && bomber.getX() <= i.getX() + i.getWidth() && !input.contains("D") && !input.contains("A")) {
                        bomber.setX(bomber.getX() + bomber.getSpeed());
                    }
                    else if(bomber.getX() + bomber.getWidth() >= i.getX() && bomber.getX() + bomber.getWidth() <= i.getX() + 20 && !input.contains("A") && !input.contains("D")) { // delete D
                        bomber.setX(bomber.getX() - bomber.getSpeed());
                    }
                    else bomber.setY(bomber.getY() + bomber.getSpeed());
                }
                if(i.getType() == 'B') {
                    if(!bomber.isCollide(i)) {
                        i.setType('A');
                        if(bomber.getMaxNumberOfBombs() == 2) enable2Bombs = true;
                    }
                }
            }
        }
        if (input.contains("S")) {
            bomber.setY(bomber.getY() + bomber.getSpeed());
            if(bomber.getDir() == 'S') {
                bomber.setMovementAdd(bomber.getMovementAdd() + 1);
                if (bomber.getMovementAdd() == 5) {
                    bomber.setImage(FileName.movingDownFileName[1]);
                    GameMedia.setUpDownWalkSound();
                    GameMedia.getUpDownWalkSound().play();
                }
                else if (bomber.getMovementAdd() == 10) bomber.setImage(FileName.movingDownFileName[2]);
                else if (bomber.getMovementAdd() == 15) bomber.setImage(FileName.movingDownFileName[1]);
                else if (bomber.getMovementAdd() == 20) {
                    bomber.setImage(FileName.movingDownFileName[0]);
                    bomber.setMovementAdd(1);
                }
            } else {
                bomber.setDir('S');
                bomber.setMovementAdd(0);
            }
            for(Barrier i : barriers) {
                if(i.getType() != 'B' && bomber.isCollide(i)) {
                    if(bomber.getX() >= i.getX() + i.getWidth() - 20 && bomber.getX() <= i.getX() + i.getWidth() && !input.contains("D") && !input.contains("A")) {
                        bomber.setX(bomber.getX() + bomber.getSpeed());
                    }
                    else if(bomber.getX() + bomber.getWidth() >= i.getX() && bomber.getX() + bomber.getWidth() <= i.getX() + 20 && !input.contains("A") && !input.contains("D")) { //delete D
                        bomber.setX(bomber.getX() - bomber.getSpeed());
                    }
                    else bomber.setY(bomber.getY() - bomber.getSpeed());
                }
                if(i.getType() == 'B') {
                    if(!bomber.isCollide(i)) {
                        i.setType('A');
                        if(bomber.getMaxNumberOfBombs() == 2) enable2Bombs = true;
                    }
                }
            }
        }
        if(input.contains("SPACE")) {
            if(bombs.size() < 1) {
//                for(int i = 0; i < Size.matrixHeight; i++) {
//                    for(int j = 0; j < Size.matrixWidth; j++) {
//                        System.out.print(Main.map[i][j] + " ");
//                    }
//                    System.out.println();
//                }
                double xIndex = ((int) ((bomber.getX() + Size.entitySize / 2) / Size.entitySize) + 0.5);
                xIndex -= 0.5;

                double yIndex = ((int) ((bomber.getY() + Size.entitySize / 2) / Size.entitySize) + 0.5);
                yIndex -= 0.5;

                bombs.add(new Bomb(xIndex * Size.entitySize, yIndex * Size.entitySize, FileName.bombFileName[0], 0));
                barriers.add(new Barrier(xIndex * Size.entitySize, yIndex * Size.entitySize, FileName.invisible, 'B'));
                Main.map[(int) yIndex][(int) xIndex] = 2;
                enable2Bombs = false;
                GameMedia.setPlaceBombSound();
                GameMedia.getPlaceBombSound().play();
            }
            if(enable2Bombs && bombs.size() == 1) {
                double xIndex = ((int) ((bomber.getX() + Size.entitySize / 2) / Size.entitySize) + 0.5);
                xIndex -= 0.5;

                double yIndex = ((int) ((bomber.getY() + Size.entitySize / 2) / Size.entitySize) + 0.5);
                yIndex -= 0.5;

                bombs.add(new Bomb(xIndex * Size.entitySize, yIndex * Size.entitySize, FileName.bombFileName[0], 0));
                barriers.add(new Barrier(xIndex * Size.entitySize, yIndex * Size.entitySize, FileName.invisible, 'B'));
                Main.map[(int) yIndex][(int) xIndex] = 1;
                enable2Bombs = false;
                GameMedia.setPlaceBombSound();
                GameMedia.getPlaceBombSound().play();
            }
        }
    }
}

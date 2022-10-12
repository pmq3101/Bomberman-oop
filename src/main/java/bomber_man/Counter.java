package bomber_man;

import constant.FileName;
import constant.Size;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.util.ArrayList;

public class Counter {
    private int maxNumberOfUnits;
    private int computerCounter;
    private int convertedCounter;
    private ArrayList<Entity> units;

    public Counter() {
        units = new ArrayList<>();
    }

    public Counter(int maxNumberOfUnits) {
        this.maxNumberOfUnits = maxNumberOfUnits;
    }

    public void setInitialUnits(double initialXPosition, double initialYPosition, int convertedCounter) {
        units.clear();
        for(int i = 0; i < maxNumberOfUnits; i++) {
            units.add(new Entity(initialXPosition + 40 * i, initialYPosition, FileName.numbersFileName[0]));
        }
        this.convertedCounter = convertedCounter;
        this.computerCounter = convertedCounter * Size.secondCounter;
        updateUnits();
    }

    public void updateUnits() {
        int tmp = convertedCounter;
        int j = tmp % 10;
        int i;
        for(i = units.size() - 1; i >= 0; i--) {
            j = tmp % 10;
            if(tmp == 0) units.get(i).setImage(FileName.numbersFileName[0]);
            else units.get(i).setImage(FileName.numbersFileName[j]);
            tmp /= 10;
        }
    }

    public int getComputerCounter() {
        return computerCounter;
    }

    public void setComputerCounter(int computerCounter) {
        this.computerCounter = computerCounter;
    }

    public int getConvertedCounter() {
        return convertedCounter;
    }

    public void setConvertedCounter(int convertedCounter) {
        this.convertedCounter = convertedCounter;
    }

    public void setMaxNumberOfUnits(int maxNumberOfUnits) {
        this.maxNumberOfUnits = maxNumberOfUnits;
    }

    public void render(GraphicsContext context) {
        for(Entity i : units) {
            i.render(context);
        }
    }

}

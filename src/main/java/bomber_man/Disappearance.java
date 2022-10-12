package bomber_man;

public class Disappearance extends Entity {
    private int second;
    private char dir;

    public Disappearance() {}

    public Disappearance(double x, double y, String fileName, int second, char dir) {
        super(x, y, fileName);
        this.second = second;
        this.dir = dir;
    }

    public char getDir() {
        return dir;
    }

    public int getStatus() {
        return ++second;
    }

    public void setSecond(int second) {
        this.second = second;
    }
}

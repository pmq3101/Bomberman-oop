package bomber_man;

public class Menu extends Entity{
    private int win; /* 0: loose, 1: win, 2: playing */

    public Menu() {}

    public Menu(double x, double y, String fileName, int win) {
        super(x, y, fileName);
        this.win = win;
    }

    public int getWin() {
        return win;
    }

    public void setWin(int win) {
        this.win = win;
    }
}

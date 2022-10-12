package bomber_man;

public class Portal extends Entity{
    int isCovered;

    public Portal() {}

    public Portal(double x, double y, String fileName, int isCovered) {
        super(x, y, fileName);
        this.isCovered = isCovered;
    }

    public int isCovered() {
        return isCovered;
    }

    public void setCovered(int covered) {
        isCovered = covered;
    }
}

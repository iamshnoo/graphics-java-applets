
import java.awt.Color;
import java.awt.Graphics;

public class fish {

    private int x;
    private int vx;
    private final int y;
    private final Quadrilateral quadrilateral;
    private final Triangle triangle;
    private final Color x3;
    private final Color x4;
    private final Color x6;
    private final Color x7;
    private final Color x8;

    public fish() {
        this.x8 = new Color(0, 130, 255);
        this.x7 = new Color(220, 255, 0);
        this.x6 = new Color(15, 20, 240);
        this.x4 = new Color(240, 25, 15);
        this.x3 = new Color(25, 160, 25);
        this.triangle = new Triangle();
        this.quadrilateral = new Quadrilateral();
        this.vx = 0;
        this.y = -40;
        this.x = 140;
    }

    public int getX() {
        return x;
    }

    public void update() {
        vx = 1;
        x += vx;
        if (x >= 155) {
            x = 155;
        }
    }

    public void reset() {
        vx = -1;
        if (x != 140) {
            while (x != 140) {
                x += vx;
            }
        }
    }

    public void paint(Graphics g, main a) {

        // DRAW THE FISH
        // Head
        triangle.fill(g, a.X, a.Y, a.gap, x, y, x, y + 8, x - 8, y, x4);
        triangle.fill(g, a.X, a.Y, a.gap, x, y, x, y - 8, x - 8, y, x4);

        // Body
        triangle.fill(g, a.X, a.Y, a.gap, x + 1, y + 7, x + 1, y + 5, x + 12, y + 5, x3);
        quadrilateral.fill(g, a.X, a.Y, a.gap, x + 1, y + 4, x + 12, y + 4, x + 12, y - 4, x + 1, y - 4, x3);
        triangle.fill(g, a.X, a.Y, a.gap, x + 1, y - 5, x + 1, y - 7, x + 12, y - 5, x3);

        // Tail
        quadrilateral.fill(g, a.X, a.Y, a.gap, x + 13, y + 2, x + 23, y + 7, x + 23, y - 1, x + 13, y - 2, x6);
        triangle.fill(g, a.X, a.Y, a.gap, x + 13, y - 3, x + 23, y - 1, x + 23, y - 7, x7);

        // Eye
        quadrilateral.fill(g, a.X, a.Y, a.gap, x - 4, y + 1, x - 2, y + 1, x - 2, y, x - 4, y, x8);
        quadrilateral.fill(g, a.X, a.Y, a.gap, x - 4, y, x - 2, y, x - 2, y - 1, x - 4, y - 1, x8);
    }
}

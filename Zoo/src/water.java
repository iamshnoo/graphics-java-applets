
import java.awt.Color;
import java.awt.Graphics;

public class water {

    private final int c;
    private final int d;
    private final int e;

    private final Quadrilateral quadrilateral;
    private final Line line;

    public water() {
        this.c = 182;
        this.d = -90;
        this.e = 117;
        this.line = new Line();
        this.quadrilateral = new Quadrilateral();
    }

    public void paint(Graphics g, main a) {

        // DRAW WATER BODY
        quadrilateral.fill(g, a.X, a.Y, a.gap, e + 0, d + 90, c + 0, d + 90, c + 0, d + 16, e + 0, d + 16, Color.cyan);        // water
        line.Bresenham(g, a.X, a.Y, a.gap, e + 0, d + 90, e + 0, d + 16, Color.blue);                                          // left 
        line.Bresenham(g, a.X, a.Y, a.gap, c + 0, d + 90, c + 0, d + 16, Color.black);                                         // right
        line.Bresenham(g, a.X, a.Y, a.gap, e + 0, d + 16, c + 0, d + 16, Color.black);                                         // bottom
        line.Bresenham(g, a.X, a.Y, a.gap, e + 0, d + 90, c + 0, d + 90, Color.black);                                         // top     

    }
}


import java.awt.Color;
import java.awt.Graphics;

public class beehive {

    private final int c;
    private final int d;
    private final int e;
    private final int x;
    private final int y;
    private final double s;
    private final Ellipse ellipse;
    private final Line line;
    private final Color body_c;
    private final Color lines_c;
    private final Color bg;
    private final Quadrilateral quadrilateral;

    public beehive() {
        this.bg = new Color(25, 115, 148);
        this.lines_c = new Color(160, 110, 30);
        this.body_c = new Color(210, 175, 15);
        this.line = new Line();
        this.ellipse = new Ellipse();
        this.quadrilateral = new Quadrilateral();
        this.s = 0.5;
        this.x = -250;
        this.y = -70;
        this.c = -237;
        this.d = -88;
        this.e = -40;
    }

    public void paint(Graphics g, main a) {

        // CAGE
        quadrilateral.fill(g, a.X, a.Y, a.gap, e - 118, e + 41, e - 52, e + 41, e - 52, e - 32, e - 118, e - 32, bg);
        line.scale(g, a.X, a.Y, a.gap, c + 53, d - 59, c - 79, d - 59, Color.black, s);
        line.scale(g, a.X, a.Y, a.gap, c - 79, d - 59, c - 79, d + 90, Color.black, s);
        line.scale(g, a.X, a.Y, a.gap, c - 79, d + 90, c + 53, d + 90, Color.black, s);
        line.scale(g, a.X, a.Y, a.gap, c + 53, d - 59, c + 53, d + 90, Color.blue, s);
        line.scale(g, a.X, a.Y, a.gap, c + 53, d - 59, c + 85, d - 59, Color.blue, s);
        line.scale(g, a.X, a.Y, a.gap, c + 53, d + 90, c + 85, d + 90, Color.blue, s);

        // DRAW THE BEEHIVE
        ellipse.scale(g, a.X, a.Y, a.gap, 30, 40, x, y, body_c, s);                       // body
        line.scale(g, a.X, a.Y, a.gap, x - 20, y + 30, x + 20, y + 30, lines_c, s);       // line1
        line.scale(g, a.X, a.Y, a.gap, x - 26, y + 20, x + 26, y + 20, lines_c, s);       // line2
        line.scale(g, a.X, a.Y, a.gap, x - 29, y + 10, x + 29, y + 10, lines_c, s);       // line3
        line.scale(g, a.X, a.Y, a.gap, x - 30, y, x + 30, y, lines_c, s);                 // line4
        line.scale(g, a.X, a.Y, a.gap, x - 29, y - 10, x + 29, y - 10, lines_c, s);       // line5
        line.scale(g, a.X, a.Y, a.gap, x - 26, y - 20, x + 26, y - 20, lines_c, s);       // line6
        line.scale(g, a.X, a.Y, a.gap, x - 20, y - 30, x + 20, y - 30, lines_c, s);       // line7
        ellipse.scale(g, a.X, a.Y, a.gap, 5, 2, x + 9, y - 5, lines_c, s);                // hole

    }
}

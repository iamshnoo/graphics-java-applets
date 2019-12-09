
import java.awt.Color;
import java.awt.Graphics;

public class lion {

    private int c;
    private int d;
    private int x;
    private int y;
    private int Px;
    private int Py;
    private double Ps;
    private double s;
    private final double s2;
    private final Point point;
    private final Line line;
    private final Quadrilateral quadrilateral;
    private final Triangle triangle;
    private final Circle circle;
    private final Ellipse ellipse;
    private final Color extras_c;
    private final Color cartoon_c;
    private final Color head_c;
    private final Color nose_c;
    private final Color body_c;
    private final Color moustache_c;
    private final Color nose_tip_c;
    private final Color bg;
    private final Color beard_c;
    private final Color eyes_c;
    private final Color lips_c;

    public lion() {
        this.bg = new Color(49, 148, 25);
        this.lips_c = new Color(250, 65, 65);
        this.eyes_c = new Color(130, 90, 30);
        this.beard_c = new Color(230, 210, 35);
        this.nose_tip_c = new Color(80, 60, 30);
        this.moustache_c = new Color(250, 230, 190);
        this.body_c = new Color(230, 160, 15);
        this.nose_c = new Color(240, 205, 15);
        this.head_c = new Color(250, 180, 0);
        this.cartoon_c = new Color(255, 255, 255);
        this.extras_c = new Color(0, 0, 0);
        this.ellipse = new Ellipse();
        this.circle = new Circle();
        this.triangle = new Triangle();
        this.quadrilateral = new Quadrilateral();
        this.line = new Line();
        this.point = new Point();
        this.s = 0.5;
        this.s2 = 0.5;
        this.x = 300;
        this.y = 120;
        this.c = 287;
        this.d = 120;

    }

    public void update(human h) {
        s = 0.5;
        x = 300;
        y = 120;
        Px = h.getX();
        Py = h.getY();
        Ps = h.getS();
        if (Ps == 1) {
            if (Px >= 140 && Py <= 130 && Py >= 40) {
                s = 0.7;
                x = 220;
                y = 75;
            }
        } else//Ps=0.5
         if (Px >= 300 && Py <= 160 && Py >= 60) {
                s = 0.7;
                x = 220;
                y = 75;
            }

    }

    public void paint(Graphics g, main a) {

        // CAGE
        quadrilateral.scale(g, a.X, a.Y, a.gap, c - 53, d - 68, c + 77, d - 68, c + 77, d + 57, c - 53, d + 57, bg, s2);
        line.scale(g, a.X, a.Y, a.gap, c + 77, d - 68, c + 77, d + 57, extras_c, s2);
        line.scale(g, a.X, a.Y, a.gap, c + 77, d - 68, c - 53, d - 68, extras_c, s2);
        line.scale(g, a.X, a.Y, a.gap, c + 77, d + 57, c - 53, d + 57, extras_c, s2);
        line.scale(g, a.X, a.Y, a.gap, c - 53, d - 68, c - 53, d + 57, Color.blue, s2);
        line.scale(g, a.X, a.Y, a.gap, c - 53, d - 68, c - 83, d - 68, Color.blue, s2);
        line.scale(g, a.X, a.Y, a.gap, c - 53, d + 57, c - 83, d + 57, Color.blue, s2);

        // DRAW THE LION
        quadrilateral.scale(g, a.X, a.Y, a.gap, x - 30, y + 5, x + 30, y + 5, x + 20, y - 35, x - 20, y - 35, body_c, s);           // body
        triangle.scale(g, a.X, a.Y, a.gap, x - 28, y + 18, x - 35, y + 37, x - 30, y + 50, head_c, s);                              // left_ear
        triangle.scale(g, a.X, a.Y, a.gap, x - 28, y + 19, x - 30, y + 50, x - 15, y + 32, beard_c, s);                             // left_ear_inner
        triangle.scale(g, a.X, a.Y, a.gap, x + 28, y + 18, x + 35, y + 37, x + 30, y + 50, head_c, s);                              // right_ear_outer
        triangle.scale(g, a.X, a.Y, a.gap, x + 28, y + 19, x + 30, y + 50, x + 15, y + 32, beard_c, s);                             // right_ear_inner
        circle.scale(g, a.X, a.Y, a.gap, 30, x, y + 5, head_c, s);                                                                  // head
        quadrilateral.scale(g, a.X, a.Y, a.gap, x - 5, y - 5, x + 5, y - 5, x + 9, y - 15, x - 9, y - 15, moustache_c, s);          // moustache_area_upper
        quadrilateral.scale(g, a.X, a.Y, a.gap, x - 9, y - 15, x + 9, y - 15, x + 5, y - 25, x - 5, y - 25, moustache_c, s);        // moustache_area_upper
        ellipse.scale(g, a.X, a.Y, a.gap, 8, 9, x, y - 20, moustache_c, s);                                                         // chin
        triangle.scale(g, a.X, a.Y, a.gap, x - 8, y - 25, x, y - 20, x, y - 22, lips_c, s);                                         // open_mouth_left
        triangle.scale(g, a.X, a.Y, a.gap, x, y - 20, x + 8, y - 25, x, y - 22, lips_c, s);                                         // open_mouth_right
        ellipse.scale(g, a.X, a.Y, a.gap, 6, 7, x, y - 8, nose_tip_c, s);                                                           // nose_bulb
        quadrilateral.scale(g, a.X, a.Y, a.gap, x - 8, y + 12, x + 8, y + 12, x + 6, y - 5, x - 6, y - 5, nose_c, s);               // nose_bridge
        triangle.scale(g, a.X, a.Y, a.gap, x - 6, y - 5, x + 6, y - 5, x, y - 8, nose_c, s);                                        // nose_tip
        triangle.scale(g, a.X, a.Y, a.gap, x - 30, y + 5, x - 20, y - 5, x - 25, y - 15, beard_c, s);                               // cheek_left_upper
        triangle.scale(g, a.X, a.Y, a.gap, x - 20, y - 5, x - 10, y - 25, x - 25, y - 15, beard_c, s);                              // cheek_left_lower
        triangle.scale(g, a.X, a.Y, a.gap, x + 30, y + 5, x + 20, y - 5, x + 25, y - 15, beard_c, s);                               // cheek_right_upper
        triangle.scale(g, a.X, a.Y, a.gap, x + 20, y - 5, x + 10, y - 25, x + 25, y - 15, beard_c, s);                              // cheek_right_lower
        triangle.scale(g, a.X, a.Y, a.gap, x - 6, y - 5, x - 9, y + 12, x - 6, y + 20, beard_c, s);                                 // nose_ridge_left
        triangle.scale(g, a.X, a.Y, a.gap, x + 6, y - 5, x + 9, y + 12, x + 6, y + 20, beard_c, s);                                 // nose_ridge_right
        triangle.scale(g, a.X, a.Y, a.gap, x - 20, y + 12, x - 9, y + 12, x - 9, y, extras_c, s);                                   // left_eye_bag_upper
        triangle.scale(g, a.X, a.Y, a.gap, x + 20, y + 12, x + 9, y + 12, x + 9, y, extras_c, s);                                   // right_eye_bag_upper
        circle.scale(g, a.X, a.Y, a.gap, 3, x - 12, y + 8, eyes_c, s);                                                              // left_retina_outer
        circle.scale(g, a.X, a.Y, a.gap, 3, x + 12, y + 8, eyes_c, s);                                                              // right_retina_outer
        point.scale(g, a.X, a.Y, a.gap, x - 12, y + 8, extras_c, s);                                                                // left_retina_inner
        point.scale(g, a.X, a.Y, a.gap, x + 12, y + 8, extras_c, s);                                                                // right_retina_inner
        line.scale(g, a.X, a.Y, a.gap, x - 6, y - 18, x - 15, y - 12, cartoon_c, s);                                                // left_upper_whisker
        line.scale(g, a.X, a.Y, a.gap, x - 6, y - 20, x - 15, y - 15, cartoon_c, s);                                                // right_lower_whisker 
        line.scale(g, a.X, a.Y, a.gap, x + 6, y - 18, x + 15, y - 12, cartoon_c, s);                                                // left_upper_whisker
        line.scale(g, a.X, a.Y, a.gap, x + 6, y - 20, x + 15, y - 15, cartoon_c, s);                                                // right_lower_whisker

        // BARS 
        line.scale(g, a.X, a.Y, a.gap, c + 77, d - 68, c + 77, d + 57, extras_c, s2);
        line.scale(g, a.X, a.Y, a.gap, c + 70, d - 68, c + 70, d + 57, extras_c, s2);
        line.scale(g, a.X, a.Y, a.gap, c + 60, d - 68, c + 60, d + 57, extras_c, s2);
        line.scale(g, a.X, a.Y, a.gap, c + 50, d - 68, c + 50, d + 57, extras_c, s2);
        line.scale(g, a.X, a.Y, a.gap, c + 40, d - 68, c + 40, d + 57, extras_c, s2);
        line.scale(g, a.X, a.Y, a.gap, c + 30, d - 68, c + 30, d + 57, extras_c, s2);
        line.scale(g, a.X, a.Y, a.gap, c + 20, d - 68, c + 20, d + 57, extras_c, s2);
        line.scale(g, a.X, a.Y, a.gap, c + 10, d - 68, c + 10, d + 57, extras_c, s2);
        line.scale(g, a.X, a.Y, a.gap, c + 0, d - 68, c + 0, d + 57, extras_c, s2);
        line.scale(g, a.X, a.Y, a.gap, c - 10, d - 68, c - 10, d + 57, extras_c, s2);
        line.scale(g, a.X, a.Y, a.gap, c - 20, d - 68, c - 20, d + 57, extras_c, s2);
        line.scale(g, a.X, a.Y, a.gap, c - 30, d - 68, c - 30, d + 57, extras_c, s2);
        line.scale(g, a.X, a.Y, a.gap, c - 40, d - 68, c - 40, d + 57, extras_c, s2);
        line.scale(g, a.X, a.Y, a.gap, c - 50, d - 68, c - 50, d + 57, extras_c, s2);
    }
}

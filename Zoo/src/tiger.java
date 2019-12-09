
import java.awt.Color;
import java.awt.Graphics;

public class tiger {

    private int c;
    private int d;
    private int x;
    private int y;
    private int Px;
    private int Py;
    private double Ps;
    private double s;
    private final double s2;
    private final Line line;
    private final Quadrilateral quadrilateral;
    private final Triangle triangle;
    private final Circle circle;
    private final Color extras_c;
    private final Color cartoon_c;
    private final Color mane_c;
    private final Color nose_c;
    private final Color body_c;
    private final Color moustache_c;
    private final Color nose_tip_c;
    private final Color beard_c;
    private final Color lips_c;
    private final Color bg;

    public tiger() {
        this.bg = new Color(97, 238, 62);
        this.lips_c = new Color(250, 65, 65);
        this.beard_c = new Color(250, 165, 55);
        this.nose_tip_c = new Color(80, 60, 30);
        this.moustache_c = new Color(250, 200, 135);
        this.body_c = new Color(255, 150, 10);
        this.nose_c = new Color(255, 150, 0);
        this.mane_c = new Color(190, 20, 20);
        this.cartoon_c = new Color(255, 255, 255);
        this.extras_c = new Color(0, 0, 0);
        this.circle = new Circle();
        this.triangle = new Triangle();
        this.quadrilateral = new Quadrilateral();
        this.line = new Line();
        this.s = 0.5;
        this.s2 = 0.5;
        this.x = -300;
        this.y = 120;
        this.c = -235;
        this.d = 120;
    }

    public void update(human h) {
        s = 0.5;
        x = -250;
        y = 120;

        Px = h.getX();
        Py = h.getY();
        Ps = h.getS();
        if (Ps == 1) {
            if (Px == -80 && Py <= 130 && Py >= 40) {
                s = 0.7;
                x = -175;
                y = 78;
            }
        } else//Ps=0.5
         if (Px == -200 && Py <= 160 && Py >= 60) {
                s = 0.7;
                x = -175;
                y = 78;
            }
    }

    public void paint(Graphics g, main a) {

        // CAGE
        quadrilateral.scale(g, a.X, a.Y, a.gap, c - 79, d + 57, c + 53, d + 57, c + 53, d - 70, c - 79, d - 70, bg, s2);
        line.scale(g, a.X, a.Y, a.gap, c + 53, d - 70, c - 79, d - 70, extras_c, s2);
        line.scale(g, a.X, a.Y, a.gap, c - 79, d - 68, c - 79, d + 57, extras_c, s2);
        line.scale(g, a.X, a.Y, a.gap, c - 79, d + 57, c + 53, d + 57, extras_c, s2);
        line.scale(g, a.X, a.Y, a.gap, c + 53, d - 70, c + 53, d + 57, Color.blue, s2);
        line.scale(g, a.X, a.Y, a.gap, c + 53, d - 70, c + 85, d - 70, Color.blue, s2);
        line.scale(g, a.X, a.Y, a.gap, c + 53, d + 57, c + 85, d + 57, Color.blue, s2);

        // DRAW THE TIGER
        quadrilateral.scale(g, a.X, a.Y, a.gap, x - 10, y + 15, x + 10, y + 15, x + 25, y - 39, x - 25, y - 39, body_c, s);        // body             
        circle.scale(g, a.X, a.Y, a.gap, 40, x, y + 5, mane_c, s);                                                                 // mane 
        quadrilateral.scale(g, a.X, a.Y, a.gap, x, y + 20, x, y - 6, x - 15, y - 6, x - 20, y + 6, beard_c, s);                    // face_lower_left
        quadrilateral.scale(g, a.X, a.Y, a.gap, x, y + 20, x, y - 6, x + 15, y - 6, x + 20, y + 6, beard_c, s);                    // face_lower_right
        triangle.scale(g, a.X, a.Y, a.gap, x - 20, y + 6, x - 13, y + 24, x, y + 13, beard_c, s);                                  // face_upper_left
        triangle.scale(g, a.X, a.Y, a.gap, x + 20, y + 6, x + 13, y + 24, x, y + 13, beard_c, s);                                  // face_upper_right
        quadrilateral.scale(g, a.X, a.Y, a.gap, x - 15, y - 6, x + 15, y - 6, x + 11, y - 11, x - 11, y - 11, moustache_c, s);     // moustache_area_upper  
        quadrilateral.scale(g, a.X, a.Y, a.gap, x - 11, y - 11, x + 11, y - 11, x + 6, y - 25, x - 6, y - 25, moustache_c, s);     // moustache_area_lower
        line.scale(g, a.X, a.Y, a.gap, x - 6, y - 10, x - 20, y - 13, cartoon_c, s);                                               // left_whisker_1
        line.scale(g, a.X, a.Y, a.gap, x - 6, y - 11, x - 21, y - 17, cartoon_c, s);                                               // left_whisker_2
        line.scale(g, a.X, a.Y, a.gap, x - 6, y - 12, x - 21, y - 22, cartoon_c, s);                                               // left_whisker_3
        line.scale(g, a.X, a.Y, a.gap, x - 6, y - 12, x - 21, y - 27, cartoon_c, s);                                               // left_whisker_4
        line.scale(g, a.X, a.Y, a.gap, x + 6, y - 10, x + 20, y - 13, cartoon_c, s);                                               // right_whisker_1
        line.scale(g, a.X, a.Y, a.gap, x + 6, y - 11, x + 21, y - 17, cartoon_c, s);                                               // right_whisker_2
        line.scale(g, a.X, a.Y, a.gap, x + 6, y - 12, x + 21, y - 22, cartoon_c, s);                                               // right_whisker_3
        line.scale(g, a.X, a.Y, a.gap, x + 6, y - 12, x + 21, y - 27, cartoon_c, s);                                               // right_whisker_4
        triangle.scale(g, a.X, a.Y, a.gap, x - 6, y + 10, x, y + 2, x - 12, y + 10, extras_c, s);                                  // left_eye
        triangle.scale(g, a.X, a.Y, a.gap, x + 6, y + 10, x, y + 2, x + 12, y + 10, extras_c, s);                                  // right_eye
        quadrilateral.scale(g, a.X, a.Y, a.gap, x - 10, y + 20, x + 10, y + 20, x + 6, y + 10, x - 6, y + 10, nose_c, s);          // nose_bridge_up
        quadrilateral.scale(g, a.X, a.Y, a.gap, x - 6, y + 10, x + 6, y + 10, x + 8, y - 2, x - 8, y - 2, nose_c, s);              // nose_bridge
        triangle.scale(g, a.X, a.Y, a.gap, x - 8, y - 2, x + 8, y - 2, x, y - 8, nose_c, s);                                       // nose_tip
        triangle.scale(g, a.X, a.Y, a.gap, x - 8, y - 2, x, y - 8, x, y - 14, nose_tip_c, s);                                      // nose_bulb_left
        triangle.scale(g, a.X, a.Y, a.gap, x + 8, y - 2, x, y - 8, x, y - 14, nose_tip_c, s);                                      // nose_bulb_right
        triangle.scale(g, a.X, a.Y, a.gap, x - 8, y - 20, x, y - 15, x, y - 17, lips_c, s);                                        // open_mouth_left
        triangle.scale(g, a.X, a.Y, a.gap, x + 8, y - 20, x, y - 15, x, y - 17, lips_c, s);                                        // open_mouth_right
        triangle.scale(g, a.X, a.Y, a.gap, x - 10, y + 20, x - 13, y + 24, x, y + 20, nose_c, s);                                  // nose_bridge_left
        triangle.scale(g, a.X, a.Y, a.gap, x + 10, y + 20, x + 13, y + 24, x, y + 20, nose_c, s);                                  // nose_bridge_right

        // BARS 
        line.scale(g, a.X, a.Y, a.gap, c - 69, d - 68, c - 69, d + 57, extras_c, s2);
        line.scale(g, a.X, a.Y, a.gap, c - 59, d - 68, c - 59, d + 57, extras_c, s2);
        line.scale(g, a.X, a.Y, a.gap, c - 49, d - 68, c - 49, d + 57, extras_c, s2);
        line.scale(g, a.X, a.Y, a.gap, c - 39, d - 68, c - 39, d + 57, extras_c, s2);
        line.scale(g, a.X, a.Y, a.gap, c - 29, d - 68, c - 29, d + 57, extras_c, s2);
        line.scale(g, a.X, a.Y, a.gap, c - 19, d - 68, c - 19, d + 57, extras_c, s2);
        line.scale(g, a.X, a.Y, a.gap, c - 9, d - 68, c - 9, d + 57, extras_c, s2);
        line.scale(g, a.X, a.Y, a.gap, c - 0, d - 68, c - 0, d + 57, extras_c, s2);
        line.scale(g, a.X, a.Y, a.gap, c + 9, d - 68, c + 9, d + 57, extras_c, s2);
        line.scale(g, a.X, a.Y, a.gap, c + 19, d - 68, c + 19, d + 57, extras_c, s2);
        line.scale(g, a.X, a.Y, a.gap, c + 29, d - 68, c + 29, d + 57, extras_c, s2);
        line.scale(g, a.X, a.Y, a.gap, c + 39, d - 68, c + 39, d + 57, extras_c, s2);
        line.scale(g, a.X, a.Y, a.gap, c + 51, d - 68, c + 51, d + 57, extras_c, s2);

    }
}

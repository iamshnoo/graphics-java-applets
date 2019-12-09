
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class human implements KeyListener {

    private int x;
    private int y;
    private int x0;
    private int y0;
    private int vx;
    private int vy;
    private final int X;
    private final int Y;
    private final int gap;
    private final Circle circle;
    private final Triangle triangle;
    private final Quadrilateral quadrilateral;
    private final Color extras_c;
    private final Color body_c;
    private final Color red;
    private final Color blue;
    private double s;

    public human() {
        this.red = new Color(210, 210, 240);
        this.blue = new Color(180, 185, 215);
        this.body_c = Color.red;//new Color(71, 75, 100);
        this.extras_c = new Color(0, 0, 0);
        this.quadrilateral = new Quadrilateral();
        this.triangle = new Triangle();
        this.circle = new Circle();
        this.x = 0;
        this.y = 0;
        this.vx = 0;
        this.vy = 0;
        this.gap = 4;
        this.Y = 360;
        this.X = 635;
        this.x0 = X + x * gap - gap / 2;
        this.y0 = Y - y * gap - gap / 2;
        this.s = 0.5; // default value of s
    }

    public int getX() {
        return x * 2; // this factor of 2 cancels out the scaling factor of 0.5 for the objects.
    }

    public int getY() {
        return y * 2; // this factor of 2 cancels out the scaling factor of 0.5 for the objects.
    }

    public double getS() {
        return s;
    }

    public void setS(double val){
        s = val;
    }
    
    public void update(main a) {

        a.addKeyListener(this);
        this.x += this.vx;
        this.y += this.vy;
        x0 = X + x * gap - gap / 2;
        y0 = Y - y * gap - gap / 2;

        // robot stays within frame
        if (s == 1) {
            if (this.x0 - 470 <= 0 || this.y0 - 238 <= 0 || this.x0 + 350 >= a.getWidth() || this.y0 + 180 >= a.getHeight()) {
                this.y -= this.vy;
                this.x -= this.vx;
                x0 = X + x * gap - gap / 2;
                y0 = Y - y * gap - gap / 2;
            }
        } else if (this.x0 - 220 <= 0 || this.y0 <= 30 || this.x0 + 20 >= a.getWidth() || this.y0 + 100 >= a.getHeight()) {
            this.y -= this.vy;
            this.x -= this.vx;
            x0 = X + x * gap - gap / 2;
            y0 = Y - y * gap - gap / 2;
        }
    }

    public void front(Graphics g) {
        // FRONT-FACING ROBOT - POSE 0
        quadrilateral.scale(g, X, Y, gap, x - 15, y + 60, x - 15, y + 35, x + 10, y + 35, x + 10, y + 60, blue, s);                     // head
        quadrilateral.scaleDraw(g, X, Y, gap, x - 15, y + 60, x - 15, y + 35, x + 10, y + 35, x + 10, y + 60, red, s);                  // head
        circle.scale(g, X, Y, gap, 5, x - 15, y + 50, extras_c, s);                                                                     // left eye
        circle.scaleDraw(g, X, Y, gap, 5, x - 15, y + 50, body_c, s);                                                                   // left eye
        circle.scale(g, X, Y, gap, 5, x + 10, y + 50, extras_c, s);                                                                     // right eye
        circle.scaleDraw(g, X, Y, gap, 5, x + 10, y + 50, body_c, s);                                                                   // right eye
        circle.scaleDraw(g, X, Y, gap, 1, x - 15, y + 50, body_c, s);                                                                   // left eye_ball
        circle.scaleDraw(g, X, Y, gap, 1, x + 10, y + 50, body_c, s);                                                                   // right eye_ball
        triangle.scale(g, X, Y, gap, x - 2, y + 50, x - 6, y + 45, x + 3, y + 45, extras_c, s);                                         // nose
        triangle.scaleDraw(g, X, Y, gap, x - 2, y + 50, x - 6, y + 45, x + 3, y + 45, body_c, s);                                       // nose
        quadrilateral.scale(g, X, Y, gap, x - 13, y + 40, x - 11, y + 38, x + 6, y + 38, x + 8, y + 40, extras_c, s);                   // mouth
        quadrilateral.scale(g, X, Y, gap, x - 18, y + 32, x - 18, y + 0, x + 15, y + 0, x + 15, y + 32, blue, s);                       // body
        quadrilateral.scaleDraw(g, X, Y, gap, x - 18, y + 32, x - 18, y + 0, x + 15, y + 0, x + 15, y + 32, red, s);                    // body
        quadrilateral.scale(g, X, Y, gap, x - 25, y + 32, x - 20, y + 32, x - 30, y + 0, x - 35, y + 0, blue, s);                       // left hand
        quadrilateral.scaleDraw(g, X, Y, gap, x - 25, y + 32, x - 20, y + 32, x - 30, y + 0, x - 35, y + 0, red, s);                    // left hand
        quadrilateral.scale(g, X, Y, gap, x + 22, y + 32, x + 17, y + 32, x + 27, y + 0, x + 32, y + 0, blue, s);                       // right hand
        quadrilateral.scaleDraw(g, X, Y, gap, x + 22, y + 32, x + 17, y + 32, x + 27, y + 0, x + 32, y + 0, red, s);                    // right hand
        quadrilateral.scale(g, X, Y, gap, x - 18, y - 3, x - 23, y - 37, x - 18, y - 37, x - 13, y - 3, blue, s);                       // left leg
        quadrilateral.scaleDraw(g, X, Y, gap, x - 18, y - 3, x - 23, y - 37, x - 18, y - 37, x - 13, y - 3, red, s);                    // left leg
        quadrilateral.scale(g, X, Y, gap, x + 10, y - 3, x + 15, y - 3, x + 23, y - 37, x + 18, y - 37, blue, s);                       // right leg 
        quadrilateral.scaleDraw(g, X, Y, gap, x + 10, y - 3, x + 15, y - 3, x + 23, y - 37, x + 18, y - 37, red, s);                    // right leg
    }

    public void leftA(Graphics g) {
        // LEFT-FACING ROBOT - POSE 3
        circle.scale(g, X, Y, gap, 5, x - 8, y + 53, extras_c, s);                                                         // left eye
        circle.scaleDraw(g, X, Y, gap, 5, x - 8, y + 53, body_c, s);                                                       // left eye
        circle.scaleDraw(g, X, Y, gap, 1, x - 8, y + 53, body_c, s);                                                       // left eye_ball
        triangle.scale(g, X, Y, gap, x - 8, y + 50, x - 4, y + 43, x - 13, y + 43, extras_c, s);                                   // nose
        quadrilateral.scale(g, X, Y, gap, x - 11, y + 40, x - 11, y + 38, x + 3, y + 38, x + 3, y + 40, extras_c, s);                  // mouth
        quadrilateral.scale(g, X, Y, gap, x - 10, y + 60, x - 10, y + 35, y + 5, y + 35, x + 5, y + 60, blue, s);                      // head
        quadrilateral.scaleDraw(g, X, Y, gap, x - 10, y + 60, x - 10, y + 35, x + 5, y + 35, x + 5, y + 60, red, s);                   // head
        quadrilateral.scale(g, X, Y, gap, x - 3, y + 32, x + 3, y + 32, x + 23, y + 10, x + 17, y + 10, blue, s);                      // left hand
        quadrilateral.scaleDraw(g, X, Y, gap, x - 3, y + 32, x + 3, y + 32, x + 23, y + 10, x + 17, y + 10, red, s);                   // left hand
        quadrilateral.scale(g, X, Y, gap, x - 13, y + 32, x - 13, y + 0, x + 10, y + 0, x + 10, y + 32, blue, s);                      // body
        quadrilateral.scaleDraw(g, X, Y, gap, x - 13, y + 32, x - 13, y + 0, x + 10, y + 0, x + 10, y + 32, red, s);                   // body
        quadrilateral.scale(g, X, Y, gap, x - 13, y - 3, x - 18, y - 37, x - 13, y - 37, x - 8, y - 3, blue, s);                       // left leg
        quadrilateral.scaleDraw(g, X, Y, gap, x - 13, y - 3, x - 18, y - 37, x - 13, y - 37, x - 8, y - 3, red, s);                    // left leg
        quadrilateral.scale(g, X, Y, gap, x + 5, y - 3, x + 10, y - 3, x + 15, y - 37, x + 10, y - 37, blue, s);                       // right leg 
        quadrilateral.scaleDraw(g, X, Y, gap, x + 5, y - 3, x + 10, y - 3, x + 15, y - 37, x + 10, y - 37, red, s);                    // right leg           
        quadrilateral.scale(g, X, Y, gap, x + 1, y + 32, x - 5, y + 32, x - 23, y + 10, x - 17, y + 10, blue, s);                      // right hand
        quadrilateral.scaleDraw(g, X, Y, gap, x + 1, y + 32, x - 5, y + 32, x - 23, y + 10, x - 17, y + 10, red, s);                   // right hand
    }

    public void leftB(Graphics g) {
        // LEFT-FACING ROBOT- POSE 4
        circle.scale(g, X, Y, gap, 5, x - 8, y + 53, extras_c, s);                                                         // left eye
        circle.scaleDraw(g, X, Y, gap, 5, x - 8, y + 53, body_c, s);                                                       // left eye
        circle.scaleDraw(g, X, Y, gap, 1, x - 8, y + 53, body_c, s);                                                       // left eye_ball
        triangle.scale(g, X, Y, gap, x - 8, y + 50, x - 4, y + 43, x - 13, y + 43, extras_c, s);                                   // nose
        quadrilateral.scale(g, X, Y, gap, x - 11, y + 40, x - 11, y + 38, x + 3, y + 38, x + 3, y + 40, extras_c, s);                  // mouth
        quadrilateral.scale(g, X, Y, gap, x - 10, y + 60, x - 10, y + 35, y + 5, y + 35, x + 5, y + 60, blue, s);                      // head
        quadrilateral.scaleDraw(g, X, Y, gap, x - 10, y + 60, x - 10, y + 35, x + 5, y + 35, x + 5, y + 60, red, s);                   // head
        quadrilateral.scale(g, X, Y, gap, x - 3, y + 32, x + 3, y + 32, x + 23, y + 10, x + 17, y + 10, blue, s);                      // left hand
        quadrilateral.scaleDraw(g, X, Y, gap, x - 3, y + 32, x + 3, y + 32, x + 23, y + 10, x + 17, y + 10, red, s);                   // left hand
        quadrilateral.scale(g, X, Y, gap, x - 13, y + 32, x - 13, y + 0, x + 10, y + 0, x + 10, y + 32, blue, s);                      // body
        quadrilateral.scaleDraw(g, X, Y, gap, x - 13, y + 32, x - 13, y + 0, x + 10, y + 0, x + 10, y + 32, red, s);                   // body
        quadrilateral.scale(g, X, Y, gap, x - 13, y - 3, x - 18, y - 37, x - 13, y - 37, x - 8, y - 3, blue, s);                       // left leg
        quadrilateral.scaleDraw(g, X, Y, gap, x - 13, y - 3, x - 18, y - 37, x - 13, y - 37, x - 8, y - 3, red, s);                    // left leg
        quadrilateral.scale(g, X, Y, gap, x + 5, y - 3, x + 10, y - 3, x + 15, y - 37, x + 10, y - 37, blue, s);                       // right leg 
        quadrilateral.scaleDraw(g, X, Y, gap, x + 5, y - 3, x + 10, y - 3, x + 15, y - 37, x + 10, y - 37, red, s);                    // right leg           
        quadrilateral.scale(g, X, Y, gap, x + 1, y + 32, x - 5, y + 32, x - 23, y + 10, x - 17, y + 10, blue, s);                      // right hand
        quadrilateral.scaleDraw(g, X, Y, gap, x + 1, y + 32, x - 5, y + 32, x - 23, y + 10, x - 17, y + 10, red, s);                   // right hand
    }

    public void rightA(Graphics g) {
        // RIGHT-FACING ROBOT - POSE 1 
        circle.scale(g, X, Y, gap, 5, x + 3, y + 53, extras_c, s);                                                         // right eye
        circle.scaleDraw(g, X, Y, gap, 5, x + 3, y + 53, body_c, s);                                                       // right eye
        circle.scaleDraw(g, X, Y, gap, 1, x + 3, y + 53, body_c, s);                                                       // right eye_ball
        triangle.scale(g, X, Y, gap, x + 3, y + 50, x - 1, y + 43, x + 8, y + 43, extras_c, s);                                    // nose
        quadrilateral.scale(g, X, Y, gap, x - 8, y + 40, x - 6, y + 38, x + 6, y + 38, x + 6, y + 40, extras_c, s);                    // mouth
        quadrilateral.scale(g, X, Y, gap, x - 10, y + 60, x - 10, y + 35, x + 5, y + 35, x + 5, y + 60, blue, s);                      // head
        quadrilateral.scaleDraw(g, X, Y, gap, x - 10, y + 60, x - 10, y + 35, x + 5, y + 35, x + 5, y + 60, red, s);                   // head
        quadrilateral.scale(g, X, Y, gap, x - 3, y + 32, x + 3, y + 32, x - 20, y + 10, x - 26, y + 10, blue, s);                      // left hand
        quadrilateral.scaleDraw(g, X, Y, gap, x - 3, y + 32, x + 3, y + 32, x - 20, y + 10, x - 26, y + 10, red, s);                   // left hand
        quadrilateral.scale(g, X, Y, gap, x - 13, y + 32, x - 13, y + 0, x + 10, y + 0, x + 10, y + 32, blue, s);                      // body
        quadrilateral.scaleDraw(g, X, Y, gap, x - 13, y + 32, x - 13, y + 0, x + 10, y + 0, x + 10, y + 32, red, s);                   // body
        quadrilateral.scale(g, X, Y, gap, x + 5, y - 3, x + 10, y - 3, x - 8, y - 37, x - 13, y - 37, blue, s);                        // right leg 
        quadrilateral.scaleDraw(g, X, Y, gap, x + 5, y - 3, x + 10, y - 3, x - 8, y - 37, x - 13, y - 37, red, s);                     // right leg
        quadrilateral.scale(g, X, Y, gap, x - 13, y - 3, x + 5, y - 37, x + 10, y - 37, x - 8, y - 3, blue, s);                        // left leg
        quadrilateral.scaleDraw(g, X, Y, gap, x - 13, y - 3, x + 5, y - 37, x + 10, y - 37, x - 8, y - 3, red, s);                     // left leg
        quadrilateral.scale(g, X, Y, gap, x + 1, y + 32, x - 5, y + 32, x + 17, y + 10, x + 23, y + 10, blue, s);                      // right hand
        quadrilateral.scaleDraw(g, X, Y, gap, x + 1, y + 32, x - 5, y + 32, x + 17, y + 10, x + 23, y + 10, red, s);                   // right hand
    }

    public void rightB(Graphics g) {
        // RIGHT-FACING ROBOT - POSE 2 
        circle.scale(g, X, Y, gap, 5, x + 3, y + 53, extras_c, s);                                                         // right eye
        circle.scaleDraw(g, X, Y, gap, 5, x + 3, y + 53, body_c, s);                                                       // right eye
        circle.scaleDraw(g, X, Y, gap, 1, x + 3, y + 53, body_c, s);                                                       // right eye_ball
        triangle.scale(g, X, Y, gap, x + 3, y + 50, x - 1, y + 43, x + 8, y + 43, extras_c, s);                                    // nose
        quadrilateral.scale(g, X, Y, gap, x - 8, y + 40, x - 6, y + 38, x + 6, y + 38, x + 6, y + 40, extras_c, s);                    // mouth
        quadrilateral.scale(g, X, Y, gap, x - 10, y + 60, x - 10, y + 35, y + 5, y + 35, x + 5, y + 60, blue, s);                      // head
        quadrilateral.scaleDraw(g, X, Y, gap, x - 10, y + 60, x - 10, y + 35, x + 5, y + 35, x + 5, y + 60, red, s);                   // head
        quadrilateral.scale(g, X, Y, gap, x - 3, y + 32, x + 3, y + 32, x + 23, y + 10, x + 17, y + 10, blue, s);                      // left hand
        quadrilateral.scaleDraw(g, X, Y, gap, x - 3, y + 32, x + 3, y + 32, x + 23, y + 10, x + 17, y + 10, red, s);                   // left hand
        quadrilateral.scale(g, X, Y, gap, x - 13, y + 32, x - 13, y + 0, x + 10, y + 0, x + 10, y + 32, blue, s);                      // body
        quadrilateral.scaleDraw(g, X, Y, gap, x - 13, y + 32, x - 13, y + 0, x + 10, y + 0, x + 10, y + 32, red, s);                   // body
        quadrilateral.scale(g, X, Y, gap, x - 13, y - 3, x - 18, y - 37, x - 13, y - 37, x - 8, y - 3, blue, s);                       // left leg
        quadrilateral.scaleDraw(g, X, Y, gap, x - 13, y - 3, x - 18, y - 37, x - 13, y - 37, x - 8, y - 3, red, s);                    // left leg
        quadrilateral.scale(g, X, Y, gap, x + 5, y - 3, x + 10, y - 3, x + 15, y - 37, x + 10, y - 37, blue, s);                       // right leg 
        quadrilateral.scaleDraw(g, X, Y, gap, x + 5, y - 3, x + 10, y - 3, x + 15, y - 37, x + 10, y - 37, red, s);                    // right leg           
        quadrilateral.scale(g, X, Y, gap, x + 1, y + 32, x - 5, y + 32, x - 23, y + 10, x - 17, y + 10, blue, s);                      // right hand
        quadrilateral.scaleDraw(g, X, Y, gap, x + 1, y + 32, x - 5, y + 32, x - 23, y + 10, x - 17, y + 10, red, s);                   // right hand
        quadrilateral.scale(g, X, Y, gap, x - 10, y + 60, x - 10, y + 35, y + 5, y + 35, x + 5, y + 60, blue, s);                      // head
        quadrilateral.scaleDraw(g, X, Y, gap, x - 10, y + 60, x - 10, y + 35, x + 5, y + 35, x + 5, y + 60, red, s);                   // head
    }

    public void paint(Graphics g) {
        // DRAW THE ROBOT
        front(g);
    }

    @Override
    public void keyTyped(KeyEvent e) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_RIGHT: {
                vx = 5;
                break;
            }

            case KeyEvent.VK_LEFT: {
                vx = -5;
                break;
            }

            case KeyEvent.VK_UP: {
                vy = 5;
                break;
            }

            case KeyEvent.VK_DOWN: {
                vy = -5;
                break;
            }

        }
        // prevent diagonal movements
        if ((vx == 5 && vy == 5) || (vx == 5 && vy == -5) || (vx == -5 && vy == -5) || (vx == -5 && vy == 5)) {
            vx = 0;
            vy = 0;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_RIGHT: {
                vx = 0;
                break;
            }

            case KeyEvent.VK_LEFT: {
                vx = 0;
                break;
            }

            case KeyEvent.VK_UP: {
                vy = 0;
                break;
            }

            case KeyEvent.VK_DOWN: {
                vy = 0;
                break;
            }

        }

    }

}

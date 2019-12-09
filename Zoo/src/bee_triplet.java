import java.awt.*;

public class bee_triplet {
    
    private int x;
    private int y;
    private double angle; 
    private final double s;
    private final Ellipse ellipse;
    private final Line line;
    private final Circle circle;
    private final Triangle triangle;
    private final Quadrilateral quadrilateral;
    private final Color head_c;
    private final Color extras_c;
    private final Color body_c;
    private final Color cartoon_c;

    public bee_triplet() {
        this.angle = 200;
        this.x = (int) (Math.cos(angle) * 20 + -350);
        this.y = -(int) (Math.sin(angle) * 20 + 150);
        this.s = 0.2;
        this.ellipse = new Ellipse();
        this.line = new Line();
        this.circle = new Circle();
        this.triangle = new Triangle();
        this.quadrilateral = new Quadrilateral();
        this.head_c = new Color(255,215,15);     
        this.extras_c = new Color(0,0,0);        
        this.body_c = new Color(210,175,15);     
        this.cartoon_c = new Color(255,255,255); 
    }
    
    public void update(){  
        // increment angle by 0.1 radian everytime this function is called
        // if angle more than 2*PI, reset angle to zero.
        angle -= 0.1;
        if(angle == 0)
            angle = 2* Math.PI;
        if(angle > (2* Math.PI))
            angle = 0.0;

        // using parametric equations of circular path to find next point
        // x' = x0 + r*cos(theta)
        // y' = y0 + r*sin(theta)
        x = (int) (Math.cos(angle) * 20 + -350);
        y = -(int) (Math.sin(angle) * 20 + 150);
            
    }

    public void paint(Graphics g, main a) {
        
        // DRAW A BEE
        
        triangle.scale(g, a.X, a.Y, a.gap, x-10, y-60, x+10, y-60, x, y-80, extras_c,s);                     // tail
        ellipse.scale(g, a.X, a.Y, a.gap, 20, 25, x, y-40, body_c,s);                                        // body
        circle.scale(g, a.X, a.Y, a.gap, 25, x, y, head_c,s);                                                // head
        line.scale(g, a.X, a.Y, a.gap, x-5, y-10, x+5, y-10, extras_c,s);                                    // lips
        circle.scale(g, a.X, a.Y, a.gap, 6, x-11, y+5, extras_c,s);                                          // left_eye
        circle.scale(g,a.X,a.Y,a.gap,6,x+11,y+5,extras_c,s);                                                 // right_eye
        line.scale(g, a.X, a.Y, a.gap, x-8, y+24, x-18, y+36, extras_c,s);                                   // left_antenna
        line.scale(g, a.X, a.Y, a.gap, x+8, y+24, x+18, y+36, extras_c,s);                                   // right_antenna
        circle.scale(g, a.X, a.Y, a.gap, 3, x-20, y+39, extras_c,s);                                         // left_antenna_head
        circle.scale(g, a.X, a.Y, a.gap, 3, x+20, y+39, extras_c,s);                                         // right_antenna_head
        quadrilateral.scale(g, a.X, a.Y, a.gap, x-18, y-30, x+18, y-30, x+20, y-35, x-20, y-35, extras_c,s); // bodyline1
        quadrilateral.scale(g, a.X, a.Y, a.gap, x-20, y-40, x+20, y-40, x+20, y-45, x-20, y-45, extras_c,s); // bodyline2
        quadrilateral.scale(g, a.X, a.Y, a.gap, x-19, y-50, x+19, y-50, x+17, y-55, x-17, y-55, extras_c,s); // bodyline3
        circle.scale(g, a.X, a.Y, a.gap, 1, x-13, y+7, cartoon_c,s);                                         // left_eye_inner
        circle.scale(g,a.X,a.Y,a.gap,1,x+13,y+7,cartoon_c,s);                                                // right_eye_inner
    }
    
}


import java.awt.*;
import java.applet.*;
import java.awt.event.*;

/**
 *
 * @author Anjishnu
 */

class Point 
{
    public void plot(Graphics g, int X, int Y, int gap, int x1, int y1, Color c)
    {
        g.setColor(c);
        g.fillRect(X+x1*gap-gap/2, Y-y1*gap-gap/2, gap, gap);  
    }
}

class Line 
{
    public void DDA (Graphics g, int X, int Y, int gap, double x1, double y1, double x2, double y2, Color c)
    {
        // calculate dx and dy
        double dx = x2 - x1;
        double dy = y2 - y1;

        // calculate the number of steps needed
        double steps = (Math.abs(dx) > Math.abs(dy) ? Math.abs(dx) : Math.abs(dy));

        // calculate increment in x and y for each step
        double x_inc = (dx/steps);
        double y_inc = (dy/steps);

        // put pixel for each step 
        double x = x1;
        double y = y1;

        Point p1 = new Point();

        // plot (x1,y1)
        p1.plot(g, X, Y, gap, (int)x, (int)y,c);
        
        // plot other points (xk,yk) from k=1 to k=steps
        for(int k = 1; k <= steps ; k++)
        {
           x = x + x_inc;
           y = y + y_inc;
           p1.plot(g, X, Y, gap, (int)x, (int)y,c);
        }

    }
    
    // modified bresenham algorithm
    public void Bresenham (Graphics g, int X, int Y, int gap, int x0, int y0, int x1, int y1, Color c)
    {
        // calculate dx, sx, dy, sy
        int dx = Math.abs(x1-x0);
        int sx = (x0<x1 ? 1:-1);
        int dy = -Math.abs(y1-y0);
        int sy = (y0<y1 ? 1:-1);
        
        // calculate the error
        int err = dx + dy;
        int e2;
        
        Point p2 = new Point();
        
        while(true)
        {
            p2.plot(g, X, Y, gap, x0, y0,c);
            e2 = 2*err;

            if(e2 >= dy)
            {
                if(x0 == x1)
                    break;
                err += dy;
                x0 += sx;
            }
            if(e2 <= dx)
            {
                if(y0 == y1)
                    break;
                err += dx;
                y0 += sy;
            }
        }
    }
    
    public void Midpoint (Graphics g, int X, int Y, int gap, double x0, double y0, double x1, double y1, Color c)
    {
        // calculate dx and dy
        double dx = x1 - x0;
        double dy = y1 - y0;
        
        // calculate initial value of decision parameter
        double d = dy - (dx/2);
        double x = x0; 
        double y = y0; 
        
        Point p1 = new Point();
        
        // plot (x0,y0)
        p1.plot(g, X, Y, gap, (int)x, (int)y,c);
        
        // plot other points
        while(x < x1)
        {
            x ++;
            if(d < 0)
                d += dy;
            else
            {
                d += (dy - dx);
                y ++;
            }
            // plot this point
            p1.plot(g, X, Y, gap, (int)x, (int)y,c);
        }
    }

}

class Triangle
{
    public void drawTriangle(Graphics g, int X, int Y, int gap, int x0, int y0, int x1, int y1, int x2, int y2, Color c)
    {
        Line l = new Line();
        l.Bresenham(g, X, Y, gap, x0, y0, x1, y1,c);
        l.Bresenham(g, X, Y, gap, x1, y1, x2, y2,c);
        l.Bresenham(g, X, Y, gap, x2, y2, x0, y0,c);
    }
    
    // Reference : https://stackoverflow.com/questions/2049582/how-to-determine-if-a-point-is-in-a-2d-triangle
    private double sign (int x0, int y0, int x1, int y1, int x2, int y2)
    {
        return (x0 - x2) * (y1 - y2) - (x1 - x2) * (y0 - y2);
    }

    private boolean PointInTriangle (int x, int y, int x0, int y0, int x1, int y1, int x2, int y2)
    {
        double d1, d2, d3;
        boolean has_neg, has_pos;

        d1 = sign(x,y, x0,y0, x1,y1);
        d2 = sign(x,y, x1,y1, x2,y2);
        d3 = sign(x,y, x2,y2, x0,y0);

        has_neg = (d1 < 0) || (d2 < 0) || (d3 < 0);
        has_pos = (d1 > 0) || (d2 > 0) || (d3 > 0);

        return !(has_neg && has_pos);
    }
    
    public void fill(Graphics g, int X, int Y, int gap, int x0, int y0, int x1, int y1, int x2, int y2, Color c)
    {
        int maxX = Math.max(x0, Math.max(x1,x2));
        int minX = Math.min(x0, Math.min(x1,x2));
        int maxY = Math.max(y0, Math.max(y1,y2));
        int minY = Math.min(y0, Math.min(y1,y2));
        
        Point p1 = new Point();
        
        for(int x = minX; x <= maxX; x++)
            for(int y = minY; y <= maxY; y++)
                if(PointInTriangle(x,y,x0,y0,x1,y1,x2,y2))
                    p1.plot(g, X, Y, gap, x, y, c);
      
    }
    
}

// Considering convex quadrilateral only
class Quadrilateral
{
    // assume (x0,y0)->(x1,y1)->(x2,y2)->(x3,y3) is specified in a cyclic order
    public void drawQuadrilateral(Graphics g, int X, int Y, int gap, int x0, int y0, int x1, int y1, int x2, int y2, int x3, int y3, Color c)
    {
        Line l = new Line();
        l.Bresenham(g, X, Y, gap, x0, y0, x1, y1,c);
        l.Bresenham(g, X, Y, gap, x1, y1, x2, y2,c);
        l.Bresenham(g, X, Y, gap, x2, y2, x3, y3,c);
        l.Bresenham(g, X, Y, gap, x3, y3, x0, y0,c);
    }
    
    // Reference : https://stackoverflow.com/questions/2049582/how-to-determine-if-a-point-is-in-a-2d-triangle
    private double sign (int x0, int y0, int x1, int y1, int x2, int y2)
    {
        return (x0 - x2) * (y1 - y2) - (x1 - x2) * (y0 - y2);
    }

    private boolean PointInTriangle (int x, int y, int x0, int y0, int x1, int y1, int x2, int y2)
    {
        double d1, d2, d3;
        boolean has_neg, has_pos;

        d1 = sign(x,y, x0,y0, x1,y1);
        d2 = sign(x,y, x1,y1, x2,y2);
        d3 = sign(x,y, x2,y2, x0,y0);

        has_neg = (d1 < 0) || (d2 < 0) || (d3 < 0);
        has_pos = (d1 > 0) || (d2 > 0) || (d3 > 0);

        return !(has_neg && has_pos);
    }
    
    public void fill(Graphics g, int X, int Y, int gap, int x0, int y0, int x1, int y1, int x2, int y2, int x3, int y3, Color c)
    {
        int maxX = Math.max(Math.max(x0,x1), Math.max(x2,x3));
        int minX = Math.min(Math.min(x0,x1), Math.min(x2,x3));
        int maxY = Math.max(Math.max(y0,y1), Math.max(y2,y3));
        int minY = Math.min(Math.min(y0,y1), Math.min(y2,y3));
        
        Point p1 = new Point();
        
        for(int x = minX; x <= maxX; x++)
            for(int y = minY; y <= maxY; y++)
                if(PointInTriangle(x,y,x0,y0,x1,y1,x2,y2) || PointInTriangle(x,y,x2,y2,x3,y3,x0,y0))
                    p1.plot(g, X, Y, gap, x, y, c);
    }
}


class Circle
{
    public void Midpoint (Graphics g, int X, int Y, int gap, int radius,int xcent,int ycent, Color c)
    {
        int x = radius;
        int y = 0; 

        // Initialising the value of p 
        int p = 1 - radius; 

        Point p1 = new Point();

        // Printing the initial point on the axes  after translation 
        p1.plot(g, X, Y, gap, x + xcent, y + ycent, c);
        p1.plot(g, X, Y, gap, -x + xcent, -y + ycent, c);

        // When radius is zero only a single point will be printed 
        if (radius > 0) 
        { 
            p1.plot(g, X, Y, gap,  x + xcent, -y + ycent, c);
            p1.plot(g, X, Y, gap,  y + xcent,  x + ycent, c);
            p1.plot(g, X, Y, gap, -y + xcent,  x + ycent, c);
            p1.plot(g, X, Y, gap, y + xcent,  -x + ycent, c);
        } 


        while (x > y) 
        {  
            y++; 

            // Mid-point is inside or on the perimeter 
            if (p <= 0) 
                p = p + 2*y + 1; 

            // Mid-point is outside the perimeter 
            else
            { 
                x--; 
                p = p + 2*y - 2*x + 1; 
            } 

            // All the perimeter points have already been printed 
            if (x < y) 
                break; 

            // Printing the generated point and its reflection 
            // in the other octants after translation 
            p1.plot(g, X, Y, gap,  x + xcent,  y + ycent, c);
            p1.plot(g, X, Y, gap, -x + xcent,  y + ycent, c);
            p1.plot(g, X, Y, gap,  x + xcent, -y + ycent, c);
            p1.plot(g, X, Y, gap, -x + xcent, -y + ycent, c);

            // If the generated point is on the line x = y then  
            // the perimeter points have already been printed 
            if (x != y) 
            {
                p1.plot(g, X, Y, gap,  y + xcent,  x + ycent, c);
                p1.plot(g, X, Y, gap, -y + xcent,  x + ycent, c);
                p1.plot(g, X, Y, gap,  y + xcent, -x + ycent, c);
                p1.plot(g, X, Y, gap, -y + xcent, -x + ycent, c);

            } 
        }
    } 
    
    public void fill (Graphics g, int X, int Y, int gap, int radius,int xcent,int ycent, Color c)
    {
        Point p1 = new Point();
        
        // Reference : https://stackoverflow.com/questions/10322341/simple-algorithm-for-drawing-filled-ellipse-in-c-c
        for(int y =-radius; y <= radius; y++)
            for(int x =-radius; x <=radius; x++)
                if(x*x + y*y <= radius*radius)
                    p1.plot(g, X, Y,gap, xcent+x, ycent+y, c);
    }
}

class Ellipse
{
    // modified midpoint algorithm
    public void Midpoint (Graphics g, int X, int Y, int gap, int rx,int ry, int xcent,int ycent, Color c)
    {
        double x = -rx;
        double y = 0;
        double e2 = ry;
        double dx = (1+2*x)*e2*e2;
        double dy = x*x;
        double err = dx+dy;
        
        Point p1 = new Point();
        
        do
        {
            p1.plot(g, X, Y, gap, xcent-(int)x, ycent+(int)y, c);
            p1.plot(g, X, Y, gap, xcent+(int)x, ycent+(int)y, c);
            p1.plot(g, X, Y, gap, xcent+(int)x, ycent-(int)y, c);
            p1.plot(g, X, Y, gap, xcent-(int)x, ycent-(int)y, c);
            
            e2 = 2*err;
            
            if(e2 >= dx)
            {
                x++;
                err += dx += 2*(double)ry*ry;
            }
            
            if(e2 <= dy)
            {
                y++;
                err += dy += 2*(double)rx*rx;
            }
            
        } while(x<=0);
        
        while (y++ < ry)
        {
            p1.plot(g, X, Y, gap, xcent, ycent+(int)y, c);
            p1.plot(g, X, Y, gap, xcent, ycent-(int)y, c);
        }
    }
    
    public void fill (Graphics g, int X, int Y, int gap, int rx,int ry,int xcent,int ycent, Color c)
    {
        Point p1 = new Point();
        
        // Reference : https://stackoverflow.com/questions/10322341/simple-algorithm-for-drawing-filled-ellipse-in-c-c
        for(int y = -ry; y <= ry; y++)
            for(int x = -rx; x <= rx; x++)
                if(x*x*ry*ry + y*y*rx*rx <= rx*rx*ry*ry)
                    p1.plot(g, X, Y, gap, xcent+x, ycent+y, c);
    }
    
}

class Bee
{
    public void drawBee(Graphics g, int X, int Y, int gap)
    {
        // location of centre of head
        int x = 0;
        int y = 20;
        
        // objects for composing bee
        Ellipse ellipse = new Ellipse();
        Line line = new Line();
        Circle circle = new Circle();
        Triangle triangle = new Triangle();
        Quadrilateral quadrilateral = new Quadrilateral();

        // Color palette
        Color head_c = new Color(255,215,15);     //Brownish-orange
        Color extras_c = new Color(0,0,0);        // Black
        Color body_c = new Color(210,175,15);     // Yellow-Ochre
        Color cartoon_c = new Color(255,255,255); // white
        
        // Draw the bee
        circle.Midpoint(g,X,Y,gap,25,x,y,head_c);                   // head
        circle.Midpoint(g, X, Y, gap, 6, x-11, y+5, extras_c);  // left_eye
        circle.Midpoint(g,X,Y,gap,6,x+11,y+5,extras_c);        // right_eye
        line.Bresenham(g, X, Y, gap, x-8, y+24, x-18, y+36, extras_c); // left_antenna
        line.Bresenham(g, X, Y, gap, x+8, y+24, x+18, y+36, extras_c); // right_antenna
        circle.Midpoint(g, X, Y, gap, 3, x-20, y+39, extras_c);      // left_antenna_head
        circle.Midpoint(g, X, Y, gap, 3, x+20, y+39, extras_c);     // right_antenna_head 
        ellipse.Midpoint(g, X, Y, gap, 20, 25, x, y-40, body_c);                   // body
        quadrilateral.drawQuadrilateral(g, X, Y, gap, x-18, y-30, x+18, y-30, x+20, y-35, x-20, y-35, extras_c); // bodyline 1
        quadrilateral.drawQuadrilateral(g, X, Y, gap, x-20, y-40, x+20, y-40, x+20, y-45, x-20, y-45, extras_c); // bodyline 2
        quadrilateral.drawQuadrilateral(g, X, Y, gap, x-19, y-50, x+19, y-50, x+17, y-55, x-17, y-55, extras_c); // bodyline 3
        circle.Midpoint(g, X, Y, gap, 1, x-13, y+7, cartoon_c);     // left_eye_inner
        circle.Midpoint(g,X,Y,gap,1,x+13,y+7,cartoon_c);           // right_eye_inner
        triangle.drawTriangle(g, X, Y, gap, x-10, y-60, x+10, y-60, x, y-80, body_c); // tail
        
        // Colour the bee
        triangle.fill(g, X, Y, gap, x-10, y-60, x+10, y-60, x, y-80, extras_c);  // tail
        ellipse.fill(g, X, Y, gap, 20, 25, x, y-40, body_c);                    // body
        circle.fill(g, X, Y, gap, 25, x, y, head_c);                           // head
        line.Bresenham(g, X, Y, gap, x-5, y-10, x+5, y-10, extras_c);        // lips
        circle.fill(g, X, Y, gap, 6, x-11, y+5, extras_c);                 // left_eye
        circle.fill(g,X,Y,gap,6,x+11,y+5,extras_c);                       // right_eye
        circle.fill(g, X, Y, gap, 3, x-20, y+39, extras_c);       // left_antenna_head
        circle.fill(g, X, Y, gap, 3, x+20, y+39, extras_c);      // right_antenna_head
        quadrilateral.fill(g, X, Y, gap, x-18, y-30, x+18, y-30, x+20, y-35, x-20, y-35, extras_c); // bodyline1
        quadrilateral.fill(g, X, Y, gap, x-20, y-40, x+20, y-40, x+20, y-45, x-20, y-45, extras_c); // bodyline2
        quadrilateral.fill(g, X, Y, gap, x-19, y-50, x+19, y-50, x+17, y-55, x-17, y-55, extras_c); // bodyline3
        circle.fill(g, X, Y, gap, 1, x-13, y+7, cartoon_c);          // left_eye_inner
        circle.fill(g,X,Y,gap,1,x+13,y+7,cartoon_c);                // right_eye_inner
    }
}

class BeeHive
{
    public void drawBeeHive(Graphics g, int X,int Y, int gap)
    {
        // centre of the entire structure
        int x = 0;
        int y = 0;
        
        // objects required for drawing
        Ellipse ellipse = new Ellipse();
        Line line = new Line();

        // color palette
        Color body_c = new Color(210,175,15);  // Yellow-Ochre
        Color lines_c = new Color(160,110,30); // Black
        
        // draw and color the structure
        ellipse.Midpoint(g, X, Y, gap, 30, 40, x, y, body_c);                // body
        ellipse.fill(g, X, Y, gap, 30, 40, x, y, body_c);                    // body
        line.Bresenham(g, X, Y, gap, x-20, y+30, x+20, y+30, lines_c);       // line1
        line.Bresenham(g, X, Y, gap, x-26, y+20, x+26, y+20, lines_c);       // line2
        line.Bresenham(g, X, Y, gap, x-29, y+10, x+29, y+10, lines_c);       // line3
        line.Bresenham(g, X, Y, gap, x-30, y, x+30, y, lines_c);             // line4
        line.Bresenham(g, X, Y, gap, x-29, y-10, x+29, y-10, lines_c);       // line5
        line.Bresenham(g, X, Y, gap, x-26, y-20, x+26, y-20, lines_c);       // line6
        line.Bresenham(g, X, Y, gap, x-20, y-30, x+20, y-30, lines_c);       // line7
        ellipse.Midpoint(g, X, Y, gap, 5, 2, x+9, y-5, lines_c);             // hole
        ellipse.fill(g, X, Y, gap, 5, 2, x+9, y-5, lines_c);                 // hole
    }
}

public class bees extends Applet implements ActionListener 
{
    private int X,Y,gap,status, image; 
    
    // Constructor
    public bees() 
    {
        this.gap = 4;
        this.status = 0;
        this.image = 0;
    }
    
    @Override
    public void init() 
    {
        // screen size
        setSize(new Dimension(800,600));
        
        // background color
        Color bgcolor=new Color(153,255,255);
        setBackground(bgcolor);
        
        // Buttons
        Button b1 = new Button("+");
        Button b2 = new Button("-");
        Button b3 = new Button("Bee");
        Button b4 = new Button("Beehive");

        add(b1);
        add(b2);
        add(b3);
        add(b4);

        b1.addActionListener(this);
        b2.addActionListener(this);
        b3.addActionListener(this);
        b4.addActionListener(this);

          
        // Text Fields and Labels
        //Label l1 = new Label("r_x = ");
        //Label l2 = new Label("r_y = ");
        //Label l3 = new Label("x_c  = ");
        //Label l4 = new Label("y_c  = ");
        
        //add(l1);
        //add(t1);
        //add(l2);
        //add(t2);
        //add(l3);
        //add(t3);
        //add(l4);
        //add(t4);
        
    }
    
    private void drawGrid (Graphics g, int X, int Y, int gap)
    {
        // Set a clor for drawing grid lines
        Color mycolor=new Color(0,0,200);
        g.setColor(mycolor);

        // lines parallel to X-axis below X-axis
        for(int i=Y;i<=getHeight();i=i+gap)
            g.drawLine(0, i, getWidth(), i);

        // lines parallel to X-axis above X-axis
        for(int i=Y;i>=0;i=i-gap)
            g.drawLine(0, i, getWidth(), i);

        // lines parallel to Y-axis to the right of Y-axis
        for(int j=X;j<=getWidth();j=j+gap)
            g.drawLine(j,0,j,getHeight());

        // lines parallel to Y-axis to the left of Y-axis
        for(int j=X;j>=0;j=j-gap)
            g.drawLine(j,0,j,getHeight());
    }
    
    private void drawAxes (Graphics g)
    {
        // Set a different color to draw axis lines
        Color axiscolor=new Color(0,255,0);
        g.setColor(axiscolor);
        
        // X- axis
        g.drawLine(0, getHeight()/2, getWidth(), getHeight()/2);
        // Y- axis
        g.drawLine(getWidth()/2, 0, getWidth()/2, getHeight());
    }
      
    @Override
    public void paint(Graphics g)
    {
        switch (status) 
        {
            case 1:
                if(gap < 100)
                    gap = gap+2;
                else
                    gap = 80;
                break;
                
            case 2:
                if(gap > 5)
                    gap = gap-2;
                else
                    gap = 4;
                break;
                
            default :
                break;
                              
        }
        
        X=(getX()+ getWidth())/2;
        Y=(getY()+getHeight())/2;
        drawGrid(g, X, Y, gap);
        drawAxes(g);
        
        Color a = new Color(255,0,0);
        g.setColor(a);

        switch (image) 
        {
            case 1:
                // BEE
                Bee bee = new Bee();
                bee.drawBee(g,X,Y,gap);
                break;

            case 2:
                // BEE HIVE
                BeeHive beehive = new BeeHive();
                beehive.drawBeeHive(g, X, Y, gap);
                break;

            default :
                break;

        }
        
        
    }

    @Override
    public void actionPerformed(ActionEvent ae)
    {

        String action = ae.getActionCommand();
        
        if (null != action) 
        {
            switch (action) 
            {
                case "+":
                    status = 1;
                    break;
                    
                case "-":
                    status = 2; 
                    break;
                    
                case "Bee":
                    image = 1; 
                    break;
                    
                case "Beehive":
                    image = 2; 
                    break;

                default:
                    break;
            }
        }
        repaint();
       }
}

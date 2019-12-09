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

class Tiger
{
    public void drawTiger(Graphics g, int X, int Y, int gap)
    {
        // location of centre of head
        int x = 0;
        int y = 0;
        
        // objects for composing tiger
        Point point = new Point();
        Line line = new Line();
        Quadrilateral quadrilateral = new Quadrilateral();
        Triangle triangle = new Triangle();
        Circle circle = new Circle();
        Ellipse ellipse = new Ellipse();
        
        // Color palette
        Color extras_c = new Color(0,0,0);        // black
        Color cartoon_c = new Color(255,255,255); // white
        Color head_c = new Color(250,180,0);      
        Color nose_c = new Color(240,205,15);
        Color body_c = new Color(230,160,15);     
        Color moustache_c = new Color(250,230,190);
        Color nose_tip_c = new Color(80,60,30);
        Color beard_c = new Color(230,210,35);
        Color eyes_c = new Color(130,90,30);
        Color lips_c = new Color(250,65,65);
        
        // Draw the tiger
        quadrilateral.drawQuadrilateral(g, X, Y, gap, x-30, y+5, x+30, y+5, x+20, y-35, x-20, y-35, body_c ); // body
        quadrilateral.fill(g, X, Y, gap, x-30, y+5, x+30, y+5, x+20, y-35, x-20, y-35, body_c );              // body
        triangle.drawTriangle(g, X, Y, gap, x-28, y+18, x-35, y+37, x-30, y+50, head_c);       // left_ear
        triangle.fill(g, X, Y, gap, x-28, y+18, x-35, y+37, x-30, y+50, head_c);               // left_ear
        triangle.drawTriangle(g, X, Y, gap, x-28, y+19, x-30, y+50, x-15, y+32, beard_c);      // left_ear_inner
        triangle.fill(g, X, Y, gap, x-28, y+19, x-30, y+50, x-15, y+32, beard_c);              // left_ear_inner
        triangle.drawTriangle(g, X, Y, gap, x+28, y+18, x+35, y+37, x+30, y+50, head_c);      // right_ear_outer
        triangle.fill(g, X, Y, gap, x+28, y+18, x+35, y+37, x+30, y+50, head_c);              // right_ear_outer
        triangle.drawTriangle(g, X, Y, gap, x+28, y+19, x+30, y+50, x+15, y+32, beard_c);     // right_ear_inner
        triangle.fill(g, X, Y, gap, x+28, y+19, x+30, y+50, x+15, y+32, beard_c);             // right_ear_inner
        circle.Midpoint(g,X,Y,gap,30,x,y+5,head_c);                                                    // head
        circle.fill(g,X,Y,gap,30,x,y+5,head_c);                                                        // head
        quadrilateral.drawQuadrilateral(g, X, Y, gap, x-5, y-5, x+5, y-5, x+9, y-15, x-9, y-15, moustache_c);   // moustache_area_upper
        quadrilateral.fill(g, X, Y, gap, x-5, y-5, x+5, y-5, x+9, y-15, x-9, y-15, moustache_c);                // moustache_area_upper
        quadrilateral.drawQuadrilateral(g, X, Y, gap, x-9, y-15, x+9, y-15, x+5, y-25, x-5, y-25, moustache_c); // moustache_area_lower
        quadrilateral.fill(g, X, Y, gap, x-9, y-15, x+9, y-15, x+5, y-25, x-5, y-25, moustache_c);              // moustache_area_upper
        ellipse.Midpoint(g, X, Y, gap, 8, 9, x, y-20, moustache_c);                                        // chin
        ellipse.fill(g, X, Y, gap, 8, 9, x, y-20, moustache_c);                                            // chin
        triangle.drawTriangle(g, X, Y, gap, x-8, y-25, x, y-20, x, y-22, lips_c);                // open_mouth_left
        triangle.drawTriangle(g, X, Y, gap, x, y-20, x+8, y-25, x, y-22, lips_c);               // open_mouth_right
        triangle.fill(g, X, Y, gap, x-8, y-25, x, y-20, x, y-22, lips_c);                        // open_mouth_left
        triangle.fill(g, X, Y, gap, x, y-20, x+8, y-25, x, y-22, lips_c);                       // open_mouth_right
        ellipse.Midpoint(g, X, Y, gap, 6, 7, x, y-8, nose_tip_c);                                     // nose_bulb
        ellipse.fill(g, X, Y, gap, 6, 7, x, y-8, nose_tip_c);                                         // nose_bulb
        quadrilateral.drawQuadrilateral(g, X, Y, gap, x-8, y+12, x+8, y+12, x+6, y-5, x-6, y-5, nose_c);  // nose_bridge
        triangle.drawTriangle(g, X, Y, gap, x-6, y-5, x+6, y-5, x, y-8, nose_c);                        // nose_tip
        quadrilateral.fill(g, X, Y, gap, x-8, y+12, x+8, y+12, x+6, y-5, x-6, y-5, nose_c);               // nose_bridge
        triangle.fill(g, X, Y, gap, x-6, y-5, x+6, y-5, x, y-8, nose_c);                                // nose_tip
        triangle.drawTriangle(g, X, Y, gap, x-30, y+5, x-20, y-5, x-25, y-15, beard_c);         // cheek_left_upper
        triangle.fill(g, X, Y, gap, x-30, y+5, x-20, y-5, x-25, y-15, beard_c);                 // cheek_left_upper
        triangle.drawTriangle(g, X, Y, gap, x-20, y-5, x-10, y-25, x-25, y-15, beard_c);        // cheek_left_lower
        triangle.fill(g, X, Y, gap, x-20, y-5, x-10, y-25, x-25, y-15, beard_c);                // cheek_left_lower
        triangle.drawTriangle(g, X, Y, gap, x+30, y+5, x+20, y-5, x+25, y-15, beard_c);        // cheek_right_upper
        triangle.fill(g, X, Y, gap, x+30, y+5, x+20, y-5, x+25, y-15, beard_c);                // cheek_right_upper
        triangle.drawTriangle(g, X, Y, gap, x+20, y-5, x+10, y-25, x+25, y-15, beard_c);       // cheek_right_lower
        triangle.fill(g, X, Y, gap, x+20, y-5, x+10, y-25, x+25, y-15, beard_c);               // cheek_right_lower
        triangle.drawTriangle(g, X, Y, gap, x-6, y-5, x-9, y+12, x-6, y+20, beard_c);            // nose_ridge_left
        triangle.fill(g, X, Y, gap, x-6, y-5, x-9, y+12, x-6, y+20, beard_c);                    // nose_ridge_left
        triangle.drawTriangle(g, X, Y, gap, x+6, y-5, x+9, y+12, x+6, y+20, beard_c);           // nose_ridge_right
        triangle.fill(g, X, Y, gap, x+6, y-5, x+9, y+12, x+6, y+20, beard_c);                   // nose_ridge_right
        triangle.drawTriangle(g, X, Y, gap, x-20, y+12, x-9, y+12, x-9, y, extras_c);         // left_eye_bag_upper
        triangle.fill(g, X, Y, gap, x-20, y+12, x-9, y+12, x-9, y, extras_c);                 // left_eye_bag_upper
        triangle.drawTriangle(g, X, Y, gap, x+20, y+12, x+9, y+12, x+9, y, extras_c);        // right_eye_bag_upper
        triangle.fill(g, X, Y, gap, x+20, y+12, x+9, y+12, x+9, y, extras_c);                // right_eye_bag_upper
        circle.Midpoint(g,X,Y,gap,3,x-12,y+8,eyes_c);                                        // left_retina_outer
        circle.fill(g,X,Y,gap,3,x-12,y+8,eyes_c);                                            // left_retina_outer
        circle.Midpoint(g,X,Y,gap,3,x+12,y+8,eyes_c);                                       // right_retina_outer
        circle.fill(g,X,Y,gap,3,x+12,y+8,eyes_c);                                           // right_retina_outer
        point.plot(g, X, Y, gap, x-12, y+8, extras_c);                                      // left_retina_inner
        point.plot(g, X, Y, gap, x+12, y+8, extras_c);                                     // right_retina_inner
        line.Bresenham(g,X, Y, gap, x-6, y-18, x-15, y-12, cartoon_c);                    // left_upper_whisker
        line.Bresenham(g, X, Y, gap, x-6, y-20, x-15, y-15, cartoon_c);                   // right_lower_whisker 
        line.Bresenham(g, X, Y, gap, x+6, y-18, x+15, y-12, cartoon_c);                  // left_upper_whisker
        line.Bresenham(g, X, Y, gap, x+6, y-20, x+15, y-15, cartoon_c);                  // right_lower_whisker
    }
}

class Lion
{
    public void drawLion(Graphics g, int X, int Y, int gap)
    {
        // location of centre of head
        int x = 0;
        int y = 0;
        
        // objects for composing lion
        Line line = new Line();
        Quadrilateral quadrilateral = new Quadrilateral();
        Triangle triangle = new Triangle();
        Circle circle = new Circle();

        // Color palette
        Color extras_c = new Color(0,0,0);        // black 
        Color cartoon_c = new Color(255,255,255); // white
        Color mane_c = new Color(190,20,20);      
        Color nose_c = new Color(255,150,0);
        Color body_c = new Color(255,150,10);     
        Color moustache_c = new Color(250,200,135);
        Color nose_tip_c = new Color(80,60,30);
        Color beard_c = new Color(250,165,55); 
        Color lips_c = new Color(250,65,65);
        
        // Draw the lion
        quadrilateral.drawQuadrilateral(g, X, Y, gap, x-10, y+15, x+10, y+15, x+25, y-39, x-25, y-39, body_c ); // body
        quadrilateral.fill(g, X, Y, gap, x-10, y+15, x+10, y+15, x+25, y-39, x-25, y-39, body_c );              // body             
        circle.Midpoint(g,X,Y,gap,40,x,y+5,mane_c);  // mane
        circle.fill(g,X,Y,gap,40,x,y+5,mane_c);      // mane 
        quadrilateral.drawQuadrilateral(g, X, Y, gap, x, y+20, x, y-6, x-15, y-6, x-20, y+6, beard_c );  // face_lower_left
        quadrilateral.fill(g, X, Y, gap, x, y+20, x, y-6, x-15, y-6, x-20, y+6, beard_c );               // face_lower_left
        quadrilateral.drawQuadrilateral(g, X, Y, gap, x, y+20, x, y-6, x+15, y-6, x+20, y+6, beard_c );  // face_lower_right
        quadrilateral.fill(g, X, Y, gap, x, y+20, x, y-6, x+15, y-6, x+20, y+6, beard_c);                // face_lower_right
        triangle.drawTriangle(g, X, Y, gap, x-20, y+6, x-13, y+ 24, x, y+13,beard_c);         // face_upper_left
        triangle.fill(g, X, Y, gap, x-20, y+6, x-13, y+ 24, x, y+13,beard_c);                 // face_upper_left
        triangle.drawTriangle(g, X, Y, gap, x+20, y+6, x+13, y+ 24, x, y+13,beard_c);         // face_upper_right
        triangle.fill(g, X, Y, gap, x+20, y+6, x+13, y+ 24, x, y+13,beard_c);                 // face_upper_right
        quadrilateral.drawQuadrilateral(g, X, Y, gap, x-15, y-6, x+15, y-6, x+11, y-11, x-11, y-11, moustache_c); // moustache_area_upper
        quadrilateral.fill(g, X, Y, gap, x-15, y-6, x+15, y-6, x+11, y-11, x-11, y-11, moustache_c);              // moustache_area_upper  
        quadrilateral.drawQuadrilateral(g, X, Y, gap, x-11, y-11, x+11, y-11, x+6, y-25, x-6, y-25, moustache_c); // moustache_area_lower
        quadrilateral.fill(g, X, Y, gap, x-11, y-11, x+11, y-11, x+6, y-25, x-6, y-25, moustache_c);              // moustache_area_lower
        line.Bresenham(g, X, Y, gap, x-6, y-10, x-20, y-13, cartoon_c);  // left_whisker_1
        line.Bresenham(g, X, Y, gap, x-6, y-11, x-21, y-17, cartoon_c);  // left_whisker_2
        line.Bresenham(g, X, Y, gap, x-6, y-12, x-21, y-22, cartoon_c);  // left_whisker_3
        line.Bresenham(g, X, Y, gap, x-6, y-12, x-21, y-27, cartoon_c);  // left_whisker_4
        line.Bresenham(g, X, Y, gap, x+6, y-10, x+20, y-13, cartoon_c);  // right_whisker_1
        line.Bresenham(g, X, Y, gap, x+6, y-11, x+21, y-17, cartoon_c);  // right_whisker_2
        line.Bresenham(g, X, Y, gap, x+6, y-12, x+21, y-22, cartoon_c);  // right_whisker_3
        line.Bresenham(g, X, Y, gap, x+6, y-12, x+21, y-27, cartoon_c);  // right_whisker_4
        triangle.drawTriangle(g, X, Y, gap, x-6, y+10, x, y+2, x-12, y+10,extras_c);    // left_eye
        triangle.fill(g, X, Y, gap, x-6, y+10, x, y+2, x-12, y+10,extras_c);            // left_eye
        triangle.drawTriangle(g, X, Y, gap, x+6, y+10, x, y+2, x+12, y+10,extras_c);    // right_eye
        triangle.fill(g, X, Y, gap, x+6, y+10, x, y+2, x+12, y+10,extras_c);            // right_eye
        quadrilateral.drawQuadrilateral(g, X, Y, gap, x-10, y+20, x+10, y+20, x+6, y+10, x-6, y+10, nose_c);  // nose_bridge_up
        quadrilateral.fill(g, X, Y, gap, x-10, y+20, x+10, y+20, x+6, y+10, x-6, y+10, nose_c);               // nose_bridge_up
        quadrilateral.drawQuadrilateral(g, X, Y, gap, x-6, y+10, x+6, y+10, x+8, y-2, x-8, y-2, nose_c);      // nose_bridge
        quadrilateral.fill(g, X, Y, gap, x-6, y+10, x+6, y+10, x+8, y-2, x-8, y-2, nose_c);                   // nose_bridge
        triangle.drawTriangle(g, X, Y, gap, x-8, y-2, x+8, y-2, x, y-8, nose_c);                // nose_tip
        triangle.fill(g, X, Y, gap, x-8, y-2, x+8, y-2, x, y-8, nose_c);                        // nose_tip
        triangle.drawTriangle(g, X, Y, gap, x-8, y-2, x, y-8, x, y-14, nose_tip_c);             // nose_bulb_left
        triangle.fill(g, X, Y, gap, x-8, y-2, x, y-8, x, y-14, nose_tip_c);                     // nose_bulb_left
        triangle.drawTriangle(g, X, Y, gap, x+8, y-2, x, y-8, x, y-14, nose_tip_c);             // nose_bulb_right
        triangle.fill(g, X, Y, gap, x+8, y-2, x, y-8, x, y-14, nose_tip_c);                     // nose_bulb_right
        triangle.drawTriangle(g, X, Y, gap, x-8, y-20, x, y-15, x, y-17, lips_c);               // open_mouth_left
        triangle.fill(g, X, Y, gap, x-8, y-20, x, y-15, x, y-17, lips_c);                       // open_mouth_left
        triangle.drawTriangle(g, X, Y, gap, x+8, y-20, x, y-15, x, y-17, lips_c);               // open_mouth_right
        triangle.fill(g, X, Y, gap, x+8, y-20, x, y-15, x, y-17, lips_c);                       // open_mouth_right
        triangle.drawTriangle(g, X, Y, gap, x-10, y+20, x-13, y+ 24, x, y+20, nose_c);          // nose_bridge_left
        triangle.fill(g, X, Y, gap, x-10, y+20, x-13, y+ 24, x, y+20, nose_c);                  // nose_bridge_left
        triangle.drawTriangle(g, X, Y, gap, x+10, y+20, x+13, y+ 24, x, y+20, nose_c);          // nose_bridge_right
        triangle.fill(g, X, Y, gap, x+10, y+20, x+13, y+ 24, x, y+20, nose_c);                  // nose_bridge_right
    }
}


public class lion_tiger extends Applet implements ActionListener{

    private int X,Y,gap,status, image; 
    
    // Constructor
    public lion_tiger() 
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
        Button b3 = new Button("Tiger");
        Button b4 = new Button("Lion");

        add(b1);
        add(b2);
        add(b3);
        add(b4);

        b1.addActionListener(this);
        b2.addActionListener(this);
        b3.addActionListener(this);
        b4.addActionListener(this);
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
                // TIGER
                Tiger tiger = new Tiger();
                tiger.drawTiger(g,X,Y,gap);
                break;

            case 2:
                // LION
                Lion lion = new Lion();
                lion.drawLion(g, X, Y, gap);
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
                    
                case "Tiger":
                    image = 1; 
                    break;
                    
                case "Lion":
                    image = 2; 
                    break;

                default:
                    break;
            }
        }
        repaint();
       }
}


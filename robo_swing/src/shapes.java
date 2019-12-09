import java.awt.*;
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
    
    // scaled point 
    public void scale(Graphics g, int X, int Y, int gap, int x1, int y1, Color c, double s)
    {
        g.setColor(c);
        int x = (int)Math.round(x1*s);
        int y = (int)Math.round(y1*s);
        g.fillRect(X+x*gap-gap/2, Y-y*gap-gap/2, gap, gap);  
    }
    
    // rotated point
    public void rotate(Graphics g, int X, int Y, int gap, int x1, int y1, Color c, int deg)
    {
        g.setColor(c);
        double degree = Math.toRadians(deg);
        int x = (int)Math.round(x1*Math.cos(degree) + y1*Math.sin(degree)); 
        int y= (int)Math.round(y1*Math.cos(degree) + -x1*Math.sin(degree)); 
        g.fillRect(X+x*gap-gap/2, Y-y*gap-gap/2, gap, gap);  
    }
    
    // rotation followed by scaling
    public void rotate_and_scale(Graphics g, int X, int Y, int gap, int x1, int y1, Color c, double s,int deg)
    {
        g.setColor(c);
        double degree = Math.toRadians(deg);
        int x = (int)Math.round(x1*Math.cos(degree) + y1*Math.sin(degree)); 
        int y= (int)Math.round(y1*Math.cos(degree) + -x1*Math.sin(degree)); 
        x = (int)Math.round(x*s);
        y = (int)Math.round(y*s);
        g.fillRect(X+x*gap-gap/2, Y-y*gap-gap/2, gap, gap);  
    }
}

class Line 
{
    // might not work for all cases
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
    
    // modified bresenham algorithm, works for all orientations
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
    
    // scaling bresenham line
    public void scale (Graphics g, int X, int Y, int gap, int x0, int y0, int x1, int y1, Color c, double s)
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
            p2.scale(g, X, Y, gap, x0, y0,c,s);
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
    
    // rotating bresenham line
    public void rotate (Graphics g, int X, int Y, int gap, int x0, int y0, int x1, int y1, Color c, int deg)
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
            p2.rotate(g, X, Y, gap, x0, y0,c,deg);
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
    
    // rotate and scale bresenham line
    public void rotate_and_scale (Graphics g, int X, int Y, int gap, int x0, int y0, int x1, int y1, Color c, double s, int deg)
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
            p2.rotate_and_scale(g, X, Y, gap, x0, y0,c,s, deg);
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
    
    // this might not work for all cases, copied directly from slides
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
    
    public void scale(Graphics g, int X, int Y, int gap, int x0, int y0, int x1, int y1, int x2, int y2, Color c, double s)
    {
        int maxX = Math.max(x0, Math.max(x1,x2));
        int minX = Math.min(x0, Math.min(x1,x2));
        int maxY = Math.max(y0, Math.max(y1,y2));
        int minY = Math.min(y0, Math.min(y1,y2));
        
        Point p1 = new Point();
        
        for(int x = minX; x <= maxX; x++)
            for(int y = minY; y <= maxY; y++)
                if(PointInTriangle(x,y,x0,y0,x1,y1,x2,y2))
                    p1.scale(g, X, Y, gap, x, y, c, s);
      
    }
    
    public void scaleDraw(Graphics g, int X, int Y, int gap, int x0, int y0, int x1, int y1, int x2, int y2, Color c, double s)
    {
        Line l = new Line();
        l.scale(g, X, Y, gap, x0, y0, x1, y1,c,s);
        l.scale(g, X, Y, gap, x1, y1, x2, y2,c,s);
        l.scale(g, X, Y, gap, x2, y2, x0, y0,c,s);
      
    }
    
    public void rotate(Graphics g, int X, int Y, int gap, int x0, int y0, int x1, int y1, int x2, int y2, Color c, int deg)
    {
        int maxX = Math.max(x0, Math.max(x1,x2));
        int minX = Math.min(x0, Math.min(x1,x2));
        int maxY = Math.max(y0, Math.max(y1,y2));
        int minY = Math.min(y0, Math.min(y1,y2));
        
        Point p1 = new Point();
        
        for(int x = minX; x <= maxX; x++)
            for(int y = minY; y <= maxY; y++)
                if(PointInTriangle(x,y,x0,y0,x1,y1,x2,y2))
                    p1.rotate(g, X, Y, gap, x, y, c, deg);
      
    }
    
    public void rotate_and_scale(Graphics g, int X, int Y, int gap, int x0, int y0, int x1, int y1, int x2, int y2, Color c, double s, int deg)
    {
        int maxX = Math.max(x0, Math.max(x1,x2));
        int minX = Math.min(x0, Math.min(x1,x2));
        int maxY = Math.max(y0, Math.max(y1,y2));
        int minY = Math.min(y0, Math.min(y1,y2));
        
        Point p1 = new Point();
        
        for(int x = minX; x <= maxX; x++)
            for(int y = minY; y <= maxY; y++)
                if(PointInTriangle(x,y,x0,y0,x1,y1,x2,y2))
                    p1.rotate_and_scale(g, X, Y, gap, x, y, c, s, deg);
      
    }
    
}

// Considering convex quadrilateral only
class Quadrilateral
{
    // assumes that (x0,y0)->(x1,y1)->(x2,y2)->(x3,y3) is specified in a cyclic order
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
    
    public void scale(Graphics g, int X, int Y, int gap, int x0, int y0, int x1, int y1, int x2, int y2, int x3, int y3, Color c, double s)
    {
        int maxX = Math.max(Math.max(x0,x1), Math.max(x2,x3));
        int minX = Math.min(Math.min(x0,x1), Math.min(x2,x3));
        int maxY = Math.max(Math.max(y0,y1), Math.max(y2,y3));
        int minY = Math.min(Math.min(y0,y1), Math.min(y2,y3));
        
        Point p1 = new Point();
        
        for(int x = minX; x <= maxX; x++)
            for(int y = minY; y <= maxY; y++)
                if(PointInTriangle(x,y,x0,y0,x1,y1,x2,y2) || PointInTriangle(x,y,x2,y2,x3,y3,x0,y0))
                    p1.scale(g, X, Y, gap, x, y, c,s);
    }
    
    public void scaleDraw(Graphics g, int X, int Y, int gap, int x0, int y0, int x1, int y1, int x2, int y2, int x3, int y3, Color c, double s)
    {
        Line l = new Line();
        l.scale(g, X, Y, gap, x0, y0, x1, y1,c,s);
        l.scale(g, X, Y, gap, x1, y1, x2, y2,c,s);
        l.scale(g, X, Y, gap, x2, y2, x3, y3,c,s);
        l.scale(g, X, Y, gap, x3, y3, x0, y0,c,s);
    }
    
    public void rotate(Graphics g, int X, int Y, int gap, int x0, int y0, int x1, int y1, int x2, int y2, int x3, int y3, Color c, int deg)
    {
        int maxX = Math.max(Math.max(x0,x1), Math.max(x2,x3));
        int minX = Math.min(Math.min(x0,x1), Math.min(x2,x3));
        int maxY = Math.max(Math.max(y0,y1), Math.max(y2,y3));
        int minY = Math.min(Math.min(y0,y1), Math.min(y2,y3));
        
        Point p1 = new Point();
        
        for(int x = minX; x <= maxX; x++)
            for(int y = minY; y <= maxY; y++)
                if(PointInTriangle(x,y,x0,y0,x1,y1,x2,y2) || PointInTriangle(x,y,x2,y2,x3,y3,x0,y0))
                    p1.rotate(g, X, Y, gap, x, y, c, deg);
    }
    
    public void rotate_and_scale(Graphics g, int X, int Y, int gap, int x0, int y0, int x1, int y1, int x2, int y2, int x3, int y3, Color c, double s, int deg)
    {
        int maxX = Math.max(Math.max(x0,x1), Math.max(x2,x3));
        int minX = Math.min(Math.min(x0,x1), Math.min(x2,x3));
        int maxY = Math.max(Math.max(y0,y1), Math.max(y2,y3));
        int minY = Math.min(Math.min(y0,y1), Math.min(y2,y3));
        
        Point p1 = new Point();
        
        for(int x = minX; x <= maxX; x++)
            for(int y = minY; y <= maxY; y++)
                if(PointInTriangle(x,y,x0,y0,x1,y1,x2,y2) || PointInTriangle(x,y,x2,y2,x3,y3,x0,y0))
                    p1.rotate_and_scale(g, X, Y, gap, x, y, c, s, deg);
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
    
    public void scale (Graphics g, int X, int Y, int gap, int radius,int xcent,int ycent, Color c, double s)
    {
        Point p1 = new Point();
        
        // Reference : https://stackoverflow.com/questions/10322341/simple-algorithm-for-drawing-filled-ellipse-in-c-c
        for(int y =-radius; y <= radius; y++)
            for(int x =-radius; x <=radius; x++)
                if(x*x + y*y <= radius*radius)
                    p1.scale(g, X, Y,gap, xcent+x, ycent+y, c, s);
    }
    
    public void scaleDraw (Graphics g, int X, int Y, int gap, int radius,int xcent,int ycent, Color c, double s)
    {
        int x = radius;
        int y = 0; 

        // Initialising the value of p 
        int p = 1 - radius; 

        Point p1 = new Point();

        // Printing the initial point on the axes  after translation 
        p1.scale(g, X, Y, gap, x + xcent, y + ycent, c,s);
        p1.scale(g, X, Y, gap, -x + xcent, -y + ycent, c,s);

        // When radius is zero only a single point will be printed 
        if (radius > 0) 
        { 
            p1.scale(g, X, Y, gap,  x + xcent, -y + ycent, c,s);
            p1.scale(g, X, Y, gap,  y + xcent,  x + ycent, c,s);
            p1.scale(g, X, Y, gap, -y + xcent,  x + ycent, c,s);
            p1.scale(g, X, Y, gap, y + xcent,  -x + ycent, c,s);
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
            p1.scale(g, X, Y, gap,  x + xcent,  y + ycent, c,s);
            p1.scale(g, X, Y, gap, -x + xcent,  y + ycent, c,s);
            p1.scale(g, X, Y, gap,  x + xcent, -y + ycent, c,s);
            p1.scale(g, X, Y, gap, -x + xcent, -y + ycent, c,s);

            // If the generated point is on the line x = y then  
            // the perimeter points have already been printed 
            if (x != y) 
            {
                p1.scale(g, X, Y, gap,  y + xcent,  x + ycent, c,s);
                p1.scale(g, X, Y, gap, -y + xcent,  x + ycent, c,s);
                p1.scale(g, X, Y, gap,  y + xcent, -x + ycent, c,s);
                p1.scale(g, X, Y, gap, -y + xcent, -x + ycent, c,s);

            } 
        }
    }
    
    public void rotate(Graphics g, int X, int Y, int gap, int radius,int xcent,int ycent, Color c, int deg)
    {
        Point p1 = new Point();
        
        // Reference : https://stackoverflow.com/questions/10322341/simple-algorithm-for-drawing-filled-ellipse-in-c-c
        for(int y =-radius; y <= radius; y++)
            for(int x =-radius; x <=radius; x++)
                if(x*x + y*y <= radius*radius)
                    p1.rotate(g, X, Y,gap, xcent+x, ycent+y, c, deg);
    }
    
    public void rotate_and_scale(Graphics g, int X, int Y, int gap, int radius,int xcent,int ycent, Color c,double s, int deg)
    {
        Point p1 = new Point();
        
        // Reference : https://stackoverflow.com/questions/10322341/simple-algorithm-for-drawing-filled-ellipse-in-c-c
        for(int y =-radius; y <= radius; y++)
            for(int x =-radius; x <=radius; x++)
                if(x*x + y*y <= radius*radius)
                    p1.rotate_and_scale(g, X, Y,gap, xcent+x, ycent+y, c,s, deg);
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
    
    public void scale (Graphics g, int X, int Y, int gap, int rx,int ry,int xcent,int ycent, Color c, double s)
    {
        Point p1 = new Point();
        
        // Reference : https://stackoverflow.com/questions/10322341/simple-algorithm-for-drawing-filled-ellipse-in-c-c
        for(int y = -ry; y <= ry; y++)
            for(int x = -rx; x <= rx; x++)
                if(x*x*ry*ry + y*y*rx*rx <= rx*rx*ry*ry)
                    p1.scale(g, X, Y, gap, xcent+x, ycent+y, c, s);
    }
    
    public void scaleDraw (Graphics g, int X, int Y, int gap, int rx,int ry,int xcent,int ycent, Color c, double s)
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
            p1.scale(g, X, Y, gap, xcent-(int)x, ycent+(int)y, c,s);
            p1.scale(g, X, Y, gap, xcent+(int)x, ycent+(int)y, c,s);
            p1.scale(g, X, Y, gap, xcent+(int)x, ycent-(int)y, c,s);
            p1.scale(g, X, Y, gap, xcent-(int)x, ycent-(int)y, c,s);
            
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
            p1.scale(g, X, Y, gap, xcent, ycent+(int)y, c,s);
            p1.scale(g, X, Y, gap, xcent, ycent-(int)y, c,s);
        }
    }
    
    public void rotate (Graphics g, int X, int Y, int gap, int rx,int ry,int xcent,int ycent, Color c, int deg)
    {
        Point p1 = new Point();
        
        // Reference : https://stackoverflow.com/questions/10322341/simple-algorithm-for-drawing-filled-ellipse-in-c-c
        for(int y = -ry; y <= ry; y++)
            for(int x = -rx; x <= rx; x++)
                if(x*x*ry*ry + y*y*rx*rx <= rx*rx*ry*ry)
                    p1.rotate(g, X, Y, gap, xcent+x, ycent+y, c, deg);
    }
    
    public void rotate_and_scale (Graphics g, int X, int Y, int gap, int rx,int ry,int xcent,int ycent, Color c,double s, int deg)
    {
        Point p1 = new Point();
        
        // Reference : https://stackoverflow.com/questions/10322341/simple-algorithm-for-drawing-filled-ellipse-in-c-c
        for(int y = -ry; y <= ry; y++)
            for(int x = -rx; x <= rx; x++)
                if(x*x*ry*ry + y*y*rx*rx <= rx*rx*ry*ry)
                    p1.rotate_and_scale(g, X, Y, gap, xcent+x, ycent+y, c, s,deg);
    }
    
}

public class shapes {
    // do nothing
}

//circle.scale(g, X, Y, gap, 4, x-27, y-37, Color.white,s);                                               // left palm
        //circle.scale(g, X, Y, gap, 4, x+27, y-37, Color.white,s);                                               // right palm
        //quadrilateral.scale(g, X, Y, gap, x+15, y-72, x+15, y-75, x+22, y-75, x+22, y-72, Color.white,s);
        //quadrilateral.scale(g, X, Y, gap, x-20, y-72, x-20, y-75, x-15, y-75, x-15, y-72, Color.white,s);
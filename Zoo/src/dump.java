/*
class Water
{
    public void drawPond(Graphics g, int X, int Y, int gap, int h, int x, int y)
    {
        Quadrilateral quadrilateral = new Quadrilateral();
        quadrilateral.fill(g, X, Y, gap, x-30, y-2, x+40, y-2, x+40, y-15, x-30, y-15,Color.blue);
    }
}

class Point 
{
    public void plot(Graphics g, int X, int Y, int gap, int x1, int y1, Color c)
    {
        g.setColor(c);
        g.fillRect(X+x1*gap-gap/2, Y-y1*gap-gap/2, gap, gap);  
    }
    
    public void scale(Graphics g, int X, int Y, int gap, int x1, int y1, Color c, double s)
    {
        g.setColor(c);
        int x = (int)Math.round(x1*s);
        int y = (int)Math.round(y1*s);
        g.fillRect(X+x*gap-gap/2, Y-y*gap-gap/2, gap, gap);  
    }
    
    public void rotate(Graphics g, int X, int Y, int gap, int x1, int y1, Color c, int deg)
    {
        g.setColor(c);
        double degree = Math.toRadians(deg);
        int x = (int)Math.round(x1*Math.cos(degree) + y1*Math.sin(degree)); 
        int y= (int)Math.round(y1*Math.cos(degree) + -x1*Math.sin(degree)); 
        g.fillRect(X+x*gap-gap/2, Y-y*gap-gap/2, gap, gap);  
    }
    
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


class Tiger
{
    public void drawTiger(Graphics g, int X, int Y, int gap, int x, int y)
    {
        // location of centre of head
        //int x = -50;
        //int y = 0;

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

    public void scale(Graphics g, int X, int Y, int gap, double s, int x, int y)
    {
        // location of centre of head
        //int x = 50;
        //int y = 0;

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

        quadrilateral.scale(g, X, Y, gap, x-30, y+5, x+30, y+5, x+20, y-35, x-20, y-35, body_c,s );              // body

        triangle.scale(g, X, Y, gap, x-28, y+18, x-35, y+37, x-30, y+50, head_c,s);               // left_ear

        triangle.scale(g, X, Y, gap, x-28, y+19, x-30, y+50, x-15, y+32, beard_c,s);              // left_ear_inner

        triangle.scale(g, X, Y, gap, x+28, y+18, x+35, y+37, x+30, y+50, head_c,s);              // right_ear_outer

        triangle.scale(g, X, Y, gap, x+28, y+19, x+30, y+50, x+15, y+32, beard_c,s);             // right_ear_inner

        circle.scale(g,X,Y,gap,30,x,y+5,head_c,s);                                                        // head

        quadrilateral.scale(g, X, Y, gap, x-5, y-5, x+5, y-5, x+9, y-15, x-9, y-15, moustache_c,s);                // moustache_area_upper

        quadrilateral.scale(g, X, Y, gap, x-9, y-15, x+9, y-15, x+5, y-25, x-5, y-25, moustache_c,s);              // moustache_area_upper

        ellipse.scale(g, X, Y, gap, 8, 9, x, y-20, moustache_c,s);                                            // chin


        triangle.scale(g, X, Y, gap, x-8, y-25, x, y-20, x, y-22, lips_c,s);                        // open_mouth_left
        triangle.scale(g, X, Y, gap, x, y-20, x+8, y-25, x, y-22, lips_c,s);                       // open_mouth_right

        ellipse.scale(g, X, Y, gap, 6, 7, x, y-8, nose_tip_c,s);                                         // nose_bulb

        quadrilateral.scale(g, X, Y, gap, x-8, y+12, x+8, y+12, x+6, y-5, x-6, y-5, nose_c,s);               // nose_bridge
        triangle.scale(g, X, Y, gap, x-6, y-5, x+6, y-5, x, y-8, nose_c,s);                                // nose_tip


        triangle.scale(g, X, Y, gap, x-30, y+5, x-20, y-5, x-25, y-15, beard_c,s);                 // cheek_left_upper

        triangle.scale(g, X, Y, gap, x-20, y-5, x-10, y-25, x-25, y-15, beard_c,s);                // cheek_left_lower

        triangle.scale(g, X, Y, gap, x+30, y+5, x+20, y-5, x+25, y-15, beard_c,s);                // cheek_right_upper

        triangle.scale(g, X, Y, gap, x+20, y-5, x+10, y-25, x+25, y-15, beard_c,s);               // cheek_right_lower

        triangle.scale(g, X, Y, gap, x-6, y-5, x-9, y+12, x-6, y+20, beard_c,s);                    // nose_ridge_left

        triangle.scale(g, X, Y, gap, x+6, y-5, x+9, y+12, x+6, y+20, beard_c,s);                   // nose_ridge_right

        triangle.scale(g, X, Y, gap, x-20, y+12, x-9, y+12, x-9, y, extras_c,s);                 // left_eye_bag_upper

        triangle.scale(g, X, Y, gap, x+20, y+12, x+9, y+12, x+9, y, extras_c,s);                // right_eye_bag_upper

        circle.scale(g,X,Y,gap,3,x-12,y+8,eyes_c,s);                                            // left_retina_outer

        circle.scale(g,X,Y,gap,3,x+12,y+8,eyes_c,s);                                           // right_retina_outer
        point.scale(g, X, Y, gap, x-12, y+8, extras_c,s);                                      // left_retina_inner
        point.scale(g, X, Y, gap, x+12, y+8, extras_c,s);                                     // right_retina_inner
        line.scale(g,X, Y, gap, x-6, y-18, x-15, y-12, cartoon_c,s);                    // left_upper_whisker
        line.scale(g, X, Y, gap, x-6, y-20, x-15, y-15, cartoon_c,s);                   // right_lower_whisker 
        line.scale(g, X, Y, gap, x+6, y-18, x+15, y-12, cartoon_c,s);                  // left_upper_whisker
        line.scale(g, X, Y, gap, x+6, y-20, x+15, y-15, cartoon_c,s);                  // right_lower_whisker
    }

    public void rotate(Graphics g, int X, int Y, int gap, int deg, int x, int y)
    {
        // location of centre of head
        //int x = 50;
        //int y = 0;

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

        quadrilateral.rotate(g, X, Y, gap, x-30, y+5, x+30, y+5, x+20, y-35, x-20, y-35, body_c,deg );              // body

        triangle.rotate(g, X, Y, gap, x-28, y+18, x-35, y+37, x-30, y+50, head_c,deg);               // left_ear

        triangle.rotate(g, X, Y, gap, x-28, y+19, x-30, y+50, x-15, y+32, beard_c,deg);              // left_ear_inner

        triangle.rotate(g, X, Y, gap, x+28, y+18, x+35, y+37, x+30, y+50, head_c,deg);              // right_ear_outer

        triangle.rotate(g, X, Y, gap, x+28, y+19, x+30, y+50, x+15, y+32, beard_c,deg);             // right_ear_inner

        circle.rotate(g,X,Y,gap,30,x,y+5,head_c,deg);                                                        // head

        quadrilateral.rotate(g, X, Y, gap, x-5, y-5, x+5, y-5, x+9, y-15, x-9, y-15, moustache_c,deg);                // moustache_area_upper

        quadrilateral.rotate(g, X, Y, gap, x-9, y-15, x+9, y-15, x+5, y-25, x-5, y-25, moustache_c,deg);              // moustache_area_upper

        ellipse.rotate(g, X, Y, gap, 8, 9, x, y-20, moustache_c,deg);                                            // chin


        triangle.rotate(g, X, Y, gap, x-8, y-25, x, y-20, x, y-22, lips_c,deg);                        // open_mouth_left
        triangle.rotate(g, X, Y, gap, x, y-20, x+8, y-25, x, y-22, lips_c,deg);                       // open_mouth_right

        ellipse.rotate(g, X, Y, gap, 6, 7, x, y-8, nose_tip_c,deg);                                         // nose_bulb

        quadrilateral.rotate(g, X, Y, gap, x-8, y+12, x+8, y+12, x+6, y-5, x-6, y-5, nose_c,deg);               // nose_bridge
        triangle.rotate(g, X, Y, gap, x-6, y-5, x+6, y-5, x, y-8, nose_c,deg);                                // nose_tip


        triangle.rotate(g, X, Y, gap, x-30, y+5, x-20, y-5, x-25, y-15, beard_c,deg);                 // cheek_left_upper

        triangle.rotate(g, X, Y, gap, x-20, y-5, x-10, y-25, x-25, y-15, beard_c,deg);                // cheek_left_lower

        triangle.rotate(g, X, Y, gap, x+30, y+5, x+20, y-5, x+25, y-15, beard_c,deg);                // cheek_right_upper

        triangle.rotate(g, X, Y, gap, x+20, y-5, x+10, y-25, x+25, y-15, beard_c,deg);               // cheek_right_lower

        triangle.rotate(g, X, Y, gap, x-6, y-5, x-9, y+12, x-6, y+20, beard_c,deg);                    // nose_ridge_left

        triangle.rotate(g, X, Y, gap, x+6, y-5, x+9, y+12, x+6, y+20, beard_c,deg);                   // nose_ridge_right

        triangle.rotate(g, X, Y, gap, x-20, y+12, x-9, y+12, x-9, y, extras_c,deg);                 // left_eye_bag_upper

        triangle.rotate(g, X, Y, gap, x+20, y+12, x+9, y+12, x+9, y, extras_c,deg);                // right_eye_bag_upper

        circle.rotate(g,X,Y,gap,3,x-12,y+8,eyes_c,deg);                                            // left_retina_outer

        circle.rotate(g,X,Y,gap,3,x+12,y+8,eyes_c,deg);                                           // right_retina_outer
        point.rotate(g, X, Y, gap, x-12, y+8, extras_c,deg);                                      // left_retina_inner
        point.rotate(g, X, Y, gap, x+12, y+8, extras_c,deg);                                     // right_retina_inner
        line.rotate(g,X, Y, gap, x-6, y-18, x-15, y-12, cartoon_c,deg);                    // left_upper_whisker
        line.rotate(g, X, Y, gap, x-6, y-20, x-15, y-15, cartoon_c,deg);                   // right_lower_whisker 
        line.rotate(g, X, Y, gap, x+6, y-18, x+15, y-12, cartoon_c,deg);                  // left_upper_whisker
        line.rotate(g, X, Y, gap, x+6, y-20, x+15, y-15, cartoon_c,deg);                  // right_lower_whisker
    }
}

class Lion
{
    public void drawLion(Graphics g, int X, int Y, int gap, int x, int y)
    {
        // location of centre of head
        //int x = 50;
        //int y = 0;

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

    public void scale(Graphics g, int X, int Y, int gap, double s, int x, int y)
    {
        // location of centre of head
        //int x = -50;
        //int y = 0;

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

        quadrilateral.scale(g, X, Y, gap, x-10, y+15, x+10, y+15, x+25, y-39, x-25, y-39, body_c,s );              // body             

        circle.scale(g,X,Y,gap,40,x,y+5,mane_c,s);      // mane 

        quadrilateral.scale(g, X, Y, gap, x, y+20, x, y-6, x-15, y-6, x-20, y+6, beard_c,s );               // face_lower_left

        quadrilateral.scale(g, X, Y, gap, x, y+20, x, y-6, x+15, y-6, x+20, y+6, beard_c,s);                // face_lower_right

        triangle.scale(g, X, Y, gap, x-20, y+6, x-13, y+ 24, x, y+13,beard_c,s);                 // face_upper_left

        triangle.scale(g, X, Y, gap, x+20, y+6, x+13, y+ 24, x, y+13,beard_c,s);                 // face_upper_right

        quadrilateral.scale(g, X, Y, gap, x-15, y-6, x+15, y-6, x+11, y-11, x-11, y-11, moustache_c,s);              // moustache_area_upper  

        quadrilateral.scale(g, X, Y, gap, x-11, y-11, x+11, y-11, x+6, y-25, x-6, y-25, moustache_c,s);              // moustache_area_lower
        line.scale(g, X, Y, gap, x-6, y-10, x-20, y-13, cartoon_c,s);  // left_whisker_1
        line.scale(g, X, Y, gap, x-6, y-11, x-21, y-17, cartoon_c,s);  // left_whisker_2
        line.scale(g, X, Y, gap, x-6, y-12, x-21, y-22, cartoon_c,s);  // left_whisker_3
        line.scale(g, X, Y, gap, x-6, y-12, x-21, y-27, cartoon_c,s);  // left_whisker_4
        line.scale(g, X, Y, gap, x+6, y-10, x+20, y-13, cartoon_c,s);  // right_whisker_1
        line.scale(g, X, Y, gap, x+6, y-11, x+21, y-17, cartoon_c,s);  // right_whisker_2
        line.scale(g, X, Y, gap, x+6, y-12, x+21, y-22, cartoon_c,s);  // right_whisker_3
        line.scale(g, X, Y, gap, x+6, y-12, x+21, y-27, cartoon_c,s);  // right_whisker_4

        triangle.scale(g, X, Y, gap, x-6, y+10, x, y+2, x-12, y+10,extras_c,s);            // left_eye

        triangle.scale(g, X, Y, gap, x+6, y+10, x, y+2, x+12, y+10,extras_c,s);            // right_eye

        quadrilateral.scale(g, X, Y, gap, x-10, y+20, x+10, y+20, x+6, y+10, x-6, y+10, nose_c,s);               // nose_bridge_up

        quadrilateral.scale(g, X, Y, gap, x-6, y+10, x+6, y+10, x+8, y-2, x-8, y-2, nose_c,s);                   // nose_bridge

        triangle.scale(g, X, Y, gap, x-8, y-2, x+8, y-2, x, y-8, nose_c,s);                        // nose_tip

        triangle.scale(g, X, Y, gap, x-8, y-2, x, y-8, x, y-14, nose_tip_c,s);                     // nose_bulb_left

        triangle.scale(g, X, Y, gap, x+8, y-2, x, y-8, x, y-14, nose_tip_c,s);                     // nose_bulb_right

        triangle.scale(g, X, Y, gap, x-8, y-20, x, y-15, x, y-17, lips_c,s);                       // open_mouth_left

        triangle.scale(g, X, Y, gap, x+8, y-20, x, y-15, x, y-17, lips_c,s);                       // open_mouth_right

        triangle.scale(g, X, Y, gap, x-10, y+20, x-13, y+ 24, x, y+20, nose_c,s);                  // nose_bridge_left

        triangle.scale(g, X, Y, gap, x+10, y+20, x+13, y+ 24, x, y+20, nose_c,s);                  // nose_bridge_right
    }

    public void rotate(Graphics g, int X, int Y, int gap, int deg, int x, int y)
    {
        // location of centre of head
        //int x = -50;
        //int y = 0;

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
        quadrilateral.rotate(g, X, Y, gap, x-10, y+15, x+10, y+15, x+25, y-39, x-25, y-39, body_c,deg );              // body             

        circle.rotate(g,X,Y,gap,40,x,y+5,mane_c,deg);      // mane 

        quadrilateral.rotate(g, X, Y, gap, x, y+20, x, y-6, x-15, y-6, x-20, y+6, beard_c, deg );               // face_lower_left

        quadrilateral.rotate(g, X, Y, gap, x, y+20, x, y-6, x+15, y-6, x+20, y+6, beard_c, deg);                // face_lower_right

        triangle.rotate(g, X, Y, gap, x-20, y+6, x-13, y+ 24, x, y+13,beard_c,deg);                 // face_upper_left

        triangle.rotate(g, X, Y, gap, x+20, y+6, x+13, y+ 24, x, y+13,beard_c,deg);                 // face_upper_right

        quadrilateral.rotate(g, X, Y, gap, x-15, y-6, x+15, y-6, x+11, y-11, x-11, y-11, moustache_c,deg);              // moustache_area_upper  

        quadrilateral.rotate(g, X, Y, gap, x-11, y-11, x+11, y-11, x+6, y-25, x-6, y-25, moustache_c,deg);              // moustache_area_lower
        line.rotate(g, X, Y, gap, x-6, y-10, x-20, y-13, cartoon_c,deg);  // left_whisker_1
        line.rotate(g, X, Y, gap, x-6, y-11, x-21, y-17, cartoon_c,deg);  // left_whisker_2
        line.rotate(g, X, Y, gap, x-6, y-12, x-21, y-22, cartoon_c,deg);  // left_whisker_3
        line.rotate(g, X, Y, gap, x-6, y-12, x-21, y-27, cartoon_c,deg);  // left_whisker_4
        line.rotate(g, X, Y, gap, x+6, y-10, x+20, y-13, cartoon_c,deg);  // right_whisker_1
        line.rotate(g, X, Y, gap, x+6, y-11, x+21, y-17, cartoon_c,deg);  // right_whisker_2
        line.rotate(g, X, Y, gap, x+6, y-12, x+21, y-22, cartoon_c,deg);  // right_whisker_3
        line.rotate(g, X, Y, gap, x+6, y-12, x+21, y-27, cartoon_c,deg);  // right_whisker_4

        triangle.rotate(g, X, Y, gap, x-6, y+10, x, y+2, x-12, y+10,extras_c,deg);            // left_eye

        triangle.rotate(g, X, Y, gap, x+6, y+10, x, y+2, x+12, y+10,extras_c,deg);            // right_eye

        quadrilateral.rotate(g, X, Y, gap, x-10, y+20, x+10, y+20, x+6, y+10, x-6, y+10, nose_c,deg);               // nose_bridge_up

        quadrilateral.rotate(g, X, Y, gap, x-6, y+10, x+6, y+10, x+8, y-2, x-8, y-2, nose_c,deg);                   // nose_bridge

        triangle.rotate(g, X, Y, gap, x-8, y-2, x+8, y-2, x, y-8, nose_c,deg);                        // nose_tip

        triangle.rotate(g, X, Y, gap, x-8, y-2, x, y-8, x, y-14, nose_tip_c,deg);                     // nose_bulb_left

        triangle.rotate(g, X, Y, gap, x+8, y-2, x, y-8, x, y-14, nose_tip_c,deg);                     // nose_bulb_right

        triangle.rotate(g, X, Y, gap, x-8, y-20, x, y-15, x, y-17, lips_c,deg);                       // open_mouth_left

        triangle.rotate(g, X, Y, gap, x+8, y-20, x, y-15, x, y-17, lips_c,deg);                       // open_mouth_right

        triangle.rotate(g, X, Y, gap, x-10, y+20, x-13, y+ 24, x, y+20, nose_c,deg);                  // nose_bridge_left

        triangle.rotate(g, X, Y, gap, x+10, y+20, x+13, y+ 24, x, y+20, nose_c,deg);                  // nose_bridge_right
    }
}

class Fish
{
    public void draw(Graphics g, int X, int Y, int gap, int x, int y)
    {
        // use online rbg tool to figure out colors
        Color x1 = new Color(255,205,0);
        Color x2 = new Color(0,255,190);
        Color x3 = new Color(25,160,25);
        Color x4 = new Color(240,25,15);
        Color x5 = new Color(15,160,240);
        Color x6 = new Color(15,20,240);
        Color x7 = new Color(220,255,0);
        Color x8 = new Color(0,130,255);

        //int x = 0;
        //int y = 0;

        // objects for composing fish
        Quadrilateral quadrilateral = new Quadrilateral();
        Triangle triangle = new Triangle();

        // Head
        triangle.fill(g, X, Y, gap, x, y, x, y+8, x-8, y,x1);
        triangle.fill(g, X, Y, gap, x, y, x, y-8, x-8, y,x2);

        // Body
        triangle.fill(g, X, Y, gap, x+1, y+7, x+1, y+5, x+12, y+5,x3);
        quadrilateral.fill(g, X, Y, gap, x+1, y+4, x+12, y+4, x+12, y-4, x+1, y-4,x4);
        triangle.fill(g, X, Y, gap, x+1, y-5, x+1, y-7, x+12, y-5,x5);

        // Tail
        quadrilateral.fill(g, X, Y, gap, x+13, y+2, x+23, y+7, x+23, y-1, x+13, y-2,x6);
        triangle.fill(g, X, Y, gap, x+13, y-3, x+23, y-1, x+23, y-7,x7);

        // Eye
        quadrilateral.fill(g, X, Y, gap, x-4, y+1, x-2, y+1, x-2, y, x-4, y,x8);
        quadrilateral.fill(g, X, Y, gap, x-4, y, x-2, y, x-2, y-1, x-4, y-1,x8);

    }
}

class BeeHive
{
    public void drawBeeHive(Graphics g, int X,int Y, int gap, int x, int y)
    {
        // centre of the entire structure
        //int x = 0;
        //int y = 0;

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

    public void scale(Graphics g, int X,int Y, int gap, double s, int x, int y)
    {
        // centre of the entire structure
        //int x = 0;
        //int y = 0;

        // objects required for drawing
        Ellipse ellipse = new Ellipse();
        Line line = new Line();

        // color palette
        Color body_c = new Color(210,175,15);  // Yellow-Ochre
        Color lines_c = new Color(160,110,30); // Black

        // draw and color the structure
        ellipse.scale(g, X, Y, gap, 30, 40, x, y, body_c,s);                    // body
        line.scale(g, X, Y, gap, x-20, y+30, x+20, y+30, lines_c,s);       // line1
        line.scale(g, X, Y, gap, x-26, y+20, x+26, y+20, lines_c,s);       // line2
        line.scale(g, X, Y, gap, x-29, y+10, x+29, y+10, lines_c,s);       // line3
        line.scale(g, X, Y, gap, x-30, y, x+30, y, lines_c,s);             // line4
        line.scale(g, X, Y, gap, x-29, y-10, x+29, y-10, lines_c,s);       // line5
        line.scale(g, X, Y, gap, x-26, y-20, x+26, y-20, lines_c,s);       // line6
        line.scale(g, X, Y, gap, x-20, y-30, x+20, y-30, lines_c,s);       // line7
        ellipse.scale(g, X, Y, gap, 5, 2, x+9, y-5, lines_c,s);                 // hole
    }

    public void rotate(Graphics g, int X,int Y, int gap, int deg, int x, int y)
    {
        // centre of the entire structure
        //int x = 0;
        //int y = 0;

        // objects required for drawing
        Ellipse ellipse = new Ellipse();
        Line line = new Line();

        // color palette
        Color body_c = new Color(210,175,15);  // Yellow-Ochre
        Color lines_c = new Color(160,110,30); // Black

        // draw and color the structure
        ellipse.rotate(g, X, Y, gap, 30, 40, x, y, body_c,deg);                    // body
        line.rotate(g, X, Y, gap, x-20, y+30, x+20, y+30, lines_c,deg);       // line1
        line.rotate(g, X, Y, gap, x-26, y+20, x+26, y+20, lines_c,deg);       // line2
        line.rotate(g, X, Y, gap, x-29, y+10, x+29, y+10, lines_c,deg);       // line3
        line.rotate(g, X, Y, gap, x-30, y, x+30, y, lines_c,deg);             // line4
        line.rotate(g, X, Y, gap, x-29, y-10, x+29, y-10, lines_c,deg);       // line5
        line.rotate(g, X, Y, gap, x-26, y-20, x+26, y-20, lines_c,deg);       // line6
        line.rotate(g, X, Y, gap, x-20, y-30, x+20, y-30, lines_c,deg);       // line7
        ellipse.rotate(g, X, Y, gap, 5, 2, x+9, y-5, lines_c,deg);                 // hole
    }

}
*/
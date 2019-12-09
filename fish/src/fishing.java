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
    
    public void Midpoint (Graphics g, int X, int Y, int gap, int x0, int y0, int x1, int y1, Color c)
    {
        // calculate dx and dy
        int dx = x1 - x0;
        int dy = y1 - y0;
        
        // calculate initial value of decision parameter
        int d = dy - (dx/2);
        int x = x0;
        int y = y0;
        
        Point p1 = new Point();
        
        // plot (x0,y0)
        p1.plot(g, X, Y, gap, x, y,c);
        
        // plot other points
        while(x < x1)
        {
            x++;
            if(d < 0)
                d = d + dy;
            else
            {
                d = d + dy - dx;
                y++;
            }
            // plot this point
            p1.plot(g, X, Y, gap, x, y,c);
        }
    }

}

class Polygon
{ 
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
    
    private void fillTriangle(Graphics g, int X, int Y, int gap, int x0, int y0, int x1, int y1, int x2, int y2, Color c)
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
    
    private void fillQuadrilateral(Graphics g, int X, int Y, int gap, int x0, int y0, int x1, int y1, int x2, int y2, int x3, int y3, Color c)
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
    
    public void triangle(Graphics g, int X, int Y, int gap, int x0, int y0, int x1, int y1, int x2, int y2, Color c)
    {
        Line l = new Line();
        l.Bresenham(g, X, Y, gap, x0, y0, x1, y1,c);
        l.Bresenham(g, X, Y, gap, x1, y1, x2, y2,c);
        l.Bresenham(g, X, Y, gap, x2, y2, x0, y0,c);
        fillTriangle(g,X,Y,gap,x0,y0,x1,y1,x2,y2,c);
    }
    
    public void quadrilateral(Graphics g, int X, int Y, int gap, int x0, int y0, int x1, int y1, int x2, int y2, int x3, int y3, Color c)
    {
        Line l = new Line();
        l.Bresenham(g, X, Y, gap, x0, y0, x1, y1,c);
        l.Bresenham(g, X, Y, gap, x1, y1, x2, y2,c);
        l.Bresenham(g, X, Y, gap, x2, y2, x3, y3,c);
        l.Bresenham(g, X, Y, gap, x3, y3, x0, y0,c);
        fillQuadrilateral(g,X,Y,gap,x0,y0,x1,y1,x2,y2,x3,y3,c);
    }

}

class Fish
{
    public void draw(Graphics g, int X, int Y, int gap)
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
        
        Polygon p = new Polygon();

        // Head
        p.triangle(g, X, Y, gap, 0, 0, 0, 8, -8, 0,x1);
        p.triangle(g, X, Y, gap, 0, 0, 0, -8, -8, 0,x2);
        
        // Body
        p.triangle(g, X, Y, gap, 1, 7, 1, 5, 12, 5,x3);
        p.quadrilateral(g, X, Y, gap, 1, 4, 12, 4, 12, -4, 1, -4,x4);
        p.triangle(g, X, Y, gap, 1, -5, 1, -7, 12, -5,x5);
        
        // Tail
        p.quadrilateral(g, X, Y, gap, 13, 2, 23, 7, 23, -1, 13, -2,x6);
        p.triangle(g, X, Y, gap, 13, -3, 23, -1, 23, -7,x7);
        
        // Eye
        p.quadrilateral(g, X, Y, gap, -4, 1, -2, 1, -2, 0, -4, 0,x8);
        p.quadrilateral(g, X, Y, gap, -4, 0, -2, 0, -2, -1, -4, -1,x8);
         
    }

}

class Water
{
    public void drawPond(Graphics g, int X, int Y, int gap, int w, int h)
    {
        int depth = 0;
        Line l = new Line();
        while(depth <= h)
        {
            //drawDashedLine(g, X-w, Y+depth, X+w, Y+depth);
            l.Bresenham(g, X, Y, gap, -w, -depth, w, -depth, Color.blue);
            depth ++;
        }
        
    }
}

public class fishing extends Applet implements ActionListener 
{
    private int X,Y,gap,status;

    // Constructor
    public fishing() 
    {
        this.gap = 4;
        this.status = 0;
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
        add(b1);
        add(b2);
        b1.addActionListener(this);
        b2.addActionListener(this);
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
        //drawGrid(g, X, Y, gap);
        //drawAxes(g);
        
        Water w = new Water();
        w.drawPond(g,X,Y,gap,100,80);
        
        
        Fish f = new Fish();
        f.draw(g, X, Y, gap);
        
        

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
                    
                default:
                    break;
            }
        }
        repaint();
       }
}




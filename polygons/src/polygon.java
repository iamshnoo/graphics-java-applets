import java.awt.*;
import java.applet.*;
import java.awt.event.*;
import java.lang.*;

/**
 *
 * @author Anjishnu
 */

class Point 
{
    public void plot(Graphics g, int X, int Y, int gap, double x1, double y1, Color c)
    {
        g.setColor(c);
        g.fillRect((int)(X+x1*gap-gap/2), (int)(Y-y1*gap-gap/2), gap, gap);  
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
    
    public void scale_up (Graphics g, int X, int Y, int gap, int x0, int y0, int x1, int y1, int x2, int y2,int scale_factor_x, int scale_factor_y, Color c)
    {
        int A = x0 * scale_factor_x;
        int B = y0 * scale_factor_y;
        int C = x1 * scale_factor_x;
        int D = y1 * scale_factor_y;
        int E = x2 * scale_factor_x;
        int F = y2 * scale_factor_y;
        
        this.drawTriangle(g, X, Y, gap, A, B, C, D, E, F, c);
        this.fill(g, X, Y, gap, A, B, C, D, E, F, c);
        
    }
    
    public void scale_down (Graphics g, int X, int Y, int gap, int x0, int y0, int x1, int y1, int x2, int y2,int scale_factor_x, int scale_factor_y, Color c)
    {
        int A = x0 / scale_factor_x;
        int B = y0 / scale_factor_y;
        int C = x1 / scale_factor_x;
        int D = y1 / scale_factor_y;
        int E = x2 / scale_factor_x;
        int F = y2 / scale_factor_y;
        
        this.drawTriangle(g, X, Y, gap, A, B, C, D, E, F, c);
        this.fill(g, X, Y, gap, A, B, C, D, E, F, c);
        
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
    
    public void scale_up(Graphics g, int X, int Y, int gap, int x0, int y0, int x1, int y1, int x2, int y2, int x3, int y3,int scale_factor_x, int scale_factor_y, Color c)
    {
        int A = x0 * scale_factor_x;
        int B = y0 * scale_factor_y;
        int C = x1 * scale_factor_x;
        int D = y1 * scale_factor_y;
        int E = x2 * scale_factor_x;
        int F = y2 * scale_factor_y;
        int G = x3 * scale_factor_x;
        int H = y3 * scale_factor_y;
        
        this.drawQuadrilateral(g, X, Y, gap, A, B, C, D, E, F, G, H, c);
        this.fill(g, X, Y, gap, A, B, C, D, E, F, G, H, c);
    }
    
    public void scale_down(Graphics g, int X, int Y, int gap, int x0, int y0, int x1, int y1, int x2, int y2, int x3, int y3,int scale_factor_x, int scale_factor_y, Color c)
    {
        int A = x0 / scale_factor_x;
        int B = y0 / scale_factor_y;
        int C = x1 / scale_factor_x;
        int D = y1 / scale_factor_y;
        int E = x2 / scale_factor_x;
        int F = y2 / scale_factor_y;
        int G = x3 / scale_factor_x;
        int H = y3 / scale_factor_y;
        
        this.drawQuadrilateral(g, X, Y, gap, A, B, C, D, E, F, G, H, c);
        this.fill(g, X, Y, gap, A, B, C, D, E, F, G, H, c);
    }
}


public class polygon extends Applet implements ActionListener
{

    private int X,Y,gap,status,polygon;
   
    // Constructor
    public polygon() 
    {
        this.gap = 4;
        this.status = 0;
        this.polygon = 0;
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
        Button b3 = new Button("Quadrilateral");
        Button b4 = new Button("Triangle");

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
        
        int x0 =  0, y0 = 5;
        int x1 = -5, y1 = 0;
        int x2 =  5, y2 = 0;
        int x3 =  17, y3 = 5;
        
        Color a = new Color(255,0,0);
        g.setColor(a);
        
        Color b = new Color(0,0,0);
        
        if(polygon==2)
        {
            drawGrid(g, X, Y, gap);
            drawAxes(g);
            Triangle triangle = new Triangle();
            triangle.drawTriangle(g, X, Y, gap, x0, y0, x1, y1, x2, y2, a);
            triangle.scale_up(g, X, Y, gap, x0, y0, x1, y1, x2, y2,4,4, b);
            triangle.fill(g, X, Y, gap, x0, y0, x1, y1, x2, y2, a);
            triangle.scale_down(g, X, Y, gap, x0, y0, x1, y1, x2, y2,2,2, b);
        }
        else if(polygon==1)
        {
            drawGrid(g, X, Y, gap);
            drawAxes(g);
            Quadrilateral quadrilateral = new Quadrilateral();
            quadrilateral.drawQuadrilateral(g, X, Y, gap, x0, y0, x1,y1, x2, y2, x3, y3, a);
            quadrilateral.scale_up(g, X, Y, gap, x0, y0, x1,y1, x2, y2, x3, y3,4,4, b);
            quadrilateral.fill(g, X, Y, gap, x0, y0, x1,y1, x2, y2, x3, y3, a);
            quadrilateral.scale_down(g, X, Y, gap, x0, y0, x1,y1, x2, y2, x3, y3,2,2, b);
        }
        else // polygon = 0;
        {
            drawGrid(g, X, Y, gap);
            drawAxes(g);
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
                    
                case "Quadrilateral":
                    polygon = 1; 
                    break;
                    
                case "Triangle":
                    polygon = 2; 
                    break;

                default:
                    break;
            }
        }
        repaint();
       }
}


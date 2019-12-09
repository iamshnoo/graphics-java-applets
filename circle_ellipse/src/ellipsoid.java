import java.awt.*;
import java.applet.*;
import java.awt.event.*;

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

class Circle
{
    public void Midpoint (Graphics g, int X, int Y, int gap, double radius,double xcent,double ycent, Color c)
    {
        double x = radius;
        double y = 0; 

        // Initialising the value of p 
        double p = 1 - radius; 

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
    
    public void fill (Graphics g, int X, int Y, int gap, double radius,double xcent,double ycent, Color c)
    {
        Point p1 = new Point();
        
        // Reference : https://stackoverflow.com/questions/10322341/simple-algorithm-for-drawing-filled-ellipse-in-c-c
        for(int y =-(int)radius; y <= (int)radius; y++)
            for(int x =-(int)radius; x <=(int)radius; x++)
                if(x*x + y*y <= radius*radius)
                    p1.plot(g, X, Y,gap, xcent+x, ycent+y, c);
    }
    
    public void scale_up (Graphics g, int X, int Y, int gap, double radius,double xcent,double ycent, double scale_factor, Color c)
    {
        /*
        Point p1 = new Point();
        for(int y =-radius; y <= radius; y++)
            for(int x =-radius; x <=radius; x++)
                if(x*x + y*y <= radius*radius)
                    p1.plot(g, X, Y,gap, scale_factor*(xcent+x), scale_factor*(ycent+y), c);
        */
        double a = radius * scale_factor;
        this.Midpoint(g, X, Y, gap, a,xcent,ycent, c);
        this.fill (g,  X, Y, gap, a,xcent,ycent, c); 
    }
    
    public void scale_down (Graphics g, int X, int Y, int gap, double radius,double xcent,double ycent, double scale_factor, Color c)
    {
        /*
        Point p1 = new Point();
        for(int y =-radius; y <= radius; y++)
            for(int x =-radius; x <=radius; x++)
                if(x*x + y*y <= radius*radius)
                    p1.plot(g, X, Y,gap, scale_factor*(xcent+x), scale_factor*(ycent+y), c);
        */
        double a = (int)(radius / scale_factor);
        this.Midpoint(g, X, Y, gap, a,xcent,ycent, c);
        this.fill (g,  X, Y, gap, a,xcent,ycent, c); 
    }
}

class Ellipse
{
    // modified midpoint algorithm
    public void Midpoint (Graphics g, int X, int Y, int gap, double rx,double ry, double xcent,double ycent, Color c)
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
    
    public void fill (Graphics g, int X, int Y, int gap, double rx,double ry, double xcent,double ycent, Color c)
    {
        Point p1 = new Point();
        
        // Reference : https://stackoverflow.com/questions/10322341/simple-algorithm-for-drawing-filled-ellipse-in-c-c
        for(int y = -(int)ry; y <= (int)ry; y++)
            for(int x = -(int)rx; x <= (int)rx; x++)
                if(x*x*ry*ry + y*y*rx*rx <= rx*rx*ry*ry)
                    p1.plot(g, X, Y, gap, xcent+x, ycent+y, c);
    }
    
    public void scale_up (Graphics g, int X, int Y, int gap, double rx,double ry, double xcent,double ycent, double scale_factor_x, double scale_factor_y, Color c)
    {
        /*
        Point p1 = new Point();
        for(int y = -ry; y <= ry; y++)
            for(int x = -rx; x <= rx; x++)
                if(x*x*ry*ry + y*y*rx*rx <= rx*rx*ry*ry)
                    p1.plot(g, X, Y,gap, scale_factor*(xcent+x), scale_factor*(ycent+y), c);
        */
        
        double a = rx * scale_factor_x;
        double b = ry * scale_factor_y;
        this.Midpoint(g, X, Y, gap, a,b, xcent,ycent, c);
        this.fill (g,  X, Y, gap, a,b, xcent,ycent, c);   
    }
    
    public void scale_down (Graphics g, int X, int Y, int gap, double rx,double ry, double xcent,double ycent, double scale_factor_x, double scale_factor_y, Color c)
    {
        /*
        Point p1 = new Point();
        for(int y = -ry; y <= ry; y++)
            for(int x = -rx; x <= rx; x++)
                if(x*x*ry*ry + y*y*rx*rx <= rx*rx*ry*ry)
                    p1.plot(g, X, Y,gap, scale_factor*(xcent+x), scale_factor*(ycent+y), c);
        */
        
        double a = rx / scale_factor_x;
        double b = ry / scale_factor_y;
        this.Midpoint(g, X, Y, gap, a,b, xcent,ycent, c);
        this.fill (g,  X, Y, gap, a,b, xcent,ycent, c);   
    }
     
}

public class ellipsoid extends Applet implements ActionListener 
{   
    private int X,Y,gap,status, ellipsoid;

    // Text fields for input
    private final TextField t1 = new TextField();
    private final TextField t2 = new TextField();
    private final TextField t3 = new TextField();   
    private final TextField t4 = new TextField();  
    
    // Constructor
    public ellipsoid() 
    {
        this.gap = 4;
        this.status = 0;
        this.ellipsoid = 0;
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
        Button b3 = new Button("Circle");
        Button b4 = new Button("Ellipse");

        add(b1);
        add(b2);
        add(b3);
        add(b4);

        b1.addActionListener(this);
        b2.addActionListener(this);
        b3.addActionListener(this);
        b4.addActionListener(this);

          
        // Text Fields and Labels
        Label l1 = new Label("r_x = ");
        Label l2 = new Label("r_y = ");
        Label l3 = new Label("x_c  = ");
        Label l4 = new Label("y_c  = ");
        
        add(l1);
        add(t1);
        add(l2);
        add(t2);
        add(l3);
        add(t3);
        add(l4);
        add(t4);
        
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
                    gap = 5;
                break;
                
            default :
                break;
                              
        }
        
        X=(getX()+ getWidth())/2;
        Y=(getY()+getHeight())/2;
        drawGrid(g, X, Y, gap);
        drawAxes(g);
        
        if(!"".equals(t1.getText()) && !"".equals(t2.getText()) && !"".equals(t3.getText()) && !"".equals(t4.getText()))
        {
            //int radius_x = Integer.parseInt(t1.getText());
            //int radius_y = Integer.parseInt(t2.getText());
            //int xcent  = Integer.parseInt(t3.getText());
            //int ycent  = Integer.parseInt(t4.getText());
            
            double radius_x = Double.parseDouble(t1.getText());
            double radius_y = Double.parseDouble(t2.getText());
            double xcent  = Double.parseDouble(t3.getText());
            double ycent  = Double.parseDouble(t4.getText());
            
            Color a = new Color(255,0,0);
            g.setColor(a);
            
            Color b = new Color(0,0,0);

            switch (ellipsoid) 
            {
                case 1:
                    Circle circle = new Circle();
                    circle.Midpoint(g, X, Y, gap, radius_x, xcent, ycent, a);
                    
                    circle.scale_up(g, X, Y, gap, radius_x, xcent, ycent,4, b);
                    circle.fill(g, X, Y, gap, radius_x, xcent, ycent, a);
                    
                    circle.scale_down(g, X, Y, gap, radius_x, xcent, ycent,2, b);
                    break;

                case 2:
                    Ellipse ellipse = new Ellipse();
                    //ellipse.Midpoint(g, X, Y, gap, radius_x, radius_y, xcent, ycent, a);
                    
                    ellipse.scale_up(g, X, Y, gap, radius_x, radius_y, xcent, ycent,4,4, b);
                    ellipse.fill(g, X, Y, gap, radius_x, radius_y, xcent, ycent, a);
                    ellipse.scale_down(g, X, Y, gap, radius_x, radius_y, xcent, ycent,2,2, b);
                    break;

                default :
                    break;

            }
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
                    
                case "Circle":
                    ellipsoid = 1; 
                    break;
                    
                case "Ellipse":
                    ellipsoid = 2; 
                    break;

                default:
                    break;
            }
        }
        repaint();
       }
}

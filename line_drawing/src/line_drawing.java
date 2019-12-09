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
        g.fillRect(X+x1*gap-gap/4, Y-y1*gap-gap/4, gap/2, gap/2);  
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


public class line_drawing extends Applet implements ActionListener 
{
    private int X,Y,gap,status, algorithm;

    // Text fields for input
    private final TextField t1 = new TextField();
    private final TextField t2 = new TextField();
    private final TextField t3 = new TextField();
    private final TextField t4 = new TextField();    

    // Constructor
    public line_drawing() 
    {
        this.gap = 20;
        this.status = 0;
        this.algorithm = 0;
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
        Button b3 = new Button("DDA");
        Button b4 = new Button("Bresenham");
        Button b5 = new Button("Midpoint");
        add(b1);
        add(b2);
        add(b3);
        add(b4);
        add(b5);
        b1.addActionListener(this);
        b2.addActionListener(this);
        b3.addActionListener(this);
        b4.addActionListener(this);
        b5.addActionListener(this);
          
        // Text Fields and Labels
        Label l1 = new Label("x1 = ");
        Label l2 = new Label("y1 = ");
        Label l3 = new Label("x2 = ");
        Label l4 = new Label("y2 = ");
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
            int x1 = Integer.parseInt(t1.getText());
            int y1 = Integer.parseInt(t2.getText());
            int x2 = Integer.parseInt(t3.getText());
            int y2 = Integer.parseInt(t4.getText());
        
            Color a = new Color(255,0,0);
            g.setColor(a);

            Line l = new Line();

            switch (algorithm) 
            {
                case 1:
                    l.DDA(g, X, Y, gap, x1,y1,x2,y2,a);
                    break;

                case 2:
                    l.Bresenham(g, X, Y, gap, x1,y1,x2,y2,a);
                    break;

                case 3:
                    l.Midpoint(g, X, Y, gap, x1,y1,x2,y2,a);
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
                    
                case "DDA":
                    algorithm = 1; 
                    break;
                    
                case "Bresenham":
                    algorithm = 2; 
                    break;
                    
                case "Midpoint":
                    algorithm = 3; 
                    break;
                    
                default:
                    break;
            }
        }
        repaint();
       }
}




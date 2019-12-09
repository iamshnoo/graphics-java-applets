package java_application;

import java.awt.*;
import java.applet.*;
import java.awt.event.*;

/**
 *
 * @author Anjishnu
 */
public class NewApplet extends Applet implements ActionListener {
    private int X,Y,gap,status,function;

    // Constructor
    public NewApplet() 
    {
        this.gap = 15;
        this.status = 0;
        this.function = 0;
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
        Button b1 = new Button("Zoom In");
        Button b2 = new Button("Zoom Out");
        Button b3 = new Button("Draw Square");
        Button b4 = new Button("Draw Line");
        Button b5 = new Button("Plot points");
        Button b6 = new Button("Bresenham's Line");
        Button b7 = new Button("DDA Line");
        Button b8 = new Button("Triangle");
        add(b1);
        add(b2);
        add(b3);
        add(b4);
        add(b5);
        add(b6);
        add(b7);
        add(b8);
        b1.addActionListener(this);
        b2.addActionListener(this);
        b3.addActionListener(this);
        b4.addActionListener(this);
        b5.addActionListener(this);
        b6.addActionListener(this);
        b7.addActionListener(this);
        b8.addActionListener(this);
    }
    
    
    public void midpoint (){
        
    }
    
    @Override
    public void paint(Graphics g)
    {
        switch (status) {
            case 1:
                gap = gap + 5;
                break;
                
            case 2:
                if(gap > 5)
                    gap = gap - 5;
                break;
                
            default :
                break;
                              
        }
        
        // Set a clor for drawing grid lines
        Color mycolor=new Color(0,0,204);
        g.setColor(mycolor);

        // Origin at centre of grid
        X=(getX()+getWidth())/2;
        Y=(getY()+getHeight())/2;

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
        
        // Set a different color to draw axis lines
        Color axiscolor=new Color(0,255,0);
        g.setColor(axiscolor);
        
        // X- axis
        g.drawLine(0, getHeight()/2, getWidth(), getHeight()/2);
        // Y- axis
        g.drawLine(getWidth()/2, 0, getWidth()/2, getHeight());

        // Set a different color for shapes
        Color shapecolor=new Color(0,0,0);
        g.setColor(shapecolor);
                        
        
        switch (function) {
            case 1:
                // Draw a rectangle
                g.fillRect(X-gap, Y-gap, 2*gap, 2*gap); 
                break;
                
            case 2:
                // Draw a line
                g.drawLine(0, 0, getWidth(), getHeight()); 
                break;
                
            case 3:
                // Set a different color to draw axis lines
                Color pointscolor;
                pointscolor = new Color(255,0,255);
                g.setColor(pointscolor);
                // plot points
                for(int i=-20; i<20; i+=2)
                {
                    g.fillRect(X+(i+1)*gap/2, Y - (i+1)*gap/2, gap, gap);
                }
                break;
                
            case 4:
                // Bresenham's Line Drawing algorithm
                //Line line_bresenham = new Line();
                //line_bresenham.Bresenham (g,20,20,100,100);
                break;
            
            case 5:
                // DDA Line Drawing Algorithm
                //Line line_dda = new Line();
                //line_dda.DDA (g,0,0,600,200,gap);
                break;
                
            case 6:
                // Triangle
                Line line1 = new Line();
                //Line line2 = new Line();
                //Line line3 = new Line();
                
                line1.DDA (g,20,100,20,100,gap);
                //line2.DDA(g, 100,100, 20,20,gap);
                
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
                case "Zoom In":
                    //gap = 5  + gap;
                    status = 1;
                    break;
                case "Zoom Out":
                    status = 2; 
                    break;
                case "Draw Square":
                    function = 1;
                    break;
                case "Draw Line":
                    function = 2;
                    break;
                case "Plot points":
                    function = 3;
                    break;
                case "Bresenham's Line":
                    function = 4;
                    break;
                case "DDA Line":
                    function = 5;
                    break;
                case "Triangle":
                    function = 6;
                    break;
                default:
                    break;
            }
        }
        repaint();
       }
}

class Point {
    public void plot(Graphics g, int X, int Y, int gap, int x1, int y1)
    {
        Color pointscolor = new Color(255,0,255);
        g.setColor(pointscolor);
        g.fillRect(X+x1*gap-gap/4, Y-y1*gap-gap/4, gap/2, gap/2);  
    }
}

class Line {
    public void DDA (Graphics g, int x1, int y1, int x2, int y2, int gap)
    {
        // calculate dx and dy
        int dx = x2 - x1;
        int dy = y2 - y1;
        
        // calculate the number of steps needed
        int steps = (int) (Math.abs(dx) > Math.abs(dy) ? Math.abs(dx) : Math.abs(dy));
        
        // calculate increment in x and y for each step
        double x_inc = (dx/steps);
        double y_inc = (dy/steps);
         
        // put pixel for each step 
        double x = x1;
        double y = y1;
        
        g.setColor(Color.red);
        
        g.fillRect((int)x*gap + gap/2,(int)y*gap + gap/2,gap,gap);
        for(int k = 1; k <= steps ; k++)
        {
           x += gap+x_inc;
           y += gap+y_inc;
           g.fillRect((int)x + gap/2,(int)y + gap/2,gap,gap);
        }

    }
    
    //This version of bresenham does not work
    public void Bresenham (Graphics g, int X, int Y, int gap, int x1, int y1, int x2, int y2)
    {
        // find the left endpoint (x0,y0)
        if((x1-x2)>0)
        {
            Bresenham(g,X,Y,gap,x2,y2,x1,y1);
            return;
        }
        
        // calculate p0
        double dx = Math.abs(x2-x1);
        double dy = Math.abs(y2-y1);
        double p = 2*dy - dx;
        
        //int x = x1;
        int y = y1;
        
        Point p2 = new Point();
        
        // plot the left endpoint
        //p2.plot(g, X, Y, gap, x, y);
        //g.fillOval(x1,y1,5,5);
        
        //for(int k = 0; k < dx; k++)
        for(int x = x1; x < x2; x++)
        {
            if(p < 0)
            {
                //x = x + 1;
                p2.plot(g, X, Y, gap, x, y);
                //g.fillRect(x++,y,5,5);
                p = p + 2*dy;
            }
            else
            {
                //x = x + 1;
                y = y + 1;
                p2.plot(g, X, Y, gap, x, y);
                //g.fillRect(x++,y++,5,5);
                p = p + 2*(dy-dx);
            }
        }
    }
}

/*  
    -- Testing code for Line Algorithms and point plotting --

    //Polygon p = new Polygon();
    //p.triangle(g, X, Y, gap, -1, 1, -1, 10, -10, 1,x1);
    //p.triangle(g, X, Y, gap, -1, 1, -1, -8, -10, 1,x2);
    //p.triangle(g, X, Y, gap, -1, 9, -1, 5, 3, 5,x3);
    //p.quadrilateral(g, X, Y, gap, -1, -5, -1, 5, 3, 5, 3, -5,x4);
    //p.triangle(g, X, Y, gap, -1, -9, -1, -5, 3, -5,x5);
    //p.quadrilateral(g, X, Y, gap, 3, 5, 3, -5, 11, 5, 11, 9,x6);
    //p.triangle(g, X, Y, gap, 11, -10, 3, -5, 11, 5,x7);
    //p.quadrilateral(g, X, Y, gap, -8, 2, -8, -2, -6, -2, -6, 2,x8);

    //Color a=new Color(0,0,0);
    //g.setColor(a);
    //l.Midpoint(g, X, Y, gap, 10,10,-10,-10,a);
    //l.Midpoint(g, X, Y, gap, -10,10,10,-10,a);
    //l.Midpoint(g, X, Y, gap, -10,10,10,10,a);
    //l.Midpoint(g, X, Y, gap, 10,0,10,5,a);
    //l.Midpoint(g, X, Y, gap, -10,10,10,-5,a);

    //Line l = new Line();
    //l.DDA(g, X, Y, gap, 10,10,-10,-10);
    //l.DDA(g, X, Y, gap, -10,10,10,-10);
    //l.DDA(g, X, Y, gap, -10,10,10,10);
    //l.DDA(g, X, Y, gap, 10,0,10,5);
    //l.DDA(g, X, Y, gap, -10,10,10,-5);

    //l.Bresenham(g, X, Y, gap, 10,10,-10,-10);
    //l.Bresenham(g, X, Y, gap, -10,10,10,-10);  
    //l.Bresenham(g, X, Y, gap, -10,10,10,10);   
    //l.Bresenham(g, X, Y, gap, 10,0,10,5);     
    //l.Bresenham(g, X, Y, gap, -10,10,10,-5);  

    //Point p = new Point();
    //p.plot(g, X, Y, gap, 10, -5);
    //p.plot(g, X, Y, gap, 9, 9); // 1st quad
    //p.plot(g, X, Y, gap, -9, 9); // 2nd quad
    //p.plot(g, X, Y, gap, -9, -9); // 3rd quad
    //p.plot(g, X, Y, gap, 9, -9); // 4th quad
    //for(int i=10; i>=-10; i--)
    //    p.plot(g,X,Y,gap,i,i);


    public void drawDashedLine(Graphics g, int x1, int y1, int x2, int y2)
    {

       //creates a copy of the Graphics instance
       Graphics2D g2d = (Graphics2D) g.create();

       Stroke dashed = new BasicStroke(3, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[]{9}, 0);
       g2d.setStroke(dashed);
       Color a = new Color(0,0,0);
       g2d.setColor(a);
       g2d.drawLine(x1, y1, x2, y2);

       //gets rid of the copy
       g2d.dispose();
    }
    
        
*/
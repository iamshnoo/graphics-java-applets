import java.applet.Applet;
import java.awt.Button;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class Colour
{
    public Color brown()
    {
        Color myColor = new Color(153,0,0);
        return myColor;
    }
   
    public Color reddishOrange()
    {
        Color myColor = new Color(255,69,0);
        return myColor;
    }
   
    public Color darkOrange()
    {
        Color myColor = new Color(255,140,0);
        return myColor;
    }
}

public class Curve extends Applet implements ActionListener
{
    Button button1,button2,button3,button4,button5,button6,button7;
   
    int X=0,Y=0,status=0,gap=4,shape=0;
    int x=0, y=0, x1=0, y1=0, x2=0, y2=0, xc=0, yc=0;
    int dx = 0, dy=0, k = 0, steps = 0;
    double Dx=0, Dy=0, p =0, Xc=0, Yc=0, Steps = 0;
   
    public void init()
    {
        button1 = new Button("Zoom Out");
        add(button1);
        button1.addActionListener(this);
       
        button2 = new Button("Reset");
        add(button2);
        button2.addActionListener(this);

        button3 = new Button("Zoom In");
        add(button3);
        button3.addActionListener(this);
    }

    public void actionPerformed(ActionEvent e)
    {
        if (e.getSource() == button1)
        {
            gap = gap/2;
            repaint();
        }

        else if(e.getSource() == button2)
        {
            gap = 4;
            repaint();
        }

        else if(e.getSource() == button3)
        {
            gap = gap*2;
            repaint();
        }
    }
    
    public void bezierCurve(Graphics g, int originX, int originY, int x[] , int y[], int gap, Color c) 
    {
        Point p = new Point();
        double xu = 0.0 , yu = 0.0 , u = 0.0 ; 
        int i = 0 ; 
        for(u = 0.0 ; u <= 1.0 ; u += 0.0001) 
        { 
            xu = Math.pow(1-u,3)*x[0]+3*u*Math.pow(1-u,2)*x[1]+3*Math.pow(u,2)*(1-u)*x[2] 
                 +Math.pow(u,3)*x[3]; 
            yu = Math.pow(1-u,3)*y[0]+3*u*Math.pow(1-u,2)*y[1]+3*Math.pow(u,2)*(1-u)*y[2] 
                 +Math.pow(u,3)*y[3]; 
            p.plot(g, originX, originY, gap, (int)xu , (int)yu, c) ;
        }
    }

    @Override
    public void paint(Graphics g)
    {    
        int xloc = 0, yloc = 0;
        int i = 0;
        int originX = 0, originY = 0;
       
        originX = (getX() + getWidth())/2;
        originY = (getY() + getHeight())/2;
        xloc = this.getWidth();
        yloc = this.getHeight();
       
        for(i = originY;i <= yloc; i = i + gap)
        {
            g.drawLine(0, i, xloc, i);
        }
       
        for(i = originY;i >= 0; i = i - gap)
        {
            g.drawLine(0, i, xloc, i);
        }
       
        for(i = originX; i <= xloc; i = i + gap)
        {
            g.drawLine(i, 0, i, yloc);
        }
       
        for(i = originX; i >= 0; i = i - gap)
        {
            g.drawLine(i, 0, i, yloc);
        }
       
        Color mycolor = new Color(0,255,0);
        g.setColor(mycolor);
        g.drawLine(0, yloc/2, xloc, yloc/2);
        g.drawLine(xloc/2, 0, xloc/2, yloc);
        
        Triangle triangle = new Triangle();
        Ellipse ellipse = new Ellipse();
        Line line = new Line();
        Circle circle = new Circle();
        Quadrilateral quadrilateral = new Quadrilateral();
        
        int a[] = new int[4];
        int b[] = new int[4];
        
        Color head_c = new Color(255,215,15);     
        Color extras_c = new Color(0,0,0);        
        Color body_c = new Color(210,175,15);     
        Color cartoon_c = new Color(255,255,255);
        
        triangle.fill(g, originX, originY, gap, x-10, y-60, x+10, y-60, x, y-80, extras_c);                     // tail
        ellipse.fill(g, originX, originY, gap, 20, 25, x, y-40, body_c);                                        // body
        circle.fill(g, originX, originY, gap, 25, x, y, head_c);                                                // head
        
        //line.Bresenham(g, originX, originY, gap, x-5, y-10, x+5, y-10, extras_c);                               // lips
        
        a[0] = x-5;
        b[0] = y-10;
        a[1] = x-3;
        b[1] = y-15;
        a[2] = x-1;
        b[2] = y-15;
        a[3] = x;
        b[3] = y-10;
        
        bezierCurve(g, originX, originY, a, b, gap, extras_c);
        
        a[0] = x;
        b[0] = y-10;
        a[1] = x+1;
        b[1] = y-15;
        a[2] = x+3;
        b[2] = y-15;
        a[3] = x+5;
        b[3] = y-10;
        
        bezierCurve(g, originX, originY, a, b, gap, extras_c);
        
        circle.fill(g, originX, originY, gap, 6, x-11, y+5, extras_c);                                          // left_eye
        circle.fill(g,originX, originY, gap, 6, x+11, y+5, extras_c);                                           // right_eye
        
        
        //line.Bresenham(g, originX, originY, gap, x-8, y+24, x-18, y+36, extras_c);                              // left_antenna
        
        a[0] = x-8;
        b[0] = y+24;
        a[1] = x-6;
        b[1] = y+30;
        a[2] = x-6;
        b[2] = y+33;
        a[3] = x-18;
        b[3] = y+36;
        
        bezierCurve(g, originX, originY, a, b, gap, extras_c);
        
        a[0] = x-18;
        b[0] = y+36;
        a[1] = x-24;
        b[1] = y+32;
        a[2] = x-24;
        b[2] = y+34;
        a[3] = x-18;
        b[3] = y+27;
        
        bezierCurve(g, originX, originY, a, b, gap, extras_c);
        
        //line.Bresenham(g, originX, originY, gap, x+8, y+24, x+18, y+36, extras_c);// right_antenna
        
        a[0] = x+8;
        b[0] = y+24;
        a[1] = x+6;
        b[1] = y+30;
        a[2] = x+6;
        b[2] = y+33;
        a[3] = x+18;
        b[3] = y+36;
        
        bezierCurve(g, originX, originY, a, b, gap, extras_c);
        
        a[0] = x+18;
        b[0] = y+36;
        a[1] = x+24;
        b[1] = y+32;
        a[2] = x+24;
        b[2] = y+34;
        a[3] = x+18;
        b[3] = y+27;
        
        bezierCurve(g, originX, originY, a, b, gap, extras_c);
        
        //circle.fill(g, originX, originY, gap, 3, x-20, y+39, extras_c);                                         // left_antenna_head
        //circle.fill(g, originX, originY, gap, 3, x+20, y+39, extras_c);                                         // right_antenna_head
        
        
        
        a[0] = x-18;
        b[0] = y-30;
        a[1] = x-5;
        b[1] = y-40;
        a[2] = x+5;
        b[2] = y-40;
        a[3] = x+19;
        b[3] = y-30;
        
        bezierCurve(g, originX, originY, a, b, gap, extras_c);
        
        a[0] = x-18;
        b[0] = y-31;
        a[1] = x-5;
        b[1] = y-41;
        a[2] = x+5;
        b[2] = y-41;
        a[3] = x+19;
        b[3] = y-31;
        
        bezierCurve(g, originX, originY, a, b, gap, extras_c);
        
        a[0] = x-18;
        b[0] = y-32;
        a[1] = x-5;
        b[1] = y-42;
        a[2] = x+5;
        b[2] = y-42;
        a[3] = x+19;
        b[3] = y-32;
        
        bezierCurve(g, originX, originY, a, b, gap, extras_c);
        
        a[0] = x-19;
        b[0] = y-33;
        a[1] = x-5;
        b[1] = y-43;
        a[2] = x+5;
        b[2] = y-43;
        a[3] = x+20;
        b[3] = y-33;
        
        bezierCurve(g, originX, originY, a, b, gap, extras_c);
        
        a[0] = x-19;
        b[0] = y-34;
        a[1] = x-5;
        b[1] = y-44;
        a[2] = x+5;
        b[2] = y-44;
        a[3] = x+20;
        b[3] = y-34;
        
        bezierCurve(g, originX, originY, a, b, gap, extras_c);
        
        a[0] = x-19;
        b[0] = y-35;
        a[1] = x-5;
        b[1] = y-45;
        a[2] = x+5;
        b[2] = y-45;
        a[3] = x+20;
        b[3] = y-35;
        
        bezierCurve(g, originX, originY, a, b, gap, extras_c);
        
        a[0] = x-19;
        b[0] = y-36;
        a[1] = x-5;
        b[1] = y-46;
        a[2] = x+5;
        b[2] = y-46;
        a[3] = x+20;
        b[3] = y-36;
        
        bezierCurve(g, originX, originY, a, b, gap, extras_c);
        
        a[0] = x-19;
        b[0] = y-37;
        a[1] = x-5;
        b[1] = y-47;
        a[2] = x+5;
        b[2] = y-47;
        a[3] = x+20;
        b[3] = y-37;
        
        bezierCurve(g, originX, originY, a, b, gap, extras_c);
        
        a[0] = x-19;
        b[0] = y-38;
        a[1] = x-5;
        b[1] = y-48;
        a[2] = x+5;
        b[2] = y-48;
        a[3] = x+20;
        b[3] = y-38;
        
        bezierCurve(g, originX, originY, a, b, gap, extras_c);
        
        a[0] = x-20;
        b[0] = y-39;
        a[1] = x-5;
        b[1] = y-49;
        a[2] = x+5;
        b[2] = y-49;
        a[3] = x+20;
        b[3] = y-39;
        
        bezierCurve(g, originX, originY, a, b, gap, extras_c);
        
        circle.fill(g, originX, originY, gap, 1, x-13, y+7, cartoon_c);                                         // left_eye_inner
        circle.fill(g,originX, originY, gap,1,x+13,y+7,cartoon_c);                                              // right_eye_inner
    }
}

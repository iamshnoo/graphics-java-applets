/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.applet.*;
import java.awt.*;
import java.awt.event.*;

/**
 *
 * @author Anjishnu
 */

/*
public class Snake extends Applet implements KeyListener,Runnable
{
	Thread t;
	int sx[]=new int[100];
	int sy[]=new int[100];
	int snalen=10;
	Color c;
	int n,x=10,y=0,px=300,py=190;


	public void init()
	{
		addKeyListener(this);
		c= new Color(150, 150, 150);
		setBackground(c);
		sx[0]=250;sx[1]=240;sx[2]=230;sx[3]=220;
		sy[0]=250;sy[1]=250;sy[2]=250;sy[3]=250;
	}

	public void start()
	{
		t= new Thread(this);
		t.start();
	}

	public void keyTyped(KeyEvent ke){}
	public void keyPressed(KeyEvent ke)
	{
		n=ke.getKeyCode();
		switch(n)
		{
			case KeyEvent.VK_UP:
			{
				x=0;
				y=-10;
				showStatus("Up key is pressed:");
				break;
			}
			case KeyEvent.VK_DOWN:
			{
				x=0;
				y=10;
				showStatus("Down key is pressed:");
				break;
			}
			case KeyEvent.VK_LEFT:
			{
				x=-10;
				y=0;
				showStatus("Left key is pressed:");
				break;
			}
			case KeyEvent.VK_RIGHT:
			{
				x=10;
				y=0;
				showStatus("Right key is pressed:");
				break;
			}
		}
	}
	public void keyReleased(KeyEvent ke){}

	public void run()
	{
		go:while(true)
		{
			try
			{
				Thread.sleep(250-snalen*5);
				repaint();

				if(!(sx[0]>=6 && sx[0]<=489 && sy[0]>=6 && sy[0]<=489))
				{
					break go;
				}
				for(int i=1;i<snalen;i++)
					{
						if(sx[0]==sx[i] && sy[0]== sy[i])
							{break go;}
					}
			}
			catch(InterruptedException e){}
		}

	}

	public void paint(Graphics g)
	{
		c=new Color(90,10,90);
		g.setColor(c);
		g.fillRect(5,5,490,490);			//frame

		c= new Color(200,200,200);
		g.setColor(c);

		for(int i=0;i<snalen;i++)
		{
			g.fillRect(sx[i],sy[i],8,8);   //snake
		}

			for(int i=snalen-2;i>=0;i--)
			{
				sx[i+1]=sx[i];
				sy[i+1]=sy[i];
			}
			sx[0]=sx[0]+x;
			sy[0]=sy[0]+y;

		g.fillRect(px,py,8,8);		//food

		if(sx[0]==px && sy[0]==py)
		{
			px=(int) Math.ceil(Math.random()*(sx[0]+50));
			py=(int) Math.floor(Math.random()*(sy[0]+100));

			if(!(px%10==0 && py%10==0))
			{
				int e1,e2;
				e1=px%10;
				e2=py%10;

				px-=e1;
				py-=e2;
			}

			if(!(px>10 && px<485 && py>10 && py<485))
			{
				px=50;
				py=100;
			}
			snalen++;
			showStatus("Score: " +snalen*10);
		}

	}

}
*/

/*
// AnimatorApplet
public class spatial extends Applet implements Runnable {
    int frameNumber = -1;
    int delay;
    Thread animatorThread;
    boolean frozen = false;
    
    @Override
    public void init() {
        String str;
        int fps = 10;

        //How many milliseconds between frames?
        str = getParameter("fps");
        try {
            if (str != null) {
                fps = Integer.parseInt(str);
            }
        } catch (Exception e) {}
        delay = (fps > 0) ? (1000 / fps) : 100;
    }

    @Override
    public void start() {
        if (frozen) { 
            //Do nothing.  The user has requested that we 
            //stop changing the image.
        } else {
            //Start animating!
            if (animatorThread == null) {
                animatorThread = new Thread(this);
            }
            animatorThread.start();
        }
    }

    @Override
    public void stop() {
        //Stop the animating thread.
        animatorThread = null;
    }

    @Override
    public boolean mouseDown(Event e, int x, int y) {
        if (frozen) {
            frozen = false;
            start();
        } else {
            frozen = true;
            stop();
        }
        return true;
    }

    @Override
    public void run() {
        //Just to be nice, lower this thread's priority
        //so it can't interfere with other processing going on.
        Thread.currentThread().setPriority(Thread.MIN_PRIORITY);

        //Remember the starting time.
        long startTime = System.currentTimeMillis();

        //This is the animation loop.
        while (Thread.currentThread() == animatorThread) {
            //Advance the animation frame.
            frameNumber++;

            //Display it.
            repaint();

            //Delay depending on how far we are behind.
            try {
                startTime += delay;
                Thread.sleep(Math.max(0, startTime-System.currentTimeMillis()));
            } catch (InterruptedException e) {
                break;
            }
        }
    }

    //Draw the current frame of animation.
    @Override
    public void paint(Graphics g) {
        g.drawString("Frame " + frameNumber, 0, 30);
    }
}

*/
/*
public class spatial extends Applet implements ActionListener{

   
   Button button1, button2,button3,button4,button5,button6,button7,button8;
    int rows=100;
      int cols=100;
      int xtra=0,ytra=0;
      int xsca=1,ysca=1,cos,sin;
int width=2000;
int height = 1600;
 int rowHt = height / (rows);
 int rowWid = width / (cols);
 Label l1 = new Label("radius = ");
Label l2 = new Label("xcent ");
Label l3 = new Label("ycent = ");

 TextField t1 = new TextField(3);
TextField t2 = new TextField(3);
TextField t3 = new TextField(3);

int i;
   @Override
    public void init() {
        // TODO start asynchronous download of heavy resources
        
        add(l1);
        add(t1);
        add(l2);
        add(t2);
        add(l3);
        add(t3);
        
      
        this.setSize(new Dimension(1000,800));
        setBackground(Color.black);
         button3 = new Button("DRAW");
        add(button3);
        button3.addActionListener((ActionListener) this);
        
        button8 = new Button("ROTATE");
        add(button8);
        button8.addActionListener((ActionListener) this);
        
        button4 = new Button("TRANSLATE");
        add(button4);
        button4.addActionListener((ActionListener) this);
        
        button5 = new Button("TRANSLATE_BACK");
        add(button5);
        button5.addActionListener((ActionListener) this);
        
         button6 = new Button("SCALING");
        add(button6);
        button6.addActionListener((ActionListener) this);
        
        button7 = new Button("SCALING_BACK");
        add(button7);
        button7.addActionListener((ActionListener) this);
        
        button1 = new Button("Button 1");
		add(button1);
		button1.addActionListener((ActionListener) this);

		button2 = new Button("Button 2");
		add(button2);
		button2.addActionListener((ActionListener) this);
        
    }
   @Override
    public void actionPerformed(ActionEvent e) {
		if (e.getSource() == button1) {
			System.out.println("Button 1 was pressed");
                       rowHt=rowHt+3;
                       rowWid=rowWid+3;
                       repaint();
                }
                if(e.getSource() == button2){
			System.out.println("Button 2 was pressed");
                       rowHt=rowHt-3;
                       rowWid=rowWid-3;
                       repaint();
                }
                if(e.getSource() == button3) {
                    repaint();
                }
                if(e.getSource() == button4) {
                    xtra=xtra+4;
                    ytra=ytra+6;
                    repaint();
                }
                if(e.getSource() == button5) {
                    xtra=xtra-4;
                    ytra=ytra-6;
                    repaint();
                }
                if(e.getSource() == button6) {
                    xsca=xsca*2;
                    ysca=ysca*2;
                    repaint();
                }
                if(e.getSource() == button7) {
                    xsca=xsca/2;
                    ysca=ysca/2;
                    repaint();
                }
                if(e.getSource() == button8) {
                   
                    
                    cos=0;
                    sin=1;
                    
                    repaint();
                }
	}
   @Override
   public void paint( Graphics g)
{
   g.setColor(Color.white);
  
  
    // draw the rows
   
   
    for (i = 0; i < rows; i++)
      g.drawLine(0, i * rowHt, width, i * rowHt);

    // draw the columns
  
    
    for (i = 0; i < cols; i++)
      g.drawLine(i * rowWid, 0, i * rowWid, height);
    
    
    int x,y,k,p;

int radius,xcent,ycent;
radius=Integer.parseInt(t1.getText());
xcent=Integer.parseInt(t2.getText());
ycent=Integer.parseInt(t3.getText());
p=1-radius;
x=0;
y=radius;
g.setColor(Color.yellow);
g.fillOval((x+xcent)*rowWid,(y+ycent)*rowHt,rowWid,rowHt);
for(k=0;k<=y;k++)
{
    
if(p<0)
{
g.fillOval((x*rowWid*xsca)+((xcent+xtra)*rowWid),(y*rowHt*ysca)+(ycent+ytra)*rowHt,rowWid,rowHt);
g.fillOval((x*rowWid*xsca)+(xcent+xtra)*rowWid,-(y*rowHt*ysca)+(ycent+ytra)*rowHt,rowWid,rowHt);
g.fillOval(-(x*rowWid*xsca)+(xcent+xtra)*rowWid,-(y*rowHt*ysca)+(ycent+ytra)*rowHt,rowWid,rowHt);
g.fillOval(-(x*rowWid*xsca)+(xcent+xtra)*rowWid,(y*rowHt*ysca)+(ycent+ytra)*rowHt,rowWid,rowHt);
g.fillOval((y*rowWid*xsca)+(ycent+xtra)*rowWid,(x*rowHt*ysca)+(xcent+ytra)*rowHt,rowWid,rowHt);
g.fillOval((y*rowWid*xsca)+(ycent+xtra)*rowWid,-(x*rowHt*ysca)+(xcent+ytra)*rowHt,rowWid,rowHt);
g.fillOval(-(y*rowWid*xsca)+(ycent+xtra)*rowWid,(x*rowHt*ysca)+(xcent+ytra)*rowHt,rowWid,rowHt);
g.fillOval(-(y*rowWid*xsca)+(ycent+xtra)*rowWid,-(x*rowHt*ysca)+(xcent+ytra)*rowHt,rowWid,rowHt);

x++;
//y=y;
p=p+(2*x)+1;
}
else
{
g.fillOval((x*rowWid*xsca)+((xcent+xtra)*rowWid),(y*rowHt*ysca)+(ycent+ytra)*rowHt,rowWid,rowHt);
g.fillOval((x*rowWid*xsca)+(xcent+xtra)*rowWid,-(y*rowHt*ysca)+(ycent+ytra)*rowHt,rowWid,rowHt);
g.fillOval(-(x*rowWid*xsca)+(xcent+xtra)*rowWid,-(y*rowHt*ysca)+(ycent+ytra)*rowHt,rowWid,rowHt);
g.fillOval(-(x*rowWid*xsca)+(xcent+xtra)*rowWid,(y*rowHt*ysca)+(ycent+ytra)*rowHt,rowWid,rowHt);
g.fillOval((y*rowWid*xsca)+(ycent+xtra)*rowWid,(x*rowHt*ysca)+(xcent+ytra)*rowHt,rowWid,rowHt);
g.fillOval((y*rowWid*xsca)+(ycent+xtra)*rowWid,-(x*rowHt*ysca)+(xcent+ytra)*rowHt,rowWid,rowHt);
g.fillOval(-(y*rowWid*xsca)+(ycent+xtra)*rowWid,(x*rowHt*ysca)+(xcent+ytra)*rowHt,rowWid,rowHt);
g.fillOval(-(y*rowWid*xsca)+(ycent+xtra)*rowWid,-(x*rowHt*ysca)+(xcent+ytra)*rowHt,rowWid,rowHt);
x++;
y--;
p=p+(2*x-2*y)+1;
}
}
}
   
 
}
*/
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.applet.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.Graphics;

/**
 *
 * @author Anjishnu
 */
public class robot extends Applet implements ActionListener {

    /**
     * Initialization method that will be called after the Applet is loaded into
     * the browser.
     */
    Button button1, button2,button3;
    int rows = 300;
    int cols = 300;
    int width = 2000;
    int height = 2000;
    int rowHt = height / (rows);
    int rowWid = width / (cols);
    int i, x = 115, y = 63, q = 0,flag=0,flag1=0;

    @Override
    public void init() {
        // TODO start asynchronous download of heavy resources
        this.setSize(new Dimension(1600, 1600));
        Color bgcolor=new Color(153,255,255);
        setBackground(bgcolor);
        button1 = new Button("Jump");
        add(button1);
        button1.addActionListener((ActionListener) this);

        button2 = new Button("Walk");
        add(button2);
        button2.addActionListener((ActionListener) this);
          button3 = new Button("Stop");
        add(button3);
        button3.addActionListener((ActionListener) this);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == button1) {
            q = 1;
        }
        if (e.getSource() == button2) {
            q = 2;
        }
        if (e.getSource() == button3) {
            q = 0;
        }
        

        repaint();
    }

    @Override
    public void paint(Graphics g) {
        
        // background color
        Color mycolor=new Color(255,255,255);
        g.setColor(mycolor);

        // draw the rows of grid
        for (i = 0; i < rows; i++) {
            g.drawLine(0, i * rowHt, width, i * rowHt);
        }

        // draw the columns of grid
        for (i = 0; i < cols; i++) {
            g.drawLine(i * rowWid, 0, i * rowWid, height);
        }
        
        // body rectangle of the robot <-- sometimes alternates with head during walking
        //g.setColor(Color.black);
        //g.fillRect(0 * rowWid, 107 * rowHt, rowWid * 300, rowHt);
        
        
        if (q == 0) {
            basic(g);
        }
        if (q == 1) {
            basic(g);
            jump();
        }
        switch (q) {
            // take one step and set a flag to determine direction.
            case 2:
                move(g);
                movement();
                if(flag1==0){
                    q = 3;flag1=1;}
                else{
                    q=4;flag1=0;
                }   break;
                
            // move one step towards left, set flags to decide next step, delay  
            case 3:
                move1(g);
                movement();
                slep();
                q=2;
                break;
                
            // move one step towards right, set flags to decide next step, delay    
            case 4:
                move2(g);
                movement();
                slep();
                q=2;
                break;
            default:
                break;
        }

    }

    // draw the robot at stopped condition, front-facing, at any location
    public void basic(Graphics g) {
        g.fillRect(x * rowWid, y * rowHt, rowWid, rowHt);
        for (int j = 0; j <= 30; j++) {

            g.fillRect((x - 15) * rowWid, (y - 15 + j) * rowHt, rowWid * 31, rowHt);

        }
        g.setColor(Color.yellow);
        for (int j = 0; j <= 10; j++) {
            g.fillRect((x - 10) * rowWid, (y + 16 + j) * rowHt, rowWid * 7, rowHt);
            g.fillRect((x + 4) * rowWid, (y + 16 + j) * rowHt, rowWid * 7, rowHt);
        }

        g.setColor(Color.yellow);
        for (int j = 0; j <= 10; j++) {
            g.fillRect((x - 10) * rowWid, (y + 29 + j) * rowHt, rowWid * 7, rowHt);
            g.fillRect((x + 4) * rowWid, (y + 29 + j) * rowHt, rowWid * 7, rowHt);

        }
        g.fillRect((x + 4) * rowWid, (y + 40) * rowHt, rowWid * 10, rowHt * 2);
        g.fillRect((x - 13) * rowWid, (y + 40) * rowHt, rowWid * 10, rowHt * 2);
        g.setColor(Color.YELLOW);
        for (int j = 0; j < 5; j++) {
            g.fillRect((x - 3) * rowWid, (y - 15 - j) * rowHt, rowWid * 7, rowHt);

        }
        g.setColor(Color.red);
        for (int j = 0; j <= 15; j++) {

            g.fillRect((x - 8) * rowWid, (y - 35 + j) * rowHt, rowWid * 17, rowHt);

        }
        g.setColor(Color.yellow);
        for (int j = 0; j <= 16; j++) {
            g.fillRect((x - 20) * rowWid, (y - 16 + j) * rowHt, rowWid * 5, rowHt);
            g.fillRect((x + 16) * rowWid, (y - 16 + j) * rowHt, rowWid * 5, rowHt);

        }
        g.setColor(Color.red);
        g.fillOval((x + 6) * rowWid, (y + 26) * rowHt, rowWid * 4, rowHt * 4);
        g.fillOval((x - 8) * rowWid, (y + 26) * rowHt, rowWid * 4, rowHt * 4);
        g.setColor(Color.BLUE);
        g.fillOval((x - 22) * rowWid, (y) * rowHt, rowWid * 6, rowHt * 6);
        g.fillOval((x + 18) * rowWid, (y) * rowHt, rowWid * 6, rowHt * 6);
        g.fillOval((x + 3) * rowWid, (y - 31) * rowHt, rowWid * 3, rowHt * 3);
        g.fillOval((x - 4) * rowWid, (y - 31) * rowHt, rowWid * 3, rowHt * 3);
        g.fillRect((x - 4) * rowWid, (y - 24) * rowHt, rowWid * 10, rowHt * 2);

    }

    public void draw(Graphics g) {
        g.setColor(Color.white);

        // draw the rows
        for (i = 0; i < rows; i++) {
            g.drawLine(0, i * rowHt, width, i * rowHt);
        }

        // draw the columns
        for (i = 0; i < cols; i++) {
            g.drawLine(i * rowWid, 0, i * rowWid, height);
        }
        g.setColor(Color.red);
        g.fillRect(0 * rowWid, 107 * rowHt, rowWid * 300, rowHt);

    }

    public void jump() {
        y = y - 2;
        slep();
        if (y > 50) {
            repaint();
        } else {
            y = 65;
            repaint();
        }

    }

    public void movement() {
        
        
        slep();
        if(x>200)
            flag=1;
        else if(x<30)
            flag= 0;
        if (x < 200 && flag==0) {
           x = x + 2;
          
        } else if(x>30 && flag==1){
            x=x-2;

        }
    }

    public void move(Graphics g) {

        for (int j = 0; j <= 15; j++) {

            g.fillRect((x - 7) * rowWid, (y - 35 + j) * rowHt, rowWid * 14, rowHt);
        }
        g.setColor(Color.YELLOW);
        for (int j = 0; j < 5; j++) {
            g.fillRect((x - 4) * rowWid, (y - 15 - j) * rowHt, rowWid * 7, rowHt);

        }
        g.setColor(Color.red);
        for (int j = 0; j <= 30; j++) {

            g.fillRect((x - 10) * rowWid, (y - 15 + j) * rowHt, rowWid * 20, rowHt);

        }
        g.setColor(Color.blue);
        for (int j = 0; j < 5; j++) {
            g.fillRect((x - 3 + j) * rowWid, (y - 15) * rowHt, rowWid, rowHt * 20);
        }
        g.fillOval((x - 3) * rowWid, (y + 28) * rowHt, rowWid * 4, rowHt * 4);
        g.setColor(Color.yellow);
        for (int j = 0; j < 7; j++) {
            g.fillRect((x - 4 + j) * rowWid, (y + 16) * rowHt, rowWid, rowHt * 13);
        }
        g.setColor(Color.yellow);
        for (int j = 0; j < 7; j++) {
            g.fillRect((x - 4 + j) * rowWid, (y + 31) * rowHt, rowWid, rowHt * 11);
        }
        g.fillRect((x - 4) * rowWid, (y + 42) * rowHt, rowWid * 9, rowHt * 2);
        repaint();

    }

    public void move1(Graphics g) {
        g.setColor(Color.red);
        for (int j = 0; j <= 15; j++) {

            g.fillRect((x - 7) * rowWid, (y - 35 + j) * rowHt, rowWid * 14, rowHt);
        }
        g.setColor(Color.YELLOW);
        for (int j = 0; j < 5; j++) {
            g.fillRect((x - 4) * rowWid, (y - 15 - j) * rowHt, rowWid * 7, rowHt);

        }
        g.setColor(Color.BLUE);
        for (int j = 0; j <= 19; j++) {
            g.fillRect((x - 3 - j) * rowWid, (y - 15 + j) * rowHt, rowWid * 7, rowHt);
        }
        g.setColor(Color.red);
        for (int j = 0; j <= 30; j++) {

            g.fillRect((x - 10) * rowWid, (y - 15 + j) * rowHt, rowWid * 20, rowHt);

        }
        g.setColor(Color.BLUE);
        for (int j = 0; j <= 19; j++) {
            g.fillRect((x - 3 + j) * rowWid, (y - 15 + j) * rowHt, rowWid * 7, rowHt);
        }
        g.setColor(Color.YELLOW);
        for (int j = 0; j <= 10; j++) {
            g.fillRect((x - 7 + j) * rowWid, (y + 16 + j) * rowHt, rowWid * 9, rowHt);
        }
        g.setColor(Color.YELLOW);
        for (int j = 0; j <= 10; j++) {
            g.fillRect((x - 2 - j) * rowWid, (y + 16 + j) * rowHt, rowWid * 9, rowHt);
        }
        for (int j = 0; j <= 13; j++) {
            g.fillRect((x - 13 - j) * rowWid, (y + 28 + j) * rowHt, rowWid * 9, rowHt);
        }
        for (int j = 0; j <= 13; j++) {
            g.fillRect((x + 5 + j) * rowWid, (y + 28 + j) * rowHt, rowWid * 9, rowHt);
        }
    
        g.fillRect((x + 18 ) * rowWid, (y + 42 ) * rowHt, rowWid * 11, rowHt * 2);
         g.fillRect((x -26 ) * rowWid, (y + 42 ) * rowHt, rowWid * 11, rowHt * 2);
        repaint();
    }
    
    public void move2(Graphics g){
        
         g.setColor(Color.red);
        for (int j = 0; j <= 15; j++) {

            g.fillRect((x - 7) * rowWid, (y - 35 + j) * rowHt, rowWid * 14, rowHt);
        }
        g.setColor(Color.YELLOW);
        for (int j = 0; j < 5; j++) {
            g.fillRect((x - 4) * rowWid, (y - 15 - j) * rowHt, rowWid * 7, rowHt);

        }
        g.setColor(Color.BLUE);
        for (int j = 0; j <= 19; j++) {
            g.fillRect((x - 3 + j) * rowWid, (y - 15 + j) * rowHt, rowWid * 7, rowHt);
        }
        g.setColor(Color.red);
        for (int j = 0; j <= 30; j++) {

            g.fillRect((x - 10) * rowWid, (y - 15 + j) * rowHt, rowWid * 20, rowHt);

        }
        g.setColor(Color.BLUE);
        for (int j = 0; j <= 19; j++) {
            g.fillRect((x - 3 - j) * rowWid, (y - 15 + j) * rowHt, rowWid * 7, rowHt);
        }
        g.setColor(Color.YELLOW);
        for (int j = 0; j <= 10; j++) {
            g.fillRect((x - 7 + j) * rowWid, (y + 16 + j) * rowHt, rowWid * 9, rowHt);
        }
        g.setColor(Color.YELLOW);
        for (int j = 0; j <= 10; j++) {
            g.fillRect((x - 2 - j) * rowWid, (y + 16 + j) * rowHt, rowWid * 9, rowHt);
        }
        for (int j = 0; j <= 13; j++) {
            g.fillRect((x - 13 - j) * rowWid, (y + 28 + j) * rowHt, rowWid * 9, rowHt);
        }
        for (int j = 0; j <= 13; j++) {
            g.fillRect((x + 5 + j) * rowWid, (y + 28 + j) * rowHt, rowWid * 9, rowHt);
        }
    
        g.fillRect((x + 18 ) * rowWid, (y + 42 ) * rowHt, rowWid * 11, rowHt * 2);
         g.fillRect((x -26 ) * rowWid, (y + 42 ) * rowHt, rowWid * 11, rowHt * 2);
        repaint();
    }

    void slep() //for delay
    {
        try {
            Thread.sleep(200);
        } catch (Exception ex) {
        }
    }

}


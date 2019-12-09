import javax.swing.JFrame;
import java.applet.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 *
 * @author Anjishnu
 */
public class robo {
    
    public static void main(String args[]){
        JFrame f = new JFrame();
        human h = new human();
        Color bgcolor = new Color(247, 207, 5);
        f.add(h);
        f.setVisible(true);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setSize(1270,720);
        f.getContentPane().setBackground(bgcolor);
    }

    
}
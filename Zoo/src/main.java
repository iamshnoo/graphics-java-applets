
import java.applet.*;
import java.awt.*;

/**
 *
 * @author Anjishnu
 */

@SuppressWarnings("serial")
public class main extends Applet implements Runnable {

    public int X, Y, gap; // keeping these as public variables instead of defining getX and getY
                          // functions.
    public human h; // keeping this as public variables also to avoid complications.
    private Thread t1;
    private boolean running;
    private Image dbImage; // double buffering Image
    private Graphics dbg; // double buffering Graphics
    private Color bgcolor;
    private Frame title;
    private tiger t;
    private lion l;
    private fish f;
    private water w;
    private bee b;
    private bee_pair b1;
    private beehive bh;
    private boolean bee_move; // This variable is needed for controlling the updates for bee 0.
    private boolean bee_1_move;// This variable is needed for controlling the updates for bee 1.
    private boolean fish_move; // This variable is needed for controlling the updates for fish.

    public main() {
        this.running = true;
    }

    @Override
    public void init() {

        title = (Frame) this.getParent().getParent();
        bgcolor = new Color(247, 207, 5);
        h = new human();
        b = new bee();
        b1 = new bee_pair();
        l = new lion();
        t = new tiger();
        f = new fish();
        bh = new beehive();
        w = new water();
        this.setSize(1270, 720);
        this.setBackground(bgcolor);
        this.gap = 4;
        this.X = (this.getX() + this.getWidth()) / 2;
        this.Y = (this.getY() + this.getHeight()) / 2;
        title.setTitle("A VISIT TO THE ZOO");
        h.setS(0.5);
    }

    @Override
    public void start() {
        if (t1 == null) {
            t1 = new Thread(this);
            t1.start();
        }
    }

    @Override
    public void destroy() {
        running = false;
        dbg.dispose();
    }

    @Override
    public void stop() {
        if (t1 != null) {
            t1.stop();
            t1 = null;
        }
        running = false;
    }

    @Override
    public void run() {

        // every 50 millisec it checks the while loop contents
        while (running) {
            try {
                repaint(); // calls paint() every 50 msec ; thus, have to use double buffering.
                h.update(this);
                l.update(h);
                t.update(h);

                // controlled updates for fish
                if (h.getS() == 1) {
                    fish_move = (h.getX() >= 140 && h.getY() <= -10 && h.getY() >= -140);
                } else {
                    fish_move = (h.getX() >= 300 && h.getY() <= -10 && h.getY() >= -140);
                }
                if (fish_move == true) {
                    f.update();
                } else if (f.getX() != 140) {
                    f.reset();
                }

                // controlled updates for bee 0
                if (h.getS() == 1) {
                    bee_move = (h.getX() == -80 && h.getY() <= 5 && h.getY() >= -140);
                } else {
                    bee_move = (h.getX() == -200 && h.getY() <= -10 && h.getY() >= -140);
                }
                if (bee_move == true) {
                    b.update();
                }

                // controlled updates for bee 1
                if (h.getS() == 1) {
                    bee_1_move = (h.getX() == -80 && h.getY() <= 5 && h.getY() >= -140);
                } else {
                    bee_1_move = (h.getX() == -200 && h.getY() <= -10 && h.getY() >= -140);
                }
                if (bee_1_move == true) {
                    b1.update();
                }

                Thread.sleep(50);// t1.sleep(50);
            } catch (InterruptedException e) {
                System.out.println("Thread error occurs for t1");
            }
        }
    }

    // For Double Buffering to avoid flickering, override the update method. (reason
    // explained below)
    // Calling run actually calls update first, which is supposed to clear the
    // screen of all contents, and then call paint.
    // We can either override the update method only to not clear the screen or
    // clear only a part of it,
    // or we can override both update and paint methods for double buffering.
    @Override
    public void update(Graphics g) {
        // create a copy of the screen
        dbImage = createImage(getWidth(), getHeight());

        // Creates a graphics context for drawing to an off-screen image.
        // This method can only be called for off-screen images.
        dbg = dbImage.getGraphics();
        paint(dbg);
        g.drawImage(dbImage, 0, 0, this);
    }

    @Override
    public void paint(Graphics g) {
        t.paint(g, this);
        l.paint(g, this);
        bh.paint(g, this);
        b.paint(g, this);
        b1.paint(g, this);
        w.paint(g, this);
        f.paint(g, this);
        h.paint(g);
    }

}

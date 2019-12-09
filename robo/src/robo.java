
import java.applet.*;
import java.awt.*;

/**
 *
 * @author Anjishnu
 */
public class robo extends Applet implements Runnable {

    public int X, Y, gap;  // keeping these as public variables instead of defining getX and getY functions.
    public human h;        // keeping this as public variables also to avoid complications.
    private Thread t1;
    private boolean running;
    private Image dbImage; // double buffering Image
    private Graphics dbg;  // double buffering Graphics
    private Color bgcolor;
    private Frame title;

    public robo() {
        this.running = true;
    }

    @Override
    public void init() {

        title = (Frame) this.getParent().getParent();
        bgcolor = new Color(247, 207, 5);
        h = new human();
        this.setSize(1270, 720);
        this.setBackground(bgcolor);
        this.gap = 4;
        this.X = (this.getX() + this.getWidth()) / 2;
        this.Y = (this.getY() + this.getHeight()) / 2;
        title.setTitle("ANJISHNU KA ROBOT");
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

        // every 20 millisec it checks the while loop contents
        while (running) {
            repaint();      // calls paint() every 20 msec ; thus, have to use double buffering.
            h.update_pos(this);

            try {
                t1.sleep(50);
            } catch (InterruptedException e) {
                System.out.println("Thread error occurs");
            }
        }
    }

    // For Double Buffering to avoid flickering, override the update method. (reason explained below)
    // Calling run actually calls update first, which is supposed to clear the screen of all contents, and then call paint.
    // We can either override the update method only to not clear the screen or clear only a part of it,
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
        h.paint_robo(g);
    }

}

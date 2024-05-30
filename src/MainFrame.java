import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame implements Runnable {
    private GraphicsPanel panel;

    public MainFrame(){
        JFrame frame = new JFrame("Platformer");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 790);
        frame.setLocationRelativeTo(null);

        panel = new GraphicsPanel();
        frame.add(panel);
        frame.setVisible(true);

        Thread thread1 = new Thread(this);
        thread1.start();


    }

    public void run(){
        while (true){
            panel.repaint();
            ThreadGravity threadG = new ThreadGravity("tG");
            threadG.start();
        }
    }
    class ThreadGravity extends Thread {
        private Thread t;
        private String threadName;

        ThreadGravity( String name) {
            threadName = name;
        }

        public void run() {
            try {
                for(int i = 4; i > 0; i--) {
                    Thread.sleep(50);
                }
            } catch (InterruptedException e) {
                System.out.println();
            }
            panel.gravity();
        }

        public void start () {
            if (t == null) {
                t = new Thread (this, threadName);
                t.start ();
            }
        }
    }
    class ThreadCollision extends Thread {
        private Thread t;
        private String threadName;

        ThreadCollision( String name) {
            threadName = name;
        }

        public void run() {
            try {
                for(int i = 4; i > 0; i--) {
                    Thread.sleep(50);
                }
            } catch (InterruptedException e) {
                System.out.println();
            }
            panel.gravity();
        }

        public void start () {
            if (t == null) {
                t = new Thread (this, threadName);
                t.start ();
            }
        }
    }


}

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame implements Runnable {
    private GraphicsPanel panel;

    public MainFrame(){
        JFrame frame = new JFrame("Platformer");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 800);
        frame.setLocationRelativeTo(null);

        panel = new GraphicsPanel();
        frame.add(panel);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);

        Thread threadMain = new Thread(this);
        threadMain.start();
        threadMain.setPriority(Thread.MAX_PRIORITY);


    }

    public void run(){
        while (true){
            panel.repaint();
            ThreadMovement threadM = new ThreadMovement("tM");
            threadM.setPriority(9);
            threadM.start();
            //ThreadGravity threadG = new ThreadGravity("tG");
            //threadG.start();
            ThreadCollision threadC = new ThreadCollision("tC");
            threadC.start();
            ThreadJumpAndDash threadJanD = new ThreadJumpAndDash("JanD");
            threadJanD.start();
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
            panel.Collision();
        }

        public void start () {
            if (t == null) {
                t = new Thread (this, threadName);
                t.start ();
            }
        }
    }
    class ThreadJumpAndDash extends Thread {
        private Thread t;
        private String threadName;

        ThreadJumpAndDash( String name) {
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
            panel.dashAndJumpCounter();
        }

        public void start () {
            if (t == null) {
                t = new Thread (this, threadName);
                t.start ();
            }
        }
    }
    class ThreadMovement extends Thread {
        private Thread t;
        private String threadName;

        ThreadMovement( String name) {
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
            panel.movement();
        }

        public void start () {
            if (t == null) {
                t = new Thread (this, threadName);
                t.start ();
            }
        }
    }

}

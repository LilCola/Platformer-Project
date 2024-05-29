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

        Thread thread1 = new Thread(this);
        thread1.start();

    }

    public void run(){
        while (true){
            panel.repaint();
        }
    }
    public void gravity() {
        panel.gravity();
        Thread thread2 = new Thread(this);
        thread2.start();
    }

}

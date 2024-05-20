import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame implements Runnable {
    private GraphicsPanel panel;

    public MainFrame(){
        JFrame frame = new JFrame("Intro to Animation");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 800);
        frame.setLocationRelativeTo(null);

        panel = new GraphicsPanel();
        frame.add(panel);
        frame.setVisible(true);

        Thread thread = new Thread(this);
        thread.start();
    }

    public void run(){
        while (true){
            panel.repaint();
        }
    }
}

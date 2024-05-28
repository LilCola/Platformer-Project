import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.geom.Rectangle2D;
import javax.swing.AbstractAction;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.Timer;

public class Test {
    public static void main(String[] args) {
        new Test();
    }

    public Test() {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                JFrame frame = new JFrame();
                frame.add(new TestPane());
                frame.pack();
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
            }
        });
    }

    public class TestPane extends JPanel {

        private double yDelta = 0;
        private double gravityDelta = 0.01;
        private double terminalVelocity = 4.0;

        private boolean isFalling = false;

        private Timer timer;

        private Rectangle2D player;

        public TestPane() {
            player = new Rectangle2D.Double(90, 0, 20, 20);

            getInputMap(WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0, false), "start");
            getActionMap().put("start", new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (isFalling) {
                        return;
                    }
                    yDelta = 0;
                    isFalling = true;
                    player = new Rectangle2D.Double(90, 0, 20, 20);
                }
            });
        }

        protected void doNextUpdate() {
            if (isFalling) {
                yDelta += gravityDelta;
                if (yDelta > terminalVelocity) {
                    yDelta = terminalVelocity;
                }

                player.setRect(player.getX(), player.getY() + yDelta, player.getWidth(), player.getHeight());
                if (player.getY() + player.getHeight() > getHeight()) {
                    yDelta = 0;
                    isFalling = false;
                    player.setRect(player.getX(), getHeight() - player.getHeight(), player.getWidth(), player.getHeight());
                }
            }

            repaint();
        }

        @Override
        public Dimension getPreferredSize() {
            return new Dimension(200, 400);
        }

        @Override
        public void addNotify() {
            super.addNotify();
            if (timer != null) {
                timer.stop();
            }
            timer = new Timer(5, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    doNextUpdate();
                }
            });
            timer.start();
        }

        @Override
        public void removeNotify() {
            super.removeNotify();
            if (timer != null) {
                timer.stop();
            }
            timer = null;
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g.create();
            g2d.setColor(Color.RED);
            g2d.fill(player);
            g2d.dispose();
        }

    }
}
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.*;

public class GraphicsPanel extends JPanel implements KeyListener, MouseListener{
    private BufferedImage baseKirbyImg;
    private BufferedImage starKirbyImg;
    private BufferedImage background;
    private double yDelta = 0;
    private Timer timer;
    private Player player = new Player("images/leftKirby.png", "images/rightKirby.png", "images/starLeft.png", "images/starRight.png");;
    private boolean[] pressedKeys;

    public GraphicsPanel(){
       /* try{
            background = ImageIO.read(new File("images/background.png"));
        }catch (IOException e){
            System.out.println(e.getMessage());
        }*/
        pressedKeys = new boolean[128];
        addKeyListener(this);
        addMouseListener(this);
        setFocusable(true);
        requestFocusInWindow();
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        //g.drawImage(background, 0, 0, null);
        g.drawImage(player.getPlayerImg(), player.getxCoord(), player.getyCoord(), null);



        if(pressedKeys[65]){
            player.faceLeft();
            player.moveLeft();
        }

        if(pressedKeys[68]){
            player.faceRight();
            player.moveRight();
        }

        if(pressedKeys[87]){
            player.moveUp();
        }

        if(pressedKeys[39]){
            player.faceRight();
            player.moveRight();
        }
        if(pressedKeys[37]){
            player.faceLeft();
            player.moveLeft();
        }

        if(pressedKeys[38]){
            player.moveUp();
        }
        if(pressedKeys[32]){
            player.dash();
        }


    }

    public void keyTyped(KeyEvent e){}

    public void keyPressed(KeyEvent e){
        int key = e.getKeyCode();
        pressedKeys[key] = true;
    }
    public void keyReleased(KeyEvent e){
        int key = e.getKeyCode();
        pressedKeys[key] = false;
    }
    public void gravity(){
        while(player.getIsFalling()) {
            double gravityDelta = 0.1;
            double terminalVelocity = 4;
            yDelta += gravityDelta;
            if (yDelta > terminalVelocity) {
                yDelta = terminalVelocity;
            }
            player.changeYCoord((player.getyCoord() + yDelta));
            if (player.getyCoord() + player.getPlayerImg().getHeight() > 750) {
                yDelta = 0;
                player.isFalling(false);
            }
        }
    }
    public void Collision(){}

    public void mouseClicked(MouseEvent e){}
    public void mousePressed(MouseEvent e){}
    public void mouseReleased(MouseEvent e){
    }
    public void mouseEntered(MouseEvent e){}
    public void mouseExited(MouseEvent e){}

}

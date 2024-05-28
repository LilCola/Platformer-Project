import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.awt.image.*;

public class GraphicsPanel extends JPanel implements KeyListener, MouseListener{
    private BufferedImage baseKirbyImg;
    private BufferedImage starKirbyImg;
    private BufferedImage background;
    private Player player;
    private boolean[] pressedKeys;

    public GraphicsPanel(){
       /* try{
            background = ImageIO.read(new File("images/background.png"));
        }catch (IOException e){
            System.out.println(e.getMessage());
        }*/
        player = new Player("images/leftKirby.png", "images/rightKirby.jpg", "images/starLeft.png", "images/starRight.png");
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
        //up w
        if(pressedKeys[87]){
            player.moveUp();
        }
        //down s
        if(pressedKeys[83]){
            player.gravity();
        }
        if(pressedKeys[39]){
            player.faceRight();
            player.moveRight();
        }
        if(pressedKeys[37]){
            player.faceLeft();
            player.moveLeft();
        }
        if(pressedKeys[40]){
            player.moveDown();
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

    public void mouseClicked(MouseEvent e){}
    public void mousePressed(MouseEvent e){}
    public void mouseReleased(MouseEvent e){
    }
    public void mouseEntered(MouseEvent e){}
    public void mouseExited(MouseEvent e){}

}

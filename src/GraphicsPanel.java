import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;

public class GraphicsPanel extends JPanel implements KeyListener, MouseListener, ActionListener {
    private BufferedImage baseKirbyImg;
    private BufferedImage starKirbyImg;
    private BufferedImage background;
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
        /*if(player.getIsFalling()){
            player.changeYCoord((double)player.getyCoord() + 1);
            if (player.getyCoord() + player.getPlayerImg().getHeight() > 750) {
                player.isFalling(false);
            }
        }*/
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
            player.moveUp();
            player.moveUp();
            player.moveUp();
            player.moveUp();
        }

        if(pressedKeys[32]){
            player.dash();
            player.dash();
            player.dash();
            player.changeCanDash(false);
            player.changeDashing(false);
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
        double yDelta = 0;
        if(player.getIsDashing()){
            player.isFalling(false);
        }
        if(player.getIsFalling()) {
            double gravityDelta = 1;
            yDelta += gravityDelta;
            player.changeYCoord((double)player.getyCoord() + yDelta);
            if (player.getyCoord() + player.getPlayerImg().getHeight() > 750) {
                yDelta =0;
                player.isFalling(false);
            }
        }
    }
    public void dashAndJumpCounter(){
        if(!player.getIsFalling() && !player.getIsDashing()){
            player.changeCanDash(true);
            player.changeCanJump(true);
        }
    }
    public void Collision(){

    }

    public void mouseClicked(MouseEvent e){}
    public void mousePressed(MouseEvent e){}
    public void mouseReleased(MouseEvent e){
    }
    public void mouseEntered(MouseEvent e){}
    public void mouseExited(MouseEvent e){}

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}

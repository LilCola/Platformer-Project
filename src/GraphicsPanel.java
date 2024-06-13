import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.util.ArrayList;

public class GraphicsPanel extends JPanel implements KeyListener, MouseListener, ActionListener {
    private BufferedImage baseKirbyImg;
    private BufferedImage starKirbyImg;
    private BufferedImage background;
    private Timer timer;
    private Player player = new Player("images/leftKirby.png", "images/rightKirby.png", "images/starLeft.png", "images/starRight.png");
    private ArrayList<Platform> platforms;
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
        platforms = new ArrayList<Platform>();


    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        //g.drawImage(background, 0, 0, null);
        Platform initalPlatform = new Platform("images/RopePlatform.png", 50, 600);
        platforms.add(initalPlatform );
        g.drawImage(player.getPlayerImg(), player.getxCoord(), player.getyCoord(), null);
        //g.drawRect(player.getxCoord(), player.getyCoord(), player.getPlayerImg().getWidth(), player.getPlayerImg().getHeight());
        if(player.getIsFalling()){
            player.changeYCoord((double)player.getyCoord() + 1);
            if (player.getyCoord() + player.getPlayerImg().getHeight() > 750) {
                player.isFalling(false);
            }
        }
        if(player.getyCoord()+player.getPlayerImg().getHeight() > 800){
            player.isFalling(false);
        }
        for(int i=0; i < platforms.size(); i++){
            Platform platform = platforms.get(i);
            g.drawImage(platform.getPlatformImg(), platform.getxCoord(), platform.getyCoord(), null);

        }
        if(player.getyCoord()+player.getPlayerImg().getHeight() >=790){
            player.isFalling(false);
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
        if(player.getIsFalling() && !player.getTouchingPlatform()) {
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
    public void movement(){
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
        }
    }
    public void Collision(){
        /*System.out.println(player.getyCoord());
        System.out.println(600);
        if(player.getPlayerRect().intersects(platforms.getPlatformRect())){
            player.isFalling(false);
        }
        if(!(player.getPlayerRect().intersects(platforms.getPlatformRect()))){
            player.isFalling(true);
        }*/
        for(int i=0; i < platforms.size(); i++){
            if (platforms.get(i).getPlatformRect().intersects(player.getPlayerRect())){
                player.changeTouchingPlatform(true);
                player.isFalling(false);
                player.changeYCoord(platforms.get(i).getyCoord() - player.getPlayerImg().getHeight());
                break;
            }else{
                player.changeTouchingPlatform(false);
            }
        }
    }

    public void mouseClicked(MouseEvent e){}
    public void mousePressed(MouseEvent e){}
    public void mouseReleased(MouseEvent e){
        if(e.getButton() == MouseEvent.BUTTON1){
            Point mouseClickLocation = e.getPoint();
            Platform platform = new Platform("images/RopePlatform.png", mouseClickLocation.x, mouseClickLocation.y);
            platforms.add(platform);
        }
    }
    public void mouseEntered(MouseEvent e){}
    public void mouseExited(MouseEvent e){}

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}

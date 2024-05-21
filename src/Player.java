import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
public class Player {
    private final double MOVE_AMT = 0.5;
    private BufferedImage right;
    private BufferedImage left;
    private BufferedImage dashingLeft;
    private BufferedImage dashingRight;
    private boolean facingRight;
    private boolean dashing;
    private double xCoord;
    private double yCoord;

    public Player(String leftImg, String rightImg, String dashLeft, String dashRight){
        facingRight = true;
        dashing = false;
        xCoord = 50;
        yCoord = 400;
        try{
            left = ImageIO.read(new File(leftImg));
            right = ImageIO.read(new File(rightImg));
            dashingLeft = ImageIO.read(new File(dashLeft));
            dashingRight = ImageIO.read(new File(dashRight));
        } catch (IOException e){
            System.out.println(e.getMessage());
        }
    }

    public int getxCoord(){
        return (int) xCoord;
    }
    public int getyCoord(){
        return (int) yCoord;
    }

    public void faceRight(){
        facingRight = true;
    }

    public void faceLeft(){
        facingRight = false;
    }

    public void dashing(){
        dashing = true;
    }

    public void moveRight(){
        if(xCoord + MOVE_AMT + getPlayerImg().getWidth() <= 1000){
            xCoord += MOVE_AMT;
        }
    }

    public void moveLeft(){
        if(xCoord - MOVE_AMT >= 0){
            xCoord -= MOVE_AMT;
        }
    }

    public void moveUp(){
        if(yCoord - MOVE_AMT >= 0){
            yCoord -= MOVE_AMT;
        }
    }
    public void moveDown(){
        if(yCoord + MOVE_AMT + getPlayerImg().getHeight() <= 800){
            yCoord += MOVE_AMT;
        }
    }
    public void dash(){
        if(facingRight){
            if(xCoord + 100 <= 1000){
                xCoord += 100;
            }
        }else {
            if (xCoord - 100 >= 0) {
                xCoord -= 100;
            }
        }
    }

    public BufferedImage getPlayerImg(){
        if(dashing && facingRight) {
            return dashingRight;
        }else if(dashing){
            return dashingLeft;
        }else if(facingRight){
            return right;
        }else{
            return left;
        }
    }

    public Rectangle getPlayerRect(){
        int imageHeight = getPlayerImg().getHeight();
        int imageWidth = getPlayerImg().getWidth();
        Rectangle rect = new Rectangle((int)xCoord, (int)yCoord, imageWidth, imageHeight);
        return rect;
    }
}

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
public class Player {
    private final double MOVE_AMT = 0.1;
    private BufferedImage right;
    private BufferedImage left;
    private BufferedImage dashingLeft;
    private BufferedImage dashingRight;
    private boolean facingRight;
    private boolean dashing;
    private boolean isFalling;
    private static boolean canDash;
    private static boolean canJump;
    private double yDelta = 0;
    private double xCoord;
    private double yCoord;

    public Player(String leftImg, String rightImg, String dashLeft, String dashRight){
        facingRight = true;
        dashing = false;
        isFalling = true;
        canDash = true;
        canJump = true;
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
    public void isFalling(boolean newBoolean){
        isFalling = newBoolean;
    }
    public boolean getIsFalling(){
        return isFalling;
    }
    public void changeYCoord(double yCoord){
        this.yCoord = yCoord;
    }
    public void changeCanDash(boolean canDash){
        this.canDash = canDash;
    }
    public void changeCanJump(boolean canJump){
        this.canJump = canJump;
    }


    public void moveRight(){
        if(xCoord + MOVE_AMT + getPlayerImg().getWidth() <= 1000){
            xCoord += MOVE_AMT;
        }
        dashing =false;
    }

    public void moveLeft(){
        if(xCoord - MOVE_AMT >= 0){
            xCoord -= MOVE_AMT;
        }
        dashing = false;
    }

    public void moveUp(){
        if(canJump){
            yCoord -= 4;
            yCoord -= 4;
            yCoord -= 4;
            yCoord -= 4;
            yCoord -= 4;
            yCoord -= 4;
            yCoord -= 4;
            yCoord -= 4;
            yCoord -= 4;
            yCoord -= 4;
            isFalling = true;
            canJump = false;
        }
        if(yCoord < 0){
            yCoord = 0;
        }
    }

    public void dash(){
        dashing  = true;
        if(canDash){
            if(facingRight){
                xCoord += 2;
                xCoord += 2;
                xCoord += 2;
                xCoord += 2;
                xCoord += 2;
                if(xCoord+getPlayerImg().getWidth() >= 1000){
                    xCoord = 1000 - getPlayerImg().getWidth();
                }
                canDash = false;
            }else{
                xCoord -= 2;
                xCoord -= 2;
                xCoord -= 2;
                xCoord -= 2;
                xCoord -= 2;
                if(xCoord < 0){
                    xCoord = 0;
                }
                canDash = false;
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
        return new Rectangle((int)xCoord, (int)yCoord, imageWidth, imageHeight);
    }
}

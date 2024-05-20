import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
public class Player {
    private double moveAmt;
    private BufferedImage right;
    private BufferedImage left;
    private BufferedImage dash;
    private boolean facingRight;
    private double xCoord;
    private double yCoord;

    public Player(String leftImg, String rightImg, String dashImg){
        facingRight =true;
        xCoord = 50;
        yCoord = 400;
        try{
            left = ImageIO.read(new File(leftImg));
            right = ImageIO.read(new File(rightImg));
            dash = ImageIO.read(new File(dashImg));
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
}

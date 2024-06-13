import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Platform {
    private BufferedImage image;
    private int xCoord;
    private int yCoord;
    public Platform(String platImage, int x, int y){
        xCoord = x;
        yCoord = y;
        try{
            image = ImageIO.read(new File(platImage));
        } catch (IOException e){
            System.out.println(e.getMessage());
        }
    }
    public int getxCoord(){return xCoord;}
    public int getyCoord(){return yCoord;}
    public BufferedImage getPlatformImg(){
        return image;
    }

    public Rectangle getPlatformRect(){
        int imageHeight = getPlatformImg().getHeight();
        int imageWidth = getPlatformImg().getWidth();
        return new Rectangle((int)xCoord, (int)yCoord, imageWidth, imageHeight);
    }
}

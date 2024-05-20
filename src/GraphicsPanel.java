import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.awt.image.*;

public class GraphicsPanel extends JPanel {
    private BufferedImage baseKirbyImg;
    private BufferedImage starKirbyImg;
    private double kirbyX;
    private double kirbyY;

    public GraphicsPanel(){
        kirbyX = 50;
        kirbyY = 50;
        try{
            baseKirbyImg = ImageIO.read(new File("images/baseKirby.jpg"));
            starKirbyImg = ImageIO.read(new File("images/starKirby.jpg"));
        }catch (IOException e){
            System.out.println(e.getMessage());
        }
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        /*kirbyX += 1;
        if(kirbyX + baseKirbyImg.getWidth() > 1000){
            kirbyX = 0;
        }
        kirbyY += .5;
        if(kirbyY + baseKirbyImg.getHeight() > 800){
            kirbyY = 0;
        }*/

        g.drawImage(baseKirbyImg, (int)kirbyX, (int)kirbyY, null);

    }


}

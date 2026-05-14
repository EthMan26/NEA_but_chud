package Backrounds;
import main.GamePanel;
import java.awt.image.BufferedImage;

public class background{
    public BufferedImage Image;
    public int[] currentBackgrounds;
    GamePanel gp;

    public int[] backgroundHolder(GamePanel gp){
        this.gp = gp;
        return currentBackgrounds;
    }


}

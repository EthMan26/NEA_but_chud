package sprites;
import main.GamePanel;
import main.Keys;
import Backrounds.backgroundManager;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class player extends entities{
    GamePanel gp;
    Keys key;
    backgroundManager BM;


    public player(GamePanel gp,Keys key,backgroundManager BM){
        this.gp = gp;
        this.key = key;
        this.BM =BM;
        defaultPlayer(gp.screenWidthMid-(gp.playerSize/2),gp.screenHeightMid-(gp.playerSize/2),5);
        getPlayerVisuals();
    }

    public void defaultPlayer(int x,int y,double Speed){
        this.x =x;
        this.y =y;
        this.Speed = Speed;
        direction = "down";
    }
    public void getPlayerVisuals(){
        try{
            up1 = ImageIO.read(getClass().getResourceAsStream("/player/up_1.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("/player/up_2.png"));
            up3 = ImageIO.read(getClass().getResourceAsStream("/player/up_1.png"));
            up4 = ImageIO.read(getClass().getResourceAsStream("/player/up_2.png"));
            left1 = ImageIO.read(getClass().getResourceAsStream("/player/left_1.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/player/left_2.png"));
            left3 = ImageIO.read(getClass().getResourceAsStream("/player/left_3.png"));
            left4 = ImageIO.read(getClass().getResourceAsStream("/player/left_4.png"));
            right1 = ImageIO.read(getClass().getResourceAsStream("/player/right_1.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/player/right_2.png"));
            right3 = ImageIO.read(getClass().getResourceAsStream("/player/right_3.png"));
            right4 = ImageIO.read(getClass().getResourceAsStream("/player/right_4.png"));
            down1 = ImageIO.read(getClass().getResourceAsStream("/player/down_1.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("/player/down_2.png"));
            dft = ImageIO.read(getClass().getResourceAsStream("/player/dft.png"));
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    public void update(){
        if (key.up || key.left || key.right || key.down) {
            //improper coordinate system
            if (key.up) {
                direction = "up";
                y -= Speed;
                gp.coordsY+=Speed;
            }
            if (key.down) {
                direction = "down";
                y += Speed;
                gp.coordsY-=Speed;
            }
            if (key.left) {
                direction = "left";
                x -= Speed;
                gp.coordsX-=Speed;
            }
            if (key.right) {
                direction = "right";
                x += Speed;
                gp.coordsX+=Speed;
            }
            //Player edge of screen check, will see if the player is at the edge of the screen, and will move the backround accordingly
            //will have to add collision checker prior in chronologicality
            //sticky movement
            //overcompliacted by adding the diff twice
            if (x < 100) {
                int diff = 100-x;
                x = 100;
                BM.playerHitLeft(diff);

            } else if (x > (gp.screenWidth - gp.playerSize) -100) {
                int diff = x - ((gp.screenWidth - gp.playerSize) - 100);
                x = (gp.screenWidth - gp.playerSize) -100;
                BM.playerHitRight(diff);
            }

            if (y < 100) {
                int diff = 100-y;
                y = 100;
                BM.playerHitTop(diff);

            } else if (y > (gp.screenHeight - gp.playerSize) -100) {
                int diff = y - ((gp.screenHeight - gp.playerSize) - 100);
                y = (gp.screenHeight - gp.playerSize) -100;
                BM.playerHitBottom(diff);
            }

            spriteCounter++;
            if (spriteCounter > 10) {
                if (spriteNum < 4) {
                    spriteNum++;
                } else if (spriteNum == 4) {
                    spriteNum = 1;
                }
                spriteCounter = 0;
            }
        } else {
            direction = "default";
        }
    }
    public void paintComponent(Graphics g2){

        BufferedImage img = null;

        switch (direction){
            case "up":
                if(spriteNum==1) {
                    img = up1;
                }if(spriteNum==2){
                    img = up2;
                }if(spriteNum==3){
                    img = up3;
                }if(spriteNum==4){
                    img = up4;
                }
                break;
            case "down":
                if(spriteNum==1) {
                    img = down1;
                }if(spriteNum==2) {
                    img = down2;
                }
                break;
            case "left":
                if(spriteNum==1) {
                    img = left1;
                }if(spriteNum==2){
                    img = left2;
                }if(spriteNum==3) {
                    img = left3;
                }if(spriteNum==4){
                    img = left4;
                }
                break;
            case "right":
                if(spriteNum==1) {
                    img = right1;
                }if(spriteNum==2){
                    img = right2;
                }if(spriteNum==3){
                    img = right3;
                }if(spriteNum==4){
                    img = right4;
                }
                break;
            case "default":
                img =dft;
                break;
        }
        g2.drawImage(img,x,y,gp.playerSize,gp.playerSize,null);
    }
}
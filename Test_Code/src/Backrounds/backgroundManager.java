package Backrounds;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;

public class backgroundManager {
    GamePanel gp;
    background[] Backgrounds;
    public int[] backgroundStats;
    public int[][] current;
    public int currentPointer = 0;
    public int backgroundID=0;
    int[] emptyArr= {-1,-1,-1,-1,-1,-1,-1};
    public BufferedImage WHITE;

    public backgroundManager(GamePanel gp){
        this.gp = gp;
        Backgrounds = new background[25000];
        current = new int[25000][8];
        for(int i=0;i<current.length;i++){
            for(int j=0;j<current[i].length;j++){
                current[i][j] = -1;
            }
        }
        getImg();
    }
    public void getImg(){
        try{
            WHITE = ImageIO.read(getClass().getResourceAsStream("/backgrounds/WHITE.png"));
            Backgrounds[0] = new background();
            Backgrounds[0].Image = ImageIO.read(getClass().getResourceAsStream("/backgrounds/water.png"));
            Backgrounds[1] = new background();
            Backgrounds[1].Image = ImageIO.read(getClass().getResourceAsStream("/backgrounds/path.png"));
            Backgrounds[2] = new background();
            Backgrounds[2].Image = ImageIO.read(getClass().getResourceAsStream("/backgrounds/bon.png"));
            Backgrounds[3] = new background();
            Backgrounds[3].Image = ImageIO.read(getClass().getResourceAsStream("/backgrounds/bottomLeftOverworld.png"));
            Backgrounds[4] = new background();
            Backgrounds[4].Image = ImageIO.read(getClass().getResourceAsStream("/backgrounds/bottomRightOverworld.png"));
            Backgrounds[5] = new background();
            Backgrounds[5].Image = ImageIO.read(getClass().getResourceAsStream("/backgrounds/topRightOverworld.png"));
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public int setCurrentBackgrounds(boolean add, int[] background){
        if(add){
            current[currentPointer] = background;
            currentPointer++;
            return currentPointer;
        }else{
            for (int j=0 ; j<current.length -1; j++){
                if(current[j] == background){
                    current[j] = emptyArr;
                    currentPointer = j;
                    break;
                }
            }//Sort the backgrounds
            boolean sorted=false;
            while(!sorted){
                if(current[currentPointer+1][0]==-1){
                    sorted = true;
                }else{
                    current[currentPointer] = current[currentPointer+1];
                    current[currentPointer+1] = emptyArr;
                    currentPointer++;
                }
            }
            return currentPointer;
        }
    }

    public int[][] getCurrentBackrounds(){
        return current;
    }

    public void playerHitLeft(int speed){
        for(int i=0;i<currentPointer;i++){
            current[i][2]= current[i][2] +speed;
        }

    }

    public void playerHitRight(int speed){
        for(int i=0;i<currentPointer;i++){
            current[i][2] = current[i][2] -speed;
        }
    }

    public void playerHitTop(int speed){
        for(int i=0;i<currentPointer;i++){
            current[i][3] = current[i][3] +speed;
        }
    }

    public void playerHitBottom(int speed){
        for(int i=0;i<currentPointer;i++){
            current[i][3] = current[i][3] -speed;
        }
    }
    public int[] makePersonalisedBackground(int img, int x, int y, int width, int height){
        int[] arr;
        arr = new int[6];
        arr[0] = backgroundID;
        backgroundID++;
        arr[1] = img;
        arr[2] = x;
        arr[3] = y;
        arr[4] = width;
        arr[5] = height;
        return arr;
    }

    public void draw(Graphics2D g2, int[] identifier){
        int x = identifier[2];
        int y = identifier[3];
        int width = identifier[4];
        int height = identifier[5];
        g2.drawImage(Backgrounds[identifier[1]].Image,x,y,width,height,null);
    }
}
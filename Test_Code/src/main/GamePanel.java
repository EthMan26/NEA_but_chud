package main;

import Backrounds.backgroundManager;
import sprites.player;
import java.awt.*;
import javax.swing.JPanel;

public class GamePanel extends JPanel implements Runnable{
    public int playerSize =  86;
    public int screenWidth = 896;
    public int screenWidthMid = screenWidth /2;
    public int screenHeight = 672;
    public int screenHeightMid = screenHeight /2;
    int[] holdingArr= new int[6];
    Keys keyused = new Keys();
    Thread gameTime;
    backgroundManager BM = new backgroundManager(this);
    public int coordsX, coordsY = 0;
    double START_TIME = System.nanoTime();


    public int FPS =60;
    player you= new player(this,keyused, BM);


    public GamePanel(){
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.white);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyused);
        this.setFocusable(true);
    }

    public void start(){
        gameTime = new Thread(this);
        gameTime.start();
    }

    @Override
    public void run() {

        double drawTimeChange = 1000000000/FPS;
        double delta =0;
        long lastTime = System.nanoTime();
        long currentTime;
        int backgroundMult = 4;
        holdingArr = BM.makePersonalisedBackground(5,screenWidthMid,screenHeightMid-(2700*backgroundMult),2700*backgroundMult,2700*backgroundMult);
        BM.currentPointer = BM.setCurrentBackgrounds(true,holdingArr);
        holdingArr = BM.makePersonalisedBackground(4,screenWidthMid,screenHeightMid,2700*backgroundMult,2700*backgroundMult);
        BM.currentPointer = BM.setCurrentBackgrounds(true,holdingArr);
        holdingArr = BM.makePersonalisedBackground(2,0,0,screenWidth,300);
        BM.currentPointer = BM.setCurrentBackgrounds(true,holdingArr);
        holdingArr = BM.makePersonalisedBackground(2,-1000,-1000,screenWidth,screenHeight);
        BM.currentPointer = BM.setCurrentBackgrounds(true,holdingArr);
        holdingArr = BM.makePersonalisedBackground(0,-200,200,5,screenHeightMid - (holdingArr[1] /2));
        BM.currentPointer = BM.setCurrentBackgrounds(true,holdingArr);



        while(gameTime != null){
            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawTimeChange;
            lastTime = currentTime;
            if(delta >= 1){
                update();
                repaint();
                delta --;
            }
        }
    }
    public void update(){
        //ensure the getscreenWidth etc are still relevant
        screenWidth = this.getWidth();
        screenHeight = this.getHeight();
        screenWidthMid = screenWidthMid/2;
        screenHeightMid = screenHeightMid/2;
        you.update();
    }
    public void paintComponent(Graphics g){
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D)g;
        // paint the background
        for(int i=0;i<BM.currentPointer;i++){
            BM.draw(g2,BM.current[i]);
        }

        //paint the entities incl the player
        you.paintComponent(g2);

        //paint effects on screen above the entities and behind the outer info

        // using the paint component I have added the coordinates in the top left of the screen with a white background behind them
        //It is here so it is always visible to the player for where they are
        //and also how long the game has been going on dependet on speedrun mode
        g2.drawImage(BM.WHITE,0,0,250,55,null);
        g2.setColor(Color.CYAN);
        g2.setFont(new Font("Ink Free", Font.BOLD, 20));
        g2.drawString("COORDS: ("+coordsX/25+" , "+coordsY/25+")",15,30);

        g2.drawImage(BM.WHITE,screenWidth-250,0,250,55,null);
        g2.setColor(Color.CYAN);
        g2.setFont(new Font("Ink Free", Font.BOLD, 20));
        long displayTime = (long) ((System.nanoTime() - START_TIME)/10000000);
        int hundreths,seconds,minutes;
        hundreths = (int) displayTime%100;
        seconds = (int) displayTime / 100;
        minutes = seconds / 60;
        while(seconds>59){
            seconds -= 60;
        }
        g2.drawString("TIME: "+minutes+" : "+seconds+" : "+hundreths,screenWidth -200,30);
        g2.dispose();
    }
}
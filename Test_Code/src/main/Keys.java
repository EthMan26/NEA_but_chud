package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Keys implements KeyListener {
    public boolean up;
    public boolean down;
    public boolean right;
    public boolean left;
    public boolean jump;

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_W || key== KeyEvent.VK_UP){
            up =true;
        }
        if (key == KeyEvent.VK_A || key== KeyEvent.VK_LEFT){
            left=true;
        }
        if (key == KeyEvent.VK_S || key== KeyEvent.VK_DOWN){
             down=true;
        }
        if (key == KeyEvent.VK_D || key== KeyEvent.VK_RIGHT){
            right=true;
        }if (key == KeyEvent.VK_ESCAPE){
            System.exit(73291);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_W || key== KeyEvent.VK_UP){
            up =false;
        }
        if (key == KeyEvent.VK_A || key== KeyEvent.VK_LEFT){
            left=false;
        }
        if (key == KeyEvent.VK_S || key== KeyEvent.VK_DOWN){
            down=false;
        }
        if (key == KeyEvent.VK_D || key== KeyEvent.VK_RIGHT){
            right=false;
        }
    }
}
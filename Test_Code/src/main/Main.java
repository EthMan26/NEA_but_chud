package main;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        BufferedImage dft = null;
        try {
            dft = ImageIO.read(Main.class.getResourceAsStream("/player/up_1.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(true);
        window.setTitle("Whimsy and fun");
        window.setIconImage(dft);


        GamePanel gameP = new GamePanel();
        window.add(gameP);

        window.pack();

        window.setLocationRelativeTo(null);
        window.setVisible(true);
        gameP.start();
    }
}
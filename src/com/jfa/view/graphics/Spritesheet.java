package com.jfa.view.graphics;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Spritesheet {

    private BufferedImage spritesheet;

    public Spritesheet(String caminho){
        try {
            spritesheet = ImageIO.read(getClass().getResource(caminho));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public BufferedImage pegaSprite(int x, int y, int width, int height){
        return spritesheet.getSubimage(x,y,width,height);
    }

}

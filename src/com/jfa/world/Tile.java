package com.jfa.world;

import com.jfa.main.Game;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Tile {

    public static BufferedImage TILE_CHAO= Game.spritesheet.pegaSprite(64, 256,64,64);
    public static BufferedImage TILE_CHAO_BORDA= Game.spritesheet.pegaSprite(64, 256,64,64);
    public static BufferedImage TILE_PAREDE_CIMA= Game.spritesheet.pegaSprite(128, 256,64,64);
    public static BufferedImage TILE_LAPIDE= Game.spritesheet.pegaSprite(0, 256,64,64);
    public static BufferedImage TILE_CANTO_ESQUERDA= Game.spritesheet.pegaSprite(192, 256,64,64);
    public static BufferedImage TILE_CANTO_DIREITA= Game.spritesheet.pegaSprite(256, 256,64,64);
    public static BufferedImage TILE_PAREDE_VERTICAL= Game.spritesheet.pegaSprite(320, 256,64,64);
    public static BufferedImage TILE_CHAO_CHIQUE= Game.spritesheet.pegaSprite(384, 256,64,64);
    public static BufferedImage TILE_CHAO_CHIQUERRIMO= Game.spritesheet.pegaSprite(448, 256,64,64);

    private BufferedImage sprite;
    private int x,y;

    public Tile(int x, int y, BufferedImage sprite){
        this.x=x;
        this.y=y;
        this.sprite=sprite;

    }

    public void render(Graphics g){
        g.drawImage(sprite,x - Camera.x ,y- Camera.y,null);
    }
}

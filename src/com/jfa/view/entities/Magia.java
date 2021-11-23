package com.jfa.view.entities;

import com.jfa.view.main.Game;
import com.jfa.view.world.Camera;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Magia extends Entidade{

    private int dx, dy;
    private double vleocidad=3.5;
    private int dir =1;
    private int direita__dir =1, esquerda__dir= 2;
    private boolean mexeu= true;
    private int maxFrames = 9,frames =0, index=0, maxIndex=1;
    private BufferedImage[] magia_esq;
    private BufferedImage[] magia_dir;
    private int duracao = 150, durAtual= 0;

    public Magia(int x, int y, int width, int height, BufferedImage sprite, int dx, int dy) {
        super(x, y, width, height, sprite);
        magia_dir = new BufferedImage[2];
        magia_esq = new BufferedImage[2];
        magia_dir[0]= Game.spritesheet.pegaSprite(256+ 64, 128, 64, 64);
        magia_dir[1]= Game.spritesheet.pegaSprite(256+ 128, 128, 64, 64);
        magia_esq[0]= Game.spritesheet.pegaSprite(256+ 128+64, 128+64, 64, 64);
        magia_esq[1]= Game.spritesheet.pegaSprite(256+ 128, 128+64, 64, 64);
        this.dx = dx;
        this.dy= dy;
    }

    public void tick(){
        if(dx==1) {
            dir = direita__dir;
        }else if(dx< 0){
            dir = esquerda__dir;
        }
        x+=dx*vleocidad;
        y+=dy*vleocidad;
        frameRate();
        durAtual++;
        if(durAtual==duracao){
            Game.magias.remove(this);
            Game.entidades.remove(this);
            return;
        }
    }

    private void frameRate(){
        if (mexeu) {
            frames++;
            if (frames == maxFrames) {
                frames = 0;
                index++;
                if (index > maxIndex)
                    index = 0;
            }
        }
    }

    public void render(Graphics g) {
        if (dir == direita__dir)
            g.drawImage(magia_dir[index], this.getX() - Camera.x , this.getY() - Camera.y, null);
        else if (dir == esquerda__dir)
            g.drawImage(magia_esq[index], this.getX() - Camera.x, this.getY()- Camera.y, null);
    }
}

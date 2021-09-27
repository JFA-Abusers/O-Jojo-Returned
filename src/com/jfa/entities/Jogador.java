package com.jfa.entities;

import com.jfa.main.Game;
import com.jfa.world.Camera;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Jogador extends Entidade{

    public boolean cima,embaixo,esquerda,direita;
    public double vleocidad= 3.0;
    private boolean mexeu =false;

    private int frames = 0, maxFrames=6, index =0, maxIndex=4;
    private BufferedImage[] manoDireita;
    private BufferedImage[] manoEsquerda;
    private BufferedImage[] manoCima;
    private BufferedImage[] manoBaixo;
    public int direita__dir=0, esquerda__dir=1,cima__dir=2,baixo__dir=3,dir=0;

    public Jogador(int x, int y, int width, int height, BufferedImage sprite) {
        super(x, y, width, height, sprite);
        manoDireita = new BufferedImage[5];
        manoEsquerda = new BufferedImage[5];
        manoCima = new BufferedImage[5];
        manoBaixo = new BufferedImage[5];
        manoDireita[0]= Game.spritesheet.pegaSprite(576, 0,64,64);
        manoDireita[1]= Game.spritesheet.pegaSprite(256, 64,64,64);
        manoDireita[2]= Game.spritesheet.pegaSprite(320, 64,64,64);
        manoDireita[3]= Game.spritesheet.pegaSprite(384, 64,64,64);
        manoDireita[4]= Game.spritesheet.pegaSprite(576, 64,64,64);

        manoEsquerda[0]= Game.spritesheet.pegaSprite(0, 128,64,64);
        manoEsquerda[1]= Game.spritesheet.pegaSprite(0, 64,64,64);
        manoEsquerda[2]= Game.spritesheet.pegaSprite(64, 64,64,64);
        manoEsquerda[3]= Game.spritesheet.pegaSprite(128, 64,64,64);
        manoEsquerda[4]= Game.spritesheet.pegaSprite(192, 64,64,64);

        manoCima[0]= Game.spritesheet.pegaSprite(384, 0,64,64);
        manoCima[1]= Game.spritesheet.pegaSprite(448, 0,64,64);
        manoCima[2]= Game.spritesheet.pegaSprite(512, 0,64,64);
        manoCima[3]= Game.spritesheet.pegaSprite(448, 64,64,64);
        manoCima[4]= Game.spritesheet.pegaSprite(512, 64,64,64);

        manoBaixo[0]= Game.spritesheet.pegaSprite(64, 0,64,64);
        manoBaixo[1]= Game.spritesheet.pegaSprite(128, 0,64,64);
        manoBaixo[2]= Game.spritesheet.pegaSprite(192, 0,64,64);
        manoBaixo[3]= Game.spritesheet.pegaSprite(256, 0,64,64);
        manoBaixo[4]= Game.spritesheet.pegaSprite(320, 0,64,64);


    }

    public void tick(){
        mexeu=false;
        if(direita) {
            mexeu=true;
            x += vleocidad;
            dir = direita__dir;


        } if(esquerda) {
            mexeu=true;
            x -= vleocidad;
            dir = esquerda__dir;
        }
        if(cima) {
            mexeu=true;
            y -= vleocidad;
            dir = cima__dir;
        }else if(embaixo) {
            mexeu=true;
            y += vleocidad;
            dir= baixo__dir;
        }

        if(mexeu){
            frames++;
            if(frames== maxFrames){
                frames=0;
                index++;
                if(index>maxIndex)
                    index=0;
            }
        }
        Camera.x = this.getX() - (Game.WIDTH/2);
        Camera.y = this.getY() - (Game.HEIGHT/2);
    }

    public void render(Graphics g){
        if(dir==direita__dir)
            g.drawImage(manoDireita[index],this.getX() - Camera.x ,this.getY() - Camera.y,null);
        else if(dir==esquerda__dir)
            g.drawImage(manoEsquerda[index],this.getX() - Camera.x,this.getY()- Camera.y,null);
        else if(dir==cima__dir)
            g.drawImage(manoCima[index],this.getX() - Camera.x,this.getY() - Camera.y,null);
        else if(dir==baixo__dir)
            g.drawImage(manoBaixo[index],this.getX() - Camera.x,this.getY() - Camera.y,null);
    }
}

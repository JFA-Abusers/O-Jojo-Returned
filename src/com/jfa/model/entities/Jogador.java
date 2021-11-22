package com.jfa.model.entities;

import com.jfa.view.world.Camera;
import com.jfa.view.world.Mundo;
import com.jfa.view.main.Game;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Jogador extends Entidade{

    public boolean cima,embaixo,esquerda,direita;
    public double velocidade = 3.0;
    public boolean mexeu =false;

    private int frames = 0, maxFrames=6, index =0, maxIndex=4;
    private BufferedImage[] manoDireita;
    private BufferedImage[] manoEsquerda;
    private BufferedImage[] manoCima;
    private BufferedImage[] manoBaixo;
    public int direita__dir=0, esquerda__dir=1,cima__dir=2,baixo__dir=3,dir=0;
    public static double vida = 100, vidaMaxima= 100;

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
        movimentacaoDoPlayer();
        if(Jogador.vida<=0){
            Game.ESTADO_DO_JOGO="PERDEU";
        }
    }

    public void tickCutscene(){
        if(this.getX()<1000){
            dir=direita__dir;
            mexeu=true;
            x+=velocidade;
        }else if(this.getX()>=1000 && this.getY()<1000){
            dir=baixo__dir;
            mexeu=true;
            y+=velocidade;
        }
        if(this.getX()>=1000 && this.getY()>=1000) {
            mexeu = false;
            Game.ESTADO_DO_JOGO = "NORMAL";
            index=0;
        }
    }

    public void renderCutscene(Graphics g){
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(new Color(0,0,0,100));
        g2.fillRect(0,0, Game.WIDTH*Game.SCALE, Game.HEIGHT*Game.SCALE);
    }

    private void movimentacaoDoPlayer(){
        if(direita && Mundo.taLivre((int)(x+ velocidade),(int)y)) {
            mexeu=true;
            x += velocidade;
            dir = direita__dir;


        } if(esquerda && Mundo.taLivre((int)(x- velocidade), (int)y)) {
            mexeu=true;
            x -= velocidade;
            dir = esquerda__dir;
        }
        if(cima && Mundo.taLivre((int)x, (int)(y- velocidade))) {
            mexeu=true;
            y -= velocidade;
            dir = cima__dir;
        }else if(embaixo && Mundo.taLivre((int)x, (int)(y+ velocidade))) {
            mexeu=true;
            y += velocidade;
            dir= baixo__dir;
        }
    }

    private void frameRate(){
        if(mexeu){
            frames++;
            if(frames== maxFrames){
                frames=0;
                index++;
                if(index>maxIndex)
                    index=0;
            }
        }
    }

    public void render(Graphics g){
        Camera.x = Camera.Clamp(this.getX() - (Game.WIDTH/2),0, Mundo.WIDTH*64 - Game.WIDTH);
        Camera.y =Camera.Clamp(this.getY() - (Game.HEIGHT/2),0,Mundo.HEIGHT*64 - Game.HEIGHT);
        frameRate();
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

package com.jfa.entities;

import com.jfa.main.Game;
import com.jfa.world.Mundo;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Inimigo extends Entidade {

    public int direita__dir = 0, esquerda__dir = 1, cima__dir = 2, baixo__dir = 3, dir = 0;

    private boolean mexeu = true;
    private double velocidade = 3.0;

    private int frames = 0, maxFrames = 9, index = 0, maxIndex = 2;
    private BufferedImage[] inimigo_esq;
    private BufferedImage[] inimigo_dir;

    public Inimigo(int x, int y, int width, int height, BufferedImage sprite) {
        super(x, y, width, height, sprite);
        inimigo_dir = new BufferedImage[3];
        inimigo_esq = new BufferedImage[3];
        for (int i = 0; i < 3; i++) {
            inimigo_esq[i] = Game.spritesheet.pegaSprite(i * 64, 192, 64, 64);
            inimigo_dir[i] = Game.spritesheet.pegaSprite((i * 64) + 192, 192, 64, 64);
        }
    }


    public void tick() {
        if((int)x < Game.jogador.getX() && Mundo.taLivre((int)(x+velocidade), this.getY())){
            x+=velocidade;
        }else if ((int)x > Game.jogador.getX() && Mundo.taLivre((int)(x-velocidade), this.getY())){
            x-=velocidade;
        }
        if((int)y < Game.jogador.getY() && Mundo.taLivre(this.getX(), (int)(y+velocidade))){
            y+=velocidade;
        }else if ((int)y > Game.jogador.getY() && Mundo.taLivre(this.getX(), (int)(y-velocidade))){
            y-=velocidade;
        }
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
            g.drawImage(inimigo_dir[index], this.getX(), this.getY(), null);
        else if (dir == esquerda__dir)
            g.drawImage(inimigo_esq[index], this.getX(), this.getY(), null);
    }
}
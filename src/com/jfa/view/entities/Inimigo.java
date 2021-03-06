package com.jfa.view.entities;

import com.jfa.view.world.Camera;
import com.jfa.view.world.Mundo;
import com.jfa.view.main.Game;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Inimigo extends Entidade {

    public int direita__dir = 0, esquerda__dir = 1, cima__dir = 2, baixo__dir = 3, dir = 0;

    private boolean mexeu = true;
    private double velocidade = 1.0;
    private double life;

    private int frames = 0, maxFrames = 9, index = 0, maxIndex = 2;
    private BufferedImage[] inimigo_esq;
    private BufferedImage[] inimigo_dir;

    public Inimigo(int x, int y, int width, int height, BufferedImage sprite, double life) {
        super(x, y, width, height, sprite);
        inimigo_dir = new BufferedImage[3];
        inimigo_esq = new BufferedImage[3];
        for (int i = 0; i < 3; i++) {
            inimigo_esq[i] = Game.spritesheet.pegaSprite(i * 64, 192, 64, 64);
            inimigo_dir[i] = Game.spritesheet.pegaSprite((i * 64) + 192, 192, 64, 64);
        }
        this.life= life;
    }


    public void tick() {
        if(!taBatendoNoPlayer()){
            movimentacaoDoInimigo();
            frameRate();
        }else{
            if(Game.rand.nextInt(100)<10) {
                Jogador.vida--;
            }
        }
        tomouBala();
        if(life<=0){
            autoDestruir();
            Game.jogador.pontos++;
            return;
        }
    }

    private void autoDestruir() {
        Game.entidades.remove(this);
        Game.inimigos.remove(this);
    }

    private void tomouBala(){
        for(int i =0; i< Game.entidades.size(); i++){
            Entidade e = Game.entidades.get(i);
            if(e instanceof Magia){
                if(Entidade.taBatendo(this,e)){
                    life--;
                    Game.entidades.remove(e);
                    Game.magias.remove(e);
                    return;
                }
            }
        }
    }

    private void movimentacaoDoInimigo(){
        if ((int) x < Game.jogador.getX() && Mundo.taLivre((int) (x + velocidade), this.getY()) && !taBatendoNele((int) (x + velocidade), this.getY())) {
            x += velocidade;
            dir = direita__dir;
        } else if ((int) x > Game.jogador.getX() && Mundo.taLivre((int) (x - velocidade), this.getY()) && !taBatendoNele((int) (x - velocidade), this.getY())) {
            x -= velocidade;
            dir = esquerda__dir;
        }
        if ((int) y < Game.jogador.getY() && Mundo.taLivre(this.getX(), (int) (y + velocidade)) && !taBatendoNele(this.getX(), (int) (y + velocidade))) {
            y += velocidade;
        } else if ((int) y > Game.jogador.getY() && Mundo.taLivre(this.getX(), (int) (y - velocidade)) && !taBatendoNele(this.getX(), (int) (y - velocidade))) {
            y -= velocidade;
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

    private boolean taBatendoNoPlayer(){
        Rectangle inimigo = new Rectangle(this.getX(),this.getY(),20,30);
        Rectangle jogador = new Rectangle(Game.jogador.getX(),Game.jogador.getY(),20,30);
        return inimigo.intersects(jogador);
    }

    public boolean taBatendoNele (int proximoX,int proximoY){
        Rectangle inimigoAtual = new Rectangle(proximoX,proximoY,20,30);

        for (int i = 0; i<Game.inimigos.size(); i++){
            Inimigo e = Game.inimigos.get(i);
            if(e == this)
                continue;
            Rectangle inimigoAlvo = new Rectangle(e.getX(),e.getY(),20,30);
            if(inimigoAtual.intersects(inimigoAlvo)){
                return true;
            }
        }

        return false;
    }

    public void render(Graphics g) {
        if (dir == direita__dir)
            g.drawImage(inimigo_dir[index], this.getX() - Camera.x , this.getY() - Camera.y, null);
        else if (dir == esquerda__dir)
            g.drawImage(inimigo_esq[index], this.getX() - Camera.x, this.getY()- Camera.y, null);
    }
}
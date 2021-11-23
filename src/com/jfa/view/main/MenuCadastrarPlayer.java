package com.jfa.view.main;

import java.awt.*;

public class MenuCadastrarPlayer {
    private String nome = "";

    public String getNome() {
        return nome;
    }

    public void setNome(char caracter) {
        nome+=caracter;
    }
    public void setNome(String caracter, int nada) {
        this.nome=caracter;
    }

    public void render(Graphics g){
        g.setColor(new Color(0,0,0,50));
        g.fillRect(0,0,Game.WIDTH*Game.SCALE, Game.HEIGHT*Game.SCALE);
        g.setColor(Color.white);
        g.setFont(new Font("Times New Roman", Font.BOLD,50));
        g.drawString("DIGITE O NOME DO TEU PLAYER",Game.WIDTH*Game.SCALE/2-240 ,90);
        g.setFont(new Font("Times New Roman", Font.BOLD,20));
        g.drawString("Nome : "+this.nome,Game.WIDTH*Game.SCALE/2-70 ,130);
    }
}

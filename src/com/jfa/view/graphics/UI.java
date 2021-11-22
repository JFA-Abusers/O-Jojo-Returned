package com.jfa.view.graphics;

import com.jfa.view.entities.Jogador;

import java.awt.*;

public class UI {

    public void render(Graphics g){
        g.setColor(Color.green);
        g.fillRect(550,5, (int)((Jogador.vida/Jogador.vidaMaxima)*80),15);
        if(Jogador.vida<Jogador.vidaMaxima) {
            g.setColor(Color.red);
            g.fillRect(550+(int)((Jogador.vida/Jogador.vidaMaxima)*80), 5, 3, 15);
        }
    }
}

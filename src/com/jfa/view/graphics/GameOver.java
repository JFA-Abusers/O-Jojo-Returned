package com.jfa.view.graphics;

import com.jfa.view.main.Game;

import java.awt.*;

public class GameOver {

    public void render(Graphics g){
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(new Color(0,0,0,100));
        g2.fillRect(0,0, Game.WIDTH*Game.SCALE, Game.HEIGHT*Game.SCALE);
        g.setFont(new Font("Comic Sans MS", Font.BOLD,100));
        g.setColor(Color.white);
        g.drawString("GAME OVER",370, (Game.HEIGHT*Game.SCALE)/2);
        g.setFont(new Font("Comic Sans MS", Font.BOLD,30));
        g.setColor(Color.white);
        g.drawString("Aperte ENTER para continuar...",370, (Game.HEIGHT*Game.SCALE)/2+45);
    }

}

package com.jfa.view.graphics;

import com.jfa.view.main.Game;

import java.awt.*;

public class Waves {

    public void render(Graphics g){
        g.setFont(new Font("Times New Roman", Font.BOLD,15));
        g.setColor(Color.white);
        g.drawString("WAVE "+Game.waves,260, 20);
    }

}

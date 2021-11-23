package com.jfa.view.main;

import com.jfa.controller.SaveController;
import com.jfa.model.SaveModel;

import java.awt.*;
import java.util.List;

public class MenuEscolherSave {

    public List<SaveModel> saves;
    private SaveController saveController;

    public int opcaoAtual =0;
    public int opcoesTotais = saves.toArray().length-1;

    public MenuEscolherSave(){
        saveController = new SaveController();
        saves = saveController.pegaSaves();
    }

    public void render(Graphics g){
        g.setColor(new Color(0,0,0,50));
        g.fillRect(0,0,Game.WIDTH*Game.SCALE, Game.HEIGHT*Game.SCALE);
        g.setColor(Color.white);
        int x =2;
        for(SaveModel s : saves){
            g.setFont(new Font("Times New Roman", Font.BOLD,20));
            g.drawString(s.getNome(),Game.WIDTH*Game.SCALE/2-70 ,50+40*x);
            x++;
        }
    }
}

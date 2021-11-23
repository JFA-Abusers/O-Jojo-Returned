package com.jfa.view.main;

import com.jfa.controller.SaveController;

import java.awt.*;

public class Menu {

    public boolean sobe, desce, enter;
    public static boolean temJogador= false;
    SaveController saveController;

    public String[] options = {
            "Novo jogo",
            "Novo jogador",
            "Salvar jogo",
            "Carregar jogo",
            "Leaderboards",
            "Sair"
    };

    public int opcaoAtual = 0;
    public int opcoesTotais = options.length-1;

    public void tick(){
        if(sobe){
            sobe=false;
            opcaoAtual--;
            if(opcaoAtual< 0){
                opcaoAtual = opcoesTotais;
            }
        }else if(desce){
            desce= false;
            opcaoAtual++;
            if(opcaoAtual>opcoesTotais){
                opcaoAtual = 0;
            }
        }
        if(enter){
            enter=false;
            System.out.println("entrou no if");
            if(options[opcaoAtual]=="Novo jogo"){
                System.out.println("nvoo jogow");
                Game.resetar = true;
            }
            if(options[opcaoAtual]=="Novo jogador"){
                System.out.println("nvoo jogow");
                Game.temJogador=false;
            }
            if(options[opcaoAtual]=="Salvar jogo"){
                System.out.println("salvo");
                saveController = new SaveController();
                saveController.inserirSave(Game.jogador.id);
            }
        }
    }

    public void render(Graphics g){
        g.setColor(new Color(0,0,0,50));
        g.fillRect(0,0,Game.WIDTH*Game.SCALE, Game.HEIGHT*Game.SCALE);
        g.setColor(Color.white);
        g.setFont(new Font("Times New Roman", Font.BOLD,50));
        g.drawString("O JOJO: RETURNeD.",Game.WIDTH*Game.SCALE/2-240 ,90);
        g.setFont(new Font("Times New Roman", Font.BOLD,20));
        g.drawString("NEW GAME",Game.WIDTH*Game.SCALE/2-70 ,130);
        g.drawString("NOVO PLAYER", Game.WIDTH * Game.SCALE / 2 - 80, 170);
        g.setFont(new Font("Times New Roman", Font.BOLD, 20));
        g.setFont(new Font("Times New Roman", Font.BOLD, 20));
        g.drawString("SALVAR", Game.WIDTH * Game.SCALE / 2 - 60, 210);
        g.setFont(new Font("Times New Roman", Font.BOLD, 20));
        //g.drawString("CARREGAR", Game.WIDTH * Game.SCALE / 2 - 71, 250);
        //g.setFont(new Font("Times New Roman", Font.BOLD, 20));
        //g.drawString("PLACAR DE LIDERANÇA", Game.WIDTH * Game.SCALE / 2 - 120, 290);
        //g.setFont(new Font("Times New Roman", Font.BOLD,20));
        //g.drawString("SAIR",Game.WIDTH*Game.SCALE/2-40 ,330);
        if(options[opcaoAtual]== "Salvar jogo"){
            g.drawString(">",Game.WIDTH*Game.SCALE/2-75 ,210);
        }


        if(options[opcaoAtual]== "Novo jogador"){
            g.drawString(">",Game.WIDTH*Game.SCALE/2-95 ,170);
        }
        if(options[opcaoAtual]== "Novo jogo"){
            g.drawString(">",Game.WIDTH*Game.SCALE/2-85 ,130);
        }
    }
}

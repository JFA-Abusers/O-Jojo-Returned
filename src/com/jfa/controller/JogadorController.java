package com.jfa.controller;

import com.jfa.dao.JogadorDAO;
import com.jfa.dao.SaveDAO;
import com.jfa.model.JogadorModel;
import com.jfa.model.SaveModel;
import com.jfa.view.entities.Jogador;
import com.jfa.view.main.Game;

import java.util.List;

public class JogadorController {

    public void RegistrarPlayer(String nome){
        int playerId;
        JogadorDAO joDao = new JogadorDAO();
        SaveDAO saveDAO = new SaveDAO();
        JogadorModel jogadorModel = new JogadorModel();
        jogadorModel.setNome(nome);
        jogadorModel.setOndas_sobrevividas(0);
        playerId = joDao.inserir(jogadorModel);
        System.out.println("Vapo foi de cadastro fml");
        SaveModel saveModel = new SaveModel();
        saveModel.setNome("Playthrough do cara numero "+ playerId);
        Game.jogador.id= playerId;
        saveModel.setNumero_onda(Game.waves);
        saveModel.setPontuacao(Game.jogador.pontos);
        saveModel.setPlayer_id(playerId);
        saveDAO.inserir(saveModel);
    }
}

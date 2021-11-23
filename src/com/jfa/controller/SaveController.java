package com.jfa.controller;

import com.jfa.dao.SaveDAO;
import com.jfa.model.SaveModel;
import com.jfa.view.main.Game;

import java.util.List;

public class SaveController {

    public void inserirSave(int playerId){
        SaveDAO saveDAO = new SaveDAO();
        SaveModel saveModel = new SaveModel();
        saveModel.setNome("Playthrough do cara numero "+ playerId);
        saveModel.setNumero_onda(Game.waves);
        saveModel.setPontuacao(Game.jogador.pontos);
        saveModel.setPlayer_id(playerId);
        saveDAO.inserir(saveModel);
    }

    public List<SaveModel> pegaSaves(){
        SaveDAO saveDAO = new SaveDAO();
        return saveDAO.getSaves();
    }
}

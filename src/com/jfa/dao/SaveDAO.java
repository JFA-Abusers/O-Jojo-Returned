package com.jfa.dao;

import com.jfa.model.JogadorModel;
import com.jfa.model.SaveModel;
import com.mysql.cj.jdbc.JdbcPreparedStatement;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class SaveDAO {

    public void inserir(SaveModel save){
        String sql = "INSERT INTO salvar(pontuacao,nome,numero_onda,player_id) VALUES(?,?,?,?)";

        Connection conn = ConexaoUtil.getConnection().Connect();
        JdbcPreparedStatement pstm = null;

        try{

            conn = ConexaoUtil.getConnection().Connect();
            pstm = (JdbcPreparedStatement) conn.prepareStatement(sql);
            pstm.setInt(1, save.getPontuacao());
            pstm.setString(2, save.getNome());
            pstm.setInt(3, save.getNumero_onda());
            pstm.setInt(4, save.getPlayer_id());

            pstm.execute();
        }catch(Exception e){
            e.printStackTrace();
        }finally {
            try{
                if(pstm!=null){
                    pstm.close();
                }
                if (conn!=null){
                    conn.close();
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }


    public List<SaveModel> getSaves() {
        String sql = "SELECT * FROM salvar";

        List<SaveModel> saves = new ArrayList<>();

        Connection conn = null;
        JdbcPreparedStatement pstm = null;
        ResultSet rset = null;

        try {
            conn = ConexaoUtil.getConnection().Connect();

            pstm = (JdbcPreparedStatement) conn.prepareStatement(sql);

            rset = pstm.executeQuery();

            while (rset.next()) {
                SaveModel save = new SaveModel();

                save.setId(rset.getInt("id"));
                save.setNome(rset.getString("nome"));
                save.setNumero_onda(rset.getInt("numero_onda"));
                save.setPontuacao(rset.getInt("pontuacao"));
                save.setPlayer_id(rset.getInt("player_id"));

                saves.add(save);

            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (pstm != null) {
                    pstm.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return saves;
        }
    }
}

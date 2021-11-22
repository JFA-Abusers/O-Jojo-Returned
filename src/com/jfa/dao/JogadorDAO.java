package com.jfa.dao;

import com.jfa.model.JogadorModel;
import com.jfa.view.entities.Jogador;
import com.mysql.cj.jdbc.JdbcPreparedStatement;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class JogadorDAO {

    public void inserir(JogadorModel jogador){
        String sql = "INSERT INTO player(nome,ondas_sobrevividas) VALUES(?,?)";

        Connection conn = ConexaoUtil.getConnection().Connect();
        JdbcPreparedStatement pstm = null;

        try{
            conn = ConexaoUtil.getConnection().Connect();
            pstm = (JdbcPreparedStatement) conn.prepareStatement(sql);

            pstm.setString(1, jogador.getNome());
            pstm.setInt(2,jogador.getOndas_sobrevividas());

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

    public List<JogadorModel> getJogadores() {
        String sql = "SELECT * FROM player";

        List<JogadorModel> jogadores = new ArrayList<>();

        Connection conn = null;
        JdbcPreparedStatement pstm = null;
        ResultSet rset = null;

        try {
            conn = ConexaoUtil.getConnection().Connect();

            pstm = (JdbcPreparedStatement) conn.prepareStatement(sql);

            rset = pstm.executeQuery();

            while (rset.next()) {
                JogadorModel jogador = new JogadorModel();

                jogador.setId(rset.getInt("id"));
                jogador.setNome(rset.getString("nome"));
                jogador.setOndas_sobrevividas(rset.getInt("ondas_sobrevividas"));
                jogadores.add(jogador);

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
            return jogadores;
        }
    }

    public void update(JogadorModel jogador){
        String sql = "UPDATE player SET nome = ?,ondas_sobrevividas = ? WHERE id = ?";

        Connection conn = null;
        JdbcPreparedStatement pstm = null;

        try{
            conn =ConexaoUtil.getConnection().Connect();

            pstm = (JdbcPreparedStatement) conn.prepareStatement(sql);

            pstm.setString(1, jogador.getNome());
            pstm.setInt(2,jogador.getOndas_sobrevividas());

            pstm.setInt(4,jogador.getId());

            pstm.execute();

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
        }
    }

    public void deleteById(int id){
        String sql = "DELETE FROM player WHERE id = ?";

        Connection conn = null;

        JdbcPreparedStatement pstm = null;

        try{
            conn = ConexaoUtil.getConnection().Connect();

            pstm = (JdbcPreparedStatement) conn.prepareStatement(sql);

            pstm.setInt(1,id);

            pstm.execute();

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
        }
    }
}

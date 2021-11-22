package com.jfa.dao;

import java.sql.DriverManager;

public class ConexaoUtil {

    private String caminho = "localhost";
    private String porta = "3306";
    private String user = "root";
    private String senha = "";
    private String nome = "game";

    private String URL= "jdbc:mysql://"+caminho+":"+porta+"/"+nome+"?useTimezone=true&serverTimezone=GMT";

    public static ConexaoUtil getConnection(){
        ConexaoUtil conexaoUtil = null;
        if(conexaoUtil==null){
            conexaoUtil = new ConexaoUtil();
            return conexaoUtil;
        }else{
            return null;
        }
    }

    public void Connect(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            DriverManager.getConnection(URL, user, senha);
        } catch (Exception e){
            System.err.println("Erro na hora de conectar"+e);
        }
    }

}

package com.jfa.teste;

import com.jfa.dao.ConexaoUtil;

public class Teste {

    public static void main(String[] args) {
        ConexaoUtil.getConnection().Connect();
    }

}

package com.jfa.model;

public class JogadorModel {
    private String nome;
    private int ondas_sobrevividas;
    private int id;

    public JogadorModel(){

    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getOndas_sobrevividas() {
        return ondas_sobrevividas;
    }

    public void setOndas_sobrevividas(int ondas_sobrevividas) {
        this.ondas_sobrevividas = ondas_sobrevividas;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}

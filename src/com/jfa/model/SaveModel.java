package com.jfa.model;

public class SaveModel {
    private String nome;
    private int pontuacao;
    private int numero_onda;
    private int player_id;
    private int id;

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getPontuacao() {
        return pontuacao;
    }

    public void setPontuacao(int pontuacao) {
        this.pontuacao = pontuacao;
    }

    public int getNumero_onda() {
        return numero_onda;
    }

    public void setNumero_onda(int numero_onda) {
        this.numero_onda = numero_onda;
    }

    public int getPlayer_id() {
        return player_id;
    }

    public void setPlayer_id(int player_id) {
        this.player_id = player_id;
    }
}

package com.bugalho.footdream;

public class User {

    private UserType userType;
    private String nome;
    private String descricao;

    //Clube
    public User(UserType userType,String nome, String descricao){
        this.userType = userType;
        this.nome = nome;
        this.descricao = descricao;
    }

    private String clube;

    //Treinador
    public User(UserType userType,String nome,String clube,String descricao){
        this.userType = userType;
        this.nome = nome;
        this.clube = clube;
        this.descricao = descricao;
    }

    private String escalao;
    private String posicao;
    private String divisao;

    //Jogador
    public User(UserType userType,String nome,String escalao,String posicao, String divisao,String cube, String descricao){
        this.userType = userType;
        this.nome = nome;
        this.descricao = descricao;
        this.escalao = escalao;
        this.posicao = posicao;
        this.divisao = divisao;
        this.clube = clube;
    }

    public Enum getUserType() {
        return userType;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getClube() {
        return clube;
    }

    public void setClube(String clube) {
        this.clube = clube;
    }

    public String getEscalao() {
        return escalao;
    }

    public void setEscalao(String escalao) {
        this.escalao = escalao;
    }

    public String getPosicao() {
        return posicao;
    }

    public void setPosicao(String posicao) {
        this.posicao = posicao;
    }

    public String getDivisao() {
        return divisao;
    }

    public void setDivisao(String divisao) {
        this.divisao = divisao;
    }
}

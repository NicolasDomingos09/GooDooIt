package edu.curso.goodooit.domain.model;

public class StatusProjeto {
    private Integer ID;
    private String nome;
    private String descricao;

    public StatusProjeto() {}

    public StatusProjeto(String nome, String descricao) {
        this.nome = nome;
        this.descricao = descricao;
    }

    public StatusProjeto(Integer ID, String nome, String descricao) {
        this.ID = ID;
        this.nome = nome;
        this.descricao = descricao;
    }

    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public String toString() {
        return "StatusProjeto{" +
                "ID=" + ID +
                ", nome='" + nome + '\'' +
                ", descricao='" + descricao + '\'' +
                '}';
    }
}

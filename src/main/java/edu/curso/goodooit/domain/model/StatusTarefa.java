package edu.curso.goodooit.domain.model;

public class StatusTarefa {
    private Integer ID;
    private String nome;
    private String descricao;
    private Integer projetoID;

    public StatusTarefa() {}

    public StatusTarefa(String nome, String descricao, Integer projetoID) {
        this.nome = nome;
        this.descricao = descricao;
        this.projetoID = projetoID;
    }

    public StatusTarefa(Integer ID, String nome, String descricao, Integer projetoID) {
        this.ID = ID;
        this.nome = nome;
        this.descricao = descricao;
        this.projetoID = projetoID;
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

    public Integer getProjetoID() {
        return projetoID;
    }

    public void setProjetoID(Integer projetoID) {
        this.projetoID = projetoID;
    }

    @Override
    public String toString() {
        return "StatusTarefa{" +
                "ID=" + ID +
                ", nome='" + nome + '\'' +
                ", descricao='" + descricao + '\'' +
                ", projetoID=" + projetoID +
                '}';
    }
}

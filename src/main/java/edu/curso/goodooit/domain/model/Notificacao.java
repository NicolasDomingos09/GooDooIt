package edu.curso.goodooit.domain.model;

import java.time.LocalDate;

public class Notificacao {
    Integer ID;
    String titulo;
    String descricao;
    LocalDate dataEnvio;

    public Notificacao() {}

    public Notificacao(Integer ID, String titulo, String descricao, LocalDate dataEnvio) {
        this.ID = ID;
        this.titulo = titulo;
        this.descricao = descricao;
        this.dataEnvio = dataEnvio;
    }

    public Notificacao(String titulo, String descricao, LocalDate dataEnvio) {
        this.titulo = titulo;
        this.descricao = descricao;
        this.dataEnvio = dataEnvio;
    }

    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public LocalDate getDataEnvio() {
        return dataEnvio;
    }

    public void setDataEnvio(LocalDate dataEnvio) {
        this.dataEnvio = dataEnvio;
    }

    @Override
    public String toString() {
        return "Notificacao{" +
                "ID=" + ID +
                ", titulo='" + titulo + '\'' +
                ", descricao='" + descricao + '\'' +
                ", dataEnvio=" + dataEnvio +
                '}';
    }
}

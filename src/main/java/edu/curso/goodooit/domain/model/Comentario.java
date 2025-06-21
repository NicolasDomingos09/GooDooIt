package edu.curso.goodooit.domain.model;

import java.time.LocalDate;

public class Comentario {

    private Integer tarefaID;
    private Integer usuarioID;
    private String texto;
    private LocalDate dataCriacao;

    public Comentario() {}

    public Comentario(Integer tarefaID, Integer usuarioID, String texto, LocalDate dataCriacao) {
        this.tarefaID = tarefaID;
        this.usuarioID = usuarioID;
        this.texto = texto;
        this.dataCriacao = dataCriacao;
    }

    public Integer getTarefaID() {
        return tarefaID;
    }

    public void setTarefaID(Integer tarefaID) {
        this.tarefaID = tarefaID;
    }

    public Integer getUsuarioID() {
        return usuarioID;
    }

    public void setUsuarioID(Integer usuarioID) {
        this.usuarioID = usuarioID;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public LocalDate getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDate dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    @Override
    public String toString() {
        return "Comentario{" +
                "tarefaID=" + tarefaID +
                ", usuarioID=" + usuarioID +
                ", texto='" + texto + '\'' +
                ", dataCriacao=" + dataCriacao +
                '}';
    }
}

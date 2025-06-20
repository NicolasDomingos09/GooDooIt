package edu.curso.goodooit.domain.model;

import java.util.ArrayList;
import java.util.List;

public class Quadro {
    private Integer id;
    private String titulo;
    private Integer projetoId;
    private List<Lista> listas = new ArrayList<>();

    public Quadro(){}

    public Quadro(String titulo, Integer projetoId) {
        this.titulo = titulo;
        this.projetoId = projetoId;
    }

    public Quadro(Integer id, String titulo, Integer projetoId) {
        this.id = id;
        this.titulo = titulo;
        this.projetoId = projetoId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Integer getProjetoId() {
        return projetoId;
    }

    public void setProjetoId(Integer projetoId) {
        this.projetoId = projetoId;
    }

    public List<Lista> getListas() {
        return listas;
    }

    public void setListas(List<Lista> listas) {
        this.listas = listas;
    }

    @Override
    public String toString() {
        return "Quadro{" +
                "id=" + id +
                ", titulo='" + titulo + '\'' +
                ", projetoId=" + projetoId +
                ", listas=" + listas +
                '}';
    }
}

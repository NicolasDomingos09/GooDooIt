package edu.curso.goodooit.domain.model;

import java.util.List;

public class Usuario {
    Integer ID;
    String nome;
    String sobrenome;
    String login;
    String senha;
    String email;

    //As listas sao responsabilidade da dao de projeto
    List<Projeto> projetosColaborador;
    List<Projeto> projetosLider;

    //Responsabilidade DAO de comentarios
    List<Comentario> comentariosEscritos;

    //Responsabilidade DAO de notificacao
    List<Notificacao> notificacoesRecebidas;

    public Usuario() {
    }

    public Usuario(Integer ID, String nome, String sobrenome, String login, String senha, String email, List<Projeto> projetosColaborador, List<Projeto> projetosLider, List<Comentario> comentariosEscritos, List<Notificacao> notificacoesRecebidas) {
        this.ID = ID;
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.login = login;
        this.senha = senha;
        this.email = email;
        this.projetosColaborador = projetosColaborador;
        this.projetosLider = projetosLider;
        this.comentariosEscritos = comentariosEscritos;
        this.notificacoesRecebidas = notificacoesRecebidas;
    }

    public Usuario(Integer ID, String nome, String sobrenome, String login, String senha, String email) {
        this.ID = ID;
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.login = login;
        this.senha = senha;
        this.email = email;
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

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Projeto> getProjetosColaborador() {
        return projetosColaborador;
    }

    public void setProjetosColaborador(List<Projeto> projetosColaborador) {
        this.projetosColaborador = projetosColaborador;
    }

    public List<Projeto> getProjetosLider() {
        return projetosLider;
    }

    public void setProjetosLider(List<Projeto> projetosLider) {
        this.projetosLider = projetosLider;
    }

    public List<Comentario> getComentariosEscritos() {
        return comentariosEscritos;
    }

    public void setComentariosEscritos(List<Comentario> comentariosEscritos) {
        this.comentariosEscritos = comentariosEscritos;
    }

    public List<Notificacao> getNotificacoesRecebidas() {
        return notificacoesRecebidas;
    }

    public void setNotificacoesRecebidas(List<Notificacao> notificacoesRecebidas) {
        this.notificacoesRecebidas = notificacoesRecebidas;
    }
}

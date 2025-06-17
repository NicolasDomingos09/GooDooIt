package edu.curso.goodooit.domain.repository;

import edu.curso.goodooit.domain.model.Projeto;

import java.util.List;

public interface IProjetoDAO {
    public List<Projeto> buscarTodosProjetos();

    public Projeto buscarProjetoId(Long id);

    public Projeto buscarProjetoNome(String nome);

    public Projeto registrarProjeto(Projeto projeto);

    public void excluirProjeto(Projeto projeto);

    public void atualizarProjeto(Projeto projeto);
}

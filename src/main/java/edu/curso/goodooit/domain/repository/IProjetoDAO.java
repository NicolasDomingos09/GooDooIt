package edu.curso.goodooit.domain.repository;

import edu.curso.goodooit.domain.model.Projeto;

import java.sql.SQLException;
import java.util.List;

public interface IProjetoDAO {
    public List<Projeto> buscarTodosProjetos() throws SQLException;

    public Projeto buscarProjetoId(Long id) throws SQLException;

    public Projeto buscarProjetoNome(String nome) throws SQLException;

    public Projeto registrarProjeto(Projeto projeto) throws SQLException;

    public void excluirProjeto(Projeto projeto) throws SQLException;

    public void atualizarProjeto(Projeto projeto) throws SQLException;
}

package edu.curso.goodooit.domain.repository;

import edu.curso.goodooit.domain.model.StatusProjeto;

import java.sql.SQLException;
import java.util.List;

public interface IStatusProjetoDAO {
    public List<StatusProjeto> buscarTodosStatusProjeto() throws SQLException;

    public StatusProjeto buscarStatusProjetoId(Integer id) throws SQLException;

    public Integer registrarStatusProjeto(StatusProjeto statusProjeto) throws SQLException;

    public void excluirStatusProjeto(StatusProjeto statusProjeto) throws SQLException;

    public void atualizarStatusProjeto(StatusProjeto statusProjeto) throws SQLException;
}

package edu.curso.goodooit.domain.repository;

import edu.curso.goodooit.domain.model.StatusTarefa;

import java.sql.SQLException;
import java.util.List;

public interface IStatusTarefaDAO {
    public List<StatusTarefa> buscarTodosStatusTarefa() throws SQLException;

    public StatusTarefa buscarStatusTarefaId(Integer id) throws SQLException;

    public Integer registrarStatusTarefa(StatusTarefa statusTarefa) throws SQLException;

    public List<StatusTarefa> buscarStatusTarefaProjetoId(Integer idProjeto) throws SQLException;

    public void excluirStatusTarefa(StatusTarefa statusTarefa) throws SQLException;

    public void atualizarStatusTarefa(StatusTarefa statusTarefa) throws SQLException;
}

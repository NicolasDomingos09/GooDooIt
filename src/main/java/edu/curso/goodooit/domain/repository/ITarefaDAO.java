package edu.curso.goodooit.domain.repository;

import edu.curso.goodooit.domain.model.Tarefa;

import java.sql.SQLException;
import java.util.List;

public interface ITarefaDAO {
    public List<Tarefa> buscarTodosTarefa() throws SQLException;

    public Tarefa buscarTarefaId(Integer id) throws SQLException;

    public Tarefa buscarTarefaNome(String nome) throws SQLException;

    public List<Tarefa> buscarTarefaPrioridade(int prioridade) throws SQLException;

    public Tarefa registrarTarefa(Tarefa tarefa) throws SQLException;

    public void excluirTarefa(Tarefa tarefa) throws SQLException;

    public void atualizarTarefa(Tarefa tarefa) throws SQLException;
}

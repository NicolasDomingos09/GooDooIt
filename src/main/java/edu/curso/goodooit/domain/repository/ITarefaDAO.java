package edu.curso.goodooit.domain.repository;

import edu.curso.goodooit.domain.model.Tarefa;

import java.sql.SQLException;
import java.util.List;

public interface ITarefaDAO {
    public List<Tarefa> buscarTodosTarefa() throws SQLException;

    public Tarefa buscarTarefaId(Integer id) throws SQLException;

    public Tarefa buscarTarefaNome(String nome) throws SQLException;

    public List<Tarefa> buscarTarefaIdResponsavel(Integer idResponsavel) throws SQLException;

    public List<Tarefa> buscarTarefaIdResponsavelProjeto(Integer idResposavel, Integer idProjeto) throws SQLException;

    public List<Tarefa> buscarTarefaIdCriador(Integer idCriador) throws SQLException;

    public List<Tarefa> buscarTarefaIdLista(Integer idLista) throws SQLException;

    public List<Tarefa> buscarTarefaPrioridade(int prioridade) throws SQLException;

    public Integer registrarTarefa(Tarefa tarefa) throws SQLException;

    public void excluirTarefa(Tarefa tarefa) throws SQLException;

    public void atualizarTarefa(Tarefa tarefa) throws SQLException;
}

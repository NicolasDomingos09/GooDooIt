package edu.curso.goodooit.domain.repository;

import edu.curso.goodooit.domain.model.Tarefa;

import java.util.List;

public interface ITarefaDAO {
    public List<Tarefa> buscarTodosTarefa();

    public Tarefa buscarTarefaId(Long id);

    public Tarefa buscarTarefaNome(String nome);

    public List<Tarefa> buscarTarefaPrioridade(int prioridade);

    public Tarefa registrarTarefa(Tarefa tarefa);

    public void excluirTarefa(Tarefa tarefa);

    public void atualizarTarefa(Tarefa tarefa);
}

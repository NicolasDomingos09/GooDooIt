package edu.curso.goodooit.domain.repository;

import edu.curso.goodooit.domain.model.StatusTarefa;

import java.util.List;

public interface IStatusTarefaDAO {
    public List<StatusTarefa> buscarTodosStatusTarefa();

    public StatusTarefa buscarStatusTarefaId(Long id);

    public StatusTarefa registrarStatusTarefa(StatusTarefa statusTarefa);

    public List<StatusTarefa> buscarStatusTarefaProjetoId(Long idProjeto);

    public void excluirStatusTarefa(StatusTarefa statusTarefa);

    public void atualizarStatusTarefa(StatusTarefa statusTarefa);
}

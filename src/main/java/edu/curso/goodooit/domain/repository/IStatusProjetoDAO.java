package edu.curso.goodooit.domain.repository;

import edu.curso.goodooit.domain.model.StatusProjeto;

import java.util.List;

public interface IStatusProjetoDAO {
    public List<StatusProjeto> buscarTodosStatusProjeto();

    public StatusProjeto buscarStatusProjetoId(Long id);

    public StatusProjeto registrarStatusProjeto(StatusProjeto statusProjeto);

    public void excluirStatusProjeto(StatusProjeto statusProjeto);

    public void atualizarStatusProjeto(StatusProjeto statusProjeto);
}

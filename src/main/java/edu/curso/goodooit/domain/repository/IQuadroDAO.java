package edu.curso.goodooit.domain.repository;

import edu.curso.goodooit.domain.model.Quadro;

import java.util.List;

public interface IQuadroDAO {
    public List<Quadro> buscarTodosQuadros();

    public void criarQuadros(Long idProjeto);

    public Quadro buscarQuadroID(Long id);
}

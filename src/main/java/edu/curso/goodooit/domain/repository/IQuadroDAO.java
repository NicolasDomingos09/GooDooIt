package edu.curso.goodooit.domain.repository;

import edu.curso.goodooit.domain.model.Lista;
import edu.curso.goodooit.domain.model.Quadro;

import java.sql.SQLException;
import java.util.List;

public interface IQuadroDAO {
    public List<Quadro> buscarTodosQuadros() throws SQLException;

    public Integer criarQuadros(String titulo, Integer idProjeto) throws SQLException;

    public Quadro buscarQuadroID(Integer id) throws SQLException;
}

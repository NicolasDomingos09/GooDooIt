package edu.curso.goodooit.domain.repository;

import edu.curso.goodooit.domain.model.Lista;

import java.sql.SQLException;
import java.util.List;

public interface IListaDAO {
    public List<Lista> buscarTodasListas() throws SQLException;

    public Lista buscarListaId(Integer id) throws SQLException;

    public Lista buscarListaNome(String nome) throws SQLException;

    public void registrarLista(Lista lista) throws SQLException;

    public void excluirLista(Lista lista) throws SQLException;

    public void atualizarLista(Lista lista) throws SQLException;

    public List<Lista> buscarListasQuadroId(Integer idQuadro) throws SQLException;

    public void registrarListaEmQuadro(Integer idLista, Integer idQuadro) throws SQLException;
}

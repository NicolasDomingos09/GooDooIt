package edu.curso.goodooit.domain.repository;

import edu.curso.goodooit.domain.model.Lista;

import java.sql.SQLException;
import java.util.List;

public interface IListaDAO {
    public List<Lista> buscarTodosLista() throws SQLException;

    public Lista buscarListaId(Long id) throws SQLException;

    public Lista buscarListaNome(String nome) throws SQLException;

    public Lista registrarLista(Lista lista) throws SQLException;

    public void excluirLista(Lista lista) throws SQLException;

    public void atualizarLista(Lista lista) throws SQLException;
}

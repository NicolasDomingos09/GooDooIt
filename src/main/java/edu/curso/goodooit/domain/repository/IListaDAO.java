package edu.curso.goodooit.domain.repository;

import edu.curso.goodooit.domain.model.Lista;

import java.util.List;

public interface IListaDAO {
    public List<Lista> buscarTodosLista();

    public Lista buscarListaId(Long id);

    public Lista buscarListaNome(String nome);

    public Lista registrarLista(Lista lista);

    public void excluirLista(Lista lista);

    public void atualizarLista(Lista lista);
}

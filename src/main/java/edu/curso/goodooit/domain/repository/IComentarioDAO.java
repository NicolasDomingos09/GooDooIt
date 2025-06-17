package edu.curso.goodooit.domain.repository;

import edu.curso.goodooit.domain.model.Comentario;

import java.util.List;

public interface IComentarioDAO {
    public List<Comentario> buscarTodosComentarios();

    public Comentario buscarComentarioIdUsuario(Long idUsuario);

    public Comentario buscarComentarioIdTarefa(Long idTarefa);

    public Comentario registrarComentario(Comentario comentario);

    public void excluirComentario(Comentario comentario);

    public void atualizarComentario(Comentario comentario);
}

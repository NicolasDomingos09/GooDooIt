package edu.curso.goodooit.domain.repository;

import edu.curso.goodooit.domain.model.Comentario;

import java.sql.SQLException;
import java.util.List;

public interface IComentarioDAO {
    public List<Comentario> buscarTodosComentarios() throws SQLException;

    public Comentario buscarComentarioIdUsuario(Long idUsuario) throws SQLException;

    public Comentario buscarComentarioIdTarefa(Long idTarefa) throws SQLException;

    public Comentario registrarComentario(Comentario comentario) throws SQLException;

    public void excluirComentario(Comentario comentario) throws SQLException;

    public void atualizarComentario(Comentario comentario) throws SQLException;
}

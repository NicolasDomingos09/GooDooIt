package edu.curso.goodooit.domain.repository;

import edu.curso.goodooit.domain.model.Comentario;

import java.sql.SQLException;
import java.util.List;

public interface IComentarioDAO {
    public List<Comentario> buscarTodosComentarios() throws SQLException;

    public Comentario buscarComentarioIdUsuario(Integer idUsuario) throws SQLException;

    public Comentario buscarComentarioIdTarefa(Integer idTarefa) throws SQLException;

    public void registrarComentario(Comentario comentario) throws SQLException;

    public void excluirComentario(Comentario comentario) throws SQLException;

    public void atualizarComentario(Comentario comentario) throws SQLException;
}

package edu.curso.goodooit.domain.repository;

import edu.curso.goodooit.domain.model.Usuario;

import java.sql.SQLException;
import java.util.List;

public interface IUsuarioDAO {
    public List<Usuario> buscarTodosUsuarios() throws SQLException;

    public void registrarUsuario(Usuario usuario) throws SQLException;

    public void atualizarUsuario(Usuario usuario) throws SQLException;

    public void excluirUsuario(Usuario usuario) throws SQLException;

    public Usuario buscarUsuarioID(Long id) throws SQLException;

    public Usuario buscarUsuarioLogin(String login) throws SQLException;

    public Usuario buscarUsuarioNome(String nome) throws SQLException;
}

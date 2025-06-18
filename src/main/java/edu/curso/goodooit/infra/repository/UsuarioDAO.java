package edu.curso.goodooit.infra.repository;

import edu.curso.goodooit.domain.model.Usuario;
import edu.curso.goodooit.domain.repository.IUsuarioDAO;
import edu.curso.goodooit.infra.database.DataBaseConnection;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class UsuarioDAO implements IUsuarioDAO {
    private DataBaseConnection dbConn;
    private Connection connection;

    public UsuarioDAO(DataBaseConnection dbConn) {
        this.dbConn = dbConn;
    }

    /*
     *   TODO: Implementar metodos DAO aqui
     */

    @Override
    public List<Usuario> buscarTodosUsuarios() throws SQLException{
        return List.of();
    }

    @Override
    public void registrarUsuario(Usuario usuario) {

    }

    @Override
    public void atualizarUsuario(Usuario usuario) {

    }

    @Override
    public void excluirUsuario(Usuario usuario) {

    }

    @Override
    public Usuario buscarUsuarioID(Long id) {
        return null;
    }

    @Override
    public Usuario buscarUsuarioLogin(String login) {
        return null;
    }

    @Override
    public Usuario buscarUsuarioNome(String nome) {
        return null;
    }
}

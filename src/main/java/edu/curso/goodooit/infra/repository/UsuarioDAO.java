package edu.curso.goodooit.infra.repository;

import edu.curso.goodooit.domain.model.Usuario;
import edu.curso.goodooit.domain.repository.IUsuarioDAO;
import edu.curso.goodooit.infra.database.DataBaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO implements IUsuarioDAO {
    private DataBaseConnection dbConn;

    public UsuarioDAO(DataBaseConnection dbConn) {
        this.dbConn = dbConn;
    }

    private Usuario construirUsuario(ResultSet rs) throws SQLException {
        return new Usuario(
                rs.getInt("ID"),
                rs.getString("nome"),
                rs.getString("sobrenome"),
                rs.getString("login"),
                rs.getString("senha"),
                rs.getString("email")
        );
    }

    @Override
    public List<Usuario> buscarTodosUsuarios() throws SQLException {
        String sql = "SELECT * FROM usuario";
        List<Usuario> usuarios = new ArrayList<>();
        try (Connection conn = dbConn.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                usuarios.add(construirUsuario(rs));
            }
        }
        return usuarios;
    }

    @Override
    public void registrarUsuario(Usuario usuario) throws SQLException {
        String sql = "INSERT INTO usuario (nome, sobrenome, login, senha, email) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = dbConn.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);) {
            stmt.setString(1, usuario.getNome());
            stmt.setString(2, usuario.getSobrenome());
            stmt.setString(3, usuario.getLogin());
            stmt.setString(4, usuario.getSenha());
            stmt.setString(5, usuario.getEmail());
            int linhas = stmt.executeUpdate();
            System.out.println("Linhas afetadas: " + linhas);
        }
    }

    @Override
    public void atualizarUsuario(Usuario usuario) throws SQLException {

    }

    @Override
    public void excluirUsuario(Usuario usuario) throws SQLException {

    }

    @Override
    public Usuario buscarUsuarioID(Integer id) throws SQLException {
        return null;
    }

    @Override
    public Usuario buscarUsuarioLogin(String login) throws SQLException {
        return null;
    }

    @Override
    public Usuario buscarUsuarioNome(String nome) throws SQLException {
        return null;
    }
}

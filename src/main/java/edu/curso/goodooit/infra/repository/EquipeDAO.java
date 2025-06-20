package edu.curso.goodooit.infra.repository;

import edu.curso.goodooit.domain.model.Equipe;
import edu.curso.goodooit.domain.model.Usuario;
import edu.curso.goodooit.domain.repository.IEquipeDAO;
import edu.curso.goodooit.infra.database.DataBaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EquipeDAO implements IEquipeDAO {
    private final DataBaseConnection dbConn;

    public EquipeDAO(DataBaseConnection dbConn) {
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
    public List<Usuario> buscarUsuariosPorEquipe(Integer idEquipe) throws SQLException {
        String sql = """
            SELECT u.*
              FROM usuario u
              JOIN Usuario_Lista lc ON u.ID = lc.usuario_id
             WHERE lc.lista_id = ?
        """;
        List<Usuario> usuarios = new ArrayList<>();
        try (Connection conn = dbConn.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, idEquipe);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    usuarios.add(construirUsuario(rs));
                }
            }
        }
        return usuarios;
    }

    @Override
    public List<Equipe> buscarEquipesPorUsuario(Integer idUsuario) throws SQLException {
        // Primeiro busca todos os IDs de lista em que o usuário está
        String sql = "SELECT lista_id FROM Usuario_Lista WHERE usuario_id = ?";
        List<Equipe> equipes = new ArrayList<>();

        try (Connection conn = dbConn.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, idUsuario);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Integer idLista = rs.getInt("lista_id");
                    // Para cada lista, busca seus membros e monta a Equipe
                    List<Usuario> membros = buscarUsuariosPorEquipe(idLista);
                    equipes.add(new Equipe(membros));
                }
            }
        }
        return equipes;
    }
}

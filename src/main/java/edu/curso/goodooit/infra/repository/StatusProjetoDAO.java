package edu.curso.goodooit.infra.repository;

import edu.curso.goodooit.domain.model.StatusProjeto;
import edu.curso.goodooit.domain.repository.IStatusProjetoDAO;
import edu.curso.goodooit.infra.database.DataBaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StatusProjetoDAO implements IStatusProjetoDAO {
    private final DataBaseConnection dbConn;

    public StatusProjetoDAO(DataBaseConnection dbConn) {
        this.dbConn = dbConn;
    }

    private StatusProjeto construirStatusProjeto(ResultSet rs) throws SQLException {
        return new StatusProjeto(
                rs.getInt("id"),
                rs.getString("nome"),
                rs.getString("descricao")
        );
    }

    @Override
    public List<StatusProjeto> buscarTodosStatusProjeto() throws SQLException {
        String sql = "SELECT * FROM status_projeto";
        List<StatusProjeto> lista = new ArrayList<>();
        try (Connection conn = dbConn.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                lista.add(construirStatusProjeto(rs));
            }
        }
        return lista;
    }

    @Override
    public StatusProjeto buscarStatusProjetoId(Integer id) throws SQLException {
        String sql = "SELECT * FROM status_projeto WHERE id = ?";
        try (Connection conn = dbConn.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return construirStatusProjeto(rs);
                }
            }
        }
        return null;
    }

    @Override
    public Integer registrarStatusProjeto(StatusProjeto statusProjeto) throws SQLException {
        String sqlInsert = "INSERT INTO status_projeto (nome, descricao) VALUES (?, ?)";
        try (Connection conn = dbConn.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sqlInsert, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, statusProjeto.getNome());
            stmt.setString(2, statusProjeto.getDescricao());

            int linhas = stmt.executeUpdate();
            System.out.println("Linhas afetadas: " + linhas);
            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    return rs.getInt(1);
                } else {
                    throw new SQLException("Novo ID não encontrado");
                }
            }
        }
    }

    @Override
    public void excluirStatusProjeto(StatusProjeto statusProjeto) throws SQLException {
        String sql = "DELETE FROM status_projeto WHERE id = ?";
        try (Connection conn = dbConn.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, statusProjeto.getID());
            int linhas = stmt.executeUpdate();
            System.out.println("Linhas afetadas: " + linhas);
        }
    }

    @Override
    public void atualizarStatusProjeto(StatusProjeto statusProjeto) throws SQLException {
        String sql = "UPDATE status_projeto SET nome = ?, descricao = ? WHERE id = ?";
        try (Connection conn = dbConn.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, statusProjeto.getNome());
            stmt.setString(2, statusProjeto.getDescricao());
            stmt.setInt(3, statusProjeto.getID());
            int linhas = stmt.executeUpdate();
            System.out.println("Linhas afetadas: " + linhas);
        }
    }
}

package edu.curso.goodooit.infra.repository;

import edu.curso.goodooit.domain.model.StatusTarefa;
import edu.curso.goodooit.domain.repository.IStatusTarefaDAO;
import edu.curso.goodooit.infra.database.DataBaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StatusTarefaDAO implements IStatusTarefaDAO {
    private final DataBaseConnection dbConn;

    public StatusTarefaDAO(DataBaseConnection dbConn) {
        this.dbConn = dbConn;
    }

    private StatusTarefa construirStatusTarefa(ResultSet rs) throws SQLException {
        return new StatusTarefa(
                rs.getInt("id"),
                rs.getString("nome"),
                rs.getString("descricao"),
                rs.getInt("ProjetoID")
        );
    }

    @Override
    public List<StatusTarefa> buscarTodosStatusTarefa() throws SQLException {
        String sql = "SELECT * FROM status_tarefa";
        List<StatusTarefa> lista = new ArrayList<>();
        try (Connection conn = dbConn.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                lista.add(construirStatusTarefa(rs));
            }
        }
        return lista;
    }

    @Override
    public StatusTarefa buscarStatusTarefaId(Integer id) throws SQLException {
        String sql = "SELECT * FROM status_tarefa WHERE id = ?";
        try (Connection conn = dbConn.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return construirStatusTarefa(rs);
                }
            }
        }
        return null;
    }

    @Override
    public Integer registrarStatusTarefa(StatusTarefa statusTarefa) throws SQLException {
        String sqlInsert = "INSERT INTO status_tarefa (nome, descricao, ProjetoID) VALUES (?, ?, ?)";
        try (Connection conn = dbConn.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sqlInsert, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, statusTarefa.getNome());
            stmt.setString(2, statusTarefa.getDescricao());
            stmt.setInt(3, statusTarefa.getProjetoID());

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
    public List<StatusTarefa> buscarStatusTarefaProjetoId(Integer idProjeto) throws SQLException {
        String sql = "SELECT * FROM status_tarefa WHERE ProjetoID = ?";
        List<StatusTarefa> lista = new ArrayList<>();
        try (Connection conn = dbConn.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idProjeto);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    lista.add(construirStatusTarefa(rs));
                }
            }
        }
        return lista;
    }

    @Override
    public void excluirStatusTarefa(StatusTarefa statusTarefa) throws SQLException {
        String sql = "DELETE FROM status_tarefa WHERE id = ?";
        try (Connection conn = dbConn.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, statusTarefa.getID());
            int linhas = stmt.executeUpdate();
            System.out.println("Linhas afetadas: " + linhas);
        }
    }

    @Override
    public void atualizarStatusTarefa(StatusTarefa statusTarefa) throws SQLException {
        String sql =
                "UPDATE status_tarefa SET nome = ?, descricao = ?, ProjetoID = ? WHERE id = ?";
        try (Connection conn = dbConn.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, statusTarefa.getNome());
            stmt.setString(2, statusTarefa.getDescricao());
            stmt.setInt(3, statusTarefa.getProjetoID());
            stmt.setInt(4, statusTarefa.getID());
            int linhas = stmt.executeUpdate();
            System.out.println("Linhas afetadas: " + linhas);
        }
    }
}

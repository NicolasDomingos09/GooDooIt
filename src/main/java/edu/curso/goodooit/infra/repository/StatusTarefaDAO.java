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
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
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
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return construirStatusTarefa(rs);
                }
            }
        }
        return null;
    }

    @Override
    public StatusTarefa registrarStatusTarefa(StatusTarefa statusTarefa) throws SQLException {
        String sqlInsert = "INSERT INTO status_tarefa (nome, descricao, ProjetoID) VALUES (?, ?, ?)";
        try (Connection conn = dbConn.getConnection();
             PreparedStatement ps = conn.prepareStatement(sqlInsert, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, statusTarefa.getNome());
            ps.setString(2, statusTarefa.getDescricao());
            ps.setInt(3, statusTarefa.getProjetoID());

            int linhas = ps.executeUpdate();

            try (ResultSet keys = ps.getGeneratedKeys()) {
                if (keys.next()) {
                    int novoID = keys.getInt(1);
                    // Recupera e retorna o objeto completo
                    String sqlSelect = "SELECT * FROM status_tarefa WHERE id = ?";
                    try (PreparedStatement psSel = conn.prepareStatement(sqlSelect)) {
                        psSel.setInt(1, novoID);
                        try (ResultSet rs = psSel.executeQuery()) {
                            if (rs.next()) {
                                return construirStatusTarefa(rs);
                            }
                        }
                    }
                }
            }
            throw new SQLException("Falha ao obter ID do status de tarefa inserido.");
        }
    }

    @Override
    public List<StatusTarefa> buscarStatusTarefaProjetoId(Integer idProjeto) throws SQLException {
        String sql = "SELECT * FROM status_tarefa WHERE ProjetoID = ?";
        List<StatusTarefa> lista = new ArrayList<>();
        try (Connection conn = dbConn.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, idProjeto);
            try (ResultSet rs = ps.executeQuery()) {
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
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, statusTarefa.getID());
            int linhas = ps.executeUpdate();
            System.out.println("Linhas afetadas: " + linhas);
        }
    }

    @Override
    public void atualizarStatusTarefa(StatusTarefa statusTarefa) throws SQLException {
        String sql =
                "UPDATE status_tarefa SET nome = ?, descricao = ?, ProjetoID = ? WHERE id = ?";
        try (Connection conn = dbConn.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, statusTarefa.getNome());
            ps.setString(2, statusTarefa.getDescricao());
            ps.setInt(3, statusTarefa.getProjetoID());
            ps.setInt(4, statusTarefa.getID());
            int linhas = ps.executeUpdate();
            System.out.println("Linhas afetadas: " + linhas);
        }
    }
}

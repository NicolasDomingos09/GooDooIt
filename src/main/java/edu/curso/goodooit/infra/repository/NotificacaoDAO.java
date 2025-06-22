package edu.curso.goodooit.infra.repository;

import edu.curso.goodooit.domain.model.Notificacao;
import edu.curso.goodooit.domain.repository.INotificacaoDAO;
import edu.curso.goodooit.infra.database.DataBaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class NotificacaoDAO implements INotificacaoDAO {
    private final DataBaseConnection dbConn;

    public NotificacaoDAO(DataBaseConnection dbConn) {
        this.dbConn = dbConn;
    }

    private Notificacao construirNotificacao(ResultSet rs) throws SQLException {
        return new Notificacao(
                rs.getInt("ID"),
                rs.getString("titulo"),
                rs.getString("descricao"),
                rs.getDate("dataEnvio").toLocalDate()
                );
    }

    @Override
    public List<Notificacao> buscarTodosNotificacoes() throws SQLException {
        String sql = "SELECT * FROM notificacao";
        List<Notificacao> lista = new ArrayList<>();
        try (Connection conn = dbConn.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                lista.add(construirNotificacao(rs));
            }
        }
        return lista;
    }

    @Override
    public Notificacao buscarNotificacaoId(Integer id) throws SQLException {
        String sql = "SELECT * FROM notificacao WHERE ID = ?";
        Notificacao notificacao = new Notificacao();
        try (Connection conn = dbConn.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    notificacao = construirNotificacao(rs);
                }
            }
        }
        return notificacao;
    }

    @Override
    public Integer registrarNotificacao(Notificacao notificacao) throws SQLException {
        String sqlInsert = "INSERT INTO notificacao (titulo, descricao, dataEnvio) VALUES (?, ?, ?)";
        try (Connection conn = dbConn.getConnection();
             PreparedStatement psInsert = conn.prepareStatement(sqlInsert, Statement.RETURN_GENERATED_KEYS)) {

            psInsert.setString(1, notificacao.getTitulo());
            psInsert.setString(2, notificacao.getDescricao());
            psInsert.setDate(3, Date.valueOf(notificacao.getDataEnvio()));

            int linhas = psInsert.executeUpdate();
            try (ResultSet rs = psInsert.getGeneratedKeys()) {
                if (rs.next()) {
                    return rs.getInt(1);
                } else {
                    throw new SQLException("Novo ID não encontrado");
                }
            }
        }
    }

    @Override
    public void excluirNotificacao(Notificacao notificacao) throws SQLException {
        String sql = "DELETE FROM notificacao WHERE ID = ?";
        try (Connection conn = dbConn.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, notificacao.getID());
            int linhas = stmt.executeUpdate();
            System.out.println("Linhas afetadas: " + linhas);
        }
    }

    @Override
    public void atualizarNotificacao(Notificacao notificacao) throws SQLException {
        String sql = "UPDATE notificacao SET titulo = ?, descricao = ?, dataEnvio = ? WHERE ID = ?";
        try (Connection conn = dbConn.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, notificacao.getTitulo());
            stmt.setString(2, notificacao.getDescricao());
            stmt.setDate(3, Date.valueOf(notificacao.getDataEnvio()));
            stmt.setInt(4, notificacao.getID());
            int linhas = stmt.executeUpdate();
            System.out.println("Linhas afetadas: " + linhas);
        }
    }
}

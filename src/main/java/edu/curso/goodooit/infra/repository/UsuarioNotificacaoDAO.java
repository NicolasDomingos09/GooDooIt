package edu.curso.goodooit.infra.repository;

import edu.curso.goodooit.domain.model.UsuarioNotificacao;
import edu.curso.goodooit.domain.repository.IUsuarioNotificacaoDAO;
import edu.curso.goodooit.infra.database.DataBaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UsuarioNotificacaoDAO implements IUsuarioNotificacaoDAO {
    private final DataBaseConnection dbConn;

    public UsuarioNotificacaoDAO(DataBaseConnection dbConn) {
        this.dbConn = dbConn;
    }

    private UsuarioNotificacao construirUsuarioNotificacao(ResultSet rs) throws SQLException {
        return new UsuarioNotificacao(
                rs.getInt("UsuarioID"),
                rs.getInt("NotificacaoID"),
                rs.getBoolean("visualizada")
        );
    }

    @Override
    public List<UsuarioNotificacao> buscarTodosUsuarioNotificacao() throws SQLException {
        String sql = "SELECT * FROM usuario_notificacao";
        List<UsuarioNotificacao> lista = new ArrayList<>();
        try (Connection conn = dbConn.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                lista.add(construirUsuarioNotificacao(rs));
            }
        }
        return lista;
    }

    @Override
    public UsuarioNotificacao buscarUsuarioNotificacaoIdUsuario(Integer idUsuario) throws SQLException {
        String sql = "SELECT * FROM usuario_notificacao WHERE UsuarioID = ?";
        try (Connection conn = dbConn.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, idUsuario);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return construirUsuarioNotificacao(rs);
                }
            }
        }
        return null;
    }

    @Override
    public UsuarioNotificacao buscarUsuarioNotificacaoIdNotificacao(Integer idNotificacao) throws SQLException {
        String sql = "SELECT * FROM usuario_notificacao WHERE NotificacaoID = ?";
        try (Connection conn = dbConn.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, idNotificacao);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return construirUsuarioNotificacao(rs);
                }
            }
        }
        return null;
    }

    @Override
    public void registrarUsuarioNotificacao(UsuarioNotificacao usuarioNotificacao) throws SQLException {
        String sql = "INSERT INTO usuario_notificacao (UsuarioID, NotificacaoID, visualizada) VALUES (?, ?, ?)";
        try (Connection conn = dbConn.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, usuarioNotificacao.getUsuarioID());
            ps.setInt(2, usuarioNotificacao.getNotificacaoID());
            ps.setBoolean(3, usuarioNotificacao.isVisualizada());
            int linhas = ps.executeUpdate();
            System.out.println("Linhas afetadas: " + linhas);
        }
    }

    @Override
    public void excluirUsuarioNotificacao(UsuarioNotificacao usuarioNotificacao) throws SQLException {
        String sql = "DELETE FROM usuario_notificacao WHERE UsuarioID = ? AND NotificacaoID = ?";
        try (Connection conn = dbConn.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, usuarioNotificacao.getUsuarioID());
            ps.setInt(2, usuarioNotificacao.getNotificacaoID());
            int linhas = ps.executeUpdate();
            System.out.println("Linhas afetadas: " + linhas);
        }
    }

    @Override
    public void atualizarUsuarioNotificacao(UsuarioNotificacao usuarioNotificacao) throws SQLException {
        String sql = "UPDATE usuario_notificacao SET visualizada = ? WHERE UsuarioID = ? AND NotificacaoID = ?";
        try (Connection conn = dbConn.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setBoolean(1, usuarioNotificacao.isVisualizada());
            ps.setInt(2, usuarioNotificacao.getUsuarioID());
            ps.setInt(3, usuarioNotificacao.getNotificacaoID());
            int linhas = ps.executeUpdate();
            System.out.println("Linhas afetadas: " + linhas);
        }
    }
}

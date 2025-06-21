package edu.curso.goodooit.infra.repository;

import edu.curso.goodooit.domain.model.Comentario;
import edu.curso.goodooit.domain.repository.IComentarioDAO;
import edu.curso.goodooit.infra.database.DataBaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ComentarioDAO implements IComentarioDAO {
    private final DataBaseConnection dbConn;

    public ComentarioDAO(DataBaseConnection dbConn) {
        this.dbConn = dbConn;
    }

    private Comentario construirComentario(ResultSet rs) throws SQLException {
        return new Comentario(
                rs.getInt("tarefaID"),
                rs.getInt("usuarioID"),
                rs.getString("texto"),
                rs.getDate("data_criacao").toLocalDate()
        );
    }

    @Override
    public List<Comentario> buscarTodosComentarios() throws SQLException {
        String sql = "SELECT * FROM comentario";
        List<Comentario> lista = new ArrayList<>();
        try (Connection conn = dbConn.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                lista.add(construirComentario(rs));
            }
        }
        return lista;
    }

    @Override
    public List<Comentario> buscarComentarioIdUsuario(Integer idUsuario) throws SQLException {
        String sql = "SELECT * FROM comentario WHERE usuarioID = ?";
        return comporLista(idUsuario, sql);
    }

    @Override
    public List<Comentario> buscarComentarioIdTarefa(Integer idTarefa) throws SQLException {
        String sql = "SELECT * FROM comentario WHERE tarefaID = ?";
        return comporLista(idTarefa, sql);
    }

    private List<Comentario> comporLista(Integer idTarefa, String sql) throws SQLException {
        List<Comentario> lista = new ArrayList<>();
        try (Connection conn = dbConn.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, idTarefa);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    lista.add(construirComentario(rs));
                }
            }
        }
        return lista;
    }


    /*
        TODO: testar métodos com nova database pronta
     */
    @Override
    public void registrarComentario(Comentario comentario) throws SQLException {
        String sql = "INSERT INTO comentario (tarefaID, usuarioID, texto, dataCriacao) VALUES (?, ?, ?, ?)";
        try (Connection conn = dbConn.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, comentario.getTarefaID());
            ps.setInt(2, comentario.getUsuarioID());
            ps.setString(3, comentario.getTexto());
            ps.setDate(4, Date.valueOf(comentario.getDataCriacao()));
            int linhas = ps.executeUpdate();
            System.out.println("Linhas afetadas: " + linhas);
        }
    }

    @Override
    public void excluirComentario(Comentario comentario) throws SQLException {
        String sql = "DELETE FROM comentario WHERE tarefaID = ? AND usuarioID = ?";
        try (Connection conn = dbConn.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, comentario.getTarefaID());
            ps.setInt(2, comentario.getUsuarioID());
            int linhas = ps.executeUpdate();
            System.out.println("Linhas afetadas: " + linhas);
        }
    }

    @Override
    public void atualizarComentario(Comentario comentario) throws SQLException {
        String sql = "UPDATE comentario SET texto = ?, dataCriacao = ? WHERE tarefaID = ? AND usuarioID = ?";
        try (Connection conn = dbConn.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, comentario.getTexto());
            ps.setDate(2, Date.valueOf(comentario.getDataCriacao()));
            ps.setInt(3, comentario.getTarefaID());
            ps.setInt(4, comentario.getUsuarioID());
            int linhas = ps.executeUpdate();
            System.out.println("Linhas afetadas: " + linhas);
        }
    }
}

package edu.curso.goodooit.infra.repository;

import edu.curso.goodooit.domain.model.Lista;
import edu.curso.goodooit.domain.repository.IListaDAO;
import edu.curso.goodooit.infra.database.DataBaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ListaDAO implements IListaDAO {
    private final DataBaseConnection dbConn;

    public ListaDAO(DataBaseConnection dbConn) {
        this.dbConn = dbConn;
    }

    private Lista construirLista(ResultSet rs) throws SQLException {
        return new Lista(
                rs.getInt("ID"),
                rs.getString("titulo")
        );
    }

    @Override
    public List<Lista> buscarTodasListas() throws SQLException {
        String sql = "SELECT * FROM lista";
        List<Lista> listas = new ArrayList<>();
        try (Connection conn = dbConn.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery();) {
            while (rs.next()) {
                listas.add(construirLista(rs));
            }
        }
        return listas;
    }

    @Override
    public Lista buscarListaId(Integer id) throws SQLException {
        String sql = "SELECT * FROM lista WHERE ID = ?";
        Lista lista = new Lista();
        try (Connection conn = dbConn.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                lista = construirLista(rs);
            }
        }
        return lista;
    }

    @Override
    public Lista buscarListaNome(String nome) throws SQLException {
        String sql = "SELECT * FROM lista WHERE titulo LIKE ?";
        Lista lista = new Lista();
        try (Connection conn = dbConn.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);) {

            stmt.setString(1, "%" + nome + "%");
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                lista = construirLista(rs);
            }
        }
        return lista;
    }

    @Override
    public Integer registrarLista(Lista lista) throws SQLException {
        String sql = "INSERT INTO lista (titulo) VALUES (?)";
        try (Connection conn = dbConn.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);) {
            stmt.setString(1, lista.getTitulo());
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
    public void excluirLista(Lista lista) throws SQLException {
        String sql = "DELETE FROM lista WHERE ID = ?";
        try (Connection conn = dbConn.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);) {
            stmt.setInt(1, lista.getID());
            int linhas = stmt.executeUpdate();
            System.out.println("Linhas afetadas: " + linhas);
        }
    }

    @Override
    public void atualizarLista(Lista lista) throws SQLException {
        String sql = "UPDATE lista SET titulo = ? WHERE ID = ?";
        try (Connection conn = dbConn.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);) {
            stmt.setString(1, lista.getTitulo());
            int linhas = stmt.executeUpdate();
            System.out.println("Linhas afetadas: " + linhas);
        }
    }

    @Override
    public List<Lista> buscarListasQuadroId(Integer idQuadro) throws SQLException {
        String sql = """
                SELECT l.*
                FROM lista l
                INNER JOIN lista_quadro lq ON l.id = lq.ListaID
                WHERE lq.QuadroID = ?;
                """;
        List<Lista> listas = new ArrayList<>();
        try (Connection conn = dbConn.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);) {
            stmt.setInt(1, idQuadro);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Lista lista = new Lista(
                        rs.getInt("ID"),
                        rs.getString("titulo")
                );
                listas.add(lista);
            }
        }
        return listas;
    }

    @Override
    public void registrarListaEmQuadro(Integer idLista, Integer idQuadro) throws SQLException {
        String sql = "INSERT INTO lista_quadro VALUES (?,?)";
        System.out.println(sql);
        try (Connection conn = dbConn.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);) {
            stmt.setInt(1, idLista);
            stmt.setInt(2, idQuadro);
            int linhas = stmt.executeUpdate();
            System.out.println("Linhas afetadas: " + linhas);
        }
    }


    //ToDo: Testar esses métodos quando o novo db estiver pronto
    @Override
    public List<Lista> buscarListasColaboradorIdUsuario(Integer idUsuario) throws SQLException {
        String sql = """
        SELECT l.*
          FROM Lista l
          JOIN Usuario_Lista ul
            ON l.ID = ul.lista_id
         WHERE ul.usuario_id = ?
    """;
        List<Lista> listas = new ArrayList<>();
        try (Connection conn = dbConn.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, idUsuario);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    listas.add(construirLista(rs));
                }
            }
        }
        return listas;
    }

    @Override
    public void registrarUsuarioLista(Integer idLista, Integer idUsuario) throws SQLException {
        String sql = "INSERT INTO Usuario_Lista (ListaID, UsuarioID) VALUES (?, ?)";
        try (Connection conn = dbConn.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, idLista);
            ps.setInt(2, idUsuario);
            int linhas = ps.executeUpdate();
            System.out.println("Linhas afetadas: " + linhas);
        }
    }
}

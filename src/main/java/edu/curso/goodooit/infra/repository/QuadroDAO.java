package edu.curso.goodooit.infra.repository;

import edu.curso.goodooit.domain.model.Lista;
import edu.curso.goodooit.domain.model.Quadro;
import edu.curso.goodooit.domain.repository.IQuadroDAO;
import edu.curso.goodooit.infra.database.DataBaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class QuadroDAO implements IQuadroDAO {
    private final DataBaseConnection dbConn;

    public QuadroDAO(DataBaseConnection dbConn) {
        this.dbConn = dbConn;
    }

    public DataBaseConnection getDbConn() {
        return dbConn;
    }

    private Quadro construirQuadro(ResultSet rs) throws SQLException {
        return new Quadro(
                rs.getInt("ID"),
                rs.getString("titulo"),
                rs.getInt("projetoID")
        );
    }


    @Override
    public List<Quadro> buscarTodosQuadros() throws SQLException {
        String sql = "SELECT * FROM quadro";
        List<Quadro> quadros = new ArrayList<>();
        try (Connection conn = dbConn.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery();) {
            while (rs.next()) {
                quadros.add(construirQuadro(rs));
            }
        }
        return quadros;
    }

    @Override
    public void criarQuadros(String titulo, Integer idProjeto) throws SQLException {
        String sql = "INSERT INTO quadro (ID,titulo, projetoID) VALUES (?, ?, ?)";
        try (Connection conn = dbConn.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);) {
            stmt.setInt(1, 2025555);
            stmt.setString(2, titulo);
            stmt.setInt(3, idProjeto);
            int linhas = stmt.executeUpdate();
            System.out.println("Linhas afetadas: " + linhas);
        }
    }

    @Override
    public Quadro buscarQuadroID(Integer id) throws SQLException {
        String sql = "SELECT * FROM quadro WHERE ID = ?";
        Quadro quadro = new Quadro();
        try (Connection conn = dbConn.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                quadro = construirQuadro(rs);
            }
        }
        return quadro;
    }
}

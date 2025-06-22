package edu.curso.goodooit.infra.repository;

import edu.curso.goodooit.domain.model.Status;
import edu.curso.goodooit.domain.repository.IStatusDAO;
import edu.curso.goodooit.infra.database.DataBaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StatusDAO implements IStatusDAO {
    private final DataBaseConnection dbConn;

    public StatusDAO(DataBaseConnection dbConn) {
        this.dbConn = dbConn;
    }

    private Status construirStatus(ResultSet rs) throws SQLException {
        return new Status(
                rs.getInt("ID"),
                rs.getString("nome"),
                rs.getString("descricao")
        );
    }

    @Override
    public List<Status> buscarTodosStatus() throws SQLException {
        String sql = "SELECT * FROM status";
        List<Status> lista = new ArrayList<>();
        try (Connection conn = dbConn.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                lista.add(construirStatus(rs));
            }
        }
        return lista;
    }

    @Override
    public Status buscarStatusId(Integer idStatus) throws SQLException {
        String sql = "SELECT * FROM status WHERE ID = ?";
        try (Connection conn = dbConn.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);) {
            stmt.setInt(1, idStatus);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return construirStatus(rs);
            }
        }
        return null;
    }
}
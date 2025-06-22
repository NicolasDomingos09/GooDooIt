package edu.curso.goodooit.domain.repository;

import edu.curso.goodooit.domain.model.Status;

import java.sql.SQLException;
import java.util.List;

public interface IStatusDAO {
    public List<Status> buscarTodosStatus() throws SQLException;

    public Status buscarStatusId(Integer idStatus) throws SQLException;
}

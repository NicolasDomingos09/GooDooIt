package edu.curso.goodooit.domain.repository;

import edu.curso.goodooit.domain.model.Equipe;
import edu.curso.goodooit.domain.model.Usuario;

import java.sql.SQLException;
import java.util.List;

public interface IEquipeDAO {
    List<Usuario> buscarUsuariosPorEquipe(Integer idEquipe) throws SQLException;
    List<Equipe> buscarEquipesPorUsuario(Integer idUsuario) throws SQLException;
}

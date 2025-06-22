package edu.curso.goodooit.domain.repository;

import edu.curso.goodooit.domain.model.Equipe;
import edu.curso.goodooit.domain.model.Usuario;

import java.sql.SQLException;
import java.util.List;

public interface IEquipeDAO {
    public List<Usuario> buscarUsuariosPorLista(Integer idLista) throws SQLException;
    public List<Usuario> buscarUsuariosPorProjeto(Integer idProjeto) throws SQLException;
    public List<Usuario> removerUsuarioProjeto(Integer idProjeto, Integer idUsuario) throws SQLException;
    public List<Usuario> adicionarMembroProjeto(Integer idProjeto, Integer idUsuario) throws SQLException;
    public List<Equipe> buscarEquipesPorUsuario(Integer idUsuario) throws SQLException;
}

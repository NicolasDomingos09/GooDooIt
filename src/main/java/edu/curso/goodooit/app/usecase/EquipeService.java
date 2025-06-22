package edu.curso.goodooit.app.usecase;

import edu.curso.goodooit.domain.model.Projeto;
import edu.curso.goodooit.domain.model.Usuario;
import edu.curso.goodooit.infra.repository.EquipeDAO;
import edu.curso.goodooit.infra.repository.ProjetoDAO;

import java.sql.SQLException;
import java.util.List;

public class EquipeService {
    private final EquipeDAO equipeDAO;
    private final ProjetoDAO projetoDAO;

    public EquipeService(EquipeDAO equipeDAO, ProjetoDAO projetoDAO) {
        this.equipeDAO = equipeDAO;
        this.projetoDAO = projetoDAO;
    }

    public List<Usuario> listarMembrosProjeto(Integer idProjeto) throws SQLException {
        Projeto p = projetoDAO.buscarProjetoId(idProjeto);
        p.setUsuarios(equipeDAO.buscarUsuariosPorProjeto(idProjeto));

        return p.getUsuarios();
    }

    public Projeto adicionarMembroProjeto(Integer idProjeto, Integer idUsuario) throws SQLException {
        Projeto p = projetoDAO.buscarProjetoId(idProjeto);
        p.setUsuarios(equipeDAO.adicionarMembroProjeto(idProjeto, idUsuario));
        projetoDAO.atualizarProjeto(p);
        return p;
    }

    public Projeto removerMembroProjeto(Integer idUsuario, Integer idProjeto) throws SQLException {
        Projeto p = projetoDAO.buscarProjetoId(idProjeto);
        p.setUsuarios(equipeDAO.removerUsuarioProjeto(idProjeto, idUsuario));
        projetoDAO.atualizarProjeto(p);
        return p;
    }
}

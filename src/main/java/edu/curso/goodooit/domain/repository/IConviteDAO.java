package edu.curso.goodooit.domain.repository;

import edu.curso.goodooit.domain.model.Convite;

import java.sql.SQLException;
import java.util.List;

public interface IConviteDAO {
    public List<Convite> buscarTodosConvites() throws SQLException;

    public Convite ConviteIdRemetente(Long idRemetente) throws SQLException;

    public Convite buscarConviteIdDestinatario(Long idDestinatario) throws SQLException;

    public Convite buscarConviteIdProjeto(Long idProjeto) throws SQLException;

    public Convite registrarConvite(Convite convite) throws SQLException;

    public void excluirConvite(Convite convite) throws SQLException;

    public void atualizarConvite(Convite convite) throws SQLException;
}

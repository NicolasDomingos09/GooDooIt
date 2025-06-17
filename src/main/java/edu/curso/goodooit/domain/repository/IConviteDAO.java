package edu.curso.goodooit.domain.repository;

import edu.curso.goodooit.domain.model.Convite;

import java.util.List;

public interface IConviteDAO {
    public List<Convite> buscarTodosConvites();

    public Convite ConviteIdRemetente(Long idRemetente);

    public Convite buscarConviteIdDestinatario(Long idDestinatario);

    public Convite buscarConviteIdProjeto(Long idProjeto);

    public Convite registrarConvite(Convite convite);

    public void excluirConvite(Convite convite);

    public void atualizarConvite(Convite convite);
}

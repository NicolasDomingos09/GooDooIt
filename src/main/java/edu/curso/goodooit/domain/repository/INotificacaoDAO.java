package edu.curso.goodooit.domain.repository;

import edu.curso.goodooit.domain.model.Notificacao;

import java.util.List;

public interface INotificacaoDAO {
    public List<Notificacao> buscarTodosNotificacoes();

    public Notificacao buscarNotificacaoId(Long id);

    public Notificacao registrarNotificacao(Notificacao notificacao);

    public void excluirNotificacao(Notificacao notificacao);

    public void atualizarNotificacao(Notificacao notificacao);
}

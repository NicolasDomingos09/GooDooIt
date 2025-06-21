package edu.curso.goodooit.domain.repository;

import edu.curso.goodooit.domain.model.Notificacao;

import java.sql.SQLException;
import java.util.List;

public interface INotificacaoDAO {
    public List<Notificacao> buscarTodosNotificacoes() throws SQLException;

    public Notificacao buscarNotificacaoId(Integer id) throws SQLException;

    public Integer registrarNotificacao(Notificacao notificacao) throws SQLException;

    public void excluirNotificacao(Notificacao notificacao) throws SQLException;

    public void atualizarNotificacao(Notificacao notificacao) throws SQLException;
}

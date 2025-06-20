package edu.curso.goodooit.domain.repository;

import edu.curso.goodooit.domain.model.UsuarioNotificacao;

import java.sql.SQLException;
import java.util.List;

public interface IUsuarioNotificacaoDAO {
    public List<UsuarioNotificacao> buscarTodosUsuarioNotificacao() throws SQLException;

    public UsuarioNotificacao buscarUsuarioNotificacaoIdUsuario(Integer id) throws SQLException;

    public UsuarioNotificacao buscarUsuarioNotificacaoIdNotificacao(Integer id) throws SQLException;

    public void registrarUsuarioNotificacao(UsuarioNotificacao usuarioNotificacao) throws SQLException;

    public void excluirUsuarioNotificacao(UsuarioNotificacao usuarioNotificacao) throws SQLException;

    public void atualizarUsuarioNotificacao(UsuarioNotificacao usuarioNotificacao) throws SQLException;
}



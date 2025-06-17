package edu.curso.goodooit.domain.repository;

import edu.curso.goodooit.domain.model.UsuarioNotificacao;

import java.util.List;

public interface IUsuarioNotificacaoDAO {
    public List<UsuarioNotificacao> buscarTodosUsuarioNotificacao();

    public UsuarioNotificacao buscarUsuarioNotificacaoIdUsuario(Long id);

    public UsuarioNotificacao buscarUsuarioNotificacaoIdNotificacao(Long id);

    public UsuarioNotificacao registrarUsuarioNotificacao(UsuarioNotificacao usuarioNotificacao);

    public void excluirUsuarioNotificacao(UsuarioNotificacao usuarioNotificacao);

    public void atualizarUsuarioNotificacao(UsuarioNotificacao usuarioNotificacao);
}



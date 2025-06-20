package edu.curso.goodooit.domain.model;

public class UsuarioNotificacao {
    Integer usuarioID;
    Integer notificacaoID;
    boolean visualizada;

    public UsuarioNotificacao() {}

    public UsuarioNotificacao(Integer notificacaoID, boolean visualizada) {
        this.notificacaoID = notificacaoID;
        this.visualizada = visualizada;
    }

    public UsuarioNotificacao(Integer usuarioID, Integer notificacaoID, boolean visualizada) {
        this.usuarioID = usuarioID;
        this.notificacaoID = notificacaoID;
        this.visualizada = visualizada;
    }
}

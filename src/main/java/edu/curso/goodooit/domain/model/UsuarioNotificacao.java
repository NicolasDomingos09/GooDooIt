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

    public Integer getUsuarioID() {
        return usuarioID;
    }

    public void setUsuarioID(Integer usuarioID) {
        this.usuarioID = usuarioID;
    }

    public Integer getNotificacaoID() {
        return notificacaoID;
    }

    public void setNotificacaoID(Integer notificacaoID) {
        this.notificacaoID = notificacaoID;
    }

    public boolean isVisualizada() {
        return visualizada;
    }

    public void setVisualizada(boolean visualizada) {
        this.visualizada = visualizada;
    }

    @Override
    public String toString() {
        return "UsuarioNotificacao{" +
                "usuarioID=" + usuarioID +
                ", notificacaoID=" + notificacaoID +
                ", visualizada=" + visualizada +
                '}';
    }
}

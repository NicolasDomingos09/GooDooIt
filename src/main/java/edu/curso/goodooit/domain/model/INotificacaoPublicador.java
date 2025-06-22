package edu.curso.goodooit.domain.model;

import java.sql.SQLException;

public interface INotificacaoPublicador {
    public void registrarAssinante(INotificacaoAssinante assinante);
    public void removerAssinante(INotificacaoAssinante assinante);
    public void notificar(Notificacao notificacao) throws SQLException;
}

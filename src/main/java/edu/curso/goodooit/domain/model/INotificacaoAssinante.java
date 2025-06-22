package edu.curso.goodooit.domain.model;

import java.sql.SQLException;

public interface INotificacaoAssinante {
    public void receberNotificacao(Notificacao notificacao) throws SQLException;
    public void notificacaoVisualizada(Notificacao notificacao) throws SQLException;
}

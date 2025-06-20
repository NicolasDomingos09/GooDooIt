package edu.curso.goodooit.app;

import edu.curso.goodooit.domain.model.Notificacao;
import edu.curso.goodooit.domain.model.Projeto;
import edu.curso.goodooit.domain.model.Usuario;
import edu.curso.goodooit.infra.database.DataBaseConnection;
import edu.curso.goodooit.infra.repository.NotificacaoDAO;
import edu.curso.goodooit.infra.repository.ProjetoDAO;
import edu.curso.goodooit.infra.repository.UsuarioDAO;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Main{
    public static void main(String[] args) {
        //Teste DB Conn
        DataBaseConnection sql  = new DataBaseConnection("GooDooIt","sa",
                                                    "Nick159642@","localhost", 1433);

        //Teste DAO notificacao
        NotificacaoDAO notificacaoDAO = new NotificacaoDAO(sql);

        try {
            List< Notificacao > notificacaos = notificacaoDAO.buscarTodosNotificacoes();
            notificacaos.forEach(n -> System.out.println(n.toString()));
//            System.out.println(notificacaoDAO.buscarNotificacaoId(500007).toString());

            Notificacao n = notificacaoDAO.buscarNotificacaoId(500010);
//            n = notificacaoDAO.registrarNotificacao(n);
//            n.setTitulo("vamo ve");

//            notificacaoDAO.excluirNotificacao(n);

//            notificacaoDAO.atualizarNotificacao(n);
//            System.out.println(notificacaoDAO.buscarNotificacaoId(n.getID()).toString());
//
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
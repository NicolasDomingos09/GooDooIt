package edu.curso.goodooit.app;

import edu.curso.goodooit.domain.model.*;
import edu.curso.goodooit.infra.database.DataBaseConnection;
import edu.curso.goodooit.infra.repository.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        //Teste DB Conn
        DataBaseConnection sql = new DataBaseConnection("GooDooIt", "sa",
                "Nick159642@", "localhost", 1433);
        //Testes
        ComentarioDAO comentarioDAO = new ComentarioDAO(sql); //Preciso testar ainda com o banco novo
        ConviteDAO conviteDAO = new ConviteDAO(sql);

        try {
            var lista = conviteDAO.buscarTodosConvites();
            lista.forEach(convite -> System.out.println(convite.toString()));
            System.out.printf("%n");

            lista = conviteDAO.buscarConviteIdProjeto(2025100);
            lista.forEach(convite -> System.out.println(convite.toString()));
            System.out.printf("%n");

            lista = conviteDAO.buscarConviteIdDestinatario(10008);
            lista.forEach(convite -> System.out.println(convite.toString()));
            System.out.printf("%n");

            lista = conviteDAO.buscarConviteIdRemetente(10002);
            lista.forEach(convite -> System.out.println(convite.toString()));
            System.out.printf("%n");

            var c  = conviteDAO.buscarConvite(2025100, 10002,10001);
            System.out.println(c.toString());

            conviteDAO.registrarConvite(c);


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
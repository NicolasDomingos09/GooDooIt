package edu.curso.goodooit.app;

import edu.curso.goodooit.app.usecase.ProjetoService;
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
        ProjetoDAO projetoDAO = new ProjetoDAO(sql);
        UsuarioDAO uDAO = new UsuarioDAO(sql);
        try {
            ProjetoService ps = new ProjetoService(projetoDAO,conviteDAO);
            var u = uDAO.buscarUsuarioLogin("ana");
            Projeto projeto = ps.criarProjeto("teste1", "testando service", LocalDate.now(), LocalDate.now(), u.getID());
            System.out.println(projeto.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
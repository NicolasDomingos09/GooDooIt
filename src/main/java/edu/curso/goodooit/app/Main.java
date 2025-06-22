package edu.curso.goodooit.app;

import edu.curso.goodooit.app.usecase.ConviteService;
import edu.curso.goodooit.app.usecase.EquipeService;
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
        StatusProjetoDAO statusProjetoDAO = new StatusProjetoDAO(sql);
        StatusDAO statusDAO = new StatusDAO(sql);
        EquipeDAO equipeDAO = new EquipeDAO(sql);
        UsuarioDAO uDAO = new UsuarioDAO(sql);

        try {
            ProjetoService ps = new ProjetoService(projetoDAO, statusDAO);
            EquipeService es = new EquipeService(equipeDAO, projetoDAO);
            var u = uDAO.buscarUsuarioID(10000);
            Projeto projeto = ps.buscarProjetoId(2025111);
            List<Usuario> e = es.listarMembrosProjeto(2025111);


            ConviteService cs = new ConviteService(conviteDAO, equipeDAO);

//            cs.aceitarConvite(projeto.getID(),u.getID(), 10003);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
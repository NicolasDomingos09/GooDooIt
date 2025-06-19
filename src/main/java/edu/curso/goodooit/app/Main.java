package edu.curso.goodooit.app;

import edu.curso.goodooit.domain.model.Lista;
import edu.curso.goodooit.domain.model.Quadro;
import edu.curso.goodooit.infra.database.DataBaseConnection;
import edu.curso.goodooit.infra.repository.ListaDAO;
import edu.curso.goodooit.infra.repository.QuadroDAO;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class Main{
    public static void main(String[] args) {
        //Teste DB Conn
        DataBaseConnection sql  = new DataBaseConnection("GooDooIt","sa",
                                                    "Nick159642@","localhost", 1433);

        //Teste DAO Quadro
        //Teste DAO Lista

        QuadroDAO quadroDAO = new QuadroDAO(sql);
        ListaDAO listaDAO = new ListaDAO(sql);


        try {
            //Teste funcionaram
//            List<Quadro> listaQuadroTeste = quadroDAO.buscarTodosQuadros();
//            listaQuadroTeste.forEach(q -> System.out.println(q.getId()));
//
//            Quadro qd = new  Quadro(2025555, "Quadro teste DAO", 2025100);
//            quadroDAO.criarQuadros("Quadro teste DAO", 2025100);
//
//            System.out.println("\n");
//            qd = quadroDAO.buscarQuadroID(qd.getId());
//            System.out.println(qd.getId() + " ID quadro");
//            System.out.println("\n");

//            List<Lista> listaListaTeste = listaDAO.buscarTodasListas();
//            listaListaTeste.forEach(l -> System.out.println(l.getTitulo()));

            Lista lis = new Lista(8888888,"Teste DAO que vai funcionar");
////            listaDAO.registrarLista(lis);
//
////            lis = listaDAO.buscarListaNome("Teste DAO que vai funcionar");
////
////            lis = listaDAO.buscarListaId(8888888);
////            System.out.println(lis.getTitulo());
////
//            lis.setTitulo("Teste de atualização que tá rodando");
//            listaDAO.atualizarLista(lis);
//
            listaDAO.excluirLista(lis);
//            listaDAO.excluirLista(lis1);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
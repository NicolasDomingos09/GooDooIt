package edu.curso.goodooit.app;

import edu.curso.goodooit.domain.model.Usuario;
import edu.curso.goodooit.infra.database.DataBaseConnection;
import edu.curso.goodooit.infra.repository.UsuarioDAO;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Main{
    public static void main(String[] args) {
        //Teste DB Conn
        DataBaseConnection sql  = new DataBaseConnection("GooDooIt","sa",
                                                    "Nick159642@","localhost", 1433);

        //Teste DAO Usuario
        UsuarioDAO usuarioDAO = new UsuarioDAO(sql);

        try {
            List<Usuario> users = new ArrayList<>();
            users = usuarioDAO.buscarTodosUsuarios();
            users.forEach(u1 -> System.out.println(u1.toString()));

            Usuario u = usuarioDAO.buscarUsuarioID(users.getFirst().getID());
            System.out.println(u.toString());

            u = usuarioDAO.buscarUsuarioLogin("ana");
            System.out.println(u.toString());

            u = usuarioDAO.buscarUsuarioNomeCompleto("Bruno Lima");
            System.out.println(u.toString());

            Integer id = usuarioDAO.validarSenha("ana", "123456");
            System.out.println(id);

            u = new Usuario("Nicolas", "Domingos", "nick", "senhateste", "emailteste");
            usuarioDAO.registrarUsuario(u);
            u.setID(usuarioDAO.buscarUsuarioLogin("nick").getID());

            u.setEmail("euamoaminhajujubinha@gmail.com");
            usuarioDAO.atualizarUsuario(u);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
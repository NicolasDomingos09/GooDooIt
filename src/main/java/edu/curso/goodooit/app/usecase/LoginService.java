package edu.curso.goodooit.app.usecase;

import edu.curso.goodooit.domain.model.Usuario;
import edu.curso.goodooit.infra.repository.UsuarioDAO;

import java.sql.SQLException;

public class LoginService {
    private UsuarioDAO usuarioDAO;

    public LoginService(UsuarioDAO usuarioDAO) {
        this.usuarioDAO = usuarioDAO;
    }

    public Usuario efetuarLogin(String login, String senha) throws SQLException{
        Integer id = usuarioDAO.validarSenha(login,senha);
        if(id != null){
            return usuarioDAO.buscarUsuarioID(id);
        }
        return null;
    }
}

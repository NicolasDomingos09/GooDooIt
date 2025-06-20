package edu.curso.goodooit.app.controller;

import edu.curso.goodooit.app.usecase.LoginService;
import edu.curso.goodooit.domain.model.Usuario;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

//A controller de login pega as informações na ui.vew e requisita na service (use case)

public class LoginController {

    private LoginService service;
    private StringProperty login = new SimpleStringProperty("");
    private StringProperty senha = new SimpleStringProperty("");

    public LoginController(LoginService service) {
        this.service = service;
    }

    public LoginService getService() {
        return service;
    }

    public void setService(LoginService service) {
        this.service = service;
    }

    public boolean efetuarLogin(StringProperty login, StringProperty senha) {
        Usuario user = service.efetuarLogin();
        if (user == null) {
            login.set(" ");
            senha.set(" ");
            return false;
        }
        return true;
    }

}

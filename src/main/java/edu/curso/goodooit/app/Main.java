package edu.curso.goodooit.app;

import edu.curso.goodooit.infra.database.DataBaseConnection;

import java.sql.Connection;
import java.sql.SQLException;

public class Main{
    public static void main(String[] args) {
        /*
            Main sera responsavel por instanciar os services e daos
            Initializer sera responsavel por instaciar as telas e as controllers
         */
        DataBaseConnection sql  = new DataBaseConnection("GooDooIt","sa",
                                                    "Nick159642@","localhost", 1433);
        try {
            Connection conn = sql.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
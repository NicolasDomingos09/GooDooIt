package edu.curso.goodooit.app;

import edu.curso.goodooit.infra.database.DataBaseConnection;

import java.sql.Connection;
import java.sql.SQLException;

public class Main{
    public static void main(String[] args) {
        //Teste DB Conn
        DataBaseConnection sql  = new DataBaseConnection("GooDooIt","sa",
                                                    "Nick159642@","localhost", 1433);
        try {
            Connection conn = sql.getConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
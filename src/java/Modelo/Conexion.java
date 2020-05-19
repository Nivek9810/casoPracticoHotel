/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author user
 */
public class Conexion {

    Connection db_conn;

    public Connection getConnection() {
        try {
            Class.forName("org.postgresql.Driver").newInstance();
            String url = "jdbc:postgresql://localhost:5432/casopracticohotel";
            String user = "postgres";
            String pass = "123456";
            db_conn = DriverManager.getConnection(url, user, pass);
            if ((db_conn != null) && (!db_conn.isClosed())) {
                System.out.println("Connectado...");
            }
            return db_conn;
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | SQLException e) {
            System.out.println("Error in connecting to database " + e);
        }
        return null;
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Conexion;
import Modelo.DTO_Rol;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author user
 */
public class DAO_Rol {

    private ArrayList<DTO_Rol> listaRoles;

    private DTO_Rol objRol;
    private Statement statement;
    private Conexion con;
    private ResultSet resultSet;
    private Connection conection;

    public DAO_Rol(Connection con) throws SQLException {
        this.listaRoles = new ArrayList<>();
        this.objRol = new DTO_Rol();
        this.statement = null;
        this.con = null;
        this.conection = con;
        this.statement = conection.createStatement();
    }

    public DTO_Rol getRol(int codigo_rol) throws SQLException {
        this.objRol = null;

        String consulta = "SELECT * FROM ROL WHERE codigo_rol = " + codigo_rol + ";";
        resultSet = statement.executeQuery(consulta);
        while (resultSet.next()) {
            this.objRol = new DTO_Rol(
                    resultSet.getInt("codigo_rol"),
                    resultSet.getString("nombre"));
        }
        return this.objRol;
    }
}

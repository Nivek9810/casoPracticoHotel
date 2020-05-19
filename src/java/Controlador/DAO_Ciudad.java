/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Conexion;
import Modelo.DTO_Ciudad;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author user
 */
public class DAO_Ciudad {

    private ArrayList<DTO_Ciudad> listaCiudades;

    private DTO_Ciudad objCiudad;
    private Statement statement;
    private Conexion con;
    private ResultSet resultSet;
    private Connection conection;

    private final String TABLE = "CIUDAD";

    public DAO_Ciudad(Connection con) throws SQLException {
        this.listaCiudades = new ArrayList<>();
        this.objCiudad = new DTO_Ciudad();
        this.statement = null;
        this.con = new Conexion();
        this.conection = con;
        this.statement = conection.createStatement();
    }

    public DTO_Ciudad getCity(int codigo) throws SQLException {
        this.objCiudad = null;

        String consulta = "SELECT * FROM " + this.TABLE + "  WHERE codigo_ciudad=" + codigo + ";";
        resultSet = statement.executeQuery(consulta);
        while (resultSet.next()) {
            this.objCiudad = new DTO_Ciudad(
                    resultSet.getInt("codigo_ciudad"),
                    resultSet.getString("nombre"));
        }
        return this.objCiudad;
    }

    public ArrayList<DTO_Ciudad> getAllPaymentMethods() throws SQLException {
        this.listaCiudades.clear();
        String consulta = "SELECT * FROM " + this.TABLE + " ;";
        resultSet = statement.executeQuery(consulta);
        while (resultSet.next()) {
            this.listaCiudades.add(new DTO_Ciudad(
                    resultSet.getInt("codigo_ciudad"),
                    resultSet.getString("nombre")));
        }
        return this.listaCiudades;
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Conexion;
import Modelo.DTO_Funcionario;
import Modelo.DTO_Hotel;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author user
 */
public class DAO_Hotel {

    private ArrayList<DTO_Hotel> listaHoteles;

    private DTO_Hotel objHotel;
    private Statement statement;
    private Conexion con;
    private ResultSet resultSet;
    private Connection conection;

    private DAO_Persona objDataPersona;

    public DAO_Hotel() throws SQLException {
        this.listaHoteles = new ArrayList<>();
        this.objHotel = new DTO_Hotel();
        this.statement = null;
        this.con = new Conexion();
        this.conection = con.getConnection();
        this.statement = conection.createStatement();
        this.objDataPersona = new DAO_Persona();
    }

    public DTO_Hotel getHotel(int codigoHotel) throws SQLException {
        this.objHotel = null;

        String consulta = "SELECT * FROM HOTEL WHERE codigo_hotel =" + codigoHotel + ";";
        resultSet = statement.executeQuery(consulta);
        while (resultSet.next()) {
            this.objHotel = new DTO_Hotel(
                    resultSet.getInt("codigo_hotel"),
                    this.objDataPersona.getPersona(
                            resultSet.getString("representante_legal")),
                    resultSet.getString("nombre"),
                    resultSet.getString("nit"),
                    resultSet.getTimestamp("fecha_creacion"),
                    resultSet.getString("numero_telefonico"));
        }
        return this.objHotel;
    }
}

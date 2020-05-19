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

    private final String TABLE = "HOTEL";

    public DAO_Hotel(Connection con) throws SQLException {
        this.listaHoteles = new ArrayList<>();
        this.objHotel = new DTO_Hotel();
        this.statement = null;
        this.con = null;
        this.conection = con;
        this.statement = conection.createStatement();
        this.objDataPersona = new DAO_Persona(con);
    }

    public DTO_Hotel getHotel(int codigoHotel) throws SQLException {
        this.objHotel = null;

        String consulta = "SELECT * FROM " + this.TABLE + " WHERE codigo_hotel =" + codigoHotel + ";";
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

    public ArrayList<DTO_Hotel> getAllHotels() throws SQLException {
        this.listaHoteles.clear();
        String consulta = "SELECT * FROM " + this.TABLE + " ;";
        resultSet = statement.executeQuery(consulta);
        while (resultSet.next()) {
            this.listaHoteles.add(new DTO_Hotel(resultSet.getInt("codigo_hotel"),
                    this.objDataPersona.getPersona(
                            resultSet.getString("representante_legal")),
                    resultSet.getString("nombre"),
                    resultSet.getString("nit"),
                    resultSet.getTimestamp("fecha_creacion"),
                    resultSet.getString("numero_telefonico")));
        }
        return this.listaHoteles;
    }

    public int createHotel(DTO_Hotel objHotel) throws SQLException {
        String consulta = "INSERT INTO " + TABLE + " (representante_legal, nombre, nit, fecha_creacion, numero_telefonico) VALUES "
                + "('" + objHotel.getRepresentante_legal().getCedula() + "', "
                + "'" + objHotel.getNombre() + "', "
                + "'" + objHotel.getNIT() + "', "
                + "'" + objHotel.getFecha_creacion() + "', "
                + "'" + objHotel.getNumero_telefonico() + "');";
        int resp = statement.executeUpdate(consulta, Statement.RETURN_GENERATED_KEYS);
        int hotel_id = 0;
        if (resp > 0) {
            this.resultSet = this.statement.getGeneratedKeys();
            while (this.resultSet.next()) {
                hotel_id = this.resultSet.getInt("codigo_hotel");
            }
        }
        return hotel_id;
    }
}

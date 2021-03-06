/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Conexion;
import Modelo.DTO_Habitacion;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author user
 */
public class DAO_Habitacion {

    private ArrayList<DTO_Habitacion> listaHabitaciones;

    private DTO_Habitacion objHabitacion;
    private Statement statement;
    private Conexion con;
    private ResultSet resultSet;
    private Connection conection;

    private DAO_Hotel objDataHotel;
    private DAO_Tipo_habitacion objDataTipoHabitacion;

    private final String TABLE = "HABITACION";

    public DAO_Habitacion(Connection con) throws SQLException {
        this.listaHabitaciones = new ArrayList<>();
        this.objHabitacion = new DTO_Habitacion();
        this.statement = null;
        this.con = null;
        this.conection = con;
        this.statement = conection.createStatement();
        this.objDataHotel = new DAO_Hotel(con);
        this.objDataTipoHabitacion = new DAO_Tipo_habitacion(con);
    }

    public DTO_Habitacion getHabitacion(int codigoHabitacion) throws SQLException {
        this.objHabitacion = null;

        String consulta = "SELECT * FROM " + this.TABLE + " WHERE codigo_habitacion =" + codigoHabitacion + ";";
        resultSet = statement.executeQuery(consulta);
        while (resultSet.next()) {
            //String value = resultSet.getString("valor");
            this.objHabitacion = new DTO_Habitacion(
                    resultSet.getInt("codigo_habitacion"),
                    this.objDataHotel.getHotel(resultSet.getInt("codigo_hotel")),
                    this.objDataTipoHabitacion.getTipoHabitacion(resultSet.getInt("codigo_tipo")),
                    resultSet.getString("valor"));
        }
        return this.objHabitacion;
    }

    public ArrayList<DTO_Habitacion> getAllRooms() throws SQLException {
        this.listaHabitaciones.clear();
        String consulta = "SELECT * FROM " + this.TABLE + ";";
        resultSet = statement.executeQuery(consulta);
        while (resultSet.next()) {
            //String value = resultSet.getString("valor");
            this.listaHabitaciones.add(new DTO_Habitacion(
                    resultSet.getInt("codigo_habitacion"),
                    this.objDataHotel.getHotel(resultSet.getInt("codigo_hotel")),
                    this.objDataTipoHabitacion.getTipoHabitacion(resultSet.getInt("codigo_tipo")),
                    resultSet.getString("valor")));
        }
        return this.listaHabitaciones;
    }

    public int createHabitacion(DTO_Habitacion objHabitacion) throws SQLException {
        String consulta = "INSERT INTO " + TABLE + " (codigo_hotel, codigo_tipo, valor) VALUES "
                + "(" + objHabitacion.getHotel().getCodigo_hotel() + ", "
                + "" + objHabitacion.getTipo().getCodigo_tipo_habitacion() + ","
                + "'" + objHabitacion.getValor() + "');";
        int resp = statement.executeUpdate(consulta, Statement.RETURN_GENERATED_KEYS);
        int habitacion_id = 0;
        if (resp > 0) {
            this.resultSet = this.statement.getGeneratedKeys();
            while (this.resultSet.next()) {
                habitacion_id = this.resultSet.getInt("codigo_habitacion");
            }
        }
        return habitacion_id;
    }
}

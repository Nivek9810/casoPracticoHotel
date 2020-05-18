/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Conexion;
import Modelo.DTO_Reservacion;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author user
 */
public class DAO_Reservacion {

    private ArrayList<DTO_Reservacion> listaReservaciones;

    private DTO_Reservacion objReservacion;
    private Statement statement;
    private Conexion con;
    private ResultSet resultSet;
    private Connection conection;

    private DAO_Persona objDataPersona;
    private DAO_Habitacion objDataHabitacion;

    public DAO_Reservacion() throws SQLException {
        this.listaReservaciones = new ArrayList<>();
        this.objReservacion = new DTO_Reservacion();
        this.statement = null;
        this.con = new Conexion();
        this.conection = con.getConnection();
        this.statement = conection.createStatement();
        this.objDataPersona = new DAO_Persona();
        this.objDataHabitacion = new DAO_Habitacion();
    }

    public ArrayList<DTO_Reservacion> getAllReservations() throws SQLException {
        this.listaReservaciones.clear();
        String consulta = "SELECT * FROM RESERVACION ORDER BY fecha_ingreso DESC;";
        resultSet = statement.executeQuery(consulta);
        while (resultSet.next()) {
            this.listaReservaciones.add(new DTO_Reservacion(
                    resultSet.getInt("codigo_reservacion"),
                    this.objDataPersona.getPersona(
                            resultSet.getString("cliente")),
                    this.objDataHabitacion.getHabitacion(resultSet.getInt("codigo_habitacion")),
                    resultSet.getTimestamp("fecha_ingreso"),
                    resultSet.getTimestamp("fecha_salida")));
        }
        return this.listaReservaciones;
    }

    public boolean createReservacion(DTO_Reservacion objReservacion) throws SQLException {
        String consulta = "INSERT INTO RESERVACION (cliente, codigo_habitacion, fecha_ingreso, fecha_salida) VALUES "
                + "('" + objReservacion.getCliente().getCedula() + "',"
                + "" + objReservacion.getHabitacion().getCodigo_habitacion() + ","
                + "'" + objReservacion.getFecha_ingreso() + "',"
                + "'" + objReservacion.getFecha_salida() + "');";
        return !statement.execute(consulta);
    }

}

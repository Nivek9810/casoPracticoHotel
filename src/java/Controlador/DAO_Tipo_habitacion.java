/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Conexion;
import Modelo.DTO_Tipo_habitacion;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author user
 */
public class DAO_Tipo_habitacion {

    private ArrayList<DTO_Tipo_habitacion> listaTipoHabitaciones;

    private DTO_Tipo_habitacion objTipoHabitacion;
    private Statement statement;
    private Conexion con;
    private ResultSet resultSet;
    private Connection conection;

    public DAO_Tipo_habitacion(Connection con) throws SQLException {
        this.listaTipoHabitaciones = new ArrayList<>();
        this.objTipoHabitacion = new DTO_Tipo_habitacion();
        this.statement = null;
        this.con = null;
        this.conection = con;
        this.statement = conection.createStatement();
    }

    public DTO_Tipo_habitacion getTipoHabitacion(int codigoTipoHab) throws SQLException {
        this.objTipoHabitacion = null;

        String consulta = "SELECT * FROM TIPO_HABITACION WHERE codigo_tipo_habitacion =" + codigoTipoHab + ";";
        resultSet = statement.executeQuery(consulta);
        while (resultSet.next()) {
            this.objTipoHabitacion = new DTO_Tipo_habitacion(
                    resultSet.getInt("codigo_tipo_habitacion"),
                    resultSet.getString("Nombre"));
        }
        return this.objTipoHabitacion;
    }
}

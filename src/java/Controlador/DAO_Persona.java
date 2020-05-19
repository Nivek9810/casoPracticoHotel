/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Conexion;
import Modelo.DTO_Persona;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author user
 */
public class DAO_Persona {

    private ArrayList<DTO_Persona> lista_Personas;

    private DTO_Persona objPersona;
    private Statement statement;
    private Conexion con;
    private ResultSet resultSet;
    private Connection conection;

    private DAO_Genero objDataGenero;

    public DAO_Persona(Connection con) throws SQLException {
        this.lista_Personas = new ArrayList<>();
        this.objPersona = new DTO_Persona();
        this.statement = null;
        this.con = null;
        this.conection = con;
        this.statement = conection.createStatement();
        this.objDataGenero = new DAO_Genero(con);
    }

    public DTO_Persona getPersona(String cedula) throws SQLException {
        this.objPersona = null;

        String consulta = "SELECT * FROM PERSONA WHERE cedula='" + cedula + "';";
        resultSet = statement.executeQuery(consulta);
        while (resultSet.next()) {
            this.objPersona = new DTO_Persona(
                    resultSet.getString("cedula"),
                    this.objDataGenero.getGenero(resultSet.getInt("codigo_genero")),
                    resultSet.getString("nombre"),
                    resultSet.getString("apellido_padre"),
                    resultSet.getString("apellido_madre"),
                    resultSet.getDate("fecha_nacimiento"),
                    resultSet.getString("correo_electronico"),
                    resultSet.getString("telefono"));
        }
        return this.objPersona;
    }

    public boolean createPerson(DTO_Persona objPersona) throws SQLException {
        String consulta = "INSERT INTO PERSONA VALUES ('" + objPersona.getCedula() + "',"
                + "" + objPersona.getGenero().getCodigo_genero() + ","
                + "'" + objPersona.getNombre() + "',"
                + "'" + objPersona.getApellido_padre() + "',"
                + "'" + objPersona.getApellido_madre() + "',"
                + "'" + objPersona.getFecha_nacimiento() + "',"
                + "'" + objPersona.getCorreo_electronico() + "',"
                + "'" + objPersona.getTelefono() + "');";
        return !statement.execute(consulta);
    }

    public boolean updatePerson(DTO_Persona objPersona) throws SQLException {
        String consulta = "UPDATE PERSONA SET "
                + "codigo_genero = " + objPersona.getGenero().getCodigo_genero() + ", "
                + "nombre = '" + objPersona.getNombre() + "', "
                + "apellido_padre = '" + objPersona.getApellido_padre() + "', "
                + "apellido_madre = '" + objPersona.getApellido_madre() + "', "
                + "fecha_nacimiento = '" + objPersona.getFecha_nacimiento() + "',"
                + "correo_electronico = '" + objPersona.getCorreo_electronico() + "', "
                + "telefono = '" + objPersona.getTelefono() + "' "
                + "WHERE cedula = '" + objPersona.getCedula() + "';";

        return statement.executeUpdate(consulta) > 0;
    }

}

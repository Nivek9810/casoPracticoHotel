/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Conexion;
import Modelo.DTO_Genero;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author user
 */
public class DAO_Genero {

    private ArrayList<DTO_Genero> listaGeneros;

    private DTO_Genero objGenero;
    private Statement statement;
    private Conexion con;
    private ResultSet resultSet;
    private Connection conection;

    private final String TABLE = "GENERO";

    public DAO_Genero() throws SQLException {
        this.listaGeneros = new ArrayList<>();
        this.objGenero = new DTO_Genero();
        this.statement = null;
        this.con = new Conexion();
        this.conection = con.getConnection();
        this.statement = conection.createStatement();
    }

    public ArrayList<DTO_Genero> getAllGenres() throws SQLException {
        this.listaGeneros.clear();
        String consulta = "SELECT * FROM " + this.TABLE + ";";
        resultSet = statement.executeQuery(consulta);
        while (resultSet.next()) {
            this.listaGeneros.add(new DTO_Genero(
                    resultSet.getInt("codigo_genero"),
                    resultSet.getString("nombre")));
        }
        return this.listaGeneros;
    }

    public DTO_Genero getGenero(int codigo_genero) throws SQLException {
        this.objGenero = null;

        String consulta = "SELECT * FROM " + this.TABLE + " WHERE codigo_genero=" + codigo_genero + ";";
        resultSet = statement.executeQuery(consulta);
        while (resultSet.next()) {
            this.objGenero = new DTO_Genero(
                    resultSet.getInt("codigo_genero"),
                    resultSet.getString("Nombre"));
        }
        return this.objGenero;
    }
}

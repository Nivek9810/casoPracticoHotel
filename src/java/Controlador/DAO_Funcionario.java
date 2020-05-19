/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Conexion;
import Modelo.DTO_Funcionario;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;

/**
 *
 * @author user
 */
public class DAO_Funcionario {

    private ArrayList<DTO_Funcionario> lista_Funcionarios;

    private DTO_Funcionario objFuncionario;
    private Statement statement;
    private Conexion con;
    private ResultSet resultSet;
    private Connection conection;

    private DAO_Persona objDataPersona;
    private DAO_Rol objDataRol;

    private final String TABLE = "FUNCIONARIO";

    public DAO_Funcionario(Connection con) throws SQLException {
        this.lista_Funcionarios = new ArrayList<>();
        this.objFuncionario = new DTO_Funcionario();
        this.statement = null;
        this.con = null;
        this.conection = con;
        this.statement = conection.createStatement();
        this.objDataPersona = new DAO_Persona(con);
        this.objDataRol = new DAO_Rol(con);
    }

    public DAO_Funcionario(ArrayList<DTO_Funcionario> lista_Funcionarios, DTO_Funcionario objFuncionario, Statement statement, Conexion con, ResultSet resultSet, Connection conection, DAO_Persona objDataPersona, DAO_Rol objDataRol) {
        this.lista_Funcionarios = lista_Funcionarios;
        this.objFuncionario = objFuncionario;
        this.statement = statement;
        this.con = con;
        this.resultSet = resultSet;
        this.conection = conection;
        this.objDataPersona = objDataPersona;
        this.objDataRol = objDataRol;
    }

    public ArrayList<DTO_Funcionario> getAllFuncionarios(int condition) throws SQLException {
        ArrayList<DTO_Funcionario> lst_Funcionarios = new ArrayList<>();
        String consulta = "SELECT * FROM " + this.TABLE + " WHERE codigo_rol = " + condition + ";";
        resultSet = statement.executeQuery(consulta);
        while (resultSet.next()) {
            lst_Funcionarios.add(new DTO_Funcionario(
                    this.objDataPersona.getPersona(
                            resultSet.getString("codigo_persona")),
                    this.objDataRol.getRol(resultSet.getInt("codigo_rol")),
                    resultSet.getString("contrasena"),
                    resultSet.getTimestamp("first_login"),
                    resultSet.getTimestamp("last_login")));
        }
        return lst_Funcionarios;
    }

    public DTO_Funcionario getFuncionario(String cedula, String contrasena) throws SQLException {
        this.objFuncionario = null;

        String consulta = "SELECT * FROM " + this.TABLE + " WHERE codigo_persona ='" + cedula + "'  AND contrasena = '" + contrasena + "';";
        resultSet = statement.executeQuery(consulta);
        while (resultSet.next()) {
            this.objFuncionario = new DTO_Funcionario(
                    this.objDataPersona.getPersona(resultSet.getString("codigo_persona")),
                    this.objDataRol.getRol(resultSet.getInt("codigo_rol")),
                    resultSet.getString("contrasena"),
                    resultSet.getTimestamp("first_login"),
                    resultSet.getTimestamp("last_login"));
        }
        return this.objFuncionario;
    }

    public DTO_Funcionario getFuncionarioByCedula(String cedula) throws SQLException {
        this.objFuncionario = null;

        String consulta = "SELECT * FROM " + this.TABLE + " WHERE codigo_persona ='" + cedula + "';";
        resultSet = statement.executeQuery(consulta);
        while (resultSet.next()) {
            this.objFuncionario = new DTO_Funcionario(
                    this.objDataPersona.getPersona(resultSet.getString("codigo_persona")),
                    this.objDataRol.getRol(resultSet.getInt("codigo_rol")),
                    resultSet.getString("contrasena"),
                    resultSet.getTimestamp("first_login"),
                    resultSet.getTimestamp("last_login"));
        }
        return this.objFuncionario;
    }

    public DTO_Funcionario userSesion(String cedula, String contrasena) throws SQLException {
        if (this.updateLastLoginDate(cedula)) {
            return this.getFuncionario(cedula, contrasena);
        } else {
            return null;
        }
    }

    public boolean registerUser(DTO_Funcionario funcionario) throws SQLException {
        String consulta = "INSERT INTO " + this.TABLE + " VALUES("
                + "'" + funcionario.getCodigo_persona().getCedula() + "',"
                + "" + funcionario.getRol().getCodigo_rol() + ","
                + "'" + funcionario.getContrasena() + "',"
                + "'" + funcionario.getFirst_login() + "',"
                + "'" + funcionario.getLast_login() + "');";
        return !statement.execute(consulta);
    }

    public boolean updateUser(DTO_Funcionario funcionario) throws SQLException {
        String consulta = "UPDATE " + this.TABLE + " SET "
                + "codigo_rol = " + funcionario.getRol().getCodigo_rol() + ", "
                + "contrasena = '" + funcionario.getContrasena() + "', "
                + "first_login = '" + funcionario.getFirst_login() + "', "
                + "last_login = '" + funcionario.getLast_login() + "' "
                + "WHERE codigo_persona = '" + funcionario.getCodigo_persona().getCedula() + "';";
        int result = statement.executeUpdate(consulta);
        return result > 0;
    }

    private boolean updateLastLoginDate(String cedula) throws SQLException {
        Timestamp current_date = new Timestamp(System.currentTimeMillis());
        String consulta = "UPDATE " + this.TABLE + " SET last_login = '" + current_date + "' WHERE codigo_persona = '" + cedula + "';";
        int result = statement.executeUpdate(consulta);
        return result > 0;
    }

}

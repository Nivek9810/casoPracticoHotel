/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Conexion;
import Modelo.DTO_Hotel;
import Modelo.DTO_Sucursal;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author user
 */
public class DAO_Sucursal {

    private ArrayList<DTO_Sucursal> listaSucursales;

    private DTO_Sucursal objSucursal;
    private Statement statement;
    private Conexion con;
    private ResultSet resultSet;
    private Connection conection;

    private final String TABLE = "SUCURSAL";

    public DAO_Sucursal(Connection con) throws SQLException {
        this.listaSucursales = new ArrayList<>();
        this.objSucursal = new DTO_Sucursal();
        this.statement = null;
        this.con = null;
        this.conection = con;
        this.statement = conection.createStatement();
    }

    public boolean createSucursal(DTO_Sucursal objSucursal) throws SQLException {
        String consulta = "INSERT INTO " + TABLE + " (codigo_hotel, codigo_ciudad, direccion, telefono, fecha_de_creacion) VALUES "
                + "(" + objSucursal.getHotel().getCodigo_hotel() + ", "
                + "" + objSucursal.getCiudad().getCodigo_ciudad() + ", "
                + "'" + objSucursal.getDireccion() + "', "
                + "'" + objSucursal.getTelefono() + "', "
                + "'" + objSucursal.getFecha_creacion() + "');";
        int resp = statement.executeUpdate(consulta, Statement.RETURN_GENERATED_KEYS);
        return resp > 0;
    }
}

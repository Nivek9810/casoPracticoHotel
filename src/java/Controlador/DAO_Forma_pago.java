/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Conexion;
import Modelo.DTO_Forma_pago;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author user
 */
public class DAO_Forma_pago {

    private ArrayList<DTO_Forma_pago> listaFormaPagos;

    private DTO_Forma_pago objFormaPago;
    private Statement statement;
    private Conexion con;
    private ResultSet resultSet;
    private Connection conection;

    private final String TABLE = "FORMA_DE_PAGO";

    public DAO_Forma_pago() throws SQLException {
        this.listaFormaPagos = new ArrayList<>();
        this.objFormaPago = new DTO_Forma_pago();
        this.statement = null;
        this.con = new Conexion();
        this.conection = con.getConnection();
        this.statement = conection.createStatement();
    }

    public DTO_Forma_pago getPaymentMethod(int codigo) throws SQLException {
        this.objFormaPago = null;

        String consulta = "SELECT * FROM " + this.TABLE + "  WHERE codigo_forma_pago=" + codigo + ";";
        resultSet = statement.executeQuery(consulta);
        while (resultSet.next()) {
            this.objFormaPago = new DTO_Forma_pago(
                    resultSet.getInt("codigo_forma_pago"),
                    resultSet.getString("nombre"));
        }
        return this.objFormaPago;
    }

    public ArrayList<DTO_Forma_pago> getAllPaymentMethods() throws SQLException {
        this.listaFormaPagos.clear();
        String consulta = "SELECT * FROM " + this.TABLE + " ;";
        resultSet = statement.executeQuery(consulta);
        while (resultSet.next()) {
            this.listaFormaPagos.add(new DTO_Forma_pago(
                    resultSet.getInt("codigo_forma_pago"),
                    resultSet.getString("nombre")));
        }
        return this.listaFormaPagos;
    }
}

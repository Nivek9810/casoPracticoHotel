/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Conexion;
import Modelo.DTO_Pago;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author user
 */
public class DAO_Pago {

    private ArrayList<DTO_Pago> listaPagos;

    private DTO_Pago objPago;
    private Statement statement;
    private Conexion con;
    private ResultSet resultSet;
    private Connection conection;

    private DAO_Reservacion objDataReservacion;
    private DAO_Forma_pago objDataFormaPago;

    private final String TABLE = "PAGO";

    public DAO_Pago() throws SQLException {
        this.listaPagos = new ArrayList<>();
        this.objPago = new DTO_Pago();
        this.statement = null;
        this.con = new Conexion();
        this.conection = con.getConnection();
        this.statement = conection.createStatement();
        this.objDataReservacion = new DAO_Reservacion();
        this.objDataFormaPago = new DAO_Forma_pago();
    }

    public DTO_Pago getPayment(int codigo) throws SQLException {
        this.objPago = null;

        String consulta = "SELECT * FROM " + this.TABLE + " WHERE codigo_pago=" + codigo + ";";
        resultSet = statement.executeQuery(consulta);
        while (resultSet.next()) {
            this.objPago = new DTO_Pago(resultSet.getInt("codigo_pago"),
                    this.objDataReservacion.getReservation(resultSet.getInt("codigo_reservacion")),
                    this.objDataFormaPago.getPaymentMethod(resultSet.getInt("codigo_forma_pago")),
                    resultSet.getTimestamp("fecha_pago"),
                    resultSet.getString("valor_de_pago"));
        }
        return this.objPago;
    }

    public boolean generatePayment(DTO_Pago payment) throws SQLException {
        String consulta = "INSERT INTO " + this.TABLE + "(codigo_reservacion,codigo_forma_pago,fecha_pago,valor_de_pago) "
                + "VALUES("
                + "" + payment.getReservacion().getCodigo_reservacion() + ","
                + "" + payment.getForma_pago().getCodigo_forma_pago() + ","
                + "'" + payment.getFecha_pago() + "',"
                + "'" + payment.getValor_de_pago() + "');";
        return !statement.execute(consulta);
    }
}

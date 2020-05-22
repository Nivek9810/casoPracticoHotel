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

    public DAO_Pago(Connection con) throws SQLException {
        this.listaPagos = new ArrayList<>();
        this.objPago = new DTO_Pago();
        this.statement = null;
        this.con = null;
        this.conection = con;
        this.statement = conection.createStatement();
        this.objDataReservacion = new DAO_Reservacion(con);
        this.objDataFormaPago = new DAO_Forma_pago(con);
    }

    public DTO_Pago getPayment(int codigo) throws SQLException {
        this.objPago = null;

        String consulta = "SELECT * FROM " + this.TABLE + " WHERE codigo_pago=" + codigo + ";";
        resultSet = statement.executeQuery(consulta);
        while (resultSet.next()) {
            this.objPago = this.generateObject(resultSet);
        }
        return this.objPago;
    }

    public ArrayList<DTO_Pago> getUserPayments(String cedula) throws SQLException {
        ArrayList<DTO_Pago> listUserPayments = new ArrayList<>();
        String consulta = "SELECT P.* "
                + "FROM PAGO AS P "
                + "INNER JOIN RESERVACION AS R "
                + "ON P.codigo_reservacion = R.codigo_reservacion "
                + "WHERE R.cliente = '" + cedula + "';";
        resultSet = statement.executeQuery(consulta);
        while (resultSet.next()) {
            //String value = resultSet.getString("valor");
            listUserPayments.add(this.generateObject(resultSet));
        }
        return listUserPayments;
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

    public boolean updatePayment(int code_payment, String payment) throws SQLException {
        String consulta = "UPDATE " + this.TABLE + " "
                + "SET valor_de_pago = '" + payment + "' "
                + "WHERE codigo_pago = " + code_payment + ";";
        int response = statement.executeUpdate(consulta);
        return response == 1;
    }

    public ArrayList<DTO_Pago> checkUserPayment(int codigo) throws SQLException {
        ArrayList listPayments = new ArrayList();

        String consulta = "SELECT * FROM " + this.TABLE + " WHERE codigo_reservacion=" + codigo + ";";
        resultSet = statement.executeQuery(consulta);
        while (resultSet.next()) {
            listPayments.add(this.generateObject(resultSet));
        }
        return listPayments;
    }

    private DTO_Pago generateObject(ResultSet resultSet) throws SQLException {
        return new DTO_Pago(
                resultSet.getInt("codigo_pago"),
                this.objDataReservacion.getReservation(resultSet.getInt("codigo_reservacion")),
                this.objDataFormaPago.getPaymentMethod(resultSet.getInt("codigo_forma_pago")),
                resultSet.getTimestamp("fecha_pago"),
                resultSet.getString("valor_de_pago"));
    }
}

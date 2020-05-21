/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Conexion;
import Modelo.DTO_Calificacion;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author user
 */
public class DAO_Calificacion {

    private ArrayList<DTO_Calificacion> listaCalificaciones;

    private DTO_Calificacion objCalificacion;
    private Statement statement;
    private Conexion con;
    private ResultSet resultSet;
    private Connection conection;

    private final String TABLE = "CALIFICACION";

    private DAO_Pago objDataPago;

    public DAO_Calificacion(Connection con) throws SQLException {
        this.listaCalificaciones = new ArrayList<>();
        this.objCalificacion = new DTO_Calificacion();
        this.statement = null;
        this.con = new Conexion();
        this.conection = con;
        this.statement = conection.createStatement();
        this.objDataPago = new DAO_Pago(con);
    }

    public ArrayList<DTO_Calificacion> getAllReviews() throws SQLException {
        this.listaCalificaciones.clear();
        String consulta = "SELECT * FROM " + this.TABLE + " ;";
        this.resultSet = this.statement.executeQuery(consulta);
        while (resultSet.next()) {
            this.listaCalificaciones.add(this.buildObject(resultSet));
        }
        return this.listaCalificaciones;
    }

    public DTO_Calificacion getReview(int codigo) throws SQLException {
        this.objCalificacion = null;

        String consulta = "SELECT * FROM " + this.TABLE + "  WHERE codigo_calificacion=" + codigo + ";";
        resultSet = statement.executeQuery(consulta);
        while (resultSet.next()) {
            this.objCalificacion = this.buildObject(resultSet);
        }
        return this.objCalificacion;
    }

    public ArrayList<DTO_Calificacion> getUserReviews(String cedula) throws SQLException {
        ArrayList<DTO_Calificacion> userListReviews = new ArrayList<>();

        String consulta = "SELECT CA.* "
                + "FROM CALIFICACION AS CA "
                + "INNER JOIN PAGO AS P "
                + "ON CA.codigo_pago = P.codigo_pago "
                + "INNER JOIN RESERVACION AS R "
                + "ON P.codigo_reservacion = R.codigo_reservacion "
                + "WHERE R.cliente = '" + cedula + "';";
        resultSet = statement.executeQuery(consulta);
        while (resultSet.next()) {
            userListReviews.add(this.buildObject(resultSet));
        }
        return userListReviews;
    }

    public int addNewReview(DTO_Calificacion objCalificacion) throws SQLException {
        String consulta = "INSERT INTO " + TABLE + " (codigo_pago, hotel, sucursal, tipo_de_habitacion, calidad_del_servicio) VALUES "
                + "(" + objCalificacion.getPago().getCodigo_pago() + ", "
                + "" + objCalificacion.getHotel() + ","
                + "" + objCalificacion.getSucursal() + ","
                + "" + objCalificacion.getTipo_habitacion() + ","
                + "" + objCalificacion.getCalidad_del_servicio() + ");";
        int resp = statement.executeUpdate(consulta, Statement.RETURN_GENERATED_KEYS);
        int calificacion_id = 0;
        if (resp > 0) {
            this.resultSet = this.statement.getGeneratedKeys();
            while (this.resultSet.next()) {
                calificacion_id = this.resultSet.getInt("codigo_calificacion");
            }
        }
        return calificacion_id;
    }

    /**
     * @description This method allow us to build a calification object
     */
    private DTO_Calificacion buildObject(ResultSet resultSet) throws SQLException {
        return new DTO_Calificacion(
                resultSet.getInt("codigo_calificacion"),
                this.objDataPago.getPayment(resultSet.getInt("codigo_pago")),
                resultSet.getInt("hotel"),
                resultSet.getInt("sucursal"),
                resultSet.getInt("tipo_de_habitacion"),
                resultSet.getInt("calidad_del_servicio"));
    }

}

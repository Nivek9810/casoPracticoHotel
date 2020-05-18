/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.sql.Timestamp;

/**
 *
 * @author user
 */
public class DTO_Pago {

    private int Codigo_pago;
    private DTO_Reservacion Reservacion;
    private DTO_Forma_pago Forma_pago;
    private Timestamp Fecha_pago;
    private double Valor_de_pago;

    public DTO_Pago() {
        this.Codigo_pago = 0;
        this.Reservacion = new DTO_Reservacion();
        this.Forma_pago = new DTO_Forma_pago();
        this.Fecha_pago = null;
        this.Valor_de_pago = 0;
    }

    public DTO_Pago(int Codigo_pago, DTO_Reservacion Reservacion, DTO_Forma_pago Forma_pago, Timestamp Fecha_pago, double Valor_de_pago) {
        this.Codigo_pago = Codigo_pago;
        this.Reservacion = Reservacion;
        this.Forma_pago = Forma_pago;
        this.Fecha_pago = Fecha_pago;
        this.Valor_de_pago = Valor_de_pago;
    }

    public int getCodigo_pago() {
        return Codigo_pago;
    }

    public void setCodigo_pago(int Codigo_pago) {
        this.Codigo_pago = Codigo_pago;
    }

    public DTO_Reservacion getReservacion() {
        return Reservacion;
    }

    public void setReservacion(DTO_Reservacion Reservacion) {
        this.Reservacion = Reservacion;
    }

    public DTO_Forma_pago getForma_pago() {
        return Forma_pago;
    }

    public void setForma_pago(DTO_Forma_pago Forma_pago) {
        this.Forma_pago = Forma_pago;
    }

    public Timestamp getFecha_pago() {
        return Fecha_pago;
    }

    public void setFecha_pago(Timestamp Fecha_pago) {
        this.Fecha_pago = Fecha_pago;
    }

    public double getValor_de_pago() {
        return Valor_de_pago;
    }

    public void setValor_de_pago(double Valor_de_pago) {
        this.Valor_de_pago = Valor_de_pago;
    }

    @Override
    public String toString() {
        return "DTO_Pago{" + "Codigo_pago=" + Codigo_pago + ", Reservacion=" + Reservacion + ", Forma_pago=" + Forma_pago + ", Fecha_pago=" + Fecha_pago + ", Valor_de_pago=" + Valor_de_pago + '}';
    }

}

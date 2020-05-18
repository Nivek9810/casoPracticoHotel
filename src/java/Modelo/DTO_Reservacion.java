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
public class DTO_Reservacion {

    private int Codigo_reservacion;
    private DTO_Persona Cliente;
    private DTO_Habitacion Habitacion;
    private Timestamp Fecha_ingreso;
    private Timestamp Fecha_salida;

    public DTO_Reservacion() {
        this.Codigo_reservacion = 0;
        this.Cliente = new DTO_Persona();
        this.Habitacion = new DTO_Habitacion();
        this.Fecha_ingreso = null;
        this.Fecha_salida = null;
    }

    public DTO_Reservacion(int Codigo_reservacion, DTO_Persona Cliente, DTO_Habitacion Habitacion, Timestamp Fecha_ingreso, Timestamp Fecha_salida) {
        this.Codigo_reservacion = Codigo_reservacion;
        this.Cliente = Cliente;
        this.Habitacion = Habitacion;
        this.Fecha_ingreso = Fecha_ingreso;
        this.Fecha_salida = Fecha_salida;
    }

    public int getCodigo_reservacion() {
        return Codigo_reservacion;
    }

    public void setCodigo_reservacion(int Codigo_reservacion) {
        this.Codigo_reservacion = Codigo_reservacion;
    }

    public DTO_Persona getCliente() {
        return Cliente;
    }

    public void setCliente(DTO_Persona Cliente) {
        this.Cliente = Cliente;
    }

    public DTO_Habitacion getHabitacion() {
        return Habitacion;
    }

    public void setHabitacion(DTO_Habitacion Habitacion) {
        this.Habitacion = Habitacion;
    }

    public Timestamp getFecha_ingreso() {
        return Fecha_ingreso;
    }

    public void setFecha_ingreso(Timestamp Fecha_ingreso) {
        this.Fecha_ingreso = Fecha_ingreso;
    }

    public Timestamp getFecha_salida() {
        return Fecha_salida;
    }

    public void setFecha_salida(Timestamp Fecha_salida) {
        this.Fecha_salida = Fecha_salida;
    }

    @Override
    public String toString() {
        return "DTO_Reservacion{" + "Codigo_reservacion=" + Codigo_reservacion + ", Cliente=" + Cliente + ", Habitacion=" + Habitacion + ", Fecha_ingreso=" + Fecha_ingreso + ", Fecha_salida=" + Fecha_salida + '}';
    }

}

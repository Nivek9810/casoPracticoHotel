/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

/**
 *
 * @author user
 */
public class DTO_Calificacion {

    private int Codigo_calificacion;
    private DTO_Pago Pago;
    private int Hotel;
    private int Sucursal;
    private int Tipo_habitacion;
    private int Calidad_del_servicio;

    public DTO_Calificacion() {
        this.Codigo_calificacion = 0;
        this.Pago = new DTO_Pago();
        this.Hotel = 0;
        this.Sucursal = 0;
        this.Tipo_habitacion = 0;
        this.Calidad_del_servicio = 0;
    }

    public DTO_Calificacion(int Codigo_calificacion, DTO_Pago Pago, int Hotel, int Sucursal, int Tipo_habitacion, int Calidad_del_servicio) {
        this.Codigo_calificacion = Codigo_calificacion;
        this.Pago = Pago;
        this.Hotel = Hotel;
        this.Sucursal = Sucursal;
        this.Tipo_habitacion = Tipo_habitacion;
        this.Calidad_del_servicio = Calidad_del_servicio;
    }

    public int getCodigo_calificacion() {
        return Codigo_calificacion;
    }

    public void setCodigo_calificacion(int Codigo_calificacion) {
        this.Codigo_calificacion = Codigo_calificacion;
    }

    public DTO_Pago getPago() {
        return Pago;
    }

    public void setPago(DTO_Pago Pago) {
        this.Pago = Pago;
    }

    public int getHotel() {
        return Hotel;
    }

    public void setHotel(int Hotel) {
        this.Hotel = Hotel;
    }

    public int getSucursal() {
        return Sucursal;
    }

    public void setSucursal(int Sucursal) {
        this.Sucursal = Sucursal;
    }

    public int getTipo_habitacion() {
        return Tipo_habitacion;
    }

    public void setTipo_habitacion(int Tipo_habitacion) {
        this.Tipo_habitacion = Tipo_habitacion;
    }

    public int getCalidad_del_servicio() {
        return Calidad_del_servicio;
    }

    public void setCalidad_del_servicio(int Calidad_del_servicio) {
        this.Calidad_del_servicio = Calidad_del_servicio;
    }

    @Override
    public String toString() {
        return "DTO_Calificacion{" + "Codigo_calificacion=" + Codigo_calificacion + ", Pago=" + Pago + ", Hotel=" + Hotel + ", Sucursal=" + Sucursal + ", Tipo_habitacion=" + Tipo_habitacion + ", Calidad_del_servicio=" + Calidad_del_servicio + '}';
    }
}

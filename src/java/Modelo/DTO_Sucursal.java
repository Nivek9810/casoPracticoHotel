/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.sql.Date;

/**
 *
 * @author user
 */
public class DTO_Sucursal {

    private int Codigo_sucursal;
    private DTO_Hotel Hotel;
    private DTO_Ciudad Ciudad;
    private String Direccion;
    private String Telefono;
    private Date Fecha_creacion;

    public DTO_Sucursal() {
        this.Codigo_sucursal = 0;
        this.Hotel = new DTO_Hotel();
        this.Ciudad = new DTO_Ciudad();
        this.Direccion = "";
        this.Telefono = "";
        this.Fecha_creacion = null;
    }

    public DTO_Sucursal(int Codigo_sucursal, DTO_Hotel Hotel, DTO_Ciudad Ciudad, String Direccion, String Telefono, Date Fecha_creacion) {
        this.Codigo_sucursal = Codigo_sucursal;
        this.Hotel = Hotel;
        this.Ciudad = Ciudad;
        this.Direccion = Direccion;
        this.Telefono = Telefono;
        this.Fecha_creacion = Fecha_creacion;
    }

    public int getCodigo_sucursal() {
        return Codigo_sucursal;
    }

    public void setCodigo_sucursal(int Codigo_sucursal) {
        this.Codigo_sucursal = Codigo_sucursal;
    }

    public DTO_Hotel getHotel() {
        return Hotel;
    }

    public void setHotel(DTO_Hotel Hotel) {
        this.Hotel = Hotel;
    }

    public DTO_Ciudad getCiudad() {
        return Ciudad;
    }

    public void setCiudad(DTO_Ciudad Ciudad) {
        this.Ciudad = Ciudad;
    }

    public String getDireccion() {
        return Direccion;
    }

    public void setDireccion(String Direccion) {
        this.Direccion = Direccion;
    }

    public String getTelefono() {
        return Telefono;
    }

    public void setTelefono(String Telefono) {
        this.Telefono = Telefono;
    }

    public Date getFecha_creacion() {
        return Fecha_creacion;
    }

    public void setFecha_creacion(Date Fecha_creacion) {
        this.Fecha_creacion = Fecha_creacion;
    }

    @Override
    public String toString() {
        return "DTO_Sucursal{" + "Codigo_sucursal=" + Codigo_sucursal + ", Hotel=" + Hotel + ", Ciudad=" + Ciudad + ", Direccion=" + Direccion + ", Telefono=" + Telefono + ", Fecha_creacion=" + Fecha_creacion + '}';
    }

}

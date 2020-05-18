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
public class DTO_Hotel {

    private int Codigo_hotel;
    private DTO_Persona Representante_legal;
    private String Nombre;
    private String NIT;
    private Timestamp Fecha_creacion;
    private String Numero_telefonico;

    public DTO_Hotel() {
        this.Codigo_hotel = 0;
        this.Representante_legal = new DTO_Persona();
        this.Nombre = "";
        this.NIT = "";
        this.Fecha_creacion = null;
        this.Numero_telefonico = "";
    }

    public DTO_Hotel(int Codigo_hotel, DTO_Persona Representante_legal, String Nombre, String NIT, Timestamp Fecha_creacion, String Numero_telefonico) {
        this.Codigo_hotel = Codigo_hotel;
        this.Representante_legal = Representante_legal;
        this.Nombre = Nombre;
        this.NIT = NIT;
        this.Fecha_creacion = Fecha_creacion;
        this.Numero_telefonico = Numero_telefonico;
    }

    public int getCodigo_hotel() {
        return Codigo_hotel;
    }

    public void setCodigo_hotel(int Codigo_hotel) {
        this.Codigo_hotel = Codigo_hotel;
    }

    public DTO_Persona getRepresentante_legal() {
        return Representante_legal;
    }

    public void setRepresentante_legal(DTO_Persona Representante_legal) {
        this.Representante_legal = Representante_legal;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public String getNIT() {
        return NIT;
    }

    public void setNIT(String NIT) {
        this.NIT = NIT;
    }

    public Timestamp getFecha_creacion() {
        return Fecha_creacion;
    }

    public void setFecha_creacion(Timestamp Fecha_creacion) {
        this.Fecha_creacion = Fecha_creacion;
    }

    public String getNumero_telefonico() {
        return Numero_telefonico;
    }

    public void setNumero_telefonico(String Numero_telefonico) {
        this.Numero_telefonico = Numero_telefonico;
    }

    @Override
    public String toString() {
        return "DTO_Hotel{" + "Codigo_hotel=" + Codigo_hotel + ", Representante_legal=" + Representante_legal + ", Nombre=" + Nombre + ", NIT=" + NIT + ", Fecha_creacion=" + Fecha_creacion + ", Numero_telefonico=" + Numero_telefonico + '}';
    }

}

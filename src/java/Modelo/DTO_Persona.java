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
public class DTO_Persona {

    private String Cedula;
    private DTO_Genero Genero;
    private String Nombre;
    private String Apellido_padre;
    private String Apellido_madre;
    private Date Fecha_nacimiento;
    private String Correo_electronico;
    private String Telefono;

    public DTO_Persona() {
        this.Cedula = "";
        this.Genero = new DTO_Genero();
        this.Nombre = "";
        this.Apellido_padre = "";
        this.Apellido_madre = "";
        this.Fecha_nacimiento = null;
        this.Correo_electronico = "";
        this.Telefono = "";
    }

    public DTO_Persona(String Cedula, DTO_Genero Genero, String Nombre, String Apellido_padre, String Apellido_madre, Date Fecha_nacimiento, String Correo_electronico, String Telefono) {
        this.Cedula = Cedula;
        this.Genero = Genero;
        this.Nombre = Nombre;
        this.Apellido_padre = Apellido_padre;
        this.Apellido_madre = Apellido_madre;
        this.Fecha_nacimiento = Fecha_nacimiento;
        this.Correo_electronico = Correo_electronico;
        this.Telefono = Telefono;
    }

    public String getCedula() {
        return Cedula;
    }

    public void setCedula(String Cedula) {
        this.Cedula = Cedula;
    }

    public DTO_Genero getGenero() {
        return Genero;
    }

    public void setGenero(DTO_Genero Genero) {
        this.Genero = Genero;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public String getApellido_padre() {
        return Apellido_padre;
    }

    public void setApellido_padre(String Apellido_padre) {
        this.Apellido_padre = Apellido_padre;
    }

    public String getApellido_madre() {
        return Apellido_madre;
    }

    public void setApellido_madre(String Apellido_madre) {
        this.Apellido_madre = Apellido_madre;
    }

    public Date getFecha_nacimiento() {
        return Fecha_nacimiento;
    }

    public void setFecha_nacimiento(Date Fecha_nacimiento) {
        this.Fecha_nacimiento = Fecha_nacimiento;
    }

    public String getCorreo_electronico() {
        return Correo_electronico;
    }

    public void setCorreo_electronico(String Correo_electronico) {
        this.Correo_electronico = Correo_electronico;
    }

    public String getTelefono() {
        return Telefono;
    }

    public void setTelefono(String Telefono) {
        this.Telefono = Telefono;
    }

    @Override
    public String toString() {
        return "DTO_Persona{" + "Cedula=" + Cedula + ", Genero=" + Genero + ", Nombre=" + Nombre + ", Apellido_padre=" + Apellido_padre + ", Apellido_madre=" + Apellido_madre + ", Fecha_nacimiento=" + Fecha_nacimiento + ", Correo_electronico=" + Correo_electronico + ", Telefono=" + Telefono + '}';
    }

}

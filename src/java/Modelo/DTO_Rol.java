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
public class DTO_Rol {

    private int Codigo_rol;
    private String Nombre;

    public DTO_Rol() {
        this.Codigo_rol = 0;
        this.Nombre = "";
    }

    public DTO_Rol(int Codigo_rol, String Nombre) {
        this.Codigo_rol = Codigo_rol;
        this.Nombre = Nombre;
    }

    public int getCodigo_rol() {
        return Codigo_rol;
    }

    public void setCodigo_rol(int Codigo_rol) {
        this.Codigo_rol = Codigo_rol;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    @Override
    public String toString() {
        return "DTO_Ciudad{" + "Codigo_rol=" + Codigo_rol + ", Nombre=" + Nombre + '}';
    }
}

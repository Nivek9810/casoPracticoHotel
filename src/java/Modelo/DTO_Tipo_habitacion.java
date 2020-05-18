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
public class DTO_Tipo_habitacion {

    private int Codigo_tipo_habitacion;
    private String Nombre;

    public DTO_Tipo_habitacion() {
        this.Codigo_tipo_habitacion = 0;
        this.Nombre = "";
    }

    public DTO_Tipo_habitacion(int Codigo_tipo_habitacion, String Nombre) {
        this.Codigo_tipo_habitacion = Codigo_tipo_habitacion;
        this.Nombre = Nombre;
    }

    public int getCodigo_tipo_habitacion() {
        return Codigo_tipo_habitacion;
    }

    public void setCodigo_tipo_habitacion(int Codigo_tipo_habitacion) {
        this.Codigo_tipo_habitacion = Codigo_tipo_habitacion;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    @Override
    public String toString() {
        return "DTO_Tipo_habitacion{" + "Codigo_tipo_habitacion=" + Codigo_tipo_habitacion + ", Nombre=" + Nombre + '}';
    }

}

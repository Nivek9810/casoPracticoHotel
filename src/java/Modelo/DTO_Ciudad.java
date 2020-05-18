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
public class DTO_Ciudad {

    private int Codigo_ciudad;
    private String Nombre;

    public DTO_Ciudad() {
        this.Codigo_ciudad = 0;
        this.Nombre = "";
    }

    public DTO_Ciudad(int Codigo_ciudad, String Nombre) {
        this.Codigo_ciudad = Codigo_ciudad;
        this.Nombre = Nombre;
    }

    public int getCodigo_ciudad() {
        return Codigo_ciudad;
    }

    public void setCodigo_ciudad(int Codigo_ciudad) {
        this.Codigo_ciudad = Codigo_ciudad;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    @Override
    public String toString() {
        return "DTO_Ciudad{" + "Codigo_ciudad=" + Codigo_ciudad + ", Nombre=" + Nombre + '}';
    }

}

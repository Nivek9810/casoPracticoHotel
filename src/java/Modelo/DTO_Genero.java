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
public class DTO_Genero {

    private int Codigo_genero;
    private String Nombre;

    public DTO_Genero() {
        this.Codigo_genero = 0;
        this.Nombre = "";
    }

    public DTO_Genero(int Codigo_genero, String Nombre) {
        this.Codigo_genero = Codigo_genero;
        this.Nombre = Nombre;
    }

    public int getCodigo_genero() {
        return Codigo_genero;
    }

    public void setCodigo_genero(int Codigo_genero) {
        this.Codigo_genero = Codigo_genero;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    @Override
    public String toString() {
        return "DTO_Genero{" + "Codigo_genero=" + Codigo_genero + ", Nombre=" + Nombre + '}';
    }

}

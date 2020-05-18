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
public class DTO_Habitacion {

    private int Codigo_habitacion;
    private DTO_Hotel Hotel;
    private DTO_Tipo_habitacion Tipo;
    private String Valor;

    public DTO_Habitacion() {
        this.Codigo_habitacion = 0;
        this.Hotel = new DTO_Hotel();
        this.Tipo = new DTO_Tipo_habitacion();
        this.Valor = "";
    }

    public DTO_Habitacion(int Codigo_habitacion, DTO_Hotel Hotel, DTO_Tipo_habitacion Tipo, String Valor) {
        this.Codigo_habitacion = Codigo_habitacion;
        this.Hotel = Hotel;
        this.Tipo = Tipo;
        this.Valor = Valor;
    }

    public int getCodigo_habitacion() {
        return Codigo_habitacion;
    }

    public void setCodigo_habitacion(int Codigo_habitacion) {
        this.Codigo_habitacion = Codigo_habitacion;
    }

    public DTO_Hotel getHotel() {
        return Hotel;
    }

    public void setHotel(DTO_Hotel Hotel) {
        this.Hotel = Hotel;
    }

    public DTO_Tipo_habitacion getTipo() {
        return Tipo;
    }

    public void setTipo(DTO_Tipo_habitacion Tipo) {
        this.Tipo = Tipo;
    }

    public String getValor() {
        return Valor;
    }

    public void setValor(String Valor) {
        this.Valor = Valor;
    }

    @Override
    public String toString() {
        return "DTO_Habitacion{" + "Codigo_habitacion=" + Codigo_habitacion + ", Hotel=" + Hotel + ", Tipo=" + Tipo + ", Valor=" + Valor + '}';
    }

}

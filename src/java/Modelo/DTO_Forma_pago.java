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
public class DTO_Forma_pago {

    private int Codigo_forma_pago;
    private String Nombre;

    public DTO_Forma_pago() {
        this.Codigo_forma_pago = 0;
        this.Nombre = "";
    }

    public DTO_Forma_pago(int Codigo_forma_pago, String Nombre) {
        this.Codigo_forma_pago = Codigo_forma_pago;
        this.Nombre = Nombre;
    }

    public int getCodigo_forma_pago() {
        return Codigo_forma_pago;
    }

    public void setCodigo_forma_pago(int Codigo_forma_pago) {
        this.Codigo_forma_pago = Codigo_forma_pago;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    @Override
    public String toString() {
        return "DTO_Forma_pago{" + "Codigo_forma_pago=" + Codigo_forma_pago + ", nombre=" + Nombre + '}';
    }

}

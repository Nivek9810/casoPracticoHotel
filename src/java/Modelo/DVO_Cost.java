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
public class DVO_Cost {
    
    private double valor_a_pagar;
    private double valor_de_pago;

    public DVO_Cost(double valor_a_pagar, double valor_de_pago) {
        this.valor_a_pagar = valor_a_pagar;
        this.valor_de_pago = valor_de_pago;
    }

    public DVO_Cost() {
        this.valor_a_pagar = 0;
        this.valor_de_pago = 0;
    }

    public double getValor_a_pagar() {
        return valor_a_pagar;
    }

    public void setValor_a_pagar(double valor_a_pagar) {
        this.valor_a_pagar = valor_a_pagar;
    }

    public double getValor_de_pago() {
        return valor_de_pago;
    }

    public void setValor_de_pago(double valor_de_pago) {
        this.valor_de_pago = valor_de_pago;
    }

    @Override
    public String toString() {
        return "DVO_Cost{" + "valor_a_pagar=" + valor_a_pagar + ", valor_de_pago=" + valor_de_pago + '}';
    }
       
}

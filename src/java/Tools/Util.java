/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tools;

import java.sql.Date;

/**
 *
 * @author user
 */
public class Util {

    //private 
    public Util() {
    }

    public int getTotalDays(Date fecha_salida, Date fecha_ingreso) {
        return new Date(fecha_salida.getTime() - fecha_ingreso.getTime()).getDay();
    }

}

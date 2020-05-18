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
public class DTO_Funcionario {

    private DTO_Persona Codigo_persona;
    private DTO_Rol Rol;
    private String Contrasena;
    private Timestamp First_login;
    private Timestamp Last_login;

    public DTO_Funcionario() {
        this.Codigo_persona = new DTO_Persona();
        this.Rol = new DTO_Rol();
        this.Contrasena = "";
        this.First_login = null;
        this.Last_login = null;
    }

    public DTO_Funcionario(DTO_Persona Codigo_persona, DTO_Rol Rol, String Contrasena, Timestamp First_login, Timestamp Last_login) {
        this.Codigo_persona = Codigo_persona;
        this.Rol = Rol;
        this.Contrasena = Contrasena;
        this.First_login = First_login;
        this.Last_login = Last_login;
    }

    public DTO_Persona getCodigo_persona() {
        return Codigo_persona;
    }

    public void setCodigo_persona(DTO_Persona Codigo_persona) {
        this.Codigo_persona = Codigo_persona;
    }

    public DTO_Rol getRol() {
        return Rol;
    }

    public void setRol(DTO_Rol Rol) {
        this.Rol = Rol;
    }

    public String getContrasena() {
        return Contrasena;
    }

    public void setContrasena(String Contrasena) {
        this.Contrasena = Contrasena;
    }

    public Timestamp getFirst_login() {
        return First_login;
    }

    public void setFirst_login(Timestamp First_login) {
        this.First_login = First_login;
    }

    public Timestamp getLast_login() {
        return Last_login;
    }

    public void setLast_login(Timestamp Last_login) {
        this.Last_login = Last_login;
    }

    @Override
    public String toString() {
        return "DTO_Funcionario{" + "Codigo_persona=" + Codigo_persona.toString() + ", Rol=" + Rol + ", Contrasena=" + Contrasena + ", First_login=" + First_login + ", Last_login=" + Last_login + '}';
    }

}

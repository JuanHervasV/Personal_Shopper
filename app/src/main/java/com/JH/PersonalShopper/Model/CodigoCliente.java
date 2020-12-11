package com.JH.PersonalShopper.Model;

public class CodigoCliente {

    private String ID;
    private String NomCliente;
    private String ApeCliente;
    private String nombre;


    public CodigoCliente(String Nombre) {
        nombre = Nombre;
    }

    public String id(){return ID; }
    public String nomcliente() {
        return NomCliente;
    }
    public String apecliente() { return ApeCliente; }
}

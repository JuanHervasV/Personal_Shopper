package com.JH.PersonalShopper.Model;

public class ListarClientes {

    private String ID;
    private String Nombre;
    private String DNI;
    private String Email;
    private String nombre;


    public ListarClientes(String Nombre) {
        nombre = Nombre;
    }

    public String id(){return ID; }
    public String nombre() {
        return Nombre;
    }
    public String dni() { return DNI; }
    public String email() { return Email; }


}

package com.JH.PersonalShopper.Model;

public class RegistrarCliente {

    private String Nombre;
    private String Apellido;
    private String Genero;
    private String Dni;
    private String Celular;
    private String Email;
    private String Direccion;
    private String Distrito;
    private String Provincia;
    private String Departamento;

    private String ApellidoPaterno;
    private String ApellidoMaterno;
    private String NroIdentificacion;
    private String NomAbreviado;
    private boolean estado;
    private String mensaje;


    public boolean Estado(){return estado; }
    public String Mensale(){return mensaje;}

    public RegistrarCliente(String nombre, String apellido, String genero, String dni, String celular, String email, String direccion, String distrito, String provincia, String departamento) {
        Nombre= nombre;
        Apellido= apellido;
        Genero = genero;
        Dni = dni;
        Celular = celular;
        Email = email;
        Direccion = direccion;
        Distrito = distrito;
        Provincia = provincia;
        Departamento = departamento;
    }


}

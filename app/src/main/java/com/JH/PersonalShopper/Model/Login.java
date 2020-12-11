package com.JH.PersonalShopper.Model;

public class Login {

    private String usuario;
    private String password;

    private String ApellidoPaterno;
    private String ApellidoMaterno;
    private String NroIdentificacion;
    private String NomAbreviado;

    public String ApellidoPaterno() {
        return ApellidoPaterno;
    }
    public String ApellidoMaterno() { return ApellidoMaterno; }
    public String NroIdentificacion(){return NroIdentificacion; }
    public String NomAbreviado(){return NomAbreviado;}

    public Login(String Usuario, String Password) {
        usuario= Usuario;
        password= Password;
    }

}

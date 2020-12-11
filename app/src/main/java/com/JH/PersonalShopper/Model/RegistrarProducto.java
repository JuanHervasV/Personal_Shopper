package com.JH.PersonalShopper.Model;

public class RegistrarProducto {

    private String iDCliente;
    private String producto;
    private String tienda;
    private String tipoPago;
    private String pagoNum;
    private String valorProducto;
    private String valorEnvio;
    private String tipoEnvio;
    private String valorShopper;
    private String depositoCliente;
    private String medioPago;
    private String codigoPago;
    private String montoDevolucion;
    private String observaciones;

    private boolean estado;
    private String mensaje;


    public boolean Estado(){return estado; }
    public String Mensale(){return mensaje;}

    public RegistrarProducto(String IDCliente, String Producto, String Tienda, String TipoPago, String PagoNum, String ValorProducto, String ValorEnvio, String TipoEnvio, String ValorShopper, String DepositoCliente, String MedioPago, String CodigoPago, String MontoDevolucion, String Observaciones) {
        iDCliente = IDCliente;
        producto = Producto;
        tienda = Tienda;
        tipoPago = TipoPago;
        pagoNum = PagoNum;
        valorProducto = ValorProducto;
        valorEnvio = ValorEnvio;
        tipoEnvio = TipoEnvio;
        valorShopper = ValorShopper;
        depositoCliente = DepositoCliente;
        medioPago = MedioPago;
        codigoPago = CodigoPago;
        montoDevolucion = MontoDevolucion;
        observaciones = Observaciones;
    }
}

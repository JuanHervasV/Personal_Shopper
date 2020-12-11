package com.JH.PersonalShopper.io;

import com.JH.PersonalShopper.Model.CodigoCliente;
import com.JH.PersonalShopper.Model.ListarClientes;
import com.JH.PersonalShopper.Model.Login;
import com.JH.PersonalShopper.Model.RegistrarCliente;
import com.JH.PersonalShopper.Model.RegistrarProducto;

import retrofit2.http.Body;
import retrofit2.http.POST;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface APIRetrofitInterface {

    @POST("Login")
    Call<Login> login(@Body Login login);

    @POST("RegistrarCliente")
    Call<RegistrarCliente> registrarCliente(@Body RegistrarCliente registrarCliente);

    @POST("RegistrarProducto")
    Call<RegistrarProducto> registrarProducto(@Body RegistrarProducto registrarProducto);

    @POST("ListarClientes")
    Call<List<ListarClientes>> listarclientes(@Body ListarClientes listarClientes);

    @POST("CodigoCliente")
    Call<CodigoCliente> codigocliente(@Body CodigoCliente codigoCliente);


}

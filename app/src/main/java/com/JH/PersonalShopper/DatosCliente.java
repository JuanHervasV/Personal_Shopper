package com.JH.PersonalShopper;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.JH.PersonalShopper.Model.Login;
import com.JH.PersonalShopper.Model.RegistrarCliente;
import com.JH.PersonalShopper.io.APIRetrofitInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DatosCliente extends AppCompatActivity {

    private EditText Direccion, Distrito, Provincia, Departamento;
    private APIRetrofitInterface jsonPlaceHolderApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datos_cliente);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://200.37.50.53/ApiPersonalShopper/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        jsonPlaceHolderApi = retrofit.create(APIRetrofitInterface.class);
        Direccion = findViewById(R.id.direccion);
        Distrito = findViewById(R.id.distrito);
        Provincia = findViewById(R.id.provincia);
        Departamento = findViewById(R.id.departamento);

    }


    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.btnRegistrar:
                RegistrarClienteF();
                break;
        }
    }

    public void RegistrarClienteF(){

        Bundle bundle = getIntent().getExtras();
        String nombredata = bundle.getString("nombredata");
        String apellidodata = bundle.getString("apellidodata");
        String generodata = bundle.getString("generodata");
        String dnidata = bundle.getString("dnidata");
        String celulardata = bundle.getString("celulardata");
        String emaildata = bundle.getString("emaildata");
        String direcciondata = Direccion.getText().toString();
        String distritodata = Distrito.getText().toString();
        String provinciadata = Provincia.getText().toString();
        String departamentodata = Departamento.getText().toString();


        RegistrarCliente registrarCliente = new RegistrarCliente(nombredata, apellidodata, generodata, dnidata, celulardata, emaildata, direcciondata, distritodata, provinciadata, departamentodata);
        Call<RegistrarCliente> callo = jsonPlaceHolderApi.registrarCliente(registrarCliente);
        callo.enqueue(new Callback<RegistrarCliente>() {
            @Override
            public void onResponse(Call<RegistrarCliente> call, Response<RegistrarCliente> response) {

                if (!response.isSuccessful()) {
                    //mJsonTxtView.setText("Codigo:" + response.code());
                    Toast.makeText(getApplicationContext(), "Datos incorrectos.", Toast.LENGTH_SHORT).show();
                    return;
                }
                RegistrarCliente postsResponse = response.body();

                //Toast.makeText(Login.this, "Bienvenido "+nombre+" "+apellido, Toast.LENGTH_SHORT).show();
                //Guardar Login SharedPreferences

                Intent i = new Intent(DatosCliente.this, MainActivity.class);
                Toast.makeText(getApplicationContext(), "Datos ingresados exitosamente", Toast.LENGTH_LONG).show();
                startActivity(i);
                finish();

            }

            @Override
            public void onFailure(Call<RegistrarCliente> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Fallo al ingresar los datos, compruebe su red.", Toast.LENGTH_SHORT).show();

                return;
            }
        });
    }

    }


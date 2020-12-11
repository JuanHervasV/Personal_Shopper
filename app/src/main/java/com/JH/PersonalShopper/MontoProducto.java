package com.JH.PersonalShopper;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.JH.PersonalShopper.Model.RegistrarCliente;
import com.JH.PersonalShopper.Model.RegistrarProducto;
import com.JH.PersonalShopper.io.APIRetrofitInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MontoProducto extends AppCompatActivity {

    private long mLastClickTime = 0;
    private EditText MontoDeposito, MedioPago, CodigoPago, Devolucion, Observaciones;
    private APIRetrofitInterface jsonPlaceHolderApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_monto_producto);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://200.37.50.53/ApiPersonalShopper/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        jsonPlaceHolderApi = retrofit.create(APIRetrofitInterface.class);

        MontoDeposito = findViewById(R.id.montodeposito);
        MedioPago = findViewById(R.id.mediopago);
        CodigoPago = findViewById(R.id.codigopago);
        Devolucion = findViewById(R.id.devolucion);
        Observaciones = findViewById(R.id.observaciones);

    }


    public void onClick(View view) {
        if (SystemClock.elapsedRealtime() - mLastClickTime < 2000) {
            return;
        }
        mLastClickTime = SystemClock.elapsedRealtime();
        switch (view.getId()) {
            case R.id.btnregistro:
                //loadingThing.startLoadingAnimation();
                RegistrarData();
                //loadingThing.dismissDialog();
                break;
        }
    }

    public void RegistrarData(){

        Bundle bundles = getIntent().getExtras();
        String id = bundles.getString("id");
        String productodata = bundles.getString("productodata");
        String tiendadata = bundles.getString("tiendadata");
        String tipopagodata = bundles.getString("tipopagodata");
        String numpagodata = bundles.getString("numpagodata");
        String valorproductodata = bundles.getString("valorproductodata");
        String tipoenviodata = bundles.getString("tipoenviodata");
        String tarifaenviodata = bundles.getString("tarifaenviodata");
        String valorscharfferdata = bundles.getString("valorscharfferdata");

        String montodepositodata = MontoDeposito.getText().toString();
        String mediopagodata = MedioPago.getText().toString();
        String codigopagodata = CodigoPago.getText().toString();
        String devoluciondata = Devolucion.getText().toString();
        String observacionesdata = Observaciones.getText().toString();

        RegistrarProducto registrarProducto = new RegistrarProducto(id, productodata, tiendadata, tipopagodata, numpagodata, valorproductodata, tarifaenviodata, tipoenviodata, valorscharfferdata, montodepositodata, mediopagodata, codigopagodata, devoluciondata, observacionesdata);
        Call<RegistrarProducto> callo = jsonPlaceHolderApi.registrarProducto(registrarProducto);
        callo.enqueue(new Callback<RegistrarProducto>() {
            @Override
            public void onResponse(Call<RegistrarProducto> call, Response<RegistrarProducto> response) {

                if (!response.isSuccessful()) {
                    //mJsonTxtView.setText("Codigo:" + response.code());
                    Toast.makeText(getApplicationContext(), "Datos incorrectos.", Toast.LENGTH_SHORT).show();
                    return;
                }
                RegistrarProducto postsResponse = response.body();

                //Toast.makeText(Login.this, "Bienvenido "+nombre+" "+apellido, Toast.LENGTH_SHORT).show();
                //Guardar Login SharedPreferences

                Intent i = new Intent(MontoProducto.this, MainActivity.class);
                Toast.makeText(getApplicationContext(), "Producto registrado!", Toast.LENGTH_LONG).show();
                startActivity(i);
                finish();

            }

            @Override
            public void onFailure(Call<RegistrarProducto> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Fallo al ingresar los datos, compruebe su red.", Toast.LENGTH_SHORT).show();

                return;
            }
        });
    }

    }

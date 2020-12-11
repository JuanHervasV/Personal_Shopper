package com.JH.PersonalShopper;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.JH.PersonalShopper.Model.CodigoCliente;
import com.JH.PersonalShopper.Model.ListarClientes;
import com.JH.PersonalShopper.io.APIRetrofitInterface;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RegProducto extends AppCompatActivity {

    private APIRetrofitInterface jsonPlaceHolderApi;
    private Button Btnenvio;
    private long mLastClickTime = 0;
    private EditText Cliente;
    private ListView listaCliente;
    private String codigodata;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg_producto);

        Btnenvio = findViewById(R.id.btnenvios);
        Cliente = findViewById(R.id.cliente);
        listaCliente = findViewById(R.id.listacliente);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://200.37.50.53/ApiPersonalShopper/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        jsonPlaceHolderApi = retrofit.create(APIRetrofitInterface.class);

        Enviardatos();

    }

    public void onClick(View view) {
        if (SystemClock.elapsedRealtime() - mLastClickTime < 2000) {
            return;
        }
        mLastClickTime = SystemClock.elapsedRealtime();
        switch (view.getId()) {
            case R.id.btnenvios:
                //loadingThing.startLoadingAnimation();
                PasarData();
                //loadingThing.dismissDialog();
                break;
        }
    }

    public void Enviardatos(){

        /////////////////////////////////////////////////////////////////////////////////////////////////////

        ListarClientes listarClientes = new ListarClientes("");
        Call<List<ListarClientes>> call = jsonPlaceHolderApi.listarclientes(listarClientes);
        call.enqueue(new Callback<List<ListarClientes>>() {
            @Override
            public void onResponse(Call<List<ListarClientes>> call, Response<List<ListarClientes>> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(RegProducto.this, "No correcto", Toast.LENGTH_SHORT).show();
                    return;
                }
                List<ListarClientes> rptas = response.body();
                int tamanio = rptas.size();

                List<String> hey = new ArrayList<>();
                List<String> hola = new ArrayList<>();


                for (int i = 0 ; i<tamanio;i++){
                    ListarClientes rpts = rptas.get(i);
                    String descripf = rpts.nombre();
                    String codef = rpts.dni();

                    hey.add(descripf);
                    hola.add(codef);

                    final ArrayAdapter<String> adapterlista = new ArrayAdapter<String>(RegProducto.this,
                            android.R.layout.simple_spinner_item, hey);

                    final ArrayAdapter<String> adapterlistacodef = new ArrayAdapter<String>(RegProducto.this,
                            android.R.layout.simple_spinner_item, hola);

                    adapterlista.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    listaCliente.setAdapter(adapterlista);

                    Cliente.addTextChangedListener(new TextWatcher() {
                        @Override
                        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                        }

                        @Override
                        public void onTextChanged(CharSequence s, int start, int before, int count) {
                            adapterlista.getFilter().filter(s);
                        }

                        @Override
                        public void afterTextChanged(Editable s) {

                        }
                    });

                }

                listaCliente.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                        String codef = listaCliente.getItemAtPosition(position).toString();

                        CodigoCliente codigoCliente = new CodigoCliente(""+codef);
                        Call<CodigoCliente> call = jsonPlaceHolderApi.codigocliente(codigoCliente);
                        call.enqueue(new Callback<CodigoCliente>() {
                            @Override
                            public void onResponse(Call<CodigoCliente> call, Response<CodigoCliente> response) {
                                if (!response.isSuccessful()) {
                                    Toast.makeText(RegProducto.this, "No correcto", Toast.LENGTH_SHORT).show();
                                    return;
                                }
                                CodigoCliente rptas = response.body();
                                String id = rptas.id();
                                codigodata = id;
                                //transportedescripdtx.setText(codigo);

                            }

                            @Override
                            public void onFailure(Call<CodigoCliente> call, Throwable t) {
                                Toast.makeText(RegProducto.this, "Fallo al ingresar los datos, compruebe su red.", Toast.LENGTH_SHORT).show();
                                return;
                            }
                        });

                        Cliente.setText(codef);
                        listaCliente.setVisibility(View.GONE);
                    }
                });


            }

            @Override
            public void onFailure(Call<List<ListarClientes>> call, Throwable t) {
                Toast.makeText(RegProducto.this, "Fallo al ingresar los datos, compruebe su red.", Toast.LENGTH_SHORT).show();
                return;
            }

        });

        /////////////////////////////////////////////////////////////////////////////////////////////////////

    }

    public void PasarData(){
        Intent intent = new Intent(RegProducto.this, DatosProducto.class);
        Bundle bundle = new Bundle();
        bundle.putString("codigodata", codigodata);
        intent.putExtras(bundle);
        startActivity(intent);
    }
}
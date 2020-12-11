package com.JH.PersonalShopper;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.JH.PersonalShopper.io.APIRetrofitInterface;

public class DatosProducto extends AppCompatActivity {

    private APIRetrofitInterface jsonPlaceHolderApi;
    private Button Btnenvio;
    private long mLastClickTime = 0;
    private EditText Producto, Tienda, NumPago, ValorProducto;
    private Spinner TipoPago;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datos_producto);

        Producto = findViewById(R.id.producto);
        Tienda = findViewById(R.id.tienda);
        TipoPago = findViewById(R.id.pagotipo);
        NumPago = findViewById(R.id.pagonum);
        ValorProducto = findViewById(R.id.valorproducto);
        Btnenvio = findViewById(R.id.btnenv);

        String[] arraySpinner = new String[] {
                "Boleta", "Factura"
        };
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, arraySpinner);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        TipoPago.setAdapter(adapter);

    }

    public void onClick(View view) {
        if (SystemClock.elapsedRealtime() - mLastClickTime < 2000) {
            return;
        }
        mLastClickTime = SystemClock.elapsedRealtime();
        switch (view.getId()) {
            case R.id.btnenv:
                //loadingThing.startLoadingAnimation();
                PasarDatos();
                //loadingThing.dismissDialog();
                break;
        }
    }

    public void PasarDatos(){

        Bundle bundle = getIntent().getExtras();
        String codigodata = bundle.getString("codigodata");
        String productodata = Producto.getText().toString();
        String tiendadata = Tienda.getText().toString();
        String tipopagodata = TipoPago.getSelectedItem().toString();
        String numpagodata = NumPago.getText().toString();
        String valorproductodata = ValorProducto.getText().toString();


        Intent intent = new Intent(DatosProducto.this, EnvioProducto.class);
        Bundle bundles = new Bundle();
        bundles.putString("id", codigodata);
        bundles.putString("productodata", productodata);
        bundles.putString("tiendadata", tiendadata);
        bundles.putString("tipopagodata", tipopagodata);
        bundles.putString("numpagodata", numpagodata);
        bundles.putString("valorproductodata", valorproductodata);
        intent.putExtras(bundles);
        startActivity(intent);

    }
}
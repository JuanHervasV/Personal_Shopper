package com.JH.PersonalShopper;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

public class EnvioProducto extends AppCompatActivity {

    private long mLastClickTime = 0;
    private EditText Tarifaenvio, Valorscharffer;
    private Spinner TipoEnvio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_envio_producto);

        TipoEnvio = findViewById(R.id.tipoenvio);
        Tarifaenvio = findViewById(R.id.tarifaenvio);
        Valorscharffer = findViewById(R.id.valorscharffer);

        String[] arraySpinner = new String[] {
                "SAIO", "HOLASCHARFF"
        };
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, arraySpinner);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        TipoEnvio.setAdapter(adapter);

    }


    public void onClick(View view) {
        if (SystemClock.elapsedRealtime() - mLastClickTime < 2000) {
            return;
        }
        mLastClickTime = SystemClock.elapsedRealtime();
        switch (view.getId()) {
            case R.id.btndeposito:
                //loadingThing.startLoadingAnimation();
                PasarData();
                //loadingThing.dismissDialog();
                break;
        }
    }

    public void PasarData(){
        Bundle bundles = getIntent().getExtras();
        String id = bundles.getString("id");
        String productodata = bundles.getString("productodata");
        String tiendadata = bundles.getString("tiendadata");
        String tipopagodata = bundles.getString("tipopagodata");
        String numpagodata = bundles.getString("numpagodata");
        String valorproductodata = bundles.getString("valorproductodata");

        String tipoenviodata = TipoEnvio.getSelectedItem().toString();
        String tarifaenviodata = Tarifaenvio.getText().toString();
        String valorscharfferdata = Valorscharffer.getText().toString();

        Intent intent = new Intent(EnvioProducto.this, MontoProducto.class);
        Bundle bundle = new Bundle();
        bundle.putString("id", id);
        bundle.putString("productodata", productodata);
        bundle.putString("tiendadata", tiendadata);
        bundle.putString("tipopagodata", tipopagodata);
        bundle.putString("numpagodata", numpagodata);
        bundle.putString("valorproductodata", valorproductodata);
        bundle.putString("tipoenviodata", tipoenviodata);
        bundle.putString("tarifaenviodata", tarifaenviodata);
        bundle.putString("valorscharfferdata", valorscharfferdata);
        intent.putExtras(bundle);
        startActivity(intent);

    }

}
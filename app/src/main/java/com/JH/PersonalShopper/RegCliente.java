package com.JH.PersonalShopper;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

public class RegCliente extends AppCompatActivity {

    private EditText Nombre, Apellido, Dni, Celular, Email;
    private Spinner Genero;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg_cliente);

        Nombre = findViewById(R.id.nombre);
        Apellido = findViewById(R.id.apellido);
        Dni = findViewById(R.id.dni);
        Genero = findViewById(R.id.genero);
        Celular = findViewById(R.id.celular);
        Email = findViewById(R.id.email);

        String[] arraySpinner = new String[] {
                "Masculino", "Femenino"
        };
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, arraySpinner);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Genero.setAdapter(adapter);

    }

    public void onClick(View view) {

            switch (view.getId()) {
                case R.id.btnEnvio:
                    PasarDatos();
                    break;
            }
    }

    public void PasarDatos(){

        String nombredata = Nombre.getText().toString();
        String apellidodata = Apellido.getText().toString();
        String generodata = Genero.getSelectedItem().toString();
        String dnidata = Dni.getText().toString();
        String celulardata = Celular.getText().toString();
        String emaildata = Email.getText().toString();

        Intent intent = new Intent(RegCliente.this, DatosCliente.class);
        Bundle bundle = new Bundle();
        bundle.putString("nombredata", nombredata);
        bundle.putString("apellidodata", apellidodata);
        bundle.putString("generodata", generodata);
        bundle.putString("dnidata", dnidata);
        bundle.putString("celulardata", celulardata);
        bundle.putString("emaildata", emaildata);
        intent.putExtras(bundle);
        startActivity(intent);

    }
}
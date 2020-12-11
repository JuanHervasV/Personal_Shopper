package com.JH.PersonalShopper;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private long mLastClickTime = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClick(View view) {
        if (SystemClock.elapsedRealtime() - mLastClickTime < 2000) {
            return;
        }
        mLastClickTime = SystemClock.elapsedRealtime();
        switch (view.getId()) {
            case R.id.regcliente:
                //loadingThing.startLoadingAnimation();
                Intent i = new Intent(MainActivity.this, RegCliente.class);
                startActivity(i);
                //loadingThing.dismissDialog();
                break;

            case R.id.regproducto:
                Intent o = new Intent(MainActivity.this, RegProducto.class);
                startActivity(o);

        }
    }
}
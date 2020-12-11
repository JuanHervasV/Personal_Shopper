package com.JH.PersonalShopper;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.SystemClock;
import android.preference.PreferenceManager;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.JH.PersonalShopper.Model.Login;
import com.JH.PersonalShopper.io.APIRetrofitInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginP extends AppCompatActivity {

    private APIRetrofitInterface jsonPlaceHolderApi;
    private Button loginbtn;
    private long mLastClickTime = 0;
    private EditText LoginText, PasswordText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://200.37.50.53/ApiPersonalShopper/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        jsonPlaceHolderApi = retrofit.create(APIRetrofitInterface.class);
        LoginText = findViewById(R.id.editText_login_username);
        PasswordText = findViewById(R.id.editText_login_password);
        onTouch();

    }

    public void onTouch() {
        loginbtn = findViewById(R.id.loginp);
        loginbtn.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                if (event.getAction() == MotionEvent.ACTION_DOWN || event.getAction() == MotionEvent.ACTION_MOVE) {
                    v.setBackgroundResource(R.drawable.rounded_cornermorado);
                }

                if (event.getAction() == MotionEvent.ACTION_UP || event.getAction() == MotionEvent.ACTION_CANCEL) {
                    v.setBackgroundResource(R.drawable.rounded_cornersscharff);
                    //v.setBackgroundColor(Color.parseColor("@drawable/rounded_corners"));
                }
                return false;
            }
        });
    }

    public void onClick(View view) {
        if (SystemClock.elapsedRealtime() - mLastClickTime < 2000) {
            return;
        }
        mLastClickTime = SystemClock.elapsedRealtime();
        switch (view.getId()) {
            case R.id.loginp:
                //loadingThing.startLoadingAnimation();
                Logearse();
                //loadingThing.dismissDialog();
                break;
        }
    }

    public void Logearse() {

        final LoadingThing loadingThing = new LoadingThing(LoginP.this);
        loadingThing.startLoadingAnimation();
        String usuario = LoginText.getText().toString();
        String password = PasswordText.getText().toString();

        Login login = new Login(usuario, password);
        Call<Login> callo = jsonPlaceHolderApi.login(login);
        callo.enqueue(new Callback<Login>() {
            @Override
            public void onResponse(Call<Login> call, Response<Login> response) {

                if (!response.isSuccessful()) {
                    //mJsonTxtView.setText("Codigo:" + response.code());
                    Toast.makeText(getApplicationContext(), "Usuario/Contraseña incorrecta.", Toast.LENGTH_SHORT).show();
                    loadingThing.dismissDialog();
                    return;
                }
                Login postsResponse = response.body();

                //Toast.makeText(Login.this, "Bienvenido "+nombre+" "+apellido, Toast.LENGTH_SHORT).show();
                //Guardar Login SharedPreferences
                loadingThing.dismissDialog();
                Intent i = new Intent(LoginP.this, MainActivity.class);
                startActivity(i);
                finish();

            }

            @Override
            public void onFailure(Call<Login> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Fallo al ingresar los datos, compruebe su red.", Toast.LENGTH_SHORT).show();
                loadingThing.dismissDialog();
                return;
            }
        });
    }
}
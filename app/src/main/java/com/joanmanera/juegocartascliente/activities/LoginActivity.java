package com.joanmanera.juegocartascliente.activities;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceManager;

import com.joanmanera.juegocartascliente.R;
import com.joanmanera.juegocartascliente.interfaces.APIUtils;
import com.joanmanera.juegocartascliente.interfaces.IPartida;
import com.joanmanera.juegocartascliente.respuestas.RespuestaLogin;
import com.joanmanera.juegocartascliente.utils.Acierto;
import com.joanmanera.juegocartascliente.utils.Alerta;
import com.joanmanera.juegocartascliente.utils.Lib;
import com.joanmanera.juegocartascliente.utils.Error;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginActivity extends AppCompatActivity {

    private TextView tvRegistro;
    private EditText etUser, etPass;
    private Button bEntrar;
    private CheckBox cbRecordar;
    private SharedPreferences sharedPreferences;
    private Retrofit retrofit;
    private IPartida login;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        if (sharedPreferences.getBoolean("cbRememberPreferences", false)){
            String usuario = sharedPreferences.getString("usuario", "");
            String pass = sharedPreferences.getString("pass", "");
            iniciarSesion(usuario, pass);
        }

        etUser = findViewById(R.id.etUser);
        etPass = findViewById(R.id.etPass);
        cbRecordar = findViewById(R.id.cbRecordar);

        tvRegistro = findViewById(R.id.tvRegistro);
        tvRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        bEntrar = findViewById(R.id.bEntrar);
        bEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String usuario = etUser.getText().toString();
                String pass = Lib.encryptPassword(etPass.getText().toString());

                if(!usuario.equals("") && !pass.equals("")){
                    iniciarSesion(usuario, pass);
                }
            }
        });
    }

    private void iniciarSesion(final String usuario, final String pass){


        login = APIUtils.getPartida();
        Call<RespuestaLogin> respuestaLoginCall = login.login(usuario+":"+pass);

        respuestaLoginCall.enqueue(new Callback<RespuestaLogin>() {
            @Override
            public void onResponse(Call<RespuestaLogin> call, Response<RespuestaLogin> response) {
                if (response.isSuccessful()){

                    if (cbRecordar.isChecked()){
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString("usuario", usuario);
                        editor.putString("pass", pass);
                        editor.putBoolean("cbRememberPreferences", true);
                        editor.commit();
                    }

                    if (response.body().getCodigoError() != Error.Login.USUARIO_PASS_INCORRECTO){
                        Intent respuestaActivityLogin = new Intent();
                        respuestaActivityLogin.putExtra("idSesion", response.body().getIdSesion());
                        respuestaActivityLogin.putExtra("usuario", response.body().getUsuario());
                        setResult(Activity.RESULT_OK, respuestaActivityLogin);
                        finish();
                    } else {
                        Toast.makeText(LoginActivity.this, "Usuario o contraseña incorrecto", Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<RespuestaLogin> call, Throwable t) {

            }
        });
    }
}

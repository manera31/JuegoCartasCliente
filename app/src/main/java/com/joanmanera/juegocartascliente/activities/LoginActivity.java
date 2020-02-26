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

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceManager;

import com.google.android.material.snackbar.Snackbar;
import com.joanmanera.juegocartascliente.R;
import com.joanmanera.juegocartascliente.utils.APIUtils;
import com.joanmanera.juegocartascliente.interfaces.ICRUD;
import com.joanmanera.juegocartascliente.interfaces.IPartida;
import com.joanmanera.juegocartascliente.modelos.Usuario;
import com.joanmanera.juegocartascliente.respuestas.RespuestaKeyValue;
import com.joanmanera.juegocartascliente.respuestas.RespuestaLogin;
import com.joanmanera.juegocartascliente.utils.Control;
import com.joanmanera.juegocartascliente.utils.Lib;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Activity para controlar el inicio de sesión y el registro de la aplicación.
 * @author Joan Manera Perez
 */

public class LoginActivity extends AppCompatActivity {

    private TextView tvRegistro, tvTitulo;
    private EditText etUser, etPass, etNombre, etApellidos;
    private Button bEntrar;
    private CheckBox cbRecordar;
    private SharedPreferences sharedPreferences;
    private boolean esRegistro;

    /**
     * Método que se encarga de cargar todas las vistas que va a tener el activity.
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        encontrarVistas();

        etNombre.setVisibility(View.GONE);
        etApellidos.setVisibility(View.GONE);
        tvRegistro.setVisibility(View.VISIBLE);
        tvTitulo.setText(R.string.login_titulo);
        bEntrar.setText(R.string.login_entrar);

        // Si esta activada la opcion de recordar usuario y contraseña inicia sesión directamente sin pedir datos.
        if (sharedPreferences.getBoolean("cbRememberPreferences", false)){
            String usuario = sharedPreferences.getString("usuario", "");
            String pass = sharedPreferences.getString("pass", "");
            iniciarSesion(usuario, pass);
        }

        // Si se pulsa sobre registro modifica el layout.
        tvRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvTitulo.setText(R.string.registro_titulo);
                etNombre.setVisibility(View.VISIBLE);
                etApellidos.setVisibility(View.VISIBLE);
                tvRegistro.setVisibility(View.GONE);
                bEntrar.setText(R.string.registro_titulo);
                esRegistro = true;
            }
        });


        // Si se pulsa sobre iniciar sesión/ registrarse.
        bEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String usuario = etUser.getText().toString();
                String pass = Lib.encryptPassword(etPass.getText().toString());

                if (esRegistro){
                    // Si es un registro ejecuta el método para guardar al nuevo usuario.
                    String nombre = etNombre.getText().toString();
                    String apellidos = etApellidos.getText().toString();

                    registrarse(usuario, pass, nombre, apellidos);

                } else if(!usuario.equals("") && !pass.equals("")){
                    // Si es un inicio de sesión se inicia sesíon.
                    iniciarSesion(usuario, pass);
                }

            }
        });
    }


    private void encontrarVistas(){
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        etNombre = findViewById(R.id.etNombre);
        etApellidos = findViewById(R.id.etApellidos);
        etUser = findViewById(R.id.etUser);
        etPass = findViewById(R.id.etPass);
        cbRecordar = findViewById(R.id.cbRecordar);
        tvTitulo = findViewById(R.id.tvTitulo);
        tvRegistro = findViewById(R.id.tvRegistro);
        bEntrar = findViewById(R.id.bEntrar);
    }

    /**
     * Hace una llamada al servidor para comprobar si el usuario y la contraseña es correcto.
     * @param usuario
     * @param pass
     */
    private void iniciarSesion(final String usuario, final String pass){

        IPartida login = APIUtils.getPartida();
        Call<RespuestaLogin> respuestaLoginCall = login.login(usuario+":"+pass);

        respuestaLoginCall.enqueue(new Callback<RespuestaLogin>() {
            @Override
            public void onResponse(Call<RespuestaLogin> call, Response<RespuestaLogin> response) {
                if (response.isSuccessful()){

                    // Si se ha marcado la casilla para recordar el usuario y la contraseña se guardan en las preferencias. Encripadas.
                    if (cbRecordar.isChecked()){
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString("usuario", usuario);
                        editor.putString("pass", pass);
                        editor.putBoolean("cbRememberPreferences", true);
                        editor.commit();
                    }

                    // Este es el código que debería ir, pero como no controlo el cerrar sesíon, solo me podria conectar una vez.
                    /*switch (response.body().getCodigoError()){
                        case Control.Login.USUARIO_PASS_INCORRECTO:
                            Toast.makeText(LoginActivity.this, "Usuario o contraseña incorrecto", Toast.LENGTH_SHORT).show();
                            break;
                        case Control.Login.YA_CONECTADO:
                            Toast.makeText(LoginActivity.this, "Usuario ya conectado", Toast.LENGTH_SHORT).show();
                            break;
                        case Control.Login.ENCONTRADO:
                            Intent respuestaActivityLogin = new Intent();
                            respuestaActivityLogin.putExtra("idSesion", response.body().getIdSesion());
                            respuestaActivityLogin.putExtra("usuario", response.body().getUsuario());
                            setResult(Activity.RESULT_OK, respuestaActivityLogin);
                            finish();
                            break;
                    }*/

                    if (response.body().getCodigoError() != Control.Login.USUARIO_PASS_INCORRECTO){
                        Intent respuestaActivityLogin = new Intent();
                        respuestaActivityLogin.putExtra("idSesion", response.body().getIdSesion());
                        respuestaActivityLogin.putExtra("usuario", response.body().getUsuario());
                        setResult(Activity.RESULT_OK, respuestaActivityLogin);
                        finish();
                    } else {
                        Snackbar.make(findViewById(android.R.id.content), "Usuario o contraseña incorrecto", Snackbar.LENGTH_LONG).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<RespuestaLogin> call, Throwable t) {
                // Si el servidor está apagado y no lo encuentra, mostrará un mensaje.
                Snackbar.make(findViewById(android.R.id.content), "No se ha podido conectar con el servidor", Snackbar.LENGTH_LONG).show();
            }
        });
    }

    /**
     * Se conecta al servidor pasandole los parametros para registrar a un nuevo usuario.
     * @param user
     * @param pass
     * @param nombre
     * @param apellidos
     */
    private void registrarse(final String user, String pass, String nombre, String apellidos){

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(APIUtils.URL_CRUD)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ICRUD registro = retrofit.create(ICRUD.class);

        Call<RespuestaKeyValue> respuestaRegistro = registro.crearUsuario(new Usuario(user, nombre, apellidos, pass));

        respuestaRegistro.enqueue(new Callback<RespuestaKeyValue>() {
            @Override
            public void onResponse(Call<RespuestaKeyValue> call, Response<RespuestaKeyValue> response) {
                if(response.isSuccessful()){
                    switch (response.body().getMap().get(user)){
                        case Control.CRUD.USUARIO_EXISTE:
                            Snackbar.make(findViewById(android.R.id.content), "El usuario ya esta registrado", Snackbar.LENGTH_LONG).show();
                            break;
                        case Control.CRUD.USUARIO_FALLO_GENERAL:
                            Snackbar.make(findViewById(android.R.id.content), "Fallo general", Snackbar.LENGTH_LONG).show();
                            break;
                        case Control.CRUD.USUARIO_ANADIDO:
                            Snackbar.make(findViewById(android.R.id.content), "Usuario añadido con éxito!", Snackbar.LENGTH_LONG).show();
                            etNombre.setVisibility(View.GONE);
                            etApellidos.setVisibility(View.GONE);
                            tvRegistro.setVisibility(View.VISIBLE);
                            tvTitulo.setText(R.string.login_titulo);
                            bEntrar.setText(R.string.login_entrar);
                            esRegistro = false;
                            break;
                    }
                }
            }

            @Override
            public void onFailure(Call<RespuestaKeyValue> call, Throwable t) {

            }
        });
    }
}

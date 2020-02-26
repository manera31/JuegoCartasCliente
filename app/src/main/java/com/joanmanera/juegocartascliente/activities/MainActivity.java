package com.joanmanera.juegocartascliente.activities;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.joanmanera.juegocartascliente.R;
import com.joanmanera.juegocartascliente.fragments.FragmentMenuPrincipal;
import com.joanmanera.juegocartascliente.fragments.FragmentResultadoPartido;
import com.joanmanera.juegocartascliente.modelos.EstadisticaUsuario;
import com.joanmanera.juegocartascliente.utils.Datos;
import com.joanmanera.juegocartascliente.modelos.Usuario;
import com.joanmanera.juegocartascliente.respuestas.RespuestaResultadoMano;

import java.util.ArrayList;

/**
 * Activity para controlar la pantalla principal de la aplicación.
 * @author Joan Manera Perez
 */
public class  MainActivity extends AppCompatActivity implements View.OnClickListener {

    private FragmentMenuPrincipal fragmentMenuPrincipal;
    private String idSesion;

    /**
     * Carga los datos y inicia la activity de iniciar sesión.
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Carga las cartas y las deja en memoria.
        Datos.cargarCartas();

        // Inica el activity que controla el inicio de sesión / registro.
        Intent login = new Intent(this, LoginActivity.class);
        startActivityForResult(login, 1);
    }

    /**
     * Método para recibir la respuesta del inicio de sesión y la respuesta del resultado de la partida.
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            // Respuesta de la pantalla de inicio de sesión.
            case 1:
                if (resultCode == Activity.RESULT_OK) {
                    // Guardamos su identificador de sesión y su usuario.
                    idSesion = data.getStringExtra("idSesion");
                    Usuario usuario = (Usuario)data.getSerializableExtra("usuario");

                    // Iniciamos el fragment de la pantalla principal.
                    fragmentMenuPrincipal = new FragmentMenuPrincipal(this, usuario);
                    getSupportFragmentManager().beginTransaction().replace(R.id.contenedor, fragmentMenuPrincipal).commit();
                }
                break;

            // Respuesta de la pantalla de juego. Indica que se ha finalizado una partida.
            case 2:
                if (resultCode == Activity.RESULT_OK){
                    RespuestaResultadoMano respuestaResultadoMano = (RespuestaResultadoMano) data.getSerializableExtra("respuestaResultadoMano");

                    // Iniciamos el fragment para mostrar el resultado de la partida.
                    FragmentResultadoPartido fragmentResultadoPartido = new FragmentResultadoPartido(respuestaResultadoMano, this);
                    getSupportFragmentManager().beginTransaction().replace(R.id.contenedor, fragmentResultadoPartido).commit();
                }
                break;

        }

    }

    /**
     * Controla los eventos click que estén asociados.
     * @param v
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.bNuevaPartida:
                Intent juego = new Intent(this, JuegoActivity.class);
                juego.putExtra("idSesion", idSesion);
                startActivityForResult(juego, 2);

                break;
            case R.id.bContinuarFRP:
                getSupportFragmentManager().beginTransaction().replace(R.id.contenedor, fragmentMenuPrincipal).commit();

                break;
        }


    }

    /**
     * Carga las opciones del menu.
     * @param menu
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_preferencias, menu);
        return true;
    }

    /**
     * Controla el evento click cuando se pulsa sobre un elemento del menu.
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.itemPreferencias){
            Intent preferencias = new Intent(this, PreferenciasActivity.class);
            startActivity(preferencias);
        }
        return true;
    }

    @Override
    protected void onStop() {
        //TODO
        /*Retrofit builder = new Retrofit.Builder()
                .baseUrl(APIUtils.URL_PARTIDA)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        IPartida partida = builder.create(IPartida.class);

        partida.cerrarSesion(idSesion);

        Toast.makeText(this, "stop", Toast.LENGTH_SHORT).show();*/
        super.onStop();
    }



}

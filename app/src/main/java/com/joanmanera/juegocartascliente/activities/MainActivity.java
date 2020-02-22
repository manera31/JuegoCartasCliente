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
import android.widget.Toast;

import com.joanmanera.juegocartascliente.R;
import com.joanmanera.juegocartascliente.fragments.FragmentMenuPrincipal;
import com.joanmanera.juegocartascliente.fragments.FragmentResultadoPartido;
import com.joanmanera.juegocartascliente.interfaces.APIUtils;
import com.joanmanera.juegocartascliente.interfaces.IPartida;
import com.joanmanera.juegocartascliente.utils.Datos;
import com.joanmanera.juegocartascliente.modelos.Usuario;
import com.joanmanera.juegocartascliente.respuestas.RespuestaResultadoMano;

import java.util.Date;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class  MainActivity extends AppCompatActivity implements View.OnClickListener {

    private FragmentMenuPrincipal fragmentMenuPrincipal;
    private String idSesion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Datos.cargarCartas();
        Datos.cargarEstadisticas();


        Intent login = new Intent(this, LoginActivity.class);
        startActivityForResult(login, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case 1:
                if (resultCode == Activity.RESULT_OK) {
                    idSesion = data.getStringExtra("idSesion");
                    Usuario usuario = (Usuario)data.getSerializableExtra("usuario");

                    fragmentMenuPrincipal = new FragmentMenuPrincipal(this, usuario, Datos.getEstadisticaUsuarios());
                    getSupportFragmentManager().beginTransaction().replace(R.id.contenedor, fragmentMenuPrincipal).commit();
                }
                break;
            case 2:
                if (resultCode == Activity.RESULT_OK){
                    RespuestaResultadoMano respuestaResultadoMano = (RespuestaResultadoMano) data.getSerializableExtra("respuestaResultadoMano");

                    FragmentResultadoPartido fragmentResultadoPartido = new FragmentResultadoPartido(respuestaResultadoMano, this);
                    getSupportFragmentManager().beginTransaction().replace(R.id.contenedor, fragmentResultadoPartido).commit();
                }
                break;

        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.bNuevaPartida:
                Intent juego = new Intent(this, JuegoActivity.class);
                juego.putExtra("cartas", Datos.getCartas());
                juego.putExtra("idSesion", idSesion);
                startActivityForResult(juego, 2);

                break;
            case R.id.bContinuarFRP:
                getSupportFragmentManager().beginTransaction().replace(R.id.contenedor, fragmentMenuPrincipal).commit();

                break;
        }


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_preferencias, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.itemPreferencias){
            Intent preferencias = new Intent(this, PreferenciasActivity.class);
            startActivity(preferencias);
        }
        return true;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

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

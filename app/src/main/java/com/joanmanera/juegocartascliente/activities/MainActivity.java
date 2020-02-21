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
import com.joanmanera.juegocartascliente.interfaces.APIUtils;
import com.joanmanera.juegocartascliente.interfaces.ICRUD;
import com.joanmanera.juegocartascliente.modelos.Carta;
import com.joanmanera.juegocartascliente.modelos.Usuario;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class  MainActivity extends AppCompatActivity implements View.OnClickListener {

    private ArrayList<Carta> cartas;
    private FragmentMenuPrincipal fragmentMenuPrincipal;
    private String idSesion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Intent login = new Intent(this, LoginActivity.class);
        startActivityForResult(login, 1);



    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if (resultCode == Activity.RESULT_OK) {
                cargarCartas();

                idSesion = data.getStringExtra("idSesion");
                Usuario usuario = (Usuario)data.getSerializableExtra("usuario");

                fragmentMenuPrincipal = new FragmentMenuPrincipal(this, usuario);
                getSupportFragmentManager().beginTransaction().replace(R.id.contenedor, fragmentMenuPrincipal).commit();
            }
        }

    }

    private void cargarCartas(){

        ICRUD iCartas = APIUtils.getCRUD();

        Call<List<Carta>> respuestaCartas = iCartas.getCartas();

        respuestaCartas.enqueue(new Callback<List<Carta>>() {
            @Override
            public void onResponse(Call<List<Carta>> call, Response<List<Carta>> response) {
                if (response.isSuccessful()){
                    cartas = new ArrayList<>();
                    for (Carta c: response.body()){
                        cartas.add(c);
                    }

                }
            }

            @Override
            public void onFailure(Call<List<Carta>> call, Throwable t) {

            }
        });
    }

    @Override
    public void onClick(View v) {
        /*fragmentJuego = new FragmentJuego(cartas);
        getSupportFragmentManager().beginTransaction().replace(R.id.contenedor, fragmentJuego).addToBackStack(null).commit();*/
        Intent juego = new Intent(this, JuegoActivity.class);
        juego.putExtra("cartas", cartas);
        juego.putExtra("idSesion", idSesion);
        startActivity(juego);

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
}

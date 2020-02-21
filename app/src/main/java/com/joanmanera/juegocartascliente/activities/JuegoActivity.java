package com.joanmanera.juegocartascliente.activities;

import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.joanmanera.juegocartascliente.R;
import com.joanmanera.juegocartascliente.fragments.FragmentJuego;
import com.joanmanera.juegocartascliente.interfaces.APIUtils;
import com.joanmanera.juegocartascliente.interfaces.IPartida;
import com.joanmanera.juegocartascliente.modelos.Carta;
import com.joanmanera.juegocartascliente.respuestas.RespuestaNuevoJuego;
import com.joanmanera.juegocartascliente.utils.*;
import com.joanmanera.juegocartascliente.utils.Error;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class JuegoActivity extends AppCompatActivity {

    private Retrofit retrofit;
    private IPartida partida;

    private String idSesion;
    private String idPartida;
    private ArrayList<Carta> cartasJugador, cartasTodas;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_juego);

        cartasTodas = (ArrayList<Carta>)getIntent().getExtras().getSerializable("cartas");
        idSesion = getIntent().getExtras().getString("idSesion");

        crearJuego(idSesion);

    }

    private void crearJuego(String idSesion){

        partida = APIUtils.getPartida();
        Call<RespuestaNuevoJuego> respuestaNuevoJuegoCall = partida.crearJuego(idSesion);

        respuestaNuevoJuegoCall.enqueue(new Callback<RespuestaNuevoJuego>() {
            @Override
            public void onResponse(Call<RespuestaNuevoJuego> call, Response<RespuestaNuevoJuego> response) {
                if (response.isSuccessful()){
                    RespuestaNuevoJuego respuesta = response.body();

                    switch (respuesta.getCodigoError()){
                        case Error.Sesion.CADUCADA:
                            Toast.makeText(JuegoActivity.this, "La sesión está caducada", Toast.LENGTH_SHORT).show();
                            break;
                        case Error.Sesion.NO_ENCONTRADA:
                            Toast.makeText(JuegoActivity.this, "No se ha podido encontrar la sesión", Toast.LENGTH_SHORT).show();
                            break;
                        case Acierto.Sesion.ENCONTRADO:
                            idPartida = respuesta.getIdPartida();
                            cartasJugador = respuesta.getCartasJugador();
                            Toast.makeText(JuegoActivity.this, "entra", Toast.LENGTH_SHORT).show();
                            FragmentJuego fragmentJuego = new FragmentJuego(cartasJugador);
                            getSupportFragmentManager().beginTransaction().replace(R.id.contenedorJuego, fragmentJuego).addToBackStack(null).commit();
                            break;
                    }
                }
            }

            @Override
            public void onFailure(Call<RespuestaNuevoJuego> call, Throwable t) {

            }
        });
    }
}

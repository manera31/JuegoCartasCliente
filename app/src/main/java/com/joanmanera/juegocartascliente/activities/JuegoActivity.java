package com.joanmanera.juegocartascliente.activities;

import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.joanmanera.juegocartascliente.R;
import com.joanmanera.juegocartascliente.fragments.FragmentJuego;
import com.joanmanera.juegocartascliente.interfaces.APIUtils;
import com.joanmanera.juegocartascliente.interfaces.IPartida;
import com.joanmanera.juegocartascliente.interfaces.IRespuestas;
import com.joanmanera.juegocartascliente.modelos.Carta;
import com.joanmanera.juegocartascliente.respuestas.EnvioJugarCarta;
import com.joanmanera.juegocartascliente.respuestas.RespuestaGanadorMano;
import com.joanmanera.juegocartascliente.respuestas.RespuestaJugarCartaCPU;
import com.joanmanera.juegocartascliente.respuestas.RespuestaNuevoJuego;
import com.joanmanera.juegocartascliente.respuestas.RespuestaSorteo;
import com.joanmanera.juegocartascliente.utils.*;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class JuegoActivity extends AppCompatActivity implements IRespuestas {

    private Retrofit retrofit;
    private IPartida partida;

    private String idSesion;
    private String idPartida;
    private int mano;
    private ArrayList<Carta> cartasJugador, cartasTodas;

    private StringBuilder idSesionPartida;
    private RespuestaJugarCartaCPU respuestaJugarCartaCPU;
    private IRespuestas listener;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_juego);

        cartasTodas = (ArrayList<Carta>)getIntent().getExtras().getSerializable("cartas");
        idSesion = getIntent().getExtras().getString("idSesion");

        partida = APIUtils.getPartida();
        listener = this;

        crearJuego(idSesion);

    }

    private void crearJuego(String idSesion){


        Call<RespuestaNuevoJuego> respuestaNuevoJuegoCall = partida.crearJuego(idSesion);

        respuestaNuevoJuegoCall.enqueue(new Callback<RespuestaNuevoJuego>() {
            @Override
            public void onResponse(Call<RespuestaNuevoJuego> call, Response<RespuestaNuevoJuego> response) {
                if (response.isSuccessful()){
                    RespuestaNuevoJuego respuesta = response.body();

                    switch (respuesta.getCodigoError()){
                        case Control.Sesion.CADUCADA:
                            Toast.makeText(JuegoActivity.this, "La sesión está caducada", Toast.LENGTH_SHORT).show();
                            break;
                        case Control.Sesion.NO_ENCONTRADA:
                            Toast.makeText(JuegoActivity.this, "No se ha podido encontrar la sesión", Toast.LENGTH_SHORT).show();
                            break;
                        case Control.Sesion.ENCONTRADA:
                            idPartida = respuesta.getIdPartida();
                            cartasJugador = respuesta.getCartasJugador();
                            Toast.makeText(JuegoActivity.this, "Encontrada", Toast.LENGTH_SHORT).show();

                            sortearInicio();

                            break;
                    }
                }
            }

            @Override
            public void onFailure(Call<RespuestaNuevoJuego> call, Throwable t) {

            }
        });
    }

    private void sortearInicio(){

        idSesionPartida = new StringBuilder();
        idSesionPartida.append(idSesion).append(":").append(idPartida);

        Call<RespuestaSorteo> respuestaSorteoCall = partida.sortearInicio(idSesionPartida.toString());

        respuestaSorteoCall.enqueue(new Callback<RespuestaSorteo>() {
            @Override
            public void onResponse(Call<RespuestaSorteo> call, Response<RespuestaSorteo> response) {
                if (response.isSuccessful()){
                    RespuestaSorteo respuestaSorteo = response.body();

                    switch (respuestaSorteo.getCondigoError()){
                        case Control.Sesion.CADUCADA:
                            Toast.makeText(JuegoActivity.this, "La sesión está caducada", Toast.LENGTH_SHORT).show();
                            break;
                        case Control.Sesion.NO_ENCONTRADA:
                            Toast.makeText(JuegoActivity.this, "No se ha podido encontrar la sesión", Toast.LENGTH_SHORT).show();
                            break;
                        case Control.Partida.NO_ENCONTRADA:
                            Toast.makeText(JuegoActivity.this, "No se ha podido encontrar la partida", Toast.LENGTH_SHORT).show();
                            break;
                        case Control.Partida.ENCONTRADA:

                            jugar(respuestaSorteo.getTurno());

                            break;
                    }
                }
            }

            @Override
            public void onFailure(Call<RespuestaSorteo> call, Throwable t) {

            }
        });

    }

    private void jugar(Enums.Turno turno){

        switch (turno){
            case CPU:
                recibirCartaCPU();
                break;
            case Jugador:
                break;
        }



    }

    private void recibirCartaCPU() {

        Call<RespuestaJugarCartaCPU> respuestaJugarCartaCPUCall = partida.clienteListo(idSesionPartida.toString());

        respuestaJugarCartaCPUCall.enqueue(new Callback<RespuestaJugarCartaCPU>() {
            @Override
            public void onResponse(Call<RespuestaJugarCartaCPU> call, Response<RespuestaJugarCartaCPU> response) {
                if (response.isSuccessful()) {
                    listener.onCartaRecibidaCPU(response.body());
                }
            }

            @Override
            public void onFailure(Call<RespuestaJugarCartaCPU> call, Throwable t) {

            }
        });

    }

    @Override
    public void onCartaRecibidaCPU(RespuestaJugarCartaCPU respuestaJugarCartaCPU) {
        if (mano <= 6){
            FragmentJuego fragmentJuego = new FragmentJuego(cartasJugador, respuestaJugarCartaCPU.getCaracteristica(), this);
            getSupportFragmentManager().beginTransaction().replace(R.id.contenedorJuego, fragmentJuego).addToBackStack(null).commit();
        }
    }

    @Override
    public void onRespuestaGanadorMano(RespuestaGanadorMano respuestaGanadorMano) {

    }

    @Override
    public void onSeleccionCartaJugador(EnvioJugarCarta envioJugarCarta) {

    }
}

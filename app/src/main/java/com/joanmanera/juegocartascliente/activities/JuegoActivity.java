package com.joanmanera.juegocartascliente.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.joanmanera.juegocartascliente.R;
import com.joanmanera.juegocartascliente.fragments.FragmentJuego;
import com.joanmanera.juegocartascliente.fragments.FragmentResultadoMano;
import com.joanmanera.juegocartascliente.interfaces.APIUtils;
import com.joanmanera.juegocartascliente.interfaces.IPartida;
import com.joanmanera.juegocartascliente.interfaces.IRespuestas;
import com.joanmanera.juegocartascliente.modelos.Carta;
import com.joanmanera.juegocartascliente.respuestas.EnvioJugarCarta;
import com.joanmanera.juegocartascliente.respuestas.RespuestaResultadoMano;
import com.joanmanera.juegocartascliente.respuestas.RespuestaJugarCartaCPU;
import com.joanmanera.juegocartascliente.respuestas.RespuestaNuevoJuego;
import com.joanmanera.juegocartascliente.respuestas.RespuestaSorteo;
import com.joanmanera.juegocartascliente.utils.*;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Activity para controlar las tiradas del jugador.
 * @author Joan Manera Perez
 */
public class JuegoActivity extends AppCompatActivity implements IRespuestas, View.OnClickListener {

    private IPartida partida;

    private String idSesion;
    private String idPartida;
    private int mano;
    private ArrayList<Carta> cartasJugador, cartasTodas;

    private StringBuilder idSesionPartida;
    private IRespuestas listener;
    private Enums.Turno turno;
    private RespuestaResultadoMano respuestaResultadoManoAux;

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

    /**
     * Hace una petición a la api y crea una nueva partida en el servidor.
     * @param idSesion
     */
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

    /**
     * Hace una petición al servidor para sortear quin empieza sacando.
     */
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

                            turno = respuestaSorteo.getTurno();
                            jugar();

                            break;
                    }
                }
            }

            @Override
            public void onFailure(Call<RespuestaSorteo> call, Throwable t) {

            }
        });

    }

    private void jugar(){
        switch (turno){
            case CPU:
                recibirCartaCPU();
                break;
            case Jugador:
                FragmentJuego fragmentJuego = new FragmentJuego(cartasJugador, null, JuegoActivity.this, mano);
                getSupportFragmentManager().beginTransaction().replace(R.id.contenedorJuego, fragmentJuego).commit();
                break;
        }
    }

    /**
     * Llama al api diciendo que el cliente esta listo y le devuelve la caracteristica de la CPU.
     */
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

    /**
     * Listener para controlar cuando se recibe la caracteristica de la CPU.
     * @param respuestaJugarCartaCPU
     */
    @Override
    public void onCartaRecibidaCPU(RespuestaJugarCartaCPU respuestaJugarCartaCPU) {
        FragmentJuego fragmentJuego = new FragmentJuego(cartasJugador, respuestaJugarCartaCPU.getCaracteristica(), this, mano);
        getSupportFragmentManager().beginTransaction().replace(R.id.contenedorJuego, fragmentJuego).commit();
    }

    /**
     * Listener para controlar cuando se recible la respuesta del ganador de la mano.
     * @param respuestaResultadoMano
     */
    @Override
    public void onRespuestaGanadorMano(RespuestaResultadoMano respuestaResultadoMano) {
        respuestaResultadoManoAux = respuestaResultadoMano;
        FragmentResultadoMano fragmentResultadoMano = new FragmentResultadoMano(respuestaResultadoMano, this);
        getSupportFragmentManager().beginTransaction().replace(R.id.contenedorJuego, fragmentResultadoMano).commit();
    }

    /**
     * Listener para controlar cuando el jugador selecciona una carta de su baraja.
     * @param idCarta
     * @param caracteristica
     */
    @Override
    public void onSeleccionCartaJugador(int idCarta, Enums.Caracteristica caracteristica) {
        EnvioJugarCarta envioJugarCarta = new EnvioJugarCarta(idSesion, idPartida, idCarta, caracteristica);

        Call<RespuestaResultadoMano> respuestaGanadorManoCall = partida.jugarCarta(envioJugarCarta);

        respuestaGanadorManoCall.enqueue(new Callback<RespuestaResultadoMano>() {
            @Override
            public void onResponse(Call<RespuestaResultadoMano> call, Response<RespuestaResultadoMano> response) {
                if (response.isSuccessful()){
                    listener.onRespuestaGanadorMano(response.body());
                }
            }

            @Override
            public void onFailure(Call<RespuestaResultadoMano> call, Throwable t) {

            }
        });
    }


    /**
     * Cotnrola el click del boton continuar.
     * @param v
     */
    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.bContinuarFRM){
            mano++;
            if (mano < 6){
                if (turno == Enums.Turno.CPU){
                    turno = Enums.Turno.Jugador;
                } else {
                    turno = Enums.Turno.CPU;
                }
                jugar();
            } else {
                Intent respuesta = new Intent();
                respuesta.putExtra("respuestaResultadoMano", respuestaResultadoManoAux);
                setResult(Activity.RESULT_OK, respuesta);
                finish();
            }

        }
    }
}

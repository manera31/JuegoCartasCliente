package com.joanmanera.juegocartascliente.activities;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceManager;

import com.joanmanera.juegocartascliente.R;
import com.joanmanera.juegocartascliente.fragments.FragmentJuego;
import com.joanmanera.juegocartascliente.fragments.FragmentResultadoMano;
import com.joanmanera.juegocartascliente.modelos.CartaEstadistica;
import com.joanmanera.juegocartascliente.utils.APIUtils;
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
import java.util.HashMap;

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
    private Enums.Bot modoBot;
    private ArrayList<Carta> cartasJugador;
    private ArrayList<CartaEstadistica> cartaEstadisticas;

    private StringBuilder idSesionPartida;
    private IRespuestas listener;
    private Enums.Turno turno;
    private RespuestaResultadoMano respuestaResultadoManoAux;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_juego);

        idSesion = getIntent().getExtras().getString("idSesion");

        partida = APIUtils.getPartida();
        listener = this;

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        String[] dificultades = getResources().getStringArray(R.array.preferencias_niveles_dificultad);
        String valorDificultad = sharedPreferences.getString("lista", "");

        if (dificultades[1].equals(valorDificultad)){
            modoBot = Enums.Bot.ALEATORIO;
        } else if(dificultades[2].equals(valorDificultad)){
            modoBot = Enums.Bot.INTELOGENCIA_SUPREMA;
        } else {
            modoBot = Enums.Bot.DESACTIVADO;
        }

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

                            if (modoBot == Enums.Bot.INTELOGENCIA_SUPREMA){
                                cargarEstadisticasCartasJugador();
                            }

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
                switch (modoBot){
                    case DESACTIVADO:
                        FragmentJuego fragmentJuego = new FragmentJuego(cartasJugador, null, JuegoActivity.this, mano);
                        getSupportFragmentManager().beginTransaction().replace(R.id.contenedorJuego, fragmentJuego).commit();
                        break;
                    case ALEATORIO:
                        int idCartaAleatoria = seleccionarCartaAleatoria();
                        Enums.Caracteristica caracteristicaAleatoria = seleccionarCaracteristicaAleatoria();
                        listener.onSeleccionCartaJugador(idCartaAleatoria, caracteristicaAleatoria);
                        break;
                    case INTELOGENCIA_SUPREMA:
                        Object[] idCartaCaracteristica = seleccionarCartaEstadistica();
                        int idCarta = (int)idCartaCaracteristica[0];
                        Enums.Caracteristica caracteristica = (Enums.Caracteristica)idCartaCaracteristica[1];
                        listener.onSeleccionCartaJugador(idCarta, caracteristica);
                        break;
                    case NIVEL_DIOS:
                        break;
                }

                break;
        }
    }

    private Enums.Caracteristica seleccionarCaracteristicaAleatoria() {
        int numeroAleatorio = Lib.getRandom(Enums.Caracteristica.values().length-1, 0);
        return Enums.Caracteristica.values()[numeroAleatorio];
    }

    private int seleccionarCartaAleatoria() {
        int numeroAleatorio = Lib.getRandom(cartasJugador.size()-1, 0);
        int idCartaAleatoria = cartasJugador.get(numeroAleatorio).getId();
        cartasJugador.remove(numeroAleatorio);
        return idCartaAleatoria;
    }

    private void cargarEstadisticasCartasJugador(){
        final double difMot = 2129, difPot = 166, difVel = 80, difCil = 2, difRPM = 1500, difCon = 12.2;
        final double minMot = 1461, minPot = 64, minVel = 160, minCil = 4, minRPM = 4600, minCon = 5.8;

        // Calculo del valor de cada carta
        cartaEstadisticas = new ArrayList<>();

        HashMap<Enums.Caracteristica, Double> map;

        for (Carta c: cartasJugador){
            map = new HashMap<>();
            map.put(Enums.Caracteristica.MOTOR, calculoEstadictica(c.getMotor(), minMot, difMot));
            map.put(Enums.Caracteristica.POTENCIA, calculoEstadictica(c.getPotencia(), minPot, difPot));
            map.put(Enums.Caracteristica.VELOCIDAD, calculoEstadictica(c.getVelocidad(), minVel, difVel));
            map.put(Enums.Caracteristica.CILINDROS, calculoEstadictica(c.getCilindros(), minCil, difCil));
            map.put(Enums.Caracteristica.RPM, 100 - calculoEstadictica(c.getRpm(), minRPM, difRPM));
            map.put(Enums.Caracteristica.CONSUMO, 100 - calculoEstadictica(c.getConsumo(), minCon, difCon));

            cartaEstadisticas.add(new CartaEstadistica(c, map));
        }

    }

    private double calculoEstadictica(double valor, double minimo, double diferencia){
        return ((valor - minimo) * 100) / diferencia;
    }

    private Object[] seleccionarCartaEstadistica(){
        CartaEstadistica cartaAux = null;
        int idCarta = -1;
        double maxValorAux = -1, maxValor = -1;
        Enums.Caracteristica caracteristicaAux = null, caracteristica = null;


        for (CartaEstadistica ce: cartaEstadisticas){
            for (Enums.Caracteristica caracteristica1: Enums.Caracteristica.values()){
                if (ce.getMapaEstadisticas().get(caracteristica1) > maxValorAux){
                    maxValorAux = ce.getMapaEstadisticas().get(caracteristica1);
                    caracteristicaAux = caracteristica1;
                }
            }
            if (maxValorAux > maxValor){
                cartaAux = ce;
                idCarta = ce.getId();
                maxValor = maxValorAux;
                caracteristica = caracteristicaAux;
            }
        }

        cartaEstadisticas.remove(cartaAux);

        return new Object[]{idCarta, caracteristica};
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
                    switch (modoBot){
                        case DESACTIVADO:
                            listener.onCartaRecibidaCPU(response.body());
                            break;
                        case ALEATORIO:
                            int idCartaAleatoria = seleccionarCartaAleatoria();
                            Enums.Caracteristica caracteristicaAleatoria = response.body().getCaracteristica();
                            listener.onSeleccionCartaJugador(idCartaAleatoria, caracteristicaAleatoria);

                            break;
                        case INTELOGENCIA_SUPREMA:
                            int idCarta = seleccionarCartaEstadistica(response.body().getCaracteristica());
                            listener.onSeleccionCartaJugador(idCarta, response.body().getCaracteristica());

                            break;
                        case NIVEL_DIOS:
                            break;
                    }
                }
            }

            @Override
            public void onFailure(Call<RespuestaJugarCartaCPU> call, Throwable t) {

            }
        });

    }

    private int seleccionarCartaEstadistica(Enums.Caracteristica caracteristica){
        CartaEstadistica cartaAux = null;
        int idCarta = -1;
        double maxValor = -1;


        for (CartaEstadistica ce: cartaEstadisticas){
            if (ce.getMapaEstadisticas().get(caracteristica) > maxValor){
                cartaAux = ce;
                idCarta = ce.getId();
                maxValor = ce.getMapaEstadisticas().get(caracteristica);
            }
        }

        cartaEstadisticas.remove(cartaAux);

        return idCarta;
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

package com.joanmanera.juegocartascliente.dialogos;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.joanmanera.juegocartascliente.R;
import com.joanmanera.juegocartascliente.interfaces.IRespuestas;
import com.joanmanera.juegocartascliente.modelos.Carta;
import com.joanmanera.juegocartascliente.respuestas.EnvioJugarCarta;
import com.joanmanera.juegocartascliente.utils.Enums;

/**
 * Dialogo para seleccionar una característica del jugador.
 * @author Joan Manera Perez
 */
public class DialogoSeleccionCaracteristica extends DialogFragment {

    private RadioGroup rgCaracteristicas;
    private RadioButton rbMotor, rbCilindros, rbPotencia, rbRPM, rbVelocidad, rbConsumo;
    private Carta carta;
    private IRespuestas listener;

    /**
     * Constructor.
     * @param carta
     * @param listener
     */
    public DialogoSeleccionCaracteristica (Carta carta, IRespuestas listener){
        this.carta = carta;
        this.listener = listener;
    }

    /**
     * Crea el dialogo.
     * @param savedInstanceState
     * @return dialogo
     */
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();

        final View view = inflater.inflate(R.layout.dialogo_seleccion_caracteristica, null);

        rgCaracteristicas = view.findViewById(R.id.rgCaracteristicas);
        rbMotor = view.findViewById(R.id.rbMotor);
        rbCilindros = view.findViewById(R.id.rbCilindros);
        rbPotencia = view.findViewById(R.id.rbPotencia);
        rbRPM = view.findViewById(R.id.rbRPM);
        rbVelocidad = view.findViewById(R.id.rbVelocidad);
        rbConsumo = view.findViewById(R.id.rbConsumo);

        // Se muestra las características de la carta.
        rbMotor.append(" " + carta.getMotor());
        rbCilindros.append(" " + carta.getCilindros());
        rbPotencia.append(" " + carta.getPotencia());
        rbRPM.append(" " + carta.getRpm());
        rbVelocidad.append(" " + carta.getVelocidad());
        rbConsumo.append(" " + carta.getConsumo());

        builder.setView(view)
                .setPositiveButton("Enviar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Enums.Caracteristica caracteristica = null;
                        int i = rgCaracteristicas.getCheckedRadioButtonId();
                        // Comprueba que característica se ha marcado.
                        switch (i){
                            case R.id.rbMotor:
                                caracteristica = Enums.Caracteristica.MOTOR;
                                break;
                            case R.id.rbCilindros:
                                caracteristica = Enums.Caracteristica.CILINDROS;
                                break;
                            case R.id.rbPotencia:
                                caracteristica = Enums.Caracteristica.POTENCIA;
                                break;
                            case R.id.rbRPM:
                                caracteristica = Enums.Caracteristica.RPM;
                                break;
                            case R.id.rbVelocidad:
                                caracteristica = Enums.Caracteristica.VELOCIDAD;
                                break;
                            case R.id.rbConsumo:
                                caracteristica = Enums.Caracteristica.CONSUMO;
                                break;
                            default:

                                break;
                        }
                        // Envía el identificador de la carta y la característica.
                        if (caracteristica != null) {
                            listener.onSeleccionCartaJugador(carta.getId(), caracteristica);
                        }

                    }
                })
                .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
        return builder.create();
    }
}

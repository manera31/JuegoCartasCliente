package com.joanmanera.juegocartascliente.dialogos;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.joanmanera.juegocartascliente.utils.Enums;

public class DialogoPrimeroEnTirar extends DialogFragment {

    private Enums.PrimeroEnSacar primeroEnSacar;

    public DialogoPrimeroEnTirar (Enums.PrimeroEnSacar primeroEnSacar){
        this.primeroEnSacar = primeroEnSacar;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage(primeroEnSacar.toString())
                .setTitle("Información sorteo")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
        return builder.create();
    }
}

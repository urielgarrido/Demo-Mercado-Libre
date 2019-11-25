package com.example.testmercadolibre.utils;

import android.app.AlertDialog;
import android.content.Context;

import com.example.testmercadolibre.R;

public class ProgressBarDialog {

    private static ProgressBarDialog instacia;
    private AlertDialog alertDialog;

    public static synchronized ProgressBarDialog getInstance(){
        if (instacia == null){
            return instacia = new ProgressBarDialog();
        }else return instacia;
    }


    public AlertDialog mostrarProgressBarDialog(Context context){

        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(context);
        dialogBuilder.setView(R.layout.progress_bar_dialog_layout);

        return alertDialog = dialogBuilder.create();
    }

    public void desaparecerProgressBarDialog(){
        alertDialog.dismiss();
    }
}

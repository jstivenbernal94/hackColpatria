package com.villa.deimer.hackathon.views.dialogs;

import android.content.Context;
import dmax.dialog.SpotsDialog;
import android.app.AlertDialog;
import android.content.DialogInterface;

import com.villa.deimer.hackathon.R;
import com.villa.deimer.hackathon.models.services.events.EventDialogMessage;
import com.villa.deimer.hackathon.models.services.events.StationBus;

public class MaterialDialog {

    //Variable para alerta asincronica
    private AlertDialog progressDialog;
    private Context context;

    public MaterialDialog(Context context){
        this.context = context;
    }

    public void dialogProgress(String title){
        progressDialog = new SpotsDialog(context, title, R.style.CustomDialog);
        progressDialog.setCancelable(false);
        progressDialog.show();
    }

    public void cancelProgress(){
        progressDialog.cancel();
    }

    public void createDialogQuestion(String message) {
        final AlertDialog.Builder dialog = new AlertDialog.Builder(context);
        dialog.setCancelable(true).setMessage(message);
        dialog.setNegativeButton("No", createoptionsButtons());
        dialog.setPositiveButton("si", createoptionsButtons());
        dialog.create().show();
    }

    private DialogInterface.OnClickListener createoptionsButtons() {
        return new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which){
                    case DialogInterface.BUTTON_POSITIVE:
                        StationBus.getBus().post(new EventDialogMessage(1, true));
                        break;
                    case DialogInterface.BUTTON_NEGATIVE:
                        StationBus.getBus().post(new EventDialogMessage(1, false));
                        break;
                }
                dialog.dismiss();
            }
        };
    }

}

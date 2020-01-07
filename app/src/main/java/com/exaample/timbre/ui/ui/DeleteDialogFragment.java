package com.exaample.timbre.ui.ui;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

import androidx.fragment.app.DialogFragment;

import com.exaample.timbre.api.room.Database;


public class DeleteDialogFragment extends DialogFragment {
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage("Opțiuni")
                .setPositiveButton("Ștergeti", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        final Handler handler = new Handler();
                        (new Thread(new Runnable() {
                            @Override
                            public void run() {
                                Database database = Database.getInstance(getContext());
                                database.getDatabase().timbruDAO().deleteTimbru(getArguments().getString("id"));

                                handler.post(new Runnable() {
                                    @Override
                                    public void run() {
                                    }
                                });
                            }
                        })).start();
                    }
                })
//                .setNeutralButton("Editeaza", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        Intent intent = new Intent(getActivity(),AddActivity.class);
//                    }
//                })
                .setNegativeButton("Închide", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // User cancelled the dialog
                    }
                });
        // Create the AlertDialog object and return it
        return builder.create();
    }


}

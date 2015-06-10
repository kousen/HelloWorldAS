package com.nfjs.helloworldas;


import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.Toast;

public class NameFragment extends DialogFragment {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(R.string.alert_title)
                .setIcon(android.R.drawable.star_on)
                .setMessage(String.format("You clicked on %s",
                        getArguments().getString("name")))
                .setNeutralButton(R.string.alert_button_label,
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(getActivity(), R.string.toastFromNeutralButton,
                                        Toast.LENGTH_SHORT).show();
                            }
                        });
        return builder.create();
    }
}

package com.nfjs.helloworldas;


import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;

public class NameFragment extends DialogFragment {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(R.string.alert_title)
                .setIcon(android.R.drawable.star_on)
                .setMessage(String.format("You clicked on %s",
                        getArguments().getString("name")))
                .setNeutralButton(R.string.alert_button_label, null);
        return builder.create();
    }
}

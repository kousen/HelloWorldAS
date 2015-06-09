package com.nfjs.helloworldas;


import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;

public class NameFragment extends DialogFragment {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("This is an alert")
                .setMessage(String.format("You clicked on %s",
                        getArguments().getString("name")))
                .setNeutralButton("Yeah, I know", null);
        return builder.create();
    }
}

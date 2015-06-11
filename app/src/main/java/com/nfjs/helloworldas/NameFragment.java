package com.nfjs.helloworldas;


import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.Toast;

public class NameFragment extends DialogFragment {

    public static interface Rateable {
        void modifyRating(String name, int amount);
    }

    private Rateable rater;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        rater = (Rateable) activity;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final String name = getArguments().getString("name");
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(R.string.alert_title)
                .setIcon(android.R.drawable.star_on)
                .setMessage(String.format("How do you feel about %s?", name))
                .setPositiveButton("Positive, +1", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        rater.modifyRating(name, 1);
                    }
                })
                .setNeutralButton("Neutral, 0", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getActivity(), name + " is going to be disappointed",
                                Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton("Negative, -1", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        rater.modifyRating(name, -1);
                    }
                });
        return builder.create();
    }
}

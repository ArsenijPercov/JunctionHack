package com.example.junctionhack;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatDialogFragment;

public class AddDialog extends AppCompatDialogFragment {

    private EditText editTextBagId;
    private EditText editTextBagName;
    private AddDialogListener listener;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.add_baggage_layout, null);
        builder.setView(view)
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                }).setPositiveButton("Add", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String id = editTextBagId.getText().toString();
                String name = editTextBagName.getText().toString();
                listener.applyTexts(id, name);
            }
        });
        editTextBagId = view.findViewById(R.id.enter_baggage_id);
        editTextBagName = view.findViewById(R.id.enter_baggage_name);


        return builder.create();
    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            listener = (AddDialogListener) context;
        } catch(ClassCastException e) {
            e.printStackTrace();
        }
    }
    public interface AddDialogListener{
        void applyTexts(String id, String name);
    }

}

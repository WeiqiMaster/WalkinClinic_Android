package com.example.seg2105_project;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatDialogFragment;

import java.util.ArrayList;

public class DialogEmployeeAddService extends AppCompatDialogFragment {
    String[] ss;
    boolean[] checkedItems;
    ArrayList<String> services;
    ArrayList<Service> serviceList;
    private DialogEmployeeServiceListener listener;

    public void passValues(String[] ss,
            boolean[] checkedItems,
            ArrayList<String> services,
            ArrayList<Service> serviceList) {
        this.ss = ss;
        this.checkedItems = checkedItems;
        this.services = services;
        this.serviceList = serviceList;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Choose Services");
        builder.setMultiChoiceItems(ss, checkedItems, new DialogInterface.OnMultiChoiceClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                checkedItems[which] = isChecked;
            }
        });

        // add OK and Cancel buttons
        builder.setPositiveButton("Update", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                listener.applyResult(checkedItems);
            }
        });
        builder.setNegativeButton("Cancel", null);

        return builder.create();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            listener = (DialogEmployeeServiceListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString());
        }
    }

    public interface DialogEmployeeServiceListener {
        void applyResult(boolean[] checkedItems);
    }
}

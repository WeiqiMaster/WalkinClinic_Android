package com.example.seg2105_project;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatDialogFragment;

public class DialogChangeService extends AppCompatDialogFragment {
    private EditText etServiceName;
    private EditText etRoleOfPerson;
    private DialogChangeServiceListener listener;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_edit_service, null);

        builder.setView(view)
                .setTitle("Change Service")
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String serviceName = etServiceName.getText().toString().trim();
                        String roleOfPerson = etRoleOfPerson.getText().toString().trim();
                        listener.applyText(serviceName, roleOfPerson);
                    }
                });

        etServiceName = view.findViewById(R.id.etServiceName);
        etRoleOfPerson = view.findViewById(R.id.etRoleOfPerson);

        return builder.create();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            listener = (DialogChangeServiceListener) context;
        } catch (ClassCastException) {
            throw new ClassCastException(context.toString());
        }
    }

    public interface DialogChangeServiceListener{
        void applyText(String serviceName, String roleOfPerson);
    }
}

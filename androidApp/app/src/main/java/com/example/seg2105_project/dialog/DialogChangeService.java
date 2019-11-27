package com.example.seg2105_project.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatDialogFragment;

import com.example.seg2105_project.R;

public class DialogChangeService extends AppCompatDialogFragment {
    private EditText etServiceName;
    private EditText etRoleOfPerson;
    private DialogChangeServiceListener listener;
    private int position;

    public void setPosition(int position) {
        this.position = position;
    }
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
                        dismiss();
                    }
                })
                .setNeutralButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        listener.applyText(position, null, null);
                    }
                })
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String serviceName = etServiceName.getText().toString().trim();
                        String roleOfPerson = etRoleOfPerson.getText().toString().trim();
                        if (serviceName.equals("")) {
                            Toast.makeText(getContext(),
                                    "Modifying Service Failed!\nService Name can not be empty.",
                                    Toast.LENGTH_LONG).show();
                            return;
                        }
                        if (!(roleOfPerson.equals("nurse") || roleOfPerson.equals("doctor") || roleOfPerson.equals("staff"))) {
                            Toast.makeText(getContext(),
                                    "Modifying Service Failed!\nRole of Person has to be \"nurse\", \"doctor\" or \"staff\".",
                                    Toast.LENGTH_LONG).show();
                            return;
                        }
                        listener.applyText(position, serviceName, roleOfPerson);
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
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString());
        }
    }

    public interface DialogChangeServiceListener{
        void applyText(int positon, String serviceName, String roleOfPerson);
    }
}

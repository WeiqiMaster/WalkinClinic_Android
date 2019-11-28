package com.example.seg2105_project.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.TimePickerDialog;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.Gravity;
import android.widget.TextView;
import android.widget.TimePicker;

import com.example.seg2105_project.activities.ClinicActivity;
import com.example.seg2105_project.activities.ManageAvailabilityActivity;

import java.util.Calendar;

public class OneTimeTimePicker extends DialogFragment implements TimePickerDialog.OnTimeSetListener{
    ClinicActivity listener;
    private int position;

    public void setPosition(int position) {
        this.position = position;
    }
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState){
        //Use the current time as the default values for the time picker
        final Calendar c = Calendar.getInstance();
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);

        TimePickerDialog tpd = new TimePickerDialog(getActivity(), AlertDialog.THEME_DEVICE_DEFAULT_DARK
                ,this, hour, minute, DateFormat.is24HourFormat(getActivity()));

        TextView tvTitle = new TextView(getActivity());
        tvTitle.setText("Pick a Time");
        tvTitle.setBackgroundColor(Color.parseColor("#EEE8AA"));
        tvTitle.setPadding(5, 3, 5, 3);
        tvTitle.setGravity(Gravity.CENTER_HORIZONTAL);
        tpd.setCustomTitle(tvTitle);
        /*.........End custom title section........*/

        return tpd;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            listener = (ClinicActivity) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString());
        }
    }
    //onTimeSet() callback method
    public void onTimeSet(TimePicker view, int hourOfDay, int minute){
        final Calendar c = Calendar.getInstance();
        //int mMonth = c.get(Calendar.MONTH);
        //Do something with the user chosen time
        //Set a message for user

//        final int currentHour = c.get(Calendar.HOUR_OF_DAY);
//        final int currentMinute = c.get(Calendar.MINUTE);
//
//        if (hourOfDay < currentHour) {
//            Toast.makeText(listener,
//                    "Failed! Can not choose time before current time",
//                    Toast.LENGTH_LONG).show();
//            listener.isFailing = true;
//            return;
//        } else if (minute < currentMinute) {
//            Toast.makeText(listener,
//                    "Failed! Can not choose time before current time",
//                    Toast.LENGTH_LONG).show();
//            listener.isFailing = true;
//            return;
//        }

        listener.applyPickedTime(position, hourOfDay, minute);

        //tv.setText("Your chosen time is...\n\n");
//        tv.setText(tv.getText()+ String.valueOf(currentHour)
//                + " : " + String.valueOf(minute) + " " + aMpM + "\n");

    }
}

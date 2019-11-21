package com.example.seg2105_project;

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
import android.widget.Toast;

import java.util.Calendar;

public class TimePickerFragment extends DialogFragment implements TimePickerDialog.OnTimeSetListener{
    ManageAvailabilityActivity listener;
    private int position;
    private boolean isAdding;
    private int index;

    public void setIsAdding(boolean b, int n) {
        isAdding = b;
        index = n;
    }

    public void setPosition(int position) {
        this.position = position;
    }
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState){
        //Use the current time as the default values for the time picker
        final Calendar c = Calendar.getInstance();
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);

        //Create and return a new instance of TimePickerDialog
        /*
            public constructor.....
            TimePickerDialog(Context context, int theme,
             TimePickerDialog.OnTimeSetListener callBack, int hourOfDay, int minute, boolean is24HourView)

            The 'theme' parameter allow us to specify the theme of TimePickerDialog

            .......List of Themes.......
            THEME_DEVICE_DEFAULT_DARK
            THEME_DEVICE_DEFAULT_LIGHT
            THEME_HOLO_DARK
            THEME_HOLO_LIGHT
            THEME_TRADITIONAL

         */

        TimePickerDialog tpd = new TimePickerDialog(getActivity(), AlertDialog.THEME_DEVICE_DEFAULT_DARK
                ,this, hour, minute, DateFormat.is24HourFormat(getActivity()));

        //You can set a simple text title for TimePickerDialog

        /*.........Set a custom title for picker........*/
        TextView tvTitle = new TextView(getActivity());
        tvTitle.setText("Choose Time1");
        if (index == 1) {
            tvTitle.setText("Choose Time2");
        }
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
            listener = (ManageAvailabilityActivity) context;
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


        if (isAdding) {
            if (index == 0)
                listener.addTime0(hourOfDay, minute);
            else
                listener.addTime(hourOfDay, minute);
        } else {
            if (index == 0)
                listener.applyTime0(position, hourOfDay, minute);
            else
                listener.applyTime(position, hourOfDay, minute);
        }

        //tv.setText("Your chosen time is...\n\n");
//        tv.setText(tv.getText()+ String.valueOf(currentHour)
//                + " : " + String.valueOf(minute) + " " + aMpM + "\n");

    }
}

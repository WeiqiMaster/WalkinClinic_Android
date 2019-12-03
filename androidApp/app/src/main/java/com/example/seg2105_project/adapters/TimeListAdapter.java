package com.example.seg2105_project.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;

import com.example.seg2105_project.objects.MyTime;
import com.example.seg2105_project.R;

import java.util.ArrayList;

public class TimeListAdapter extends ArrayAdapter<MyTime> {
    private Context mContext;
    int mResource;
    private TextView tvDay;
    private TextView tvTime;
    FragmentActivity fragmentActivity;

    public TimeListAdapter(@NonNull Context context, int resource, @NonNull ArrayList<MyTime> objects) {
        super(context, resource, objects);
        mContext = context;
        mResource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        String day = (getItem(position).getMonth() + 1) + "/" + getItem(position).getDay();
        String hourMinute = getItem(position).getTimeInterval();

        LayoutInflater inflater = LayoutInflater.from(mContext);
        convertView = inflater.inflate(mResource, parent, false);

        tvDay = (TextView) convertView.findViewById(R.id.textView1);
        tvTime = (TextView) convertView.findViewById(R.id.textView2);

        tvDay.setText(day);
        tvTime.setText(hourMinute);

//        convertView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(getContext(),
//                        tvName.getText().toString(),
//                        Toast.LENGTH_LONG).show();
////                DialogChangeService dialogChangeService = new DialogChangeService();
////                dialogChangeService.show(fragmentActivity.getSupportFragmentManager(), "Modify Services");
////                AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
////                builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
////                    public void onClick(DialogInterface dialog, int id) {
////                        // User clicked OK button
////                    }
////                });
//
//            }
//        });

        return convertView;
    }
}


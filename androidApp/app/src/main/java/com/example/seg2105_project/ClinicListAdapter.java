package com.example.seg2105_project;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;

import com.example.seg2105_project.objects.Employee;

import java.util.ArrayList;

public class ClinicListAdapter extends ArrayAdapter<Employee> {
    private Context mContext;
    int mResource;
    private TextView tvName;
    private TextView tvRoleOfPerson;
    FragmentActivity fragmentActivity;

    public ClinicListAdapter(@NonNull Context context, int resource, @NonNull ArrayList<Employee> objects, FragmentActivity c) {
        super(context, resource, objects);
        mContext = context;
        mResource = resource;
        fragmentActivity = c;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        String name = getItem(position).getName();

        LayoutInflater inflater = LayoutInflater.from(mContext);
        convertView = inflater.inflate(mResource, parent, false);

        tvName = (TextView) convertView.findViewById(R.id.textView1);

        return convertView;
    }

}

package com.example.seg2105_project;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckedTextView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;

import java.util.ArrayList;

public class ServiceListAdapterEmployee extends ArrayAdapter<Service> {
    private Context mContext;
    int mResource;
    private CheckedTextView tvName;
    private TextView tvRoleOfPerson;
    FragmentActivity fragmentActivity;

    public ServiceListAdapterEmployee(@NonNull Context context, int resource, @NonNull ArrayList<Service> objects, FragmentActivity c) {
        super(context, resource, objects);
        mContext = context;
        mResource = resource;
        fragmentActivity = c;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        String name = getItem(position).getName();
        String roleOfPerson = getItem(position).getRoleOfPerson();

        Service service = new Service(name, roleOfPerson);

        LayoutInflater inflater = LayoutInflater.from(mContext);
        convertView = inflater.inflate(mResource, parent, false);

        tvName = convertView.findViewById(R.id.textView1);
        tvRoleOfPerson = (TextView) convertView.findViewById(R.id.textView2);

        tvName.setText(name);
        tvRoleOfPerson.setText(roleOfPerson);

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


package com.example.oneblood;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class DonorAdapter  extends BaseAdapter {

    Context context;
    ArrayList<UserConstructor> arrayList;

    public DonorAdapter(Context context,ArrayList<UserConstructor>arrayList){
        this.context=context;
        this.arrayList=arrayList;
    }


    @Override
    public int getCount() {
        return this.arrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.list_view_donor, null);


            TextView name = convertView.findViewById(R.id.listviewDonorName);
            TextView age = convertView.findViewById(R.id.listDonorAge);
            TextView bloodGroup = convertView.findViewById(R.id.listDonorBlood);
            TextView address = convertView.findViewById(R.id.listDonorAddress);
            TextView phone = convertView.findViewById(R.id.listDonorPhone);


            UserConstructor userConstructor = arrayList.get(position);


            name.setText(userConstructor.getFullName());
            age.setText(String.valueOf(userConstructor.getAge()));
            bloodGroup.setText(userConstructor.getBloodgroup());
            address.setText(userConstructor.getAddress());
            phone.setText(userConstructor.getPhone());



        }
        return convertView;
    }
}

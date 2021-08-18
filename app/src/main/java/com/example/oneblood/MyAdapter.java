package com.example.oneblood;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class MyAdapter  extends BaseAdapter {

    Context context;
    ArrayList<RequestConstructor> arrayList;

    public MyAdapter(Context context,ArrayList<RequestConstructor>arrayList){
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
            convertView = inflater.inflate(R.layout.list_view, null);

            TextView id=(TextView)convertView.findViewById(R.id.listId);
            TextView name = (TextView) convertView.findViewById(R.id.listviewName);
            TextView age = (TextView) convertView.findViewById(R.id.listAge);
            TextView bloodGroup = (TextView) convertView.findViewById(R.id.listBlood);
            TextView address = (TextView) convertView.findViewById(R.id.listAddress);
            TextView phone = (TextView) convertView.findViewById(R.id.listPhone);
            TextView date = (TextView) convertView.findViewById(R.id.listDate);

            RequestConstructor requestConstructor = arrayList.get(position);

            id.setText(String.valueOf(requestConstructor.getId()));
            name.setText(requestConstructor.getDonee());
            age.setText(String.valueOf(requestConstructor.getAge()));
            bloodGroup.setText(requestConstructor.getBloodgroup());
            address.setText(requestConstructor.getAddress());
            phone.setText(requestConstructor.getPatientPhone());
            date.setText(requestConstructor.getDate());


        }
        return convertView;

    }

}

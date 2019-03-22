package com.example.karan.bookreps;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Karan on 4/18/2017.
 */

class CustomAdapter extends BaseAdapter
{
    Context context;
    ArrayList<String> alBooknm=new ArrayList<String>();
    ArrayList<String> alBookauth=new ArrayList<String>();
    ArrayList<String> alemailus=new ArrayList<String>();

    LayoutInflater inflator;

    public CustomAdapter(Context context,ArrayList<String> albknm,ArrayList<String> albkauth,ArrayList<String> albkEmail)
    {
        this.context=context;
        this.alBooknm=albknm;
        this.alBookauth=albkauth;
        this.alemailus=albkEmail;

        inflator=(LayoutInflater)context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
    }


    @Override
    public int getCount() {
        return alBooknm.size();

    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {

        View view=inflator.inflate(R.layout.list_rawreq, null);

        TextView txtbknm=(TextView)view.findViewById(R.id.txtbknm);
        TextView txtbkauth=(TextView)view.findViewById(R.id.txtbkauth);
        TextView txtemailus=(TextView)view.findViewById(R.id.txtemailus);


        txtbknm.setText(alBooknm.get(position));
        txtbkauth.setText(alBookauth.get(position));
        txtemailus.setText(alemailus.get(position));


        return view;
    }
}

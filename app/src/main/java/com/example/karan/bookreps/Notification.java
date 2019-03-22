package com.example.karan.bookreps;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class Notification extends AppCompatActivity
{

    ListView list;

    Database dbsq;
    ArrayList<Model2> alstrt=new ArrayList<Model2>();
    ArrayList<String> albknm=new ArrayList<String>();
    ArrayList<String> albkauth=new ArrayList<String>();
    ArrayList<String> alemailus=new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);

        list=(ListView)findViewById(R.id.lstreq);
        dbsq=new Database(Notification.this);
        alstrt=dbsq.getAllContacts();


        for (int i = 0; i < alstrt.size(); i++)
        {

            albknm.add(alstrt.get(i).getBookNM());
            albkauth.add(alstrt.get(i).getBookAuth());
            alemailus.add(alstrt.get(i).getEmailuser());

        }
        CustomAdapter adapter=new CustomAdapter(Notification.this,albknm,albkauth,alemailus);
        list.setAdapter(adapter);



    }

}

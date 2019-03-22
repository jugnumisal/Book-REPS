package com.example.karan.bookreps;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;



import java.util.ArrayList;

public class BuyActivity extends AppCompatActivity {

    Database dbsb1;
    ArrayList<Model1> alstrt=new ArrayList<Model1>();
    ArrayList<String> alisbn=new ArrayList<String>();
    ArrayList<String> altitle=new ArrayList<String>();
    ArrayList<String> alauthor=new ArrayList<String>();
    ArrayList<String> aledition=new ArrayList<String>();
    ArrayList<String> alcondition=new ArrayList<String>();
    ArrayList<String> aldescription=new ArrayList<String>();
    ArrayList<String> alap=new ArrayList<String>();
    ArrayList<String> alop=new ArrayList<String>();
    ArrayList<String> alcntctno=new ArrayList<String>();

    ViewPager pager;
//    CircleIndicator indicator;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy);

        pager= (ViewPager)findViewById(R.id.viewpgbuy);
//        indicator = (CircleIndicator) findViewById(R.id.indicator);

        dbsb1=new Database(BuyActivity.this);
        alstrt=dbsb1.getAllCotacts();
        for (int i = 0; i < alstrt.size(); i++)
        {
            alisbn.add(alstrt.get(i).getISBN());
            altitle.add(alstrt.get(i).getTitle());
            alauthor.add(alstrt.get(i).getAuthor());
            aledition.add(alstrt.get(i).getEdition());
            alcondition.add(alstrt.get(i).getCondition());
            aldescription.add(alstrt.get(i).getDescription());
            alap.add(alstrt.get(i).getOriginal_price());
            alop.add(alstrt.get(i).getOffere_price());
            alcntctno.add(alstrt.get(i).getPhone_number());
        }



        CustomPager adpter =new  CustomPager(alisbn,altitle,alauthor,aledition,alcondition,aldescription,alap,alop,alcntctno,BuyActivity.this);

        pager.setAdapter(adpter);
//        indicator.setViewPager(pager);
    }
}

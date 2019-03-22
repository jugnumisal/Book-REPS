package com.example.karan.bookreps;

import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.widget.TextView;

public class AboutUs extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);

        WebView view = (WebView)findViewById(R.id.txtabt);
        String text;
        text = "<html><body><p align=\"justify\">";
        text+= "Book REPS is a very useful application helpful in selling/buying new/old books without any intermediate agents so that users can get best offers on their books. It was a project started to fulfill the needs of students and save their time. We focus on saving time and money of our users by offering them the best deals. All the Bibliophiles can use this application and share their library collection with their peers.";
        text+= "</p>";
        text+= "<p align=\"justify\">";
        text+= " We are on a mission to get millions of users and help all the sectors of society. We are planning to provide chat service and online payment portal shortly which will help users to make e-payments and confirm the authenticity of the seller/buyer.";
        text+= "</p></body></html>";
        view.loadData(text, "text/html", "utf-8");

        view.setBackgroundColor(Color.TRANSPARENT);

        TextView tx = (TextView)findViewById(R.id.txtabtttitle);
        Typeface custom_font = Typeface.createFromAsset(getAssets(), "fonts/cop.ttf");
        tx.setTypeface(custom_font);


    }
}

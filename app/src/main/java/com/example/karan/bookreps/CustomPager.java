package com.example.karan.bookreps;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import static com.example.karan.bookreps.R.id.edtnumber;


class CustomPager extends PagerAdapter {


    ArrayList<String> alisbn = new ArrayList<String>();
    ArrayList<String> altitle = new ArrayList<String>();
    ArrayList<String> alauthor = new ArrayList<String>();
    ArrayList<String> aledition = new ArrayList<String>();
    ArrayList<String> alcondition = new ArrayList<String>();
    ArrayList<String> aldescription = new ArrayList<String>();
    ArrayList<String> alap = new ArrayList<String>();
    ArrayList<String> alop = new ArrayList<String>();
    ArrayList<String> alcntctno = new ArrayList<String>();
    Context context;

    LayoutInflater inflater;

    public CustomPager(ArrayList<String> alisbn, ArrayList<String> altitle, ArrayList<String> alauthor, ArrayList<String> aledition, ArrayList<String> alcondition, ArrayList<String> aldescription, ArrayList<String> alap, ArrayList<String> alop, ArrayList<String> alcntctno, Context context) {

        this.alisbn = alisbn;
        this.alauthor = alauthor;
        this.altitle = altitle;
        this.aledition = aledition;
        this.alcondition = alcondition;
        this.aldescription = aldescription;
        this.alap = alap;
        this.alop = alop;
        this.alcntctno = alcntctno;
        this.context = context;
        inflater=(LayoutInflater)context.getSystemService(context.LAYOUT_INFLATER_SERVICE);



    }

    @Override
    public int getCount() {
        return alauthor.size();
    }


    public Object instantiateItem(ViewGroup container, int position) {
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View itemView = inflater.inflate(R.layout.list_raw, null);

        TextView txtisbn = (TextView) itemView.findViewById(R.id.txtisbn);
        TextView txttitle = (TextView) itemView.findViewById(R.id.txttitle);
        TextView txtauthor = (TextView) itemView.findViewById(R.id.txtauthor);
        TextView txtedition = (TextView) itemView.findViewById(R.id.txtedition);
        TextView txtcondition = (TextView) itemView.findViewById(R.id.txtcondition);
        TextView txtdescription = (TextView) itemView.findViewById(R.id.txtdesc);
        TextView txtacprice = (TextView) itemView.findViewById(R.id.txtactualprice);
        TextView txtofprice = (TextView) itemView.findViewById(R.id.txtofferprice);
        TextView txtemail = (TextView) itemView.findViewById(R.id.txtemaillist);
        TextView txtsuggestion=(TextView) itemView.findViewById(R.id.btnbuylist);


        txtisbn.setText(alisbn.get(position));
        txttitle.setText(altitle.get(position));
        txtauthor.setText(alauthor.get(position));
        txtedition.setText(aledition.get(position));
        txtcondition.setText(alcondition.get(position));
        txtdescription.setText(aldescription.get(position));
        txtacprice.setText(alap.get(position));
        txtofprice.setText(alop.get(position));
        txtemail.setText(alcntctno.get(position));

        ((ViewPager) container).addView(itemView);

        return itemView;


    }

    @Override
    public boolean isViewFromObject(View arg0, Object arg1)

    {
        return arg0 == arg1;
    }

    public void destroyItem(ViewGroup container, int position, Object object)
    {
        ((ViewPager) container).removeView((RelativeLayout) object);
    }


}


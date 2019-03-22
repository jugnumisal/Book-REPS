package com.example.karan.bookreps;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

public class BookRequest extends AppCompatActivity {


    public static ArrayList<String> albknm=new ArrayList<String>();
    public static ArrayList<String> albkauthor=new ArrayList<String>();
    public static ArrayList<String> alusemail=new ArrayList<String>();

    EditText edtbk1,edtbk2,edtbk3;
    Button btnadreq;
   Database dbq;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_request);

        edtbk1=(EditText)findViewById(R.id.edtbookname);
        edtbk2=(EditText)findViewById(R.id.edtauthorname);
        edtbk3=(EditText)findViewById(R.id.edtreqemail);
        btnadreq=(Button)findViewById(R.id.btnaddeq);


        btnadreq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(validatebknm())
                {
                    if(validatebkauthnm())
                    {
                        if(validateusemail())
                        {
                            dbq=new Database(BookRequest.this);

                            dbq.insertdata(edtbk1.getText().toString(),edtbk2.getText().toString(),edtbk3.getText().toString());
                            Intent i = new Intent(BookRequest.this, MainActivity.class);
                            startActivity(i);


                        }
                    }
                }
            }
        });


    }

    @SuppressLint("NewApi")
    private boolean validatebknm() {

        if (edtbk1.getText().toString().trim().isEmpty()) {
            edtbk1.setError("This is required field");
            edtbk1.requestFocus();
            return false;
        }
        return true;
    }

    @SuppressLint("NewApi")
    private boolean validatebkauthnm() {

        if (edtbk2.getText().toString().trim().isEmpty()) {
            edtbk2.setError("This is required field");
            edtbk2.requestFocus();
            return false;
        }
        return true;
    }

    @SuppressLint("NewApi")
    private boolean validateusemail() {
        String email = edtbk3.getText().toString().trim();
        if (email.isEmpty()) {
            edtbk3.setError("This is required field");
            edtbk3.requestFocus();
            return false;
        }
        // Toast.makeText(getApplicationContext(),
        // ""+android.util.Patterns.EMAIL_ADDRESS, Toast.LENGTH_LONG).show();
        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            edtbk3.setError("Enter valid Email-Id");
            edtbk3.requestFocus();
            return false;
        }
        return true;
    }
}

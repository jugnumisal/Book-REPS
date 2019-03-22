package com.example.karan.bookreps;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import static com.example.karan.bookreps.R.id.edtnumber;
import static com.example.karan.bookreps.R.id.edtpsin;

public class LoginActivity extends AppCompatActivity {


    TextView txt1,txtreg,txtfrgt;
    EditText edtemaillog,edtpasslog,edtnum;
    Button btnlogin;
    Database db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        txt1=(TextView)findViewById(R.id.txt1);
        txtreg=(TextView)findViewById(R.id.txtreg);
        txtfrgt=(TextView)findViewById(R.id.txtfrgt);

        db=new Database(LoginActivity.this);
        db.getReadableDatabase();

        txtreg.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent i=new Intent(LoginActivity.this,SignUpActivity.class);
                startActivity(i);
            }
        });

        edtemaillog=(EditText)findViewById(R.id.edtemaillog);
        edtpasslog=(EditText)findViewById(R.id.edtpasslog);
        edtnum=(EditText)findViewById(edtnumber);

        btnlogin=(Button)findViewById(R.id.btnlogin);
        btnlogin.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {

                if(validateEmail())
                {
                    if(validatePassword())
                    {

                        String id=db.checkLogin(edtemaillog.getText().toString(),edtpasslog.getText().toString());

                        if(!id.matches(""))
                        {

                            Intent intent=new Intent(LoginActivity.this,MainActivity.class);

                            startActivity(intent);
                        }
                        else
                        {
                            Toast.makeText(LoginActivity.this, "Invalid Email_id or password", Toast.LENGTH_LONG).show();
                        }
                    }
                }

            }
        });

        txtfrgt.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                showDialog(LoginActivity.this, "Enter your Mobile Number");

            }
        });
    }

    public void showDialog(Activity activity, String msg){
        final Dialog dialog = new Dialog(activity);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(true);
        dialog.setContentView(R.layout.activity_forgot);

        TextView text = (TextView) dialog.findViewById(R.id.txt_dia);
        text.setText(msg);

        Button dialogButton = (Button) dialog.findViewById(R.id.btn_no);

        final EditText  edtnum=(EditText)dialog.findViewById(edtnumber);
        dialogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                String id=db.checkNumber(edtemaillog.getText().toString(),edtnum.getText().toString());
//
//                if(!id.matches(""))
//                {
//                            Toast.makeText(LoginActivity.this, "Login success", Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(LoginActivity.this,MainActivity.class);
//                            intent.putExtra("ID",id);
                    startActivity(intent);

//                    String msg=db.edtpsin.getText.tostring();
//                    sendSms(edtnum.getText().toString(),msg);
//                }
//                else
//                {
//                    Toast.makeText(LoginActivity.this, "Invalid Mobile number", Toast.LENGTH_LONG).show();
//                }
            }
        });

        dialog.show();

    }

    private void sendSms(String phoneno,String message)
    {
        SmsManager smsManager = SmsManager.getDefault();
        smsManager.sendTextMessage(phoneno, null, message, null, null);
        Toast.makeText(getApplicationContext(), "SMS sent.",
                Toast.LENGTH_LONG).show();
    }

    @SuppressLint("NewApi")
    private boolean validateEmail() {
        String email = edtemaillog.getText().toString().trim();
        if (email.isEmpty()) {
            edtemaillog.setError("Enter Email ID");
            edtemaillog.requestFocus();
            return false;
        }
        // Toast.makeText(getApplicationContext(),
        // ""+android.util.Patterns.EMAIL_ADDRESS, Toast.LENGTH_LONG).show();
        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            edtemaillog.setError("Enter valid Email-Id");
            edtemaillog.requestFocus();
            return false;
        }
        return true;
    }


    @SuppressLint("NewApi")
    private boolean validatePassword() {
        if (edtpasslog.getText().toString().trim().isEmpty()) {
            edtpasslog.setError( "Enter password");
            edtpasslog.requestFocus();
            return false;
        }
        return true;
    }

}
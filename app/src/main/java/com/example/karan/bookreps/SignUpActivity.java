package com.example.karan.bookreps;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SignUpActivity extends AppCompatActivity {

    TextView txtsigntext;
    EditText edtnamesign, edtemailsign, edtpasssign, edtphonesign;
    ImageView imguser;
    RadioButton radiomale, radiofemale;
    Button btnreg;
//   String base64 = "";
    RadioGroup rdg;
    Database db;
private static final int CAMERA_REQUEST = 1888;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        btnreg = (Button) findViewById(R.id.btnreg);
        imguser = (ImageView) findViewById(R.id.imguser);

        edtnamesign = (EditText) findViewById(R.id.edtnm);
        edtemailsign = (EditText) findViewById(R.id.edtemailsi);
        edtpasssign = (EditText) findViewById(R.id.edtpsin);
        edtphonesign = (EditText) findViewById(R.id.edtcntsi);
        radiofemale = (RadioButton) findViewById(R.id.rdgfemale);
        radiomale = (RadioButton) findViewById(R.id.rdgmale);
        rdg = (RadioGroup) findViewById(R.id.rdg);




       imguser.setOnClickListener(new View.OnClickListener() {

           @Override
         public void onClick(View arg0) {

                showDialog(SignUpActivity.this, "Choose photo");
            }
      });



//        db=new Database(SignUpActivity.this);

        btnreg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                if (validateUname()) {
                    if (validateEmail()) {
                        if (validatePassword()) {
                            if (validateMobile()) {
                                if (validateradio()) {
                                    db=new Database(SignUpActivity.this);
                                   if(db.insertContact(edtnamesign.getText().toString(), edtemailsign.getText().toString(),
                                   edtpasssign.getText().toString(), edtphonesign.getText().toString()))
                                    {
                                       Intent i = new Intent(SignUpActivity.this, MainActivity.class);
                                       startActivity(i);
                                   }
                                }
                            }
                        }
                    }
                }
            }
        });

    }



    public void showDialog(Activity activity, String msg){
        final Dialog dialog = new Dialog(activity);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.dialog);

        ImageView btncsl=(ImageView) dialog.findViewById(R.id.btncsl);

        TextView text = (TextView) dialog.findViewById(R.id.txtdialog);
        text.setText(msg);
       ImageView imgc=(ImageView)dialog.findViewById(R.id.imgcam);
        ImageView imgg=(ImageView)dialog.findViewById(R.id.imgg);


        btncsl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dialog.dismiss();
            }
        });


        imgc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cameraIntent, 2);
//                dialog.dismiss();

            }
        });
        imgg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(i, 1);
//                dialog.dismiss();

            }
        });

        dialog.show();

    }




    @
            Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1 && resultCode == RESULT_OK && null != data)
        {
            Uri selectedImage = data.getData();
            String[] filePathColumn = { MediaStore.Images.Media.DATA };

            Cursor cursor = getContentResolver().query(selectedImage,
                    filePathColumn, null, null, null);
            cursor.moveToFirst();

            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            String picturePath = cursor.getString(columnIndex);

            cursor.close();

//            Bitmap bmp=BitmapFactory.decodeFile(picturePath);
//            toBase64(bmp);


            imguser.setImageBitmap(BitmapFactory.decodeFile(picturePath));
        }
        if (requestCode == 2 && resultCode == RESULT_OK)
        {
            Bitmap photo = (Bitmap) data.getExtras().get("data");
            imguser.setImageBitmap(photo);
           // toBase64(photo);
            SaveImage(photo);

        }
    }

    private void toBase64(Bitmap bpm)
    {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bpm.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] b = baos.toByteArray();
//        base64=Base64.encodeToString(b, Base64.DEFAULT);

//		byte[] decodedString = Base64.decode("", Base64.DEFAULT);
//		Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
    }



    private void SaveImage(Bitmap finalBitmap)
    {
        String root = Environment.getExternalStorageDirectory().toString();
        File myDir = new File(root + "/saved_images");
        myDir.mkdirs();

        Random generator = new Random();
        int n = 10000;
        n = generator.nextInt(n);
        String fname = "Image-"+ n +".jpg";
        File file = new File (myDir, fname);

        try
        {
            FileOutputStream out = new FileOutputStream(file);
            finalBitmap.compress(Bitmap.CompressFormat.JPEG, 90, out);
            out.flush();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @SuppressLint("NewApi")
    private boolean validateUname() {

        if (edtnamesign.getText().toString().trim().isEmpty()) {
            edtnamesign.setError("Enter name");
            edtnamesign.requestFocus();
            return false;
        }
        return true;
    }

    @SuppressLint("NewApi")
    private boolean validateEmail() {
        String email = edtemailsign.getText().toString().trim();
        if (email.isEmpty()) {
            edtemailsign.setError("Enter Email ID");
            edtemailsign.requestFocus();
            return false;
        }
        // Toast.makeText(getApplicationContext(),
        // ""+android.util.Patterns.EMAIL_ADDRESS, Toast.LENGTH_LONG).show();
        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            edtemailsign.setError("Enter valid Email-Id");
            edtemailsign.requestFocus();
            return false;
        }
        return true;
    }

    @SuppressLint("NewApi")
    private boolean validateMobile() {
        String Mobile = edtphonesign.getText().toString().trim();
        if (Mobile.isEmpty()) {
            edtphonesign.setError("Enter Mobile Number");
            edtphonesign.requestFocus();
            return false;
        }
        if (Mobile.length() != 10) {

            edtphonesign.setError("Enter Valid Mobile Number");
            edtphonesign.requestFocus();
            return false;
        }
        return true;
    }

    @SuppressLint("NewApi")
    private boolean validatePassword() {
        if (edtpasssign.getText().toString().trim().isEmpty()) {
            edtpasssign.setError( "Enter password");
            edtpasssign.requestFocus();
            return false;
        }
        return true;
    }

    private boolean validateradio(){

        if (rdg.getCheckedRadioButtonId() == -1)
        {
            Toast.makeText(getApplicationContext(),"Please select gender",Toast.LENGTH_LONG).show();
            return false;
        }
        else
        {
            return true;
        }
    }
}

package com.example.karan.bookreps;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
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
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Random;

public class SellActivity extends AppCompatActivity {



    public static ArrayList<String> alisbn=new ArrayList<String>();
    public static ArrayList<String> altitle=new ArrayList<String>();
    public static ArrayList<String> alauthor=new ArrayList<String>();
    public static ArrayList<String> aledition=new ArrayList<String>();
    public static ArrayList<String> alcondition=new ArrayList<String>();
    public static ArrayList<String> aldescription=new ArrayList<String>();
    public static ArrayList<String> alacprice=new ArrayList<String>();
    public static ArrayList<String> alofprice=new ArrayList<String>();


    ImageView imgbok;
    EditText edtisbn,edttitle,edtauthor,edtedition,edtdesc,edtcondt,edtoprice,edtofprice,edtphnsell;
    Button btnpst;
    String base64="";

    Database dbsb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sell);


        imgbok=(ImageView)findViewById(R.id.imgbk);
        btnpst=(Button)findViewById(R.id.btnpost);
        edtisbn=(EditText)findViewById(R.id.edtISBN);
        edttitle=(EditText)findViewById(R.id.edttitle);
        edtauthor=(EditText)findViewById(R.id.edtauthor);
        edtedition=(EditText)findViewById(R.id.edtedition);
        edtdesc=(EditText)findViewById(R.id.edtpublisher);
        edtcondt=(EditText)findViewById(R.id.edtcondn);
        edtoprice=(EditText)findViewById(R.id.edtop);
        edtofprice=(EditText)findViewById(R.id.edtYo);
        edtphnsell=(EditText)findViewById(R.id.edtphone);

        imgbok.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {


                showDialog(SellActivity.this, "Choose photo");



            }
        });


        btnpst.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                if(validateTitle())
                {
                    if(validateauthor())
                    {
                        if (validatecondition())
                        {
                            if(validateoprice())
                            {
                                if(validateofprice())
                                {
                                    if (validatecntct())
                                    {
                                        dbsb = new Database(SellActivity.this);

//                                        alisbn.add(edtisbn.getText().toString());
//			                            altitle.add(edttitle.getText().toString());
//                                        alauthor.add(edtauthor.getText().toString());
//			                            aledition.add(edtedition.getText().toString());
//                                        alcondition.add(edtcondt.getText().toString());
//                                        aldescription.add(edtdesc.getText().toString());
//                                        alacprice.add(edtoprice.getText().toString());
//                                        alofprice.add(edtofprice.getText().toString());
                                        dbsb.insertBookDetails(edtisbn.getText().toString(),edttitle.getText().toString(),edtauthor.getText().toString(),edtedition.getText().toString(),edtcondt.getText().toString(),edtdesc.getText().toString(),edtoprice.getText().toString(),edtofprice.getText().toString(),edtphnsell.getText().toString());

                                        Intent i = new Intent(SellActivity.this, MainActivity.class);
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




    @Override
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

            Bitmap bmp=BitmapFactory.decodeFile(picturePath);
//            toBase64(bmp);


            imgbok.setImageBitmap(BitmapFactory.decodeFile(picturePath));
        }
        if (requestCode == 2 && resultCode == RESULT_OK)
        {
            Bitmap photo = (Bitmap) data.getExtras().get("data");
            imgbok.setImageBitmap(photo);
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
    private boolean validateTitle() {

        if (edttitle.getText().toString().trim().isEmpty()) {
            edttitle.setError("This is required field");
            edttitle.requestFocus();
            return false;
        }
        return true;
    }

    @SuppressLint("NewApi")
    private boolean validateauthor() {

        if (edtauthor.getText().toString().trim().isEmpty()) {
            edtauthor.setError("This is required field");
            edtauthor.requestFocus();
            return false;
        }
        return true;
    }

    @SuppressLint("NewApi")
    private boolean validatecondition() {

        if (edtcondt.getText().toString().trim().isEmpty()) {
            edtcondt.setError("This is required field");
            edtcondt.requestFocus();
            return false;
        }
        return true;
    }

    @SuppressLint("NewApi")
    private boolean validateoprice() {

        if (edtoprice.getText().toString().trim().isEmpty()) {
            edtoprice.setError("This is required field");
            edtoprice.requestFocus();
            return false;
        }
        return true;
    }

    @SuppressLint("NewApi")
    private boolean validateofprice() {

        if (edtofprice.getText().toString().trim().isEmpty()) {
            edtofprice.setError("This is required field");
            edtoprice.requestFocus();
            return false;
        }
        return true;
    }

    @SuppressLint("NewApi")
    private boolean validatecntct() {
        String email = edtphnsell.getText().toString().trim();
        if (email.isEmpty()) {
            edtphnsell.setError("Enter Email ID");
            edtphnsell.requestFocus();
            return false;
        }
        // Toast.makeText(getApplicationContext(),
        // ""+android.util.Patterns.EMAIL_ADDRESS, Toast.LENGTH_LONG).show();
        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            edtphnsell.setError("Enter valid Email-Id");
            edtphnsell.requestFocus();
            return false;
        }
        return true;
    }

}

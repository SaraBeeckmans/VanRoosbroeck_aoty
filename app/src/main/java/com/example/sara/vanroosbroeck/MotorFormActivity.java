package com.example.sara.vanroosbroeck;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;

public class MotorFormActivity extends AppCompatActivity {

    static final int TAKE_PICTURE_0 = 0;
    static final int TAKE_PICTURE_1 = 1;
    static final int TAKE_PICTURE_2 = 2;
    static final int TAKE_PICTURE_3 = 3;

    static String filename_main = "";
    static String filename_1 = "";
    static String filename_2 = "";
    static String filename_3 = "";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_motorform);


        final EditText name_text = (EditText) findViewById(R.id.txtNaam);
        final EditText description_text = (EditText) findViewById(R.id.txtBeschrijving);


        final EditText price_sale_text = (EditText) findViewById(R.id.txtVerkoopprijs);
        final EditText price_buy_text = (EditText) findViewById(R.id.txtAankoopprijs);


        final EditText year_build_text = (EditText) findViewById(R.id.txtBouwjaar);

        final EditText waranty_text = (EditText) findViewById(R.id.txtGarantie);

        final EditText motorsize_text = (EditText) findViewById(R.id.txtCilinderinhoud);

        final EditText km_text =(EditText) findViewById(R.id.txtKilometerstand);

        final EditText power_text = (EditText) findViewById(R.id.txtVermogen);


        //checkboxes
        final CheckBox first_owner_check = (CheckBox) findViewById(R.id.checkBoxEersteEigenaar);
        final CheckBox sold_check = (CheckBox) findViewById(R.id.checkBoxVerkocht);
        final CheckBox website_check = (CheckBox) findViewById(R.id.checkBoxWebsite);
        final CheckBox home_Pagina_check = (CheckBox) findViewById(R.id.checkBoxHomePagina);




        Button btn_creat_motrocycle = (Button) findViewById(R.id.btn_aanmaken);

        btn_creat_motrocycle.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {


                MotorValues motorValues = new MotorValues();
                motorValues.setName(name_text.getText().toString());
                motorValues.setDescriptioin(description_text.getText().toString());


                motorValues.setPrice_sale(Float.parseFloat(price_sale_text.getText().toString()));
                motorValues.setPrice_buy(Float.parseFloat(price_buy_text.getText().toString()));
                motorValues.setYear_build(Integer.parseInt(year_build_text.getText().toString()));
                motorValues.setPower(power_text.getText().toString());
                motorValues.setWaranty(Integer.parseInt(waranty_text.getText().toString()));
                motorValues.setKm(Integer.parseInt(km_text.getText().toString()));
                motorValues.setMotorsize(motorsize_text.getText().toString());
                motorValues.setFirst_owner(first_owner_check.isChecked());
                motorValues.setSold(sold_check.isChecked());
                motorValues.setWebsite(website_check.isChecked());
                motorValues.setHome_pagina(home_Pagina_check.isChecked());
                motorValues.setFilename_main(filename_main);
                motorValues.setFilename_1(filename_1);
                motorValues.setFilename_2(filename_2);
                motorValues.setFilename_3(filename_3);

                new Odooconnection().execute(motorValues, null, null);

                startActivity(new Intent(MotorFormActivity.this, ProgressActivity.class));

            }

        });

        Button btnMainPic = (Button) findViewById(R.id.btnMainPic);
        btnMainPic.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent newIntent = new Intent(MotorFormActivity.this, MainPictureActivity.class);
                startActivityForResult(newIntent, TAKE_PICTURE_0);
            }

        });

        Button btnPic1 = (Button) findViewById(R.id.btnPic1);
        btnPic1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent newIntent = new Intent(MotorFormActivity.this, MainPictureActivity.class);
                startActivityForResult(newIntent, TAKE_PICTURE_1);
            }

        });

        Button btnPic2 = (Button) findViewById(R.id.btnPic2);
        btnPic2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent newIntent = new Intent(MotorFormActivity.this, MainPictureActivity.class);
                startActivityForResult(newIntent, TAKE_PICTURE_2);
            }

        });

        Button btnPic3 = (Button) findViewById(R.id.btnPic3);
        btnPic3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent newIntent = new Intent(MotorFormActivity.this, MainPictureActivity.class);
                startActivityForResult(newIntent, TAKE_PICTURE_3);
            }

        });

    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // Check which request we're responding to

        if (resultCode == Activity.RESULT_OK) {
            String str_fileName = data.getStringExtra("filename");
            ImageView mImageView = null;
            switch (requestCode) {
                case TAKE_PICTURE_0:
                    mImageView = (ImageView) findViewById(R.id.ImageViewMainImage);
                    filename_main = str_fileName;
                    break;
                case TAKE_PICTURE_1:
                    mImageView = (ImageView) findViewById(R.id.imageView2);
                    filename_1 = str_fileName;
                    break;
                case TAKE_PICTURE_2:
                    mImageView = (ImageView) findViewById(R.id.imageView3);
                    filename_2 = str_fileName;
                    break;
                case TAKE_PICTURE_3:
                    mImageView = (ImageView) findViewById(R.id.imageView4);
                    filename_3 = str_fileName;
                    break;
                default:
                    break;
            }
            setPic(mImageView, str_fileName);
        }



    }


//todo: Verzetten naar een utility class + code in NeemFoto ook verwijderen en utility aantoepen
    private void setPic(ImageView mImageView, String pathToFile) {
        // Get the dimensions of the View
        int targetW = mImageView.getWidth();
        int targetH = mImageView.getHeight();

        // Get the dimensions of the bitmap
        BitmapFactory.Options bmOptions = new BitmapFactory.Options();
        bmOptions.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(pathToFile, bmOptions);
        int photoW = bmOptions.outWidth;
        int photoH = bmOptions.outHeight;

        // Determine how much to scale down the image
        int scaleFactor = Math.min(photoW/targetW, photoH/targetH);

        // Decode the image file into a Bitmap sized to fill the View
        bmOptions.inJustDecodeBounds = false;
        bmOptions.inSampleSize = scaleFactor;
        bmOptions.inPurgeable = true;

        Bitmap bitmap = BitmapFactory.decodeFile(pathToFile, bmOptions);
        mImageView.setImageBitmap(bitmap);
    }


}

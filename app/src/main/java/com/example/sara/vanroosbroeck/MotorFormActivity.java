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
import android.widget.Spinner;
import android.widget.Toast;

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


        //spinners
        final Spinner Brand_spinner = (Spinner) findViewById(R.id.spinnerMerk);
        final Spinner License_spinner = (Spinner) findViewById(R.id.spinnerRijbewijs);


        //checkboxes
        final CheckBox first_owner_check = (CheckBox) findViewById(R.id.checkBoxEersteEigenaar);
        final CheckBox sold_check = (CheckBox) findViewById(R.id.checkBoxVerkocht);
        final CheckBox website_check = (CheckBox) findViewById(R.id.checkBoxWebsite);
        final CheckBox home_Pagina_check = (CheckBox) findViewById(R.id.checkBoxHomePagina);




        Button btn_creat_motrocycle = (Button) findViewById(R.id.btn_aanmaken);

        btn_creat_motrocycle.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {


                MotorValues motorValues = new MotorValues();

                //Strings
                motorValues.setName(name_text.getText().toString());
                motorValues.setDescriptioin(description_text.getText().toString());
                motorValues.setMotorsize(motorsize_text.getText().toString());
                motorValues.setPower(power_text.getText().toString());

                //Floats

                if(price_sale_text.getText().toString().equals("")){
                    motorValues.setPrice_sale(0.0f);
                }
                else {
                    motorValues.setPrice_sale(Float.parseFloat(price_sale_text.getText().toString()));
                }
                if(price_buy_text.getText().toString().equals("")){
                    motorValues.setPrice_buy(0.0f);
                }
                else {
                    motorValues.setPrice_buy(Float.parseFloat(price_buy_text.getText().toString()));
                }



                //integers
                if(year_build_text.getText().toString().equals("")){
                    motorValues.setYear_build(0);
                }
                else {
                    motorValues.setYear_build(Integer.parseInt(year_build_text.getText().toString()));
                }

                if(waranty_text.getText().toString().equals("")){
                    motorValues.setWaranty(0);
                }
                else {
                    motorValues.setWaranty(Integer.parseInt(waranty_text.getText().toString()));
                }

                if(km_text.getText().toString().equals("")){
                    motorValues.setKm(0);
                }
                else {
                    motorValues.setKm(Integer.parseInt(km_text.getText().toString()));
                }




              //spinners
                switch (Brand_spinner.getSelectedItem().toString().toUpperCase()){
                    case "YAMAHA":
                       motorValues.setBrand_id(1);
                        break;
                    case "HONDA":
                        motorValues.setBrand_id(2);
                        break;
                    case "BMW":
                        motorValues.setBrand_id(3);
                        break;
                    case "DUCATI":
                        motorValues.setBrand_id(4);
                        break;
                    case "SUZUKI":
                        motorValues.setBrand_id(5);
                        break;

                        default:
                            break;
                }

                motorValues.setDrivinglicense(License_spinner.getSelectedItem().toString());

                //Checkboxes
                motorValues.setFirst_owner(first_owner_check.isChecked());
                motorValues.setSold(sold_check.isChecked());
                motorValues.setWebsite(website_check.isChecked());
                motorValues.setHome_pagina(home_Pagina_check.isChecked());


                //Photos
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
            Utilities.setPic(mImageView, str_fileName);
        }



    }




}

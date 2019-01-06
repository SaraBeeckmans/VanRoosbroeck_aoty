package com.example.sara.vanroosbroeck;

import android.os.AsyncTask;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.util.Log;

import org.apache.xmlrpc.XmlRpcException;
import org.apache.xmlrpc.client.*;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

import static java.util.Collections.emptyList;


import java.io.File;
import java.net.URL;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;

import static java.util.Collections.emptyList;
import static java.util.Collections.emptyMap;

public class Odooconnection extends AsyncTask<MotorValues, Void, Void>{

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void createNewMotrocycleInDatabase(final MotorValues motorValues)  {
        try {

            final String url = "http://vanroosbroeck_app.nexar.be:8069",
                    db = "vanroosbroeck_app",
                    username = "uploads@nexar.be",
                    password = "nexaruploads2018";


            StringBuilder fileAsString = getFileAsBase64String(motorValues.getFilename_main());
            final String fileAsString_picture_main = fileAsString.toString();


            final XmlRpcClient client = new XmlRpcClient();

            final XmlRpcClientConfigImpl common_config = new XmlRpcClientConfigImpl();


            common_config.setServerURL(
                    new URL(String.format("%s/xmlrpc/2/common", url)));
            int uid = (int)client.execute(common_config, "authenticate", Arrays.asList(db, username, password, emptyMap()) );


            final XmlRpcClient models = new XmlRpcClient();
            common_config.setServerURL(new URL(String.format("%s/xmlrpc/2/object", url)));


            final Integer
                    res = (Integer) models.execute(common_config, "execute_kw",
                    Arrays.asList(db, uid, password, "vanroosbroeck.secondhand_motorcycles", "create", Arrays.asList(new HashMap() {{
                                                                                                                         put("name", motorValues.getName());
                                                                                                                         put("year_build", motorValues.getYear_build());
                                                                                                                         put("price_sale", motorValues.getPrice_sale().toString());
                                                                                                                         put("price_buy", motorValues.getPrice_buy().toString());
                                                                                                                         put("waranty", motorValues.getWaranty().toString());
                                                                                                                         put("description", motorValues.getDescriptioin());
//                                                                                                                         put("drivers_licence", "A1");
                                                                                                                         put("motorsize", motorValues.getMotorsize());
                                                                                                                         put("km", motorValues.getKm().toString());
                                                                                                                         put("power", motorValues.getPower());
                                                                                                                         put("first_owner", motorValues.getFirst_owner());
                                                                                                                         put("sold", motorValues.getSold());
                                                                                                                         put("publish_on_website", motorValues.getWebsite());
                                                                                                                         put("publish_on_homepage", motorValues.getHome_pagina());
//                                                                                                                         put("secondhand_brands_id", 2);
                                                                                                                         put("main_picture", fileAsString_picture_main);
//                                etc....
                                                                                                                     }}
                            )
                    )
            );


            uploadDetailPicture(motorValues.getFilename_1(), db, password, common_config, uid, models, res);
            uploadDetailPicture(motorValues.getFilename_2(), db, password, common_config, uid, models, res);
            uploadDetailPicture(motorValues.getFilename_3(), db, password, common_config, uid, models, res);



        }
        catch (Exception e){
//            System.out.println(e);
            Log.e("Fout met connectie", e.getMessage());
        }

    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void uploadDetailPicture(String filename, String db, String password, XmlRpcClientConfigImpl common_config, int uid, XmlRpcClient models, final Integer res) throws IOException, XmlRpcException {
        StringBuilder fileAsString;
        fileAsString = getFileAsBase64String(filename);
        final String fileAsString_final = fileAsString.toString();
        Integer
                res_foto = (Integer) models.execute(common_config, "execute_kw",
                Arrays.asList(db, uid, password, "vanroosbroeck.secondhand_motorcycles_pictures", "create", Arrays.asList(new HashMap() {{
                                                                                                                                            put("motorcycle_id", res);
                                                                                                                                            put("picture", fileAsString_final);
                                                                                                                                        }})));
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @NonNull
    private StringBuilder getFileAsBase64String(String fileName) throws IOException {
        File jpgFile = new File(fileName);
        byte[] fileContent = Files.readAllBytes(jpgFile.toPath());

        Base64.Encoder encoder = Base64.getEncoder();
        byte[] encodedFile = encoder.encode(fileContent);

        StringBuilder fileAsString = new StringBuilder();
        for (int i = 0; i < encodedFile.length; i++)
        {
            fileAsString.append((char) encodedFile[i]);
            if (i != 0 && i % 4 == 0)
                fileAsString.append(' ');
        }
        return fileAsString;
    }


    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected Void doInBackground(MotorValues... motorValues) {
        createNewMotrocycleInDatabase(motorValues[0]);

//        for (MotorValues element : motorValues) {
//            if (element != null) {
//                createNewMotrocycleInDatabase(element);
//            }
//        }
        return null;
    }
}

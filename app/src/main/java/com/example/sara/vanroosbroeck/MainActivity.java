package com.example.sara.vanroosbroeck;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button next = (Button) findViewById(R.id.button);
        Button gotofotoaction = (Button) findViewById(R.id.tmp_button_takepicture);

        next.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, MotorFormActivity.class));
            }

        });

        gotofotoaction.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, MainPictureActivity.class));
            }

        });
    }
}

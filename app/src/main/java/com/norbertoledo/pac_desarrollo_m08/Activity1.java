package com.norbertoledo.pac_desarrollo_m08;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Activity1 extends AppCompatActivity {

    Button btnActivity2;
    Button btnActivity3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_1);

        btnActivity2 = findViewById(R.id.btnActivity2);
        btnActivity3 = findViewById(R.id.btnActivity3);

        btnActivity2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               //goToActivity2(v);
                goToActivity(v, Activity2.class);
            }
        });

        btnActivity3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //goToActivity3(v);
                goToActivity(v, Activity3.class);
            }
        });
    }

    /*
    private void goToActivity2(View v){
        Intent intent = new Intent(this, Activity2.class);
        startActivity(intent);
    }

    private void goToActivity3(View v){
        Intent intent = new Intent(this, Activity3.class);
        startActivity(intent);
    }
    */

    private void goToActivity(View v, Class theClass){
        Intent intent = new Intent(this, theClass);
        startActivity(intent);
    }

}

package com.norbertoledo.pac_desarrollo_m08;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Activity1 extends AppCompatActivity {

    // Declaraciones
    Button btnActivity2;
    Button btnActivity3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_1);

        // Instancias
        btnActivity2 = findViewById(R.id.btnActivity2);
        btnActivity3 = findViewById(R.id.btnActivity3);

        // Acciones de Boton
        btnActivity2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToActivity(v, Activity2.class);
            }
        });

        btnActivity3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToActivity(v, Activity3.class);
            }
        });
    }

    // Evento de Navegacion
    private void goToActivity(View v, Class theClass){
        Intent intent = new Intent(this, theClass);
        startActivity(intent);
    }

}

package com.norbertoledo.pac_desarrollo_m08;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class Activity3 extends AppCompatActivity {

    // Declaraciones
    Button btnActivity1;
    Button btnStartService;
    Button btnStopService;
    RadioGroup radioGroup;
    RadioButton rb01;
    RadioButton rb02;
    RadioButton rb03;

    public static final int CODE_STOP_SERVICE = 0;
    public static final int CODE_START_SOUND = 1;
    public static final int CODE_STOP_SOUND = 2;
    public static final int CODE_SLEEP = 3;
    public static final String SERVICE_CODE = "action";

    public int oldServiceCodeSelected = CODE_STOP_SERVICE;
    public int serviceCodeSelected = CODE_START_SOUND;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_3);

        // Instancias
        btnActivity1 = findViewById(R.id.btnActivity1);
        btnStartService = findViewById((R.id.btnStartService));
        btnStopService = findViewById((R.id.btnStopService));
        radioGroup = findViewById(R.id.radioGroup);
        rb01 = findViewById(R.id.rb01);
        rb02 = findViewById(R.id.rb02);
        rb03 = findViewById(R.id.rb03);
        

        // Acciones de RadioButton
        rb01.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setServiceCode(CODE_START_SOUND);
            }
        });

        rb02.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setServiceCode(CODE_STOP_SOUND);
            }
        });

        rb03.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setServiceCode(CODE_SLEEP);
            }
        });


        // Acciones de boton


        // El boton de StartService primero evalua si el servicio que va a despachar
        // es distinto al que se encuentra ejecutando. De lo contrario, lanza un Toast
        btnStartService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(oldServiceCodeSelected!=serviceCodeSelected) {
                    btnStopService.setEnabled(true);
                    sendToBroadcast(serviceCodeSelected);
                }else{
                    Toast.makeText(Activity3.this, R.string.msg_service_already_started, Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnStopService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnStopService.setEnabled(false);
                sendToBroadcast(CODE_STOP_SERVICE);
            }
        });

        btnActivity1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            sendToBroadcast(CODE_STOP_SERVICE);
            gotoActivity1(v);
            }
        });

        // Setear por defecto el boton de startService como desactivado
        btnStopService.setEnabled(false);

        // Setear un codigo inicial de servicio
        setInitialCode();

    }

    // Setear el codigo inicial de servicio de acuerdo al radioButton que se encuentre seleccionado
    private void setInitialCode(){
        for(int i = 0; i < radioGroup.getChildCount(); i++ ) {
            RadioButton rb = (RadioButton) radioGroup.getChildAt(i);
            rb.setChecked(false);
            if(i == serviceCodeSelected-1 ) {
                rb.setChecked(true);
            }
        }
    }


    // Setear el codigo de servicio de acuerdo a la nueva seleccion del usuario
    private void setServiceCode(int serviceCode){
        serviceCodeSelected = serviceCode;
    }

    // Enviar al Broadcast el codigo de servicio actual e iniciarlo
    private void sendToBroadcast(int serviceCode){
        oldServiceCodeSelected = serviceCode;
        Intent intent = new Intent(this, MyReceiver.class);
        intent.putExtra(SERVICE_CODE, serviceCode);
        sendBroadcast(intent);
    }

    // Navegar a Activity 1
    private void gotoActivity1(View v){
        Intent intent =  new Intent(this, Activity1.class);
        startActivity(intent);
        finish();
    }


}

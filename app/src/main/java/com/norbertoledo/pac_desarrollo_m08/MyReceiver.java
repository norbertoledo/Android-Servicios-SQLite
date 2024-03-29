package com.norbertoledo.pac_desarrollo_m08;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

public class MyReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {

        Intent i = new Intent(context, MyService.class);

        // Validar si se recibe codigo de servicio
        if(intent.hasExtra(Activity3.SERVICE_CODE)){

            Bundle b = intent.getExtras();

            if(b != null){

                int code = b.getInt(Activity3.SERVICE_CODE);

                // Si el codigo de servicio es 0, detiene el servicio
                // Si el codigo de servicio es distinto de 0, inicia un servicio
                // y envia como Extra el codigo de servicio recibido
                if(code == 0){
                    Toast.makeText(context, R.string.msg_service_stopped, Toast.LENGTH_SHORT).show();
                    context.stopService(i);
                }else{
                    i.putExtra(Activity3.SERVICE_CODE, code);
                    Toast.makeText(context, R.string.msg_service_started, Toast.LENGTH_SHORT).show();
                    context.startService(i);
                }

            }
        }else{

            context.startService(new Intent(context, MyReceiver.class));
        }

    }
}

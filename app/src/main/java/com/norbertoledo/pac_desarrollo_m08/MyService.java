package com.norbertoledo.pac_desarrollo_m08;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.IBinder;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class MyService extends Service {

    private MediaPlayer player;

    public MyService() {
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        if(intent.hasExtra(Activity3.SERVICE_CODE)){

            Bundle b = intent.getExtras();

            if(b != null) {

                int code = b.getInt(Activity3.SERVICE_CODE);

                switch ( code ){
                    case 1:
                        startSound();
                        break;

                    case 2:
                        stopSound();
                        break;

                    case 3:
                        sendToSleep();
                        break;
                }
            }
        }

        return super.onStartCommand(intent, flags, startId);
    }


    private void startSound(){
        Toast.makeText(this, R.string.msg_music_started, Toast.LENGTH_SHORT).show();
        if( player == null) {
            player = MediaPlayer.create(this, R.raw.audio);
            player.setLooping(true);
            player.start();
        }
        if(player != null && !player.isPlaying()) {
            player = MediaPlayer.create(this, R.raw.audio);
            player.setLooping(true);
            player.start();
        }
    }

    private void stopSound(){
        Toast.makeText(this, R.string.msg_music_stopped, Toast.LENGTH_SHORT).show();
        if(player != null && player.isPlaying()){
            player.stop();
        }
    }

    private void sendToSleep(){
        Toast.makeText(this, R.string.msg_app_blocked, Toast.LENGTH_SHORT).show();
        try{
            Thread.sleep(150000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    @Override
    public void onDestroy() {
        if(player != null){
            player.release();
        }
        stopSelf();
    }
}

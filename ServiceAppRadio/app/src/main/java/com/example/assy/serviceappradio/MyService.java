package com.example.assy.serviceappradio;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import java.io.IOException;

/**
 * Created by assy on 29/10/2015.
 */
public class MyService extends Service {

    public MediaPlayer player;
    public Boolean setEnabled=false;
    private ProgressBar progressBar;
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }


 public class MyBinder extends Binder{
     MyService getService(){
         return MyService.this;
     }

 }

    IBinder binder = new MyBinder();


  public void radio()
  {
      Log.i("Hello","Radio");
      initializeMediaPlayer();
  }



    public void initializeMediaPlayer() {
        player = new MediaPlayer();
        try {
            player.setDataSource("http://212.224.108.80/S1/HLS_LIVE/dreamtv/1000/prog_index.m3u8");
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        player.setOnBufferingUpdateListener(new MediaPlayer.OnBufferingUpdateListener() {

            public void onBufferingUpdate(MediaPlayer mp, int percent) {
                Log.i("Buffering", "" + percent);
            }
        });

    }

    public void startService()
    {
        Log.i("start","service");
    }
    public void startPlaying() {
        setEnabled=true;
        Log.i("Hello","Radio");
        initializeMediaPlayer();

        player.prepareAsync();

        player.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {

            public void onPrepared(MediaPlayer mp) {
                player.start();
            }
        });

    }

    public void stopPlaying() {
        if (player.isPlaying()) {
            player.stop();
            player.release();
            initializeMediaPlayer();
        }



    }

}

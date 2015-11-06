package com.example.assy.serviceappradio;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {

    MyService myService;
    ImageButton button;
    Boolean play=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = (ImageButton) findViewById(R.id.radioButton);
        button.setImageDrawable(getResources().getDrawable(R.drawable.play));



        Intent intent = new Intent(this,MyService.class);
        bindService(intent,mConnection, Context.BIND_AUTO_CREATE);
    }








    private ServiceConnection mConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            MyService.MyBinder binder = (MyService.MyBinder) service;
            myService = binder.getService();
            if(myService!=null) {
                myService.initializeMediaPlayer();
            }

        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    public void player(View view){
        if(play==false)
        {
            button.setImageDrawable(getResources().getDrawable(R.drawable.stop));
            myService.startPlaying();
            play=true;

        }
        else
        {
            button.setImageDrawable(getResources().getDrawable(R.drawable.play));
            myService.stopPlaying();
            play=false;
        }

    }
}

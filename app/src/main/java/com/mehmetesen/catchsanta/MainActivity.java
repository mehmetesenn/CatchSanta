package com.mehmetesen.catchsanta;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.VideoView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;



public class MainActivity extends AppCompatActivity {
    VideoView videoView;
    MediaPlayer mediaPlayer;
    Handler handler;
    Runnable runnable;
    Handler handler1;
    Runnable runnable1;
    private AdView mAdView;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });
        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);





        handler=new Handler();
        runnable=new Runnable() {
            @Override
            public void run() {

                videoView=(VideoView)findViewById(R.id.videoView);
                Uri path=Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.homevideo);
                videoView.setVideoURI(path);
                videoView.start();
                handler.postDelayed(this,30000);


            }
        };
        handler.post(runnable);
        handler1=new Handler();
        runnable1=new Runnable() {
            @Override
            public void run() {
                mediaPlayer=MediaPlayer.create(MainActivity.this,R.raw.jingle);
                mediaPlayer.start();
                handler1.postDelayed(this,142000);
            }
        };
        handler1.post(runnable1);

    }

    public void easy(View view){
        mediaPlayer.pause();


        Intent intent=new Intent(MainActivity.this,MainActivity2.class);
        startActivity(intent);

    }
    public void medium(View view){
        mediaPlayer.pause();


        Intent intent5=new Intent(MainActivity.this,MainActivity3.class);
        startActivity(intent5);

    }
    public void hard(View view){

        mediaPlayer.pause();
        Intent intent7=new Intent(MainActivity.this,MainActivity4.class);
        startActivity(intent7);

    }
}
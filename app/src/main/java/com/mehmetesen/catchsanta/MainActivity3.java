package com.mehmetesen.catchsanta;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.media.Image;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;


import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

import java.util.Random;

public class MainActivity3 extends AppCompatActivity {
    TextView ttime;
    TextView sscore;
    ImageView image;
    ImageView image2;
    ImageView image3;
    ImageView image4;
    ImageView image5;
    ImageView image6;
    ImageView image7;
    ImageView image8;
    ImageView image9;
    int j;
    ImageView[] imageray;
    Runnable runnablee;
    Handler handlerr;
    MediaPlayer mediaPlayerr;
    private AdView mAdView;
    private InterstitialAd mInterstitialAd;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        ttime=(TextView)findViewById(R.id.time1);
        sscore=(TextView)findViewById(R.id.score1);
        image=findViewById(R.id.image);
        image2=findViewById(R.id.image2);
        image3=findViewById(R.id.image3);
        image4=findViewById(R.id.image4);
        image5=findViewById(R.id.image5);
        image6=findViewById(R.id.image6);
        image7=findViewById(R.id.image7);
        image8=findViewById(R.id.image8);
        image9=findViewById(R.id.image9);
        imageray=new ImageView[] {image,image2,image3,image4,image5,image6,image7,image8,image9};
        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });
        mAdView = findViewById(R.id.adView);
        AdRequest adRequest2 = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest2);
        AdRequest adRequest = new AdRequest.Builder().build();

        InterstitialAd.load(this,"ca-app-pub-4026568549714819/3306604208", adRequest, new InterstitialAdLoadCallback() {
            @Override
            public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {
                // The mInterstitialAd reference will be null until
                // an ad is loaded.
                mInterstitialAd = interstitialAd;
                Log.i("TAG", "onAdLoaded");


            }

            @Override
            public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                // Handle the error
                Log.i( "TAG",loadAdError.getMessage());
                mInterstitialAd = null;


            }
        });






        j=0;
        HideImage();
        new CountDownTimer(30000,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                ttime.setText("Time: " + millisUntilFinished/1000);


            }

            @Override
            public void onFinish() {
                ttime.setText("Time is Over ");
                handlerr.removeCallbacks(runnablee);
                for(ImageView image1:imageray){
                    image1.setVisibility(View.INVISIBLE);
                }
                AlertDialog.Builder alert1=new AlertDialog.Builder(MainActivity3.this);
                alert1.setTitle("Restart");
                alert1.setCancelable(false);
                alert1.setMessage("Would you like to restart");
                alert1.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent2=getIntent();
                        finish();
                        startActivity(intent2);
                    }
                });
                alert1.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (mInterstitialAd != null) {
                            mInterstitialAd.show(MainActivity3.this);

                        }

                        Toast.makeText(MainActivity3.this,"Game Over",Toast.LENGTH_LONG).show();
                    }
                });

                alert1.show();

            }
        }.start();


    }

    public void sscore(View view){
        mediaPlayerr=MediaPlayer.create(MainActivity3.this,R.raw.hoho);
        mediaPlayerr.start();
        j++;
        sscore.setText("Score: " + j);

    }
    public void HideImage(){
        handlerr=new Handler();
        runnablee=new Runnable() {
            @Override
            public void run() {
                for(ImageView image1:imageray){
                    image1.setVisibility(View.INVISIBLE);
                }
                Random randomm=new Random();
                int b=randomm.nextInt(9);
                imageray[b].setVisibility(View.VISIBLE);
                handlerr.postDelayed(this,500);


            }
        };
        handlerr.post(runnablee);


    }
}
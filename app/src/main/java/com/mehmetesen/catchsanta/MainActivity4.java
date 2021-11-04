package com.mehmetesen.catchsanta;




import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
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


import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;

import java.util.Random;


public class MainActivity4 extends AppCompatActivity {
    TextView zaman;
    TextView skordeger;
    ImageView resim;
    ImageView resim2;
    ImageView resim3;
    ImageView resim4;
    ImageView resim5;
    ImageView resim6;
    ImageView resim7;
    ImageView resim8;
    ImageView resim9;
    int f;
    ImageView[] resimArray;
    Handler handler11;
    Runnable runnable11;
    MediaPlayer mediaPlayer11;
    private AdView mAdView;
    private InterstitialAd mInterstitialAd;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
        zaman=(TextView)findViewById(R.id.textView);
        skordeger=(TextView)findViewById(R.id.textView2);
        resim=findViewById(R.id.imageView10);
        resim2=findViewById(R.id.imageView11);
        resim3=findViewById(R.id.imageView12);
        resim4=findViewById(R.id.imageView13);
        resim5=findViewById(R.id.imageView14);
        resim6=findViewById(R.id.imageView15);
        resim7=findViewById(R.id.imageView16);
        resim8=findViewById(R.id.imageView17);
        resim9=findViewById(R.id.imageView18);
        resimArray=new ImageView[] {resim,resim2,resim3,resim4,resim5,resim6,resim7,resim8,resim9};
        resimhide();
        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });
        mAdView = findViewById(R.id.adView);
        AdRequest adRequest3 = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest3);
        AdRequest adRequest = new AdRequest.Builder().build();

        InterstitialAd.load(this,"ca-app-pub-4026568549714819/9128587159", adRequest, new InterstitialAdLoadCallback() {
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
                Log.i("TAG", loadAdError.getMessage());
                mInterstitialAd = null;
            }
        });


        new CountDownTimer(30000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                zaman.setText("Time:" + millisUntilFinished/1000);
            }

            @Override
            public void onFinish() {
                zaman.setText("Time is Over ");
                handler11.removeCallbacks(runnable11);
                for(ImageView resm:resimArray){
                    resm.setVisibility(View.INVISIBLE);
                }
                AlertDialog.Builder alert80=new AlertDialog.Builder(MainActivity4.this);
                alert80.setTitle("Restart");
                alert80.setCancelable(false);
                alert80.setMessage("would you like to restart ");
                alert80.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent80=getIntent();
                        finish();
                        startActivity(intent80);


                    }
                });
                alert80.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (mInterstitialAd != null) {
                            mInterstitialAd.show(MainActivity4.this);
                        }

                        Toast.makeText(MainActivity4.this,"Game Over",Toast.LENGTH_LONG).show();
                    }
                });
                alert80.show();


            }
        }.start();







    }
    public void skor(View view){
        mediaPlayer11=MediaPlayer.create(MainActivity4.this,R.raw.hoho);
        mediaPlayer11.start();
        f++;
        skordeger.setText("Skor: " + f);

    }
    public void resimhide(){

        handler11=new Handler();
        runnable11=new Runnable() {
            @Override
            public void run() {
                for(ImageView resm:resimArray){
                    resm.setVisibility(View.INVISIBLE);
                }
                Random rastgele=new Random();
                int m=rastgele.nextInt(9);
                resimArray[m].setVisibility(View.VISIBLE);
                handler11.postDelayed(this,400);

            }
        };
        handler11.post(runnable11);


    }



    }

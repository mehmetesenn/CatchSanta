package com.mehmetesen.catchsanta;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;

import java.util.Random;

public class MainActivity2 extends AppCompatActivity {
    TextView score;
    TextView time;
    ImageView imageView;
    ImageView imageView2;
    ImageView imageView3;
    ImageView imageView4;
    ImageView imageView5;
    ImageView imageView6;
    ImageView imageView7;
    ImageView imageView8;
    ImageView imageView9;
    int i;
    ImageView[] imageArray;
    Runnable runnable;
    Handler handler;
    MediaPlayer mediaPlayer;
    private AdView mAdView;
    private InterstitialAd mInterstitialAd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });

        mAdView = findViewById(R.id.adView);
        AdRequest adRequest1 = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest1);

        AdRequest adRequest = new AdRequest.Builder().build();

        InterstitialAd.load(this,"ca-app-pub-4026568549714819/4974909093", adRequest, new InterstitialAdLoadCallback() {
            @Override
            public void onAdLoaded(InterstitialAd interstitialAd) {
                // The mInterstitialAd reference will be null until
                // an ad is loaded.
                mInterstitialAd = interstitialAd;

            }


        });



        score=(TextView)findViewById(R.id.score);
        time=(TextView)findViewById(R.id.time);
        imageView=findViewById(R.id.imageView);
        imageView2=findViewById(R.id.imageView2);
        imageView3=findViewById(R.id.imageView3);
        imageView4=findViewById(R.id.imageView4);
        imageView5=findViewById(R.id.imageView5);
        imageView6=findViewById(R.id.imageView6);
        imageView7=findViewById(R.id.imageView7);
        imageView8=findViewById(R.id.imageView8);
        imageView9=findViewById(R.id.imageView9);
        imageArray=new ImageView[] {imageView,imageView2,imageView3,imageView4,imageView5,imageView6,imageView7,imageView8,imageView9};
        i=0;
        hideImage();


        new CountDownTimer(30000,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                time.setText("Time: " + millisUntilFinished/1000);


            }

            @Override
            public void onFinish() {
                time.setText("Time is Over");

                handler.removeCallbacks(runnable);
                for(ImageView image:imageArray){
                    image.setVisibility(View.INVISIBLE);
                }
                AlertDialog.Builder alert=new AlertDialog.Builder(MainActivity2.this);
                alert.setTitle("Restart");
                alert.setCancelable(false);
                alert.setMessage("Would you like to restart");
                alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        Intent intent1=getIntent();
                        finish();
                        startActivity(intent1);
                    }
                });
                alert.setNegativeButton("No", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (mInterstitialAd != null) {
                            mInterstitialAd.show(MainActivity2.this);
                        }

                        Toast.makeText(MainActivity2.this,"Game Over",Toast.LENGTH_LONG).show();
                    }
                });

                alert.show();

            }
        }.start();
    }



    public void score(View view){
        mediaPlayer=MediaPlayer.create(MainActivity2.this,R.raw.hoho);
        mediaPlayer.start();
        i++;
        score.setText("Score: " + i);



    }
    public void hideImage(){

        handler=new Handler();
        runnable=new Runnable() {
            @Override
            public void run() {
                for(ImageView image:imageArray){
                    image.setVisibility(View.INVISIBLE);
                }
                Random random=new Random();
                int a=random.nextInt(9);
                imageArray[a].setVisibility(View.VISIBLE);
                handler.postDelayed(this,600);





            }
        };
        handler.post(runnable);



    }
}
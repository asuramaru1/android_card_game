package com.example.testapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.graphics.drawable.Drawable;
import android.media.Image;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private final static String APP_LOG = "my_private_logger";
    private ConstraintLayout gameDeck;
    private ImageView lastClicked = null;
    private TextView  flipText ;
    private int flipCounter = 0 ;
    private ImageView clickedNow = null;
    private boolean canClick = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        flipText = findViewById(R.id.flips);
        gameDeck = findViewById(R.id.gameDeck);
        for(int i = 0 ; i<gameDeck.getChildCount() ; i++){
            ImageView childImage  = (ImageView) gameDeck.getChildAt(i);
            childImage.setOnClickListener(this);
        }

    }




    @Override
    public void onClick(View v) {
        if(!canClick)return;
        ImageView clicked = (ImageView) v;
        Handler handler = new Handler();
        if(clicked.getVisibility() == View.INVISIBLE) return;

        turnCard(clicked);

        if(lastClicked == null){
            lastClicked = clicked;
            clicked();
        }else{
            if(lastClicked != clicked){
                clicked();
                clickedNow =  clicked;
                canClick = false;
                if(getLastChar(clicked)==getLastChar(lastClicked)){

                     handler.postDelayed(runnable1, 300);
                }else {
                    handler.postDelayed(runnable2, 300);
                }

            }
        }


    }

    Runnable runnable1 = new Runnable() {

        @Override
        public void run() {
            lastClicked.setVisibility(View.INVISIBLE);
            clickedNow.setVisibility(View.INVISIBLE);
            lastClicked = null;
            canClick = true;
        }
    };
    Runnable runnable2 = new Runnable() {

        @Override
        public void run() {
            clickedNow.setImageResource(R.drawable.unknown);
            lastClicked.setImageResource(R.drawable.unknown);
            lastClicked = null;
            canClick = true;
        }
    };
    private void turnCard(ImageView clicked) {

        char c = getLastChar(clicked);


        switch (c){
            case '1':
                clicked.setImageResource(R.drawable.misha);
                break;
            case '2':
                clicked.setImageResource(R.drawable.adaaa);
                break;
            case '3':
                clicked.setImageResource(R.drawable.corona_slayer);
                break;
            case '4':
                clicked.setImageResource(R.drawable.kala);
                break;
            case '5':
                clicked.setImageResource(R.drawable.momavali_prezidenti);
                break;
            case '6':
                clicked.setImageResource(R.drawable.bidzo);
                break;

        }



    }

    private char getLastChar(ImageView clicked) {
        String s = clicked.toString();
        return s.charAt(s.length()-2);
    }

    private void clicked() {
    flipCounter++;
    flipText.setText("Flips: "+flipCounter);
    }
}

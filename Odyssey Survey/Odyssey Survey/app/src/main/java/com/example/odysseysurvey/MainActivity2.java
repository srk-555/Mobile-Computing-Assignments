package com.example.odysseysurvey;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity2 extends AppCompatActivity {
    private String retrievedName;
    private String retrievedRole;
    private RatingBar rbar;

    private static boolean stopped = false;
    private static boolean paused = false;
    private static boolean restarted = false;


    RatingBar musicRating;
    RatingBar danceRating;
    RatingBar playRating;
    RatingBar fashionRating;
    RatingBar foodRating;

    CheckBox musicCheck;
    CheckBox danceCheck;
    CheckBox playCheck;
    CheckBox fashionCheck;
    CheckBox foodCheck;

//    public void setDanceZero(){
//        Toast.makeText(this, "sedanceZero", Toast.LENGTH_SHORT).show();
//        this.danceRating.setRating(0.0f);
//    }

//    TextView textView;
    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(stopped == true)
        {
            Toast.makeText(this, "State of activity "+this.getClass().getSimpleName()+" changed from onStop to onCreate", Toast.LENGTH_SHORT).show();
            Log.i("State Change Info", "State of activity "+this.getClass().getSimpleName()+" changed from onStop to onCreate");
        }
        else if(paused == true){
            Toast.makeText(this, "State of activity "+this.getClass().getSimpleName()+" changed from onPause to onCreate", Toast.LENGTH_SHORT).show();
            Log.i("State Change Info", "State of activity "+this.getClass().getSimpleName()+" changed from onPause to onCreate");
        }
        else{
            Toast.makeText(this, "State of activity "+this.getClass().getSimpleName()+" changed from Activity Launched to onCreate", Toast.LENGTH_SHORT).show();
            Log.i("State Change Info", "State of activity "+this.getClass().getSimpleName()+" changed from Activity Launched to onCreate");
        }

        setContentView(R.layout.activity_main2);

        Intent intent = getIntent();
//        Bundle bundle = getIntent().getExtras();
        retrievedName = intent.getStringExtra(MainActivity.NAME_KEY);
        retrievedRole = intent.getStringExtra(MainActivity.ROLE_KEY);

//        textView = findViewById(R.id.textView);
//        textView.setText("Retrieved Name is : "+retrievedName+"\n"+"Retrieved Role is : "+retrievedRole);
        this.musicRating = (RatingBar) findViewById(R.id.musicRating);
        musicRating.setIsIndicator(true);
        this.danceRating = (RatingBar)findViewById(R.id.danceRating);
        danceRating.setIsIndicator(true);
        this.playRating = (RatingBar)findViewById(R.id.playRating);
        playRating.setIsIndicator(true);
        this.fashionRating = (RatingBar)findViewById(R.id.fashionRating);
        fashionRating.setIsIndicator(true);
        this.foodRating = (RatingBar)findViewById(R.id.foodRating);
        foodRating.setIsIndicator(true);



        this.musicCheck = (CheckBox)findViewById(R.id.musicCheck);
        this.danceCheck = (CheckBox)findViewById(R.id.danceCheck);
        this.playCheck = (CheckBox)findViewById(R.id.playCheck);
        this.fashionCheck = (CheckBox)findViewById(R.id.fashionCheck);
        this.foodCheck = (CheckBox)findViewById(R.id.foodCheck);



        musicCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(((CompoundButton) view).isChecked()){
                    musicRating.setIsIndicator(false);
                } else {
                    musicRating.setRating(0.0f);
                    musicRating.setIsIndicator(true);
                }
            }
        });

        danceCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(((CompoundButton) view).isChecked()){
                    danceRating.setIsIndicator(false);
                } else {
                    danceRating.setRating(0.0f);
                    danceRating.setIsIndicator(true);
                }
            }
        });

        playCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(((CompoundButton) view).isChecked()){
                    playRating.setIsIndicator(false);
                } else {
                    playRating.setRating(0.0f);
                    playRating.setIsIndicator(true);
                }
            }
        });

        fashionCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(((CompoundButton) view).isChecked()){
                    fashionRating.setIsIndicator(false);
                } else {
                    fashionRating.setRating(0.0f);
                    fashionRating.setIsIndicator(true);
                }
            }
        });

        foodCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(((CompoundButton) view).isChecked()){
                    foodRating.setIsIndicator(false);
                } else {
                    foodRating.setRating(0.0f);
                    foodRating.setIsIndicator(true);
                }
            }
        });


    }




    public void onClickSubmit(View view){


        float musicRating=-1, danceRating=-1, playRating=-1, fashionRating=-1, foodRating=-1;
        CheckBox musicCheck = (CheckBox)findViewById(R.id.musicCheck);
        CheckBox danceCheck = (CheckBox)findViewById(R.id.danceCheck);
        CheckBox playCheck = (CheckBox)findViewById(R.id.playCheck);
        CheckBox fashionCheck = (CheckBox)findViewById(R.id.fashionCheck);
        CheckBox foodCheck = (CheckBox)findViewById(R.id.foodCheck);

        if(musicCheck.isChecked()){
//            Toast.makeText(this, "Heyy you checked", Toast.LENGTH_SHORT).show();

            rbar = (RatingBar) findViewById(R.id.musicRating);
//            int stars = rbar.getNumStars();
            musicRating = rbar.getRating();

            if(musicRating == 0.0){
                Toast.makeText(this, "Rating cannot be 0 stars", Toast.LENGTH_SHORT).show();
                return;
            }
//            Toast.makeText(this, "int rating is "+stars+"float rating is - "+rating, Toast.LENGTH_SHORT).show();
        }
        if(danceCheck.isChecked()){
            rbar = (RatingBar) findViewById(R.id.danceRating);
            danceRating = rbar.getRating();
            if(danceRating == 0.0){
                Toast.makeText(this, "Rating cannot be 0 stars", Toast.LENGTH_SHORT).show();
                return;
            }
        }
        if(playCheck.isChecked()){
            rbar = (RatingBar) findViewById(R.id.playRating);
            playRating = rbar.getRating();
            if(playRating == 0.0){
                Toast.makeText(this, "Rating cannot be 0 stars", Toast.LENGTH_SHORT).show();
                return;
            }
        }
        if(fashionCheck.isChecked()){
            rbar = (RatingBar) findViewById(R.id.fashionRating);
            fashionRating = rbar.getRating();
            if(fashionRating == 0.0){
                Toast.makeText(this, "Rating cannot be 0 stars", Toast.LENGTH_SHORT).show();
                return;
            }
        }
        if(foodCheck.isChecked()){
            rbar = (RatingBar) findViewById(R.id.foodRating);
            foodRating = rbar.getRating();
            if(foodRating == 0.0){
                Toast.makeText(this, "Rating cannot be 0 stars", Toast.LENGTH_SHORT).show();
                return;
            }
        }

        Intent intent = new Intent(this,  MainActivity3.class);

        intent.putExtra("musicR",musicRating);
        intent.putExtra("danceR",danceRating);
        intent.putExtra("playR",playRating);
        intent.putExtra("fashionR",fashionRating);
        intent.putExtra("foodR",foodRating);
        intent.putExtra("name",retrievedName);
        intent.putExtra("role",retrievedRole);

        Toast.makeText(this, "Your entry has been recorded", Toast.LENGTH_SHORT).show();
        startActivity(intent);
    }


    public void onStart() {

        super.onStart();

        if(restarted == true){
            Toast.makeText(this, "State of activity "+this.getClass().getSimpleName()+" changed from onRestart to onStart", Toast.LENGTH_SHORT).show();
            Log.i("State Change Info", "State of activity "+this.getClass().getSimpleName()+" changed from onRestart to onStart");
        }
        else{
            Toast.makeText(this, "State of activity "+this.getClass().getSimpleName()+" changed from onCreate to onStart", Toast.LENGTH_SHORT).show();
            Log.i("State Change Info", "State of activity "+this.getClass().getSimpleName()+" changed from onCreate to onStart");
        }
    }

    public void onResume() {

        super.onResume();
        if(paused == true){
            Toast.makeText(this, "State of activity "+this.getClass().getSimpleName()+" changed from onPause to onResume", Toast.LENGTH_SHORT).show();
            Log.i("State Change Info", "State of activity "+this.getClass().getSimpleName()+" changed from onPause to onResume");
        }
        else{
            Toast.makeText(this, "State of activity "+this.getClass().getSimpleName()+" changed from onStart to onResume", Toast.LENGTH_SHORT).show();
            Log.i("State Change Info", "State of activity "+this.getClass().getSimpleName()+" changed from onStart to onResume");
        }
    }

    public void onPause() {

        super.onPause();
        paused = true;
        Toast.makeText(this, "State of activity "+this.getClass().getSimpleName()+" changed from onResume to onPause", Toast.LENGTH_SHORT).show();
        Log.i("State Change Info", "State of activity "+this.getClass().getSimpleName()+" changed from onResume to onPause");
    }
    public void onStop() {

        super.onStop();
        stopped = true;
        Toast.makeText(this, "State of activity "+this.getClass().getSimpleName()+" changed from onPause to onStop", Toast.LENGTH_SHORT).show();
        Log.i("State Change Info", "State of activity "+this.getClass().getSimpleName()+" changed from onPause to onStop");
    }
    public void onDestroy() {

        super.onDestroy();
        Toast.makeText(this, "State of activity "+this.getClass().getSimpleName()+" changed from onStop to onDestroy", Toast.LENGTH_SHORT).show();
        Log.i("State Change Info", "State of activity "+this.getClass().getSimpleName()+" changed from onStop to onDestroy");
    }

    public void onRestart() {

        super.onRestart();
        restarted = true;

        Toast.makeText(this, "State of activity "+this.getClass().getSimpleName()+" changed from onStop to onDestroy", Toast.LENGTH_SHORT).show();
        Log.i("State Change Info", "State of activity "+this.getClass().getSimpleName()+" changed from onStop to onDestroy");

    }



    public void onClickClear(View view){
        CheckBox musicCheck = (CheckBox)findViewById(R.id.musicCheck);
        CheckBox danceCheck = (CheckBox)findViewById(R.id.danceCheck);
        CheckBox playCheck = (CheckBox)findViewById(R.id.playCheck);
        CheckBox fashionCheck = (CheckBox)findViewById(R.id.fashionCheck);
        CheckBox foodCheck = (CheckBox)findViewById(R.id.foodCheck);

        this.musicRating = (RatingBar) findViewById(R.id.musicRating);
        this.danceRating = (RatingBar)findViewById(R.id.danceRating);
        this.playRating = (RatingBar)findViewById(R.id.playRating);
        this.fashionRating = (RatingBar)findViewById(R.id.fashionRating);
        this.foodRating = (RatingBar)findViewById(R.id.foodRating);

        if(musicCheck.isChecked()){
            musicCheck.toggle();
        }
        if(danceCheck.isChecked()){
            danceCheck.toggle();
        }
        if(playCheck.isChecked()){
            playCheck.toggle();
        }
        if(fashionCheck.isChecked()){
            fashionCheck.toggle();
        }
        if(foodCheck.isChecked()){
            foodCheck.toggle();
        }
        musicRating.setRating(0.0f);
        danceRating.setRating(0.0f);
        playRating.setRating(0.0f);
        fashionRating.setRating(0.0f);
        foodRating.setRating(0.0f);

    }




}
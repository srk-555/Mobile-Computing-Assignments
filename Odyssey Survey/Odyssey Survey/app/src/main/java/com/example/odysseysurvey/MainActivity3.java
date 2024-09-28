package com.example.odysseysurvey;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity3 extends AppCompatActivity {
    TextView textView;


    private static boolean stopped = false;
    private static boolean paused = false;
    private static boolean restarted = false;
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


        setContentView(R.layout.activity_main3);

        Intent intent = getIntent();
//        Bundle bundle = getIntent().getExtras();
        String retrievedName = intent.getStringExtra("name");
        String retrievedRole = intent.getStringExtra("role");
        float musicR = intent.getFloatExtra("musicR", -1);
        float danceR = intent.getFloatExtra("danceR", -1);
        float playR = intent.getFloatExtra("playR", -1);
        float fashionR = intent.getFloatExtra("fashionR", -1);
        float foodR = intent.getFloatExtra("foodR", -1);

        String name = intent.getStringExtra("name");
        String role = intent.getStringExtra("role");

        Log.d("musicR", Float.toString(musicR));
        Log.d("danceR", Float.toString(danceR));
        Log.d("playR", Float.toString(playR));
        Log.d("fashionR", Float.toString(fashionR));
        Log.d("foodR", Float.toString(foodR));

        textView = findViewById(R.id.nameOutput);
        textView.setText(name);
        textView = findViewById(R.id.roleOutput);
        textView.setText(role);




        textView = findViewById(R.id.outputTextView);
        if(musicR > -1){
            textView.append("Music    > Rating : "+musicR+"/5 \n");
        }
        if(danceR > -1){
//            textView = findViewById(R.id.outputTextView);
            textView.append("Dance   > Rating : "+danceR+"/5 \n");
        }
        if(playR > -1){
//            textView = findViewById(R.id.outputTextView);
            textView.append("Play       > Rating : "+playR+"/5 \n");
        }
        if(fashionR > -1){
//            textView = findViewById(R.id.outputTextView);
            textView.append("Fashion > Rating : "+fashionR+"/5 \n");
        }
        if(foodR > -1){
//            textView = findViewById(R.id.outputTextView);
            textView.append("Food      > Rating : "+foodR+"/5 \n");
        }

//        startActivity(intent);
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



}
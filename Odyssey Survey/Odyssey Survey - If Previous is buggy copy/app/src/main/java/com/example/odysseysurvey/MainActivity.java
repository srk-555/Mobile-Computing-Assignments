package com.example.odysseysurvey;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Spinner spinner;
    String [] options = {"Active Participant", "Audience"};
    EditText name;

    private String roleText;
    public static final String NAME_KEY = "com.example.odysseysurvey.extra.NAME";
    public static final String ROLE_KEY = "com.example.odysseysurvey.extra.ROLE";
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

        setContentView(R.layout.activity_main);

        spinner = findViewById(R.id.spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_spinner_item, options);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                ((TextView) adapterView.getChildAt(0)).setTextSize(20);
                String value = adapterView.getItemAtPosition(i).toString();
//                Toast.makeText(MainActivity.this, value, Toast.LENGTH_SHORT).show();

                roleText = value.toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    public void onClickNext(View view){
//        Toast.makeText(this, "Next clicked", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this,  MainActivity2.class);

        name = findViewById(R.id.name);
        String nameText = name.getText().toString();

//        if(nameText.trim()==null){
//            Log.d("ifloop","if loop entered");
//            Toast.makeText(this, "Name field cannot be empty", Toast.LENGTH_SHORT).show();
//            return;
//        }
//        Log.d("nametext",nameText.trim());
        if (TextUtils.isEmpty(nameText.toString())){
            Toast.makeText(this, "Name field cannot be empty", Toast.LENGTH_SHORT).show();
            return;
        }


//        Bundle extras = new Bundle();
        intent.putExtra(NAME_KEY,nameText);
        intent.putExtra(ROLE_KEY,roleText);
//        intent.putExtras(extras);


//        Toast.makeText(this, nameText, Toast.LENGTH_SHORT).show();
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
}

//MT22069
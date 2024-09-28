package com.example.alarmy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.app.TimePickerDialog;
import android.os.Bundle;
import android.util.Log;
import android.widget.TimePicker;

public class MainActivity extends AppCompatActivity implements TimePickerDialog.OnTimeSetListener {

    private MainFragment frag1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        frag1 = new MainFragment();
        FragmentManager fmanager = getSupportFragmentManager();
        FragmentTransaction ftransaction = fmanager.beginTransaction();

        ftransaction.add(R.id.parentcontainer, frag1);
        ftransaction.commit();
    }

    public void setTime() {
////        DialogFragment picker = new TimeFragment();
//        picker.show(getSupportFragmentManager(),"picker");
        DialogFragment tpicker = new TimeFragment();
        tpicker.show(getSupportFragmentManager(), "picker");
    }

    public void onTimeSet(TimePicker tpicker, int hour, int min) {
//        BlankFragment1 obj = new BlankFragment1();

        frag1.onTimeSetFrag(tpicker, hour, min);
//
//        BlankFragment1 fragment = (BlankFragment1) getFragmentManager().findFragmentById(R.id.parentcontainer);
//        fragment.<specific_function_name>();
    }

    @Override
    protected void onDestroy() {
//        Log.d("INFO.........", "Main Activity Destroyed");
        stopService(MainFragment.intent);
        super.onDestroy();
    }

}
package com.example.alarmy;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MainFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MainFragment extends Fragment {

    //    private Queue<Toast> toastQueue;
    static boolean started = false;
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private Button setAlarm;
    private Button stopmyService;
    private Button startmyService;
    private ListView lview;

    private List<String> alarms = new ArrayList<>();

    public static ArrayList<Integer> hourArray = new ArrayList<>();
    public static ArrayList<Integer> minArray = new ArrayList<>();

    static Intent intent;

    public MainFragment() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static MainFragment newInstance(String param1, String param2) {
        MainFragment fragment = new MainFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
//        toastQueue = new LinkedList<>();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);

        setAlarm = rootView.findViewById(R.id.setAlarm);
        startmyService = rootView.findViewById(R.id.startService);
        stopmyService = rootView.findViewById(R.id.stopService);
        lview = rootView.findViewById(R.id.alarmlist);

        setAlarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Do something when the button is clicked
                // TODO Auto-generated method stub
                // Get Current Time
                MainActivity temp = (MainActivity) getActivity();
                temp.setTime();
            }
        });

        startmyService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Context context = getActivity();
//                Log.d("DEBUG...............", "onclick start service working................");
                if(started == false){
                    try {
//                      Toast.makeText(getActivity(), "New Intent Created", Toast.LENGTH_SHORT).show();
//                      Toast.makeText(getActivity(), "Min Array Now is "+minArray, Toast.LENGTH_SHORT).show();
//                      intent.putExtra("hours", hourArray);
//                      intent.putExtra("mins", minArray);
                        intent = new Intent(getActivity(), AlarmService.class);
                        started = true;

                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//                        ContextCompat.startForegroundService(getContext(), intent);
                            getActivity().startService(intent);
                        }

                    } catch (Exception e) {
                        Log.d("INFO", "Could not start the service");
                    }
                }else{
                    Toast.makeText(getActivity(), "Service Already Started", Toast.LENGTH_SHORT).show();
                    Log.d("INFO", "Service Already Started");
                }



            }
        });

        stopmyService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if (started == false) {
                        Log.d("INFO", "No service to stop");
                        Toast.makeText(getActivity(), "No service to stop", Toast.LENGTH_SHORT).show();
                    } else {
//                        getContext().stopService(intent);
                        requireActivity().stopService(intent);
                    }

                } catch (Exception e) {
                    Log.d("INFO", "No service to stop");
                    Toast.makeText(getActivity(), "No service to stop", Toast.LENGTH_SHORT).show();
                }
            }
        });
        return rootView;
    }

    public void onTimeSetFrag(TimePicker tpicker, int hour, int min) {
        String st = Integer.toString(hour) + " : " + Integer.toString(min);
        if (hourArray.contains(hour) && minArray.contains(min)) {
            Toast.makeText(getActivity(), "Cannot set duplicate alarms ", Toast.LENGTH_SHORT).show();
        } else {
            alarms.add(st);
            hourArray.add(hour);
            minArray.add(min);

            customAdapter cAdapt = new customAdapter(getActivity(), alarms);
            //ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, alarms);
            lview.setAdapter(cAdapt);
//            Toast toast = Toast.makeText(getActivity(), "Alarm Set at "+st, Toast.LENGTH_SHORT);

        }

    }
}

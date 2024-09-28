package com.example.alarmy;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class customAdapter extends ArrayAdapter {

    List<String> timings;

    Context context;


    public customAdapter(@NonNull Context context, List<String> timings) {
        super(context, R.layout.item_modifier, timings);

        this.timings = timings;
        this.context = context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_modifier,parent,false);
        ImageView imageView=view.findViewById(R.id.imageView);
        TextView textView = view.findViewById(R.id.listTextView);
        textView.setText(timings.get(position));
        return view;
    }

}

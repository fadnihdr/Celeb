package com.example.fadni.celepguessapp;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ToggleButton;

import static android.content.Context.MODE_PRIVATE;


/**
 * A simple {@link Fragment} subclass.
 */
public class SettingsFragment extends Fragment {

    private SharedPreferences preferences;
    private Button select6;
    private Button select4;



    public SettingsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_settings, container, false);
        preferences = getActivity().getSharedPreferences("choices",MODE_PRIVATE);
        return inflater.inflate(R.layout.fragment_settings, container, false);

    }

    public void done(){
        Intent intent = getActivity().getIntent();
        getActivity().overridePendingTransition(0, 0);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        getActivity().finish();
        getActivity().overridePendingTransition(0, 0);
        startActivity(intent);
    }
    public void onClick(View view){

        select4 = (Button) view.findViewById(R.id.select4);
        select6 = (Button) view.findViewById(R.id.select6);
        if (view == select4){
            preferences.edit().putInt("number", 4).apply();
            done();
        }
        if(view == select6){
            preferences.edit().putInt("number", 6).apply();
            done();
        }
    }

}

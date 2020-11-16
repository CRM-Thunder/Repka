package com.example.workout;

import android.content.Intent;
import android.os.Bundle;
import androidx.fragment.app.Fragment;

import android.os.SystemClock;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.TextView;

import com.google.android.gms.maps.MapView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ActivitiesFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ActivitiesFragment extends Fragment {
    private static boolean runStarted;

    public static void setRunStarted(boolean runStarted) {
        ActivitiesFragment.runStarted = runStarted;
    }

    public static boolean isRunStarted() {
        return runStarted;
    }

    private Chronometer chronometer;
    private Button startButton, pauseButton, resetButton;
    private long pauseOffset;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ActivitiesFragment() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static ActivitiesFragment newInstance(String param1, String param2) {
        ActivitiesFragment fragment = new ActivitiesFragment();
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



    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_activities, container, false);
        chronometer = (Chronometer)view.findViewById(R.id.chronometer);

        startButton = view.findViewById(R.id.startButton);
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!runStarted) {
                    /*if (ChronometerHelper.getmStartTime() == null) {
                        long mStartTime = SystemClock.elapsedRealtime();
                        ChronometerHelper.setmStartTime(mStartTime);
                        chronometer.setBase(mStartTime - pauseOffset);
                    } else {
                        chronometer.setBase(ChronometerHelper.getmStartTime());
                    }*/

                    chronometer.setBase(SystemClock.elapsedRealtime() - pauseOffset);
                    chronometer.start();
                    runStarted = true;
                }

            }
        });

        pauseButton = view.findViewById(R.id.pauseButton);
        pauseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(runStarted){
                    /*long mStartTime = SystemClock.elapsedRealtime();
                    ChronometerHelper.setmStartTime(mStartTime);
                    chronometer.setBase(mStartTime);
                    chronometer.stop();*/
                    chronometer.stop();
                    pauseOffset = SystemClock.elapsedRealtime() - chronometer.getBase();
                    runStarted = false;
                }

            }
        });

        resetButton = view.findViewById(R.id.resetButton);
        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chronometer.stop();
                runStarted = false;
                chronometer.setBase(SystemClock.elapsedRealtime());
                pauseOffset = 0;
            }
        });
        chronometer.setBase(SystemClock.elapsedRealtime());
            System.out.println(pauseOffset);
            return view;
    }

}
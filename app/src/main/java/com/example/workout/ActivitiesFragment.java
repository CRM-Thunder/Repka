package com.example.workout;

import android.content.Intent;
import android.os.Bundle;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.maps.MapView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ActivitiesFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ActivitiesFragment extends Fragment {
    private static boolean runStarted=false;


    public static boolean isRunStarted() {
        return runStarted;
    }

    Button startButton;
    TextView timeTxtView, startNewRunTxtView, velocityTxtView, distanceTxtView, kcalTxtView;
    MapView map;
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

            startButton = view.findViewById(R.id.buttonStart);
            timeTxtView=view.findViewById(R.id.timeTxtViewSt);
            startNewRunTxtView=view.findViewById(R.id.startNewRunTxtViewSt);
            velocityTxtView=view.findViewById(R.id.velocityTxtViewSt);
            distanceTxtView=view.findViewById(R.id.distanceTxtViewSt);
            kcalTxtView=view.findViewById(R.id.kcalTxtViewSt);
            startButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container,new ActivityStartedFragment()).addToBackStack(null).commit();
                    runStarted=true;
                }
            });
            return view;
    }
}
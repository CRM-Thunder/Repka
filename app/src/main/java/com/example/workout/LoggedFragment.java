package com.example.workout;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import static com.example.workout.LoginFragment.setIsLoggedIn;
/**
 * A simple {@link Fragment} subclass.
 * Use the {@link LoggedFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LoggedFragment extends Fragment {

    private Button logout_btn;
    private FirebaseAuth mAuth;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public LoggedFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment LoggedFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static LoggedFragment newInstance(String param1, String param2) {
        LoggedFragment fragment = new LoggedFragment();
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
        View view=inflater.inflate(R.layout.fragment_logged, container, false);
        // Inflate the layout for this fragment
        logout_btn=(Button) view.findViewById(R.id.logout_btn);
        mAuth=FirebaseAuth.getInstance();
        FirebaseUser user=FirebaseAuth.getInstance().getCurrentUser();
        logout_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoginFragment loginFragment= new LoginFragment();
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.container, loginFragment, "findThisFragment")
                        .addToBackStack(null)
                        .commit();
                setIsLoggedIn(false);
                System.out.println(user);
                mAuth.signOut();
               FirebaseUser yuser=FirebaseAuth.getInstance().getCurrentUser();
                System.out.println(yuser);
            }
        });






        return view;
    }
}
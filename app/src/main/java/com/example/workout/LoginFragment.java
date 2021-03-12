package com.example.workout;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link LoginFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LoginFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private Button logIn;
    public static boolean isLoggedIn=false;
    private TextView registertextview;
    private EditText loginemail, loginpassword;
    private FirebaseAuth mAuth;
    private ProgressBar progressBar;
    public static boolean isIsLoggedIn() {
        return isLoggedIn;
    }

    public static void setIsLoggedIn(boolean isLoggedIn) {
        LoginFragment.isLoggedIn = isLoggedIn;
    }

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public LoginFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment LoginFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static LoginFragment newInstance(String param1, String param2) {
        LoginFragment fragment = new LoginFragment();
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
        View view=inflater.inflate(R.layout.fragment_login, container, false);
        // Inflate the layout for this fragment
        logIn = view.findViewById(R.id.login_b);
        loginemail=(EditText)view.findViewById(R.id.loginemail);
        loginpassword=(EditText)view.findViewById(R.id.loginpassword);
        registertextview=(TextView) view.findViewById(R.id.registertextview);
        mAuth=FirebaseAuth.getInstance();
        progressBar=(ProgressBar)view.findViewById(R.id.login_progressbar);
        registertextview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RegisterFragment registerFragment= new RegisterFragment();
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.container, registerFragment, "findThisFragment")
                        .addToBackStack(null)
                        .commit();
            }
        });
        logIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userLogin();
                /*
                LoggedFragment loggedFragment= new LoggedFragment();
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.container, loggedFragment, "findThisFragment")
                        .addToBackStack(null)
                        .commit();
                        isLoggedIn=true;
                        */

            }
        });
        return view;
    }
    private void userLogin(){
        String email=loginemail.getText().toString().trim();
        String password=loginpassword.getText().toString().trim();
        if(email.isEmpty()){
            loginemail.setError("Email is required!");
            loginemail.requestFocus();
            return;
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            loginemail.setError("You didn't provide correct email!");
            loginemail.requestFocus();
            return;
        }
        if(password.isEmpty()){
            loginpassword.setError("Password is required!");
            loginpassword.requestFocus();
            return;
        }
        if(password.length()<8){
            loginpassword.setError("Your password length must be higher than 7");
            loginpassword.requestFocus();
            return;
        }
        if(password.length()>100){
            loginpassword.setError("The password you provided is too long!");
            loginpassword.requestFocus();
            return;
        }
        progressBar.setVisibility(View.VISIBLE);
        mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    LoggedFragment loggedFragment= new LoggedFragment();
                    getActivity().getSupportFragmentManager().beginTransaction()
                            .replace(R.id.container, loggedFragment, "findThisFragment")
                            .addToBackStack(null)
                            .commit();
                    isLoggedIn=true;



                }else{
                    Toast.makeText(getContext(),"Failed to login! Please check your credentials.",Toast.LENGTH_SHORT).show();



                }
                progressBar.setVisibility(View.GONE);
            }
        });




    }
}
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
import android.widget.Toast;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Objects;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link RegisterFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RegisterFragment extends Fragment {

    private EditText inputEmail, inputPassword, inputUsername;
    //private String username, email, password;
    private Button btnSignUp;
    private FirebaseAuth mAuth;
    private ProgressBar progressBar;


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public RegisterFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment RegisterFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static RegisterFragment newInstance(String param1, String param2) {
        RegisterFragment fragment = new RegisterFragment();
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
        View view=inflater.inflate(R.layout.fragment_register, container, false);

        // Inflate the layout for this fragment
        mAuth=FirebaseAuth.getInstance();
        progressBar=(ProgressBar) view.findViewById(R.id.register_progressbar);
        btnSignUp=(Button) view.findViewById(R.id.button5);
        inputEmail=(EditText) view.findViewById(R.id.registeremail) ;
        inputPassword=(EditText) view.findViewById(R.id.registerpassword) ;
        inputUsername=(EditText) view.findViewById(R.id.registerusername);
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerUser();

               /* if(!email.isEmpty() && password.length()>6&&password.length()<100){

                }
                else{

                    Toast toast=Toast.makeText(getContext(),"Wrong email or password! Try again. ",Toast.LENGTH_SHORT);
                    toast.show();
                }*/
            }
        });
        return view;
    }
    private void registerUser(){
        String username=inputUsername.getText().toString().trim();
        String email=inputEmail.getText().toString().trim();
        String password=inputPassword.getText().toString().trim();
        if(email.isEmpty()){
            inputEmail.setError("Email is required!");
            inputEmail.requestFocus();
            return;
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            inputEmail.setError("You didn't provide correct email!");
            inputEmail.requestFocus();
            return;
        }
        if(username.isEmpty()){
            inputUsername.setError("Username is required!");
            inputUsername.requestFocus();
            return;
        }
        if(password.isEmpty()){
            inputPassword.setError("Password is required!");
            inputPassword.requestFocus();
            return;
        }
        if(password.length()<8){
            inputPassword.setError("Your password length must be higher than 7");
            inputPassword.requestFocus();
            return;
        }
        if(password.length()>100){
            inputPassword.setError("The password you provided is too long!");
            inputPassword.requestFocus();
            return;
        }
        progressBar.setVisibility(View.VISIBLE);
        mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                  UserHelper user=new UserHelper(email,username);
                    System.out.println("Wszedł do pierwszego is succesful" );
                  FirebaseDatabase.getInstance().getReference("Users").child(Objects.requireNonNull(FirebaseAuth.getInstance().getCurrentUser()).getUid()).setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                      @Override
                      public void onComplete(@NonNull Task<Void> task) {
                          System.out.println("Wszedł do drugiego is succesful" );
                        if(task.isSuccessful()){
                           Toast.makeText(getContext(),"User has been registered successfully.",Toast.LENGTH_SHORT).show();

                        }else{
                            Toast.makeText(getContext(),"Something went wrong",Toast.LENGTH_SHORT).show();
                        }
                          progressBar.setVisibility(View.GONE);
                      }
                  });
                }
                else{
                    Toast.makeText(getContext(),"Something went wrong",Toast.LENGTH_SHORT).show();
                    progressBar.setVisibility(View.GONE);
                }
            }

        });
    }
}
package com.example.workout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Locale;

import android.app.Activity;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.MenuItem;
import android.os.Handler;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import static com.example.workout.ActivitiesFragment.isActiveRun;
import static com.example.workout.ActivitiesFragment.isRunStarted;
import static com.example.workout.LoginFragment.isIsLoggedIn;

public class MainActivity extends AppCompatActivity {
    BottomNavigationView bottomNavigation;
    Fragment activities = new ActivitiesFragment();
    Fragment home = new HomeFragment();
    Fragment challenges = new ChallengesFragment();
    Fragment settings = new SettingsFragment();
    Fragment loggedIn = new LoggedFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportFragmentManager().beginTransaction().replace(R.id.container,
                home).commit();
        bottomNavigation = findViewById(R.id.bottom_navigation);
        bottomNavigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment currentFragment = null;
                if (!isActiveRun()) {
                    bottomNavigation.getMenu().removeItem(R.id.bottom_navigation);

                    switch (item.getItemId()) {
                        case R.id.navigation_activities:

                            currentFragment = activities;
                            openFragment(currentFragment);
                            return true;
                        case R.id.navigation_home:
                            if(!isIsLoggedIn()) {
                                currentFragment = home;
                            }else{
                            currentFragment = loggedIn;
                            }
                            openFragment(currentFragment);
                            return true;
                        case R.id.navigation_challenges:
                            currentFragment = challenges;
                            openFragment(currentFragment);
                            return true;
                        case R.id.navigation_settings:
                            currentFragment = settings;
                            openFragment(currentFragment);
                            return true;
                    }
                    getSupportFragmentManager().beginTransaction().replace(R.id.container,
                            currentFragment).commit();
                } else {
                    showBottomNavigationView(bottomNavigation);
                }
                return false;

            }


        });
        /*
        String language=getLanguage();
        boolean changeLang=isChangeLang();
        if(isChangeLang()==true){
            setLang(language);
        }

         */
    }

    public void openFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    private void hideBottomNavigationView(BottomNavigationView view) {
        view.clearAnimation();
        view.animate().translationY(view.getHeight()).setDuration(300);
    }

    public void showBottomNavigationView(BottomNavigationView view) {
        view.clearAnimation();
        view.animate().translationY(0).setDuration(300);
    }

}

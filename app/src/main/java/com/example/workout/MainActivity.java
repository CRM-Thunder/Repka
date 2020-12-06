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
import static com.example.workout.SettingsFragment.getLanguage;
import static com.example.workout.SettingsFragment.isChangeLang;

public class MainActivity extends AppCompatActivity {
    BottomNavigationView bottomNavigation;
    Fragment activities = new ActivitiesFragment();
    Fragment home = new HomeFragment();
    Fragment challenges = new ChallengesFragment();
    Fragment settings = new SettingsFragment();

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
                            currentFragment = home;
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

    /*public void setLang(String s) {
        setAppLocale(s);
    }

    private void setAppLocale(String s) {
        Locale locale = new Locale(s);
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        getBaseContext().getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());
        SharedPreferences.Editor editor = getSharedPreferences("Settings", MODE_PRIVATE).edit();
        editor.putString("My_lang", s);
        editor.apply();
    }
    public void LoadLocale(){
        SharedPreferences prefs=getSharedPreferences("Settings", Activity.MODE_PRIVATE);
        String language=prefs.getString("My_lang","pl");
        setAppLocale(language);
    }

     */
}

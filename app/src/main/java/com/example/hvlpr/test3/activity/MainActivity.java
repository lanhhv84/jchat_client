package com.example.hvlpr.test3.activity;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.hvlpr.test3.R;

import java.util.List;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener((@NonNull MenuItem item) -> {

            String fragmentTag = null;
            switch (item.getItemId()) {
                case R.id.navigation_game:
                    fragmentTag = String.valueOf(R.id.navigation_game);
                    break;
                case R.id.navigation_chat:
                    fragmentTag = String.valueOf(R.id.navigation_chat);
                    break;
            }

            Fragment fragment = getSupportFragmentManager().findFragmentByTag(fragmentTag);
            if (fragment == null) {
                switch (item.getItemId()) {
                    case R.id.navigation_game:
                        fragment = new GameFragment();
                        break;
                    case R.id.navigation_chat:
                        fragment = new ChatFragment();
                        break;
                    case R.id.navigation_logout:
                        Intent loginIntent = new Intent(getBaseContext(), LoginActivity.class);
                        finish();
                        startActivity(loginIntent);
                }
            }
            switchFragment(fragment);
            return false;
        });
    }

    private void switchFragment(Fragment fragment) {
        String fragmentID ;
        if (fragment.getClass().equals(GameFragment.class)) {
            fragmentID = String.valueOf(R.id.navigation_game);
        }
        else {
            fragmentID = String.valueOf(R.id.navigation_chat);
        }
        this.getSupportFragmentManager().beginTransaction().replace(R.id.contentFragment, fragment, fragmentID).setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN).addToBackStack(null).commit();
    }

}

package com.katfang.test;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

/**
 * Created by katfang on 6/15/14.
 */
public class LandingActivity extends Activity {

    public void onCreate(Bundle savedInstancedState) {
        super.onCreate(savedInstancedState);
        String username = ((Data) getApplicationContext()).getUsername();
        if (username.equals("")) {
            Intent i = new Intent(getApplicationContext(), UsernameActivity.class);
            startActivity(i);
        } else {
            makeView(savedInstancedState);
        }
    }

    private void makeView(Bundle savedInstanceState) {
        setContentView(R.layout.landing_layout);

        findViewById(R.id.landingNewButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), NewGameActivity.class);
                startActivity(i);
            }
        });

        findViewById(R.id.landingJoinButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), JoinActivity.class);
                startActivity(i);
            }
        });

        findViewById(R.id.landingListButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), ListGamesActivity.class);
                startActivity(i);
            }
        });
    }
}
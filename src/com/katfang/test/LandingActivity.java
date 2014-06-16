package com.katfang.test;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import com.firebase.client.Firebase;
import com.firebase.simplelogin.FirebaseSimpleLoginError;
import com.firebase.simplelogin.FirebaseSimpleLoginUser;
import com.firebase.simplelogin.SimpleLogin;
import com.firebase.simplelogin.SimpleLoginAuthenticatedHandler;

/**
 * Created by katfang on 6/15/14.
 */
public class LandingActivity extends Activity {

    public void onCreate(Bundle savedInstancedState) {
        super.onCreate(savedInstancedState);

        // usernameOnly();
        login();
    }

    private void usernameOnly() {
        // If not logged in, need to login
        if (!usernameSet()) {
            Intent i = new Intent(getApplicationContext(), UsernameActivity.class);
            startActivity(i);

            // Otherwise show landing page
        } else {
            makeView();
        }
    }

    // Check against Application if user is logged in
    private boolean usernameSet() {
        String username = ((Data) getApplicationContext()).getUsername();
        return !"".equals(username);
    }

    private void login() {
        Firebase ref = new Firebase(Data.FIREBASE_URL);
        SimpleLogin authClient = new SimpleLogin(ref, getApplicationContext());

        if (!usernameSet()) {
            authClient.logout();
        }

        authClient.checkAuthStatus(new SimpleLoginAuthenticatedHandler() {
            @Override
            public void authenticated(FirebaseSimpleLoginError error, FirebaseSimpleLoginUser user) {
                if (error != null) {
                    // error
                } else if (user == null) {
                    Intent i = new Intent(getApplicationContext(), AuthenticateActivity.class);
                    startActivity(i);
                } else {
                    ((Data) getApplicationContext()).setUsername(user.getEmail());
                    makeView();
                }
            }
        });
    }

    // Make the buttons in the view do something
    private void makeView() {
        setContentView(R.layout.landing);

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
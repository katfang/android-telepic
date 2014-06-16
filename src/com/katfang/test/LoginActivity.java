package com.katfang.test;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import com.firebase.client.Firebase;
import com.firebase.simplelogin.FirebaseSimpleLoginError;
import com.firebase.simplelogin.FirebaseSimpleLoginUser;
import com.firebase.simplelogin.SimpleLogin;
import com.firebase.simplelogin.SimpleLoginAuthenticatedHandler;

/**
 * Created by katfang on 6/16/14.
 */
public class LoginActivity extends Activity {

    private Firebase ref;
    private SimpleLogin authClient;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.login);

        Button doneButton = (Button) findViewById(R.id.loginDoneButton);
        doneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginAndPlay();
            }
        });

        ref = new Firebase(Data.FIREBASE_URL);
        authClient = new SimpleLogin(ref, getApplicationContext());
    }

    private void loginAndPlay() {
        EditText email = (EditText) findViewById(R.id.loginEmail);
        EditText password = (EditText) findViewById(R.id.loginPassword);

        final String emailStr = email.getText().toString();
        final String passwordStr = password.getText().toString();
        authClient.loginWithEmail(emailStr, passwordStr, new SimpleLoginAuthenticatedHandler() {
            @Override
            public void authenticated(FirebaseSimpleLoginError error, FirebaseSimpleLoginUser user) {
                if (error == null) {
                    ((Data) getApplicationContext()).setUsername(emailStr);
                    Intent i = new Intent(getApplicationContext(), LandingActivity.class);
                    startActivity(i);
                }
            }
        });
    }
}
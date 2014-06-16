package com.katfang.test;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by katfang on 6/15/14.
 */
public class UsernameActivity extends Activity {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.username_layout);

        EditText usernameText = (EditText)findViewById(R.id.usernameText);
        usernameText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_NULL && event.getAction() == KeyEvent.ACTION_DOWN) {
                    triggerPage();
                }
                return true;
            }
        });

        findViewById(R.id.usernameGoButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                triggerPage();
            }
        });
    }

    private void triggerPage() {
        if (setUsername()) {
            startLanding();
        }
    }

    private boolean setUsername() {
        EditText inputText = (EditText)findViewById(R.id.usernameText);
        String input = inputText.getText().toString();
        if (!input.equals("")) {
            ((Data) getApplicationContext()).setUsername(input);
            return true;
        }
        return false;
    }

    private void startLanding() {
        Intent i = new Intent(getApplicationContext(), LandingActivity.class);
        startActivity(i);
    }
}
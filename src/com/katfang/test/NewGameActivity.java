package com.katfang.test;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;
import com.firebase.client.Firebase;

import java.util.Random;

/**
 * Created by katfang on 6/15/14.
 */
public class NewGameActivity extends Activity {

    private String username;
    private Firebase ref;
    private Random rand;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_layout);

        ref = new Firebase(Data.FIREBASE_URL + "/games");
        rand = new Random();

        EditText phraseText = (EditText)findViewById(R.id.startPhraseText);
        phraseText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_NULL && event.getAction() == KeyEvent.ACTION_DOWN) {
                    createNewGame();
                }
                return true;
            }
        });

        findViewById(R.id.startGoButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createNewGame();
            }
        });

        username = ((Data) getApplicationContext()).getUsername();
    }

    private void createNewGame() {
        EditText inputText = (EditText)findViewById(R.id.startPhraseText);
        String input = inputText.getText().toString();
        if (!input.equals("")) {
            Game g = new Game(username);
            g.addTurn(new Turn(username, input, "phrase"));

            ref.push().setValue(g, rand.nextDouble());

            Intent i = new Intent(getApplicationContext(), LandingActivity.class);
            startActivity(i);
        }
    }
}
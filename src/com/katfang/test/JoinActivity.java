package com.katfang.test;

import android.app.Activity;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.firebase.client.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * Created by katfang on 6/15/14.
 */
public class JoinActivity extends Activity {

    private Firebase ref;
    private Random rand;
    private Map<String, Game> games;
    private Game game;
    private String gameName;
    private double gamePriority;
    private String username;

//    private ChildEventListener listener;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ref = new Firebase(Data.FIREBASE_URL + "/games");
        rand = new Random();
        username = ((Data) getApplicationContext()).getUsername();

        games = new HashMap<String, Game>();
        pickGame();
    }

    private void pickGame() {
        ref.startAt(rand.nextDouble()).limit(1).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.getChildrenCount() == 0) {
                    getFirstGame();
                    return;
                }

                for (DataSnapshot d : dataSnapshot.getChildren()) {
                    setGame(d);
                    setLayout();
                }
            }

            @Override
            public void onCancelled(FirebaseError error) {

            }
        });
    }

    private void getFirstGame() {
        ref.startAt(0).limit(1).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.getChildrenCount() == 0) {
                    setNoGamesLayout();
                    return;
                }

                for (DataSnapshot d : dataSnapshot.getChildren()) {
                    setGame(d);
                    setLayout();
                }
            }

            @Override
            public void onCancelled(FirebaseError error) {

            }
        });
    }
    
    private void setGame(DataSnapshot d) {
        game = d.getValue(Game.class);
        gameName = d.getName();
        gamePriority = (Double) d.getPriority();
    }

    private void setLayout() {
        if (Turn.PHRASE.equals(game.getNext())) {
            setPhraseLayout();
        } else {
            setPictureLayout();
        }
    }

    private void setNoGamesLayout() {
        setContentView(R.layout.no_games_to_join);
        setButtons();
    }

    private void setButtons() {
        findViewById(R.id.ngNewButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), NewGameActivity.class);
                startActivity(i);
            }
        });

        Button joinButton = (Button) findViewById(R.id.ngJoinButton);
        joinButton.setEnabled(false);
        joinButton.setClickable(false);

        findViewById(R.id.ngListButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), ListGamesActivity.class);
                startActivity(i);
            }
        });
    }

    private void setPhraseLayout() {
        setContentView(R.layout.add_phrase);
        ImageView image = (ImageView) findViewById(R.id.phrasePic);

        Turn t = game.getTurns().get(game.getLast());
        String encoded = t.getContent();
        byte[] byteArray = Base64.decode(encoded, Base64.DEFAULT);
        image.setImageBitmap(BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length));

        findViewById(R.id.phraseDoneButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendPhrase();
            }
        });
    }

    private void sendPhrase() {
        TextView view = (TextView) findViewById(R.id.phraseText);
        String phrase = view.getText().toString();

        Turn t = new Turn(username, phrase, Turn.PHRASE);
        game.addTurn(t);

        ref.child(gameName).setValue(game, gamePriority);

        Intent i = new Intent(getApplicationContext(), ViewActivity.class);
        i.putExtra("name", gameName);
        startActivity(i);
    }

    private void setPictureLayout() {
        setContentView(R.layout.add_picture);
        TextView phraseText = (TextView) findViewById(R.id.picPhraseText);

        Turn t = game.getTurns().get(game.getLast());
        phraseText.setText(t.getContent());

        findViewById(R.id.picDoneButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendPicture();
            }
        });
    }

    private void sendPicture() {
        DrawingView view = (DrawingView) findViewById(R.id.drawing);
        String uri = view.getImageUri();

        Turn t = new Turn(username, uri,Turn.PICTURE);
        game.addTurn(t);

        ref.child(gameName).setValue(game, gamePriority);

        Intent i = new Intent(getApplicationContext(), ViewActivity.class);
        i.putExtra("name", gameName);
        startActivity(i);
    }
}

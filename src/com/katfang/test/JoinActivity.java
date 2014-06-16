package com.katfang.test;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
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
    private String gameId;
    private double gamePriority;
    private String username;
    private double pri;

//    private ChildEventListener listener;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ref = new Firebase(Data.FIREBASE_URL + "/games");
        rand = new Random();
        username = ((Data) getApplicationContext()).getUsername();

        games = new HashMap<String, Game>();
        pri = rand.nextDouble();
        ref.startAt(pri).limit(1).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                System.out.println("Hello " + pri + " " + dataSnapshot.getChildrenCount());
                for (DataSnapshot d : dataSnapshot.getChildren()) {
                    System.out.println("His");
                    game = d.getValue(Game.class);
                    gameId = d.getName();
                    gamePriority = (Double) d.getPriority();
                    setLayout();
                }
            }

            @Override
            public void onCancelled(FirebaseError error) {

            }
        });

//        listener = ref.addChildEventListener(new ChildEventListener() {
//            @Override
//            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
//                Game g = dataSnapshot.getValue(Game.class);
//                games.put(dataSnapshot.getName(), g);
//                pickGame();
//            }
//
//            @Override
//            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
//                Game g = dataSnapshot.getValue(Game.class);
//                games.put(dataSnapshot    .getName(), g);
//            }
//
//            @Override
//            public void onChildRemoved(DataSnapshot dataSnapshot) {
//                games.remove(dataSnapshot.getName());
//            }
//
//            @Override
//            public void onChildMoved(DataSnapshot dataSnapshot, String s) {
//                // No-op
//            }
//
//            @Override
//            public void onCancelled(FirebaseError error) {
//                // No-op
//            }
//        });
    }

    private void pickGame() {
        if (game == null) {
            Object[] gameArray = games.values().toArray();
            game = (Game) gameArray[rand.nextInt(gameArray.length)];
        }
        setLayout();
    }

    private void setLayout() {
        if (Turn.PHRASE.equals(game.getNext())) {
            setPhraseLayout();
        } else {
            setPictureLayout();
        }
    }

    private void setPhraseLayout() {
        setContentView(R.layout.phrase_layout);
    }

    private void setPictureLayout() {
        setContentView(R.layout.picture_layout);
        TextView phraseText = ((TextView) findViewById(R.id.picPhraseText));

        Turn t = game.getTurns().get(0);
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

        ref.child(gameId).setValue(game, gamePriority);

        Intent i = new Intent(getApplicationContext(), ViewActivity.class);
        i.putExtra("gameId", gameId);
        startActivity(i);
    }
}

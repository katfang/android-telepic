package com.katfang.test;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import com.firebase.client.Firebase;

public class ViewActivity extends ListActivity {

    private Firebase ref;
    private TurnListAdapter turnListAdapter;

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list);

        Intent i = getIntent();
        String gameName = i.getStringExtra("name");

        ref = new Firebase(Data.FIREBASE_URL + "/games/" + gameName);

        findViewById(R.id.viewNewButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), NewGameActivity.class);
                startActivity(i);
            }
        });

        findViewById(R.id.viewJoinButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), JoinActivity.class);
                startActivity(i);
            }
        });

        findViewById(R.id.viewListButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), ListGamesActivity.class);
                startActivity(i);
            }
        });
    }

    public void onStart() {
        super.onStart();
        final ListView listView = getListView();
        turnListAdapter = new TurnListAdapter(ref.child("/turns").limit(15), this, R.layout.turn_item);
        listView.setAdapter(turnListAdapter);
    }
}
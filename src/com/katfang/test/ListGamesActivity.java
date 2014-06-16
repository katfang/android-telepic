package com.katfang.test;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import com.firebase.client.Firebase;

/**
 * Created by katfang on 6/15/14.
 */
public class ListGamesActivity extends ListActivity {

    private Firebase ref;
    private GameListAdapter gameListAdapter;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list);

        ref = new Firebase(Data.FIREBASE_URL + "/games");

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
        gameListAdapter = new GameListAdapter(ref.limit(15), this, R.layout.game_list_item);
        listView.setAdapter(gameListAdapter);
    }
}
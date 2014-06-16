package com.katfang.test;

import android.app.Activity;
import android.app.ListActivity;
import android.os.Bundle;
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
        setContentView(R.layout.view_layout);

        ref = new Firebase(Data.FIREBASE_URL + "/games");
    }

    public void onStart() {
        super.onStart();
        final ListView listView = getListView();
        gameListAdapter = new GameListAdapter(ref.limit(15), this, R.layout.game_list_item);
        listView.setAdapter(gameListAdapter);
    }
}
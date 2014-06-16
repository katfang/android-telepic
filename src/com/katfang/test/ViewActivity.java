package com.katfang.test;

import android.app.ListActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.DataSetObserver;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.Random;

public class ViewActivity extends ListActivity {

    private Firebase ref;
    private TurnListAdapter turnListAdapter;

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_layout);

        Intent i = getIntent();
        String gameName = i.getStringExtra("name");

        ref = new Firebase(Data.FIREBASE_URL + "/games/" + gameName);
    }

    public void onStart() {
        super.onStart();
        final ListView listView = getListView();
        turnListAdapter = new TurnListAdapter(ref.child("/turns").limit(15), this, R.layout.turn_layout);
        listView.setAdapter(turnListAdapter);
    }
}
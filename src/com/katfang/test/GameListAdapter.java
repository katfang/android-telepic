package com.katfang.test;

import android.app.Activity;
import android.graphics.Color;
import android.view.View;
import android.widget.TextView;
import com.firebase.client.Query;

/**
 * Created by katfang on 6/15/14.
 */
public class GameListAdapter extends FirebaseListAdapter<Game> {

    public GameListAdapter(Query ref, Activity activity, int layout) {
        super(ref, Game.class, layout, activity);
    }

    @Override
    protected void populateView(View view, Game game) {
        String creator = game.getCreator();
        TextView creatorText = (TextView)view.findViewById(R.id.gliCreator);
        creatorText.setText(creator + ": ");

        String firstPhrase = game.getTurns().get(0).getContent();

        ((TextView)view.findViewById(R.id.gliStartPhrase)).setText(firstPhrase);
    }
}

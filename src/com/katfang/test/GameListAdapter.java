package com.katfang.test;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.firebase.client.Query;

/**
 * Created by katfang on 6/15/14.
 */
public class GameListAdapter extends FirebaseListAdapter<Game> {

    private Activity activity;

    public GameListAdapter(Query ref, Activity activity, int layout) {
        super(ref, Game.class, layout, activity);
        this.activity = activity;
    }

    @Override
    protected void populateView(View view, Game game, final String modelName) {
        String creator = game.getCreator();
        TextView creatorText = (TextView)view.findViewById(R.id.gliCreator);
        creatorText.setText(creator + ": ");

        String firstPhrase = game.getTurns().get(0).getContent();

        ((TextView)view.findViewById(R.id.gliStartPhrase)).setText(firstPhrase);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(activity.getApplicationContext(), ViewActivity.class);
                i.putExtra("name", modelName);
                activity.startActivity(i);
            }
        });
    }
}

package com.katfang.test;

import android.app.Activity;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.util.Base64;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.firebase.client.Query;

/**
 * Created by katfang on 6/15/14.
 */
public class TurnListAdapter extends FirebaseListAdapter<Turn> {

    private Activity activity;

    public TurnListAdapter(Query ref, Activity activity, int layout) {
        super(ref, Turn.class, layout, activity);
        this.activity = activity;
    }

    @Override
    protected void populateView(View view, Turn turn) {
        if (Turn.PICTURE.equals(turn.getType())) {
            String encoded = turn.getContent();
            byte[] byteArray = Base64.decode(encoded, Base64.DEFAULT);
            ImageView image = new ImageView(activity);
            image.setImageBitmap(BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length));
            ((LinearLayout) view).addView(image);
        } else {
            String content = turn.getContent();
            TextView text = new TextView(activity);
            text.setTextColor(0xFF000000);
            text.setText(content);
            ((LinearLayout) view).addView(text);
        }

        String creator = turn.getCreator();
        view.invalidate();
    }
}
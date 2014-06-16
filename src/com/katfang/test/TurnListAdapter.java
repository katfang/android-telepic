package com.katfang.test;

import android.app.ActionBar;
import android.app.Activity;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.util.Base64;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.firebase.client.Query;

/**
 * Created by katfang on 6/15/14.
 */
public class TurnListAdapter extends FirebaseListAdapter<Turn> {

    public TurnListAdapter(Query ref, Activity activity, int layout) {
        super(ref, Turn.class, layout, activity);
    }

    @Override
    protected void populateView(View view, Turn turn, String modelName) {
        if (Turn.PICTURE.equals(turn.getType())) {
            populatePicture(view, turn);
        } else {
            populatePhrase(view, turn);
        }
    }

    private void populatePicture(View view, Turn turn) {
        ImageView image = (ImageView) view.findViewById(R.id.turnPicture);
        image.setVisibility(ImageView.VISIBLE);
        String encoded = turn.getContent();
        byte[] byteArray = Base64.decode(encoded, Base64.DEFAULT);
        image.setImageBitmap(BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length));

        TextView text = (TextView) view.findViewById(R.id.turnPhrase);
        text.setVisibility(TextView.GONE);
    }

    private void populatePhrase(View view, Turn turn) {
        TextView text = (TextView) view.findViewById(R.id.turnPhrase);
        text.setVisibility(TextView.VISIBLE);
        String content = turn.getContent();
        text.setTextColor(0xFF000000);
        text.setText(content);

        ImageView image = (ImageView) view.findViewById(R.id.turnPicture);
        image.setVisibility(ImageView.GONE);
    }
}
package com.barclaycard.rpachaiyappa.MoviesAnyTime.activities;


import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.ImageView;

import android.widget.TextView;

import com.barclaycard.rpachaiyappa.MoviesAnyTime.DataCache;
import com.barclaycard.rpachaiyappa.MoviesAnyTime.MyApplication;
import com.barclaycard.rpachaiyappa.MoviesAnyTime.R;
import com.barclaycard.rpachaiyappa.MoviesAnyTime.model.MovieItem;
import com.barclaycard.rpachaiyappa.MoviesAnyTime.support.AppConstants;
import com.squareup.picasso.Picasso;

/**
 * Created by rpachaiyappa on 8/9/14.
 */
public class MovieDetailActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);

        Bundle bundle = getIntent().getExtras();

        if (bundle != null) {
            Integer movieItemPos = bundle.getInt(AppConstants.KEY_MOVIEITEMPOS);
            MovieItem movieItem = DataCache.getInstance().getMovieItemList().movieItems.get(movieItemPos.intValue());
            if (movieItem!=null) {
                ImageView imageView = (ImageView) findViewById(R.id.detail_imageView);
                String imgUrl = movieItem.posters.detailed;
                imgUrl = imgUrl.replace("tmb","ori");
                Picasso.with(MyApplication.getContext())
                        .load(imgUrl)
                        .config(Bitmap.Config.RGB_565)
                        .error(R.drawable.ic_launcher)
                        .into(imageView);
                TextView synopsis = (TextView) findViewById(R.id.tv_synopsis);
                synopsis.setText(movieItem.synopsis);

                TextView titleTextView = (TextView) findViewById(R.id.tv_title);
                titleTextView.setText(movieItem.title);
                if(movieItem.critics_consensus.length()!=0) {
                    TextView critics = (TextView) findViewById(R.id.tv_critics);
                    String criticsStr = "Critics: " + movieItem.critics_consensus;
                    critics.setText(criticsStr);
                }
            }
        } else {
            finish();
        }



    }

}

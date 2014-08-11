package com.barclaycard.rpachaiyappa.MoviesAnyTime.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.barclaycard.rpachaiyappa.MoviesAnyTime.DataCache;
import com.barclaycard.rpachaiyappa.MoviesAnyTime.MyApplication;
import com.barclaycard.rpachaiyappa.MoviesAnyTime.R;
import com.barclaycard.rpachaiyappa.MoviesAnyTime.model.MovieItem;
import com.squareup.picasso.Picasso;

/**
 * Created by rpachaiyappa on 8/2/14.
 */
public class MovieListAdapter extends BaseAdapter {

    @Override
    public int getCount() {
        return DataCache.getInstance().getMovieItemList().movieItems.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;

        if (convertView == null) {
            viewHolder = new ViewHolder();

            convertView = LayoutInflater.from(MyApplication.getContext()).inflate(R.layout.view_movie_list, null);

            viewHolder.movieImageView = (ImageView) convertView.findViewById(R.id.movie_image_view);

            viewHolder.movieTextView = (TextView) convertView.findViewById(R.id.movie_title_text_view);
            viewHolder.movieReleaseDate = (TextView) convertView.findViewById(R.id.tv_movie_release);
            viewHolder.rating = (TextView) convertView.findViewById(R.id.tv_rating);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        MovieItem movieItem = DataCache.getInstance().getMovieItemList().movieItems.get(position);

        Picasso.with(MyApplication.getContext()).load(movieItem.posters.original).into(viewHolder.movieImageView);

        viewHolder.movieTextView.setText(movieItem.title);
        String inTheaterStr = "In theater: " + movieItem.releaseDates.releaseDates_Theater;
        viewHolder.movieReleaseDate.setText(inTheaterStr);
        String rating = "Rating: " + movieItem.mpaa_rating;
        viewHolder.rating.setText(rating);

        return convertView;
    }

    class ViewHolder {
        ImageView movieImageView;

        TextView movieTextView;

        TextView movieReleaseDate;

        TextView rating;
    }
}

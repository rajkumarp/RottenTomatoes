package com.barclaycard.rpachaiyappa.MoviesAnyTime.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.barclaycard.rpachaiyappa.MoviesAnyTime.DataCache;
import com.barclaycard.rpachaiyappa.MoviesAnyTime.MyApplication;
import com.barclaycard.rpachaiyappa.MoviesAnyTime.R;
import com.barclaycard.rpachaiyappa.MoviesAnyTime.adapter.MovieListAdapter;
import com.barclaycard.rpachaiyappa.MoviesAnyTime.events.OnDataLoaded;
import com.barclaycard.rpachaiyappa.MoviesAnyTime.model.DataModel;
import com.barclaycard.rpachaiyappa.MoviesAnyTime.model.MovieItem;
import com.barclaycard.rpachaiyappa.MoviesAnyTime.model.MovieItemList;
import com.barclaycard.rpachaiyappa.MoviesAnyTime.model.MovieModel;
import com.barclaycard.rpachaiyappa.MoviesAnyTime.model.MovieTypesModel;
import com.barclaycard.rpachaiyappa.MoviesAnyTime.support.AppConstants;
import com.barclaycard.rpachaiyappa.MoviesAnyTime.activities.MovieDetailActivity;

/**
 * Created by rpachaiyappa on 8/2/14.
 */
public class MainActivity extends Activity {

    public static final String TAG = "MainActivity";

    private ListView mListView;

    private MovieListAdapter mMovieListAdapter;

    private ProgressBar mProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        mProgressBar = (ProgressBar) findViewById(R.id.progress_bar);

        mListView = (ListView) findViewById(R.id.list_view);
        mListView.setAdapter(mMovieListAdapter = new MovieListAdapter());

        MyApplication.setOnDataLoaded(new OnDataLoaded() {
            @Override
            public void onDataLoad() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (mProgressBar != null) {
                            mProgressBar.setVisibility(View.GONE);

                            mMovieListAdapter.notifyDataSetChanged();
                        }

                        Log.d(TAG, "size: " + DataCache.getInstance().getMovieItemList().movieItems.size());
                    }
                });
            }
        });

        initializeApp();

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    Intent intent = new Intent(MyApplication.getContext(), MovieDetailActivity.class);
                    intent.putExtra(AppConstants.KEY_MOVIEITEMPOS,
                            i);
                    startActivity(intent);
            }
        });

    }

    private void initializeApp() {
        MyApplication.getRottenTomatoesAPI().fetchDataList(
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("DOH", "response: " + response);

                        DataModel dataModel = new DataModel();
                        dataModel.parseAndUpdate(response);

                        fetchLists(dataModel.listsUrl);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }
        );
    }

    private void fetchLists(String url) {
        MyApplication.getRottenTomatoesAPI().fetchData(url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d(TAG, "fetchLists: " + response);

                        MovieModel movieModel = new MovieModel();
                        movieModel.parseAndUpdate(response);

                        fetchMovies(movieModel.moviesUrl);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }
        );
    }

    private void fetchMovies(String url) {
        MyApplication.getRottenTomatoesAPI().fetchData(url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d(TAG, "fetchMovies: " + response);

                        MovieTypesModel movieTypesModel = new MovieTypesModel();
                        movieTypesModel.parseAndUpdate(response);

                        fetchInTheatres(movieTypesModel.inTheaters);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }
        );
    }

    private void fetchInTheatres(String url) {
        MyApplication.getRottenTomatoesAPI().fetchData(url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d(TAG, "fetchInTheatres: " + response);

                        MovieItemList movieItemList = new MovieItemList();
                        movieItemList.parseAndUpdate(response);

                        DataCache.getInstance().getMovieItemList().movieItems.addAll(movieItemList.movieItems);
                        MyApplication.sendDataLoadedEvent();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }
        );
    }

}

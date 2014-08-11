package com.barclaycard.rpachaiyappa.MoviesAnyTime.model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by rpachaiyappa on 8/3/14.
 */
public class MovieItem {

    public String id;

    public String title;

    public int year;

    public String mpaa_rating;

    public int runtime;

    public String critics_consensus;

    public MovieRating movieRating;

    public String synopsis;

    public Posters posters;

    public ReleaseDates releaseDates;

    public Cast abridged_cast;

    public Links links;

    public void parseAndUpdate(JSONObject movieObject) {
        this.id = movieObject.optString("id");
        this.title = movieObject.optString("title");
        this.year = Integer.parseInt(movieObject.optString("year"));
        this.mpaa_rating = movieObject.optString("mpaa_rating");
        this.runtime = Integer.parseInt(movieObject.optString("runtime"));
        this.critics_consensus = movieObject.optString("critics_consensus");

        releaseDates = new ReleaseDates();
        releaseDates.parseAndUpdate(movieObject.optJSONObject("release_dates"));

        synopsis = movieObject.optString("synopsis");

        posters = new Posters();
        posters.parseAndUpdate(movieObject.optJSONObject("posters"));
    }
}

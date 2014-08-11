package com.barclaycard.rpachaiyappa.MoviesAnyTime;

import android.app.Application;
import android.content.Context;

import com.barclaycard.rpachaiyappa.MoviesAnyTime.events.OnDataLoaded;
import com.barclaycard.rpachaiyappa.MoviesAnyTime.rottentomatoes.RottenTomatoesAPI;
import com.barclaycard.rpachaiyappa.MoviesAnyTime.volley.VolleyManager;

/**
 * Created by rpachaiyappa on 8/3/14.
 */
public class MyApplication extends Application {

    private static Context sContext;

    private static VolleyManager sVolleyManager;

    private static RottenTomatoesAPI sRottenTomatoesAPI;

    private static OnDataLoaded sOnDataLoaded;

    @Override
    public void onCreate() {
        super.onCreate();

        sContext = getApplicationContext();

        sVolleyManager = new VolleyManager();

        sRottenTomatoesAPI = new RottenTomatoesAPI();
    }

    public static Context getContext() {
        return sContext;
    }

    public static VolleyManager getVolleyManager() {
        return sVolleyManager;
    }

    public static RottenTomatoesAPI getRottenTomatoesAPI() {
        return sRottenTomatoesAPI;
    }

    public static void setOnDataLoaded(OnDataLoaded onDataLoaded) {
        sOnDataLoaded = onDataLoaded;
    }

    public static void sendDataLoadedEvent() {
        if (sOnDataLoaded != null) {
            sOnDataLoaded.onDataLoad();
        }
    }


}

package com.barclaycard.rpachaiyappa.MoviesAnyTime.volley;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.barclaycard.rpachaiyappa.MoviesAnyTime.MyApplication;

/**
 * Created by rpachaiyappa on 8/3/14.
 */
public class VolleyManager {

    private RequestQueue mRequestQueue = Volley.newRequestQueue(MyApplication.getContext());

    public void addToRequestQueue(Request request) {
        if (mRequestQueue != null) {
            mRequestQueue.add(request);
        }
    }
}

package com.barclaycard.rpachaiyappa.MoviesAnyTime.model;

import org.json.JSONObject;

/**
 * Created by rpachaiyappa on 8/10/14.
 */
public class ReleaseDates  {
    public String releaseDates_Theater;
    public void parseAndUpdate(JSONObject jsonObject) {
        if (jsonObject != null) {
            releaseDates_Theater = jsonObject.optString("theater");
        }
    }
}

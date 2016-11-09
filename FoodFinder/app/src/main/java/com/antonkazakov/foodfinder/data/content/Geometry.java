package com.antonkazakov.foodfinder.data.content;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by antonkazakov on 29.10.16.
 */

public class Geometry {

    @SerializedName("location")
    @Expose
    private NearLocation location;

    /**
     * @return The location
     */
    public NearLocation getLocation() {
        return location;
    }

    /**
     * @param location The location
     */
    public void setLocation(NearLocation location) {
        this.location = location;
    }

}

package com.antonkazakov.roadsigns.content;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by antonkazakov on 22.10.16.
 */

public class Image {

    @SerializedName("thumb_120")
    @Expose
    private String thumb120;
    @SerializedName("uuid")
    @Expose
    private String uuid;

    /**
     *
     * @return
     * The thumb120
     */
    public String getThumb120() {
        return thumb120;
    }

    /**
     *
     * @param thumb120
     * The thumb_120
     */
    public void setThumb120(String thumb120) {
        this.thumb120 = thumb120;
    }

    /**
     *
     * @return
     * The uuid
     */
    public String getUuid() {
        return uuid;
    }

    /**
     *
     * @param uuid
     * The uuid
     */
    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

}

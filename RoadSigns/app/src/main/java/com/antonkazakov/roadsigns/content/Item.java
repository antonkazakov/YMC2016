package com.antonkazakov.roadsigns.content;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by antonkazakov on 22.10.16.
 */

public class Item {
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("url")
    @Expose
    private String url;
    @SerializedName("custom")
    @Expose
    private String custom;
    @SerializedName("content")
    @Expose
    private Object content;
    @SerializedName("trackable")
    @Expose
    private Boolean trackable;
    @SerializedName("uuid")
    @Expose
    private String uuid;

    /**
     *
     * @return
     * The name
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @param name
     * The name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     *
     * @return
     * The url
     */
    public String getUrl() {
        return url;
    }

    /**
     *
     * @param url
     * The url
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     *
     * @return
     * The custom
     */
    public String getCustom() {
        return custom;
    }

    /**
     *
     * @param custom
     * The custom
     */
    public void setCustom(String custom) {
        this.custom = custom;
    }

    /**
     *
     * @return
     * The content
     */
    public Object getContent() {
        return content;
    }

    /**
     *
     * @param content
     * The content
     */
    public void setContent(Object content) {
        this.content = content;
    }

    /**
     *
     * @return
     * The trackable
     */
    public Boolean getTrackable() {
        return trackable;
    }

    /**
     *
     * @param trackable
     * The trackable
     */
    public void setTrackable(Boolean trackable) {
        this.trackable = trackable;
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

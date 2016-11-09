package com.antonkazakov.roadsigns.content;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by antonkazakov on 22.10.16.
 */
public class RecognizeResponse {

    @SerializedName("search_time")
    @Expose
    private Integer searchTime;
    @SerializedName("results")
    @Expose
    private List<Result> results = new ArrayList<>();

    /**
     *
     * @return
     * The searchTime
     */
    public Integer getSearchTime() {
        return searchTime;
    }

    /**
     *
     * @param searchTime
     * The search_time
     */
    public void setSearchTime(Integer searchTime) {
        this.searchTime = searchTime;
    }

    /**
     *
     * @return
     * The results
     */
    public List<Result> getResults() {
        return results;
    }

    /**
     *
     * @param results
     * The results
     */
    public void setResults(List<Result> results) {
        this.results = results;
    }



}

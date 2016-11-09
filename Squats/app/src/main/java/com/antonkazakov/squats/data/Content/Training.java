package com.antonkazakov.squats.data.Content;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by antonkazakov on 17.10.16.
 */
public class Training extends RealmObject{

    @PrimaryKey
    Integer train_id;

    Integer trainingId;

    Long startTime;

    Long endTime;

    Integer count;

    public Training(){}

    public Training(Integer trainingId, Long startTime, Long endTime, Integer count){
        this.trainingId = trainingId;
        this.startTime = startTime;
        this.endTime = endTime;
        this.count = count;
    }

    public Integer getId() {
        return train_id;
    }

    public void setId(Integer train_id) {
        this.train_id = train_id;
    }

    public Integer getCount() {
        return count;
    }

    public Long getEndTime() {
        return endTime;
    }

    public Long getStartTime() {
        return startTime;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public void setEndTime(Long endTime) {
        this.endTime = endTime;
    }

    public void setStartTime(Long startTime) {
        this.startTime = startTime;
    }
}

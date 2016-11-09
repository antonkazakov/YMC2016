package com.antonkazakov.squats.data.Content;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by antonkazakov on 17.10.16.
 */
public class TrainingType extends RealmObject{

    @PrimaryKey
    Integer trainingtype_id;

    String name;

    String description;

    String numSquats;

    public TrainingType(){}

    public TrainingType(String name, String description,String numSquats){
        this.name = name;
        this.description = description;
        this.numSquats = numSquats;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(Integer id) {
        this.trainingtype_id = id;
    }

    public String getName() {
        return name;
    }

    public Integer getId() {
        return trainingtype_id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getTrainingtype_id() {
        return trainingtype_id;
    }

    public String getNumSquats() {
        return numSquats;
    }

    public void setNumSquats(String numSquats) {
        this.numSquats = numSquats;
    }

    public void setTrainingtype_id(Integer trainingtype_id) {
        this.trainingtype_id = trainingtype_id;
    }
}

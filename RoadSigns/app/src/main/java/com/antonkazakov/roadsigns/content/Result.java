package com.antonkazakov.roadsigns.content;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by antonkazakov on 22.10.16.
 */

public class Result {

    @SerializedName("item")
    @Expose
    private Item item;
    @SerializedName("image")
    @Expose
    private Image image;
    @SerializedName("score")
    @Expose
    private Integer score;

    /**
     *
     * @return
     * The item
     */
    public Item getItem() {
        return item;
    }

    /**
     *
     * @param item
     * The item
     */
    public void setItem(Item item) {
        this.item = item;
    }

    /**
     *
     * @return
     * The image
     */
    public Image getImage() {
        return image;
    }

    /**
     *
     * @param image
     * The image
     */
    public void setImage(Image image) {
        this.image = image;
    }

    /**
     *
     * @return
     * The score
     */
    public Integer getScore() {
        return score;
    }

    /**
     *
     * @param score
     * The score
     */
    public void setScore(Integer score) {
        this.score = score;
    }

}

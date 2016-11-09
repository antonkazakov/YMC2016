
package com.antonkazakov.radio.data.content;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("org.jsonschema2pojo")
public class Stream {

    @SerializedName("stream")
    @Expose
    private String stream;
    @SerializedName("bitrate")
    @Expose
    private Integer bitrate;
    @SerializedName("content_type")
    @Expose
    private String contentType;
    @SerializedName("status")
    @Expose
    private Integer status;

    /**
     * 
     * @return
     *     The stream
     */
    public String getStream() {
        return stream;
    }

    /**
     * 
     * @param stream
     *     The stream
     */
    public void setStream(String stream) {
        this.stream = stream;
    }

    /**
     * 
     * @return
     *     The bitrate
     */
    public Integer getBitrate() {
        return bitrate;
    }

    /**
     * 
     * @param bitrate
     *     The bitrate
     */
    public void setBitrate(Integer bitrate) {
        this.bitrate = bitrate;
    }

    /**
     * 
     * @return
     *     The contentType
     */
    public String getContentType() {
        return contentType;
    }

    /**
     * 
     * @param contentType
     *     The content_type
     */
    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    /**
     * 
     * @return
     *     The status
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 
     * @param status
     *     The status
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

}

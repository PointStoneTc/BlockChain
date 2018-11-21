package com.chain.wp.restapi.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * wp-restapi-obj
 * 
 * @author zhaoqi
 *
 */
public class Media implements Serializable {
    private static final long serialVersionUID = 7863637905702382828L;
    private int id;
    private String media_type;
    private String mime_type;
    private List<MediaDetail> media_details;

    public Media() {
        media_details = new ArrayList<MediaDetail>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMedia_type() {
        return media_type;
    }

    public void setMedia_type(String media_type) {
        this.media_type = media_type;
    }

    public String getMime_type() {
        return mime_type;
    }

    public void setMime_type(String mime_type) {
        this.mime_type = mime_type;
    }

    public List<MediaDetail> getMedia_details() {
        return media_details;
    }

    public void setMedia_details(List<MediaDetail> media_details) {
        this.media_details = media_details;
    }
}

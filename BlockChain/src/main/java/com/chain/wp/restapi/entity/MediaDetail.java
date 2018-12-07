package com.chain.wp.restapi.entity;

import java.io.Serializable;

/**
 * wp-restapi-obj
 * 
 * @author zhaoqi
 *
 */
public class MediaDetail implements Serializable {
    private static final long serialVersionUID = -386710480032579107L;
    private String name;
    private String file;
    private int width;
    private int height;
    private String mime_type;
    private String source_url;

    public MediaDetail() {}

    public MediaDetail(String name, String file, int width, int height, String mime_type, String source_url) {
        this.name = name;
        this.file = file;
        this.width = width;
        this.height = height;
        this.mime_type = mime_type;
        this.source_url = source_url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public String getMime_type() {
        return mime_type;
    }

    public void setMime_type(String mime_type) {
        this.mime_type = mime_type;
    }

    public String getSource_url() {
        return source_url;
    }

    public void setSource_url(String source_url) {
        this.source_url = source_url;
    }

    public Double getCompareValue() {
        double w = width;
        double h = height;
        return Double.valueOf(w / h);
    }
}

package com.chain.wp.restapi.entity;

import java.io.Serializable;

/**
 * wp-restapi-obj
 * 
 * @author zhaoqi
 *
 */
public class Tag implements Serializable {
    private static final long serialVersionUID = -8160562186936949079L;

    private int id;
    private int count;
    private String description;
    private String name;
    private String slug;

    public Tag() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

}

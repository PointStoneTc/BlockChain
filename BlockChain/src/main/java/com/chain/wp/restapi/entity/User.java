package com.chain.wp.restapi.entity;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * wp-restapi-obj
 * 
 * @author zhaoqi
 *
 */
public class User implements Serializable {
    private static final long serialVersionUID = 2394384935435710992L;
    private int id;
    private String name;
    private String description;

    private Map<Integer, String> avatar_urls;

    public User() {
        avatar_urls = new HashMap<Integer, String>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Map<Integer, String> getAvatar_urls() {
        return avatar_urls;
    }

    public void setAvatar_urls(Map<Integer, String> avatar_urls) {
        this.avatar_urls = avatar_urls;
    }
}

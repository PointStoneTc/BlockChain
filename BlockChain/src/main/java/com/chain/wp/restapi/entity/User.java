package com.chain.wp.restapi.entity;

import java.io.Serializable;

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

    public User() {}

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
}

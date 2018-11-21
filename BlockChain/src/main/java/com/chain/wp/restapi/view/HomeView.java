package com.chain.wp.restapi.view;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.chain.wp.restapi.entity.Category;
import com.chain.wp.restapi.entity.Post;
import com.chain.wp.restapi.entity.Tag;
import com.chain.wp.restapi.entity.User;

/**
 * wp-restapi-obj
 * 
 * @author zhaoqi
 *
 */
public class HomeView implements Serializable {
    private static final long serialVersionUID = -802377909960666488L;
    private List<Category> caterories;
    private List<Tag> tags;
    private List<User> users;
    private Map<Integer, List<Post>> postMap;

    public HomeView() {
        caterories = new ArrayList<Category>();
        tags = new ArrayList<Tag>();
        users = new ArrayList<User>();
        postMap = new HashMap<Integer, List<Post>>();
    }

    public List<Category> getCaterories() {
        return caterories;
    }

    public void setCaterories(List<Category> caterories) {
        this.caterories = caterories;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }


    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public Map<Integer, List<Post>> getPostMap() {
        return postMap;
    }

    public void setPostMap(Map<Integer, List<Post>> postMap) {
        this.postMap = postMap;
    }
}

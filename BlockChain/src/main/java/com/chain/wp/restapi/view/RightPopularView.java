package com.chain.wp.restapi.view;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.chain.wp.restapi.entity.Post;

public class RightPopularView implements Serializable {
    private static final long serialVersionUID = 3711672699778431405L;
    private int id;
    private List<Post> list;

    public RightPopularView() {
        list = new ArrayList<Post>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Post> getList() {
        return list;
    }

    public void setList(List<Post> list) {
        this.list = list;
    }

}

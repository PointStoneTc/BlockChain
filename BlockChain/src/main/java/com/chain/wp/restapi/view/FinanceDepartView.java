package com.chain.wp.restapi.view;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class FinanceDepartView implements Serializable {
    private static final long serialVersionUID = 7894885742121182039L;
    private int id;
    List<Detail> array;

    public FinanceDepartView() {
        array = new ArrayList<Detail>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Detail> getArray() {
        return array;
    }

    public void setArray(List<Detail> array) {
        this.array = array;
    }

    public void add(String publishDate, String title, String href) {
        Detail detail = new Detail(publishDate, title, href);
        array.add(detail);
    }
}


class Detail {
    private String publishDate;
    private String title;
    private String href;

    public Detail() {}

    public Detail(String publishDate, String title, String href) {
        this.publishDate = publishDate;
        this.title = title;
        this.href = href;
    }


    public String getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(String publishDate) {
        this.publishDate = publishDate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }
}

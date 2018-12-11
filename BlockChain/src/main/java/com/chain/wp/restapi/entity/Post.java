package com.chain.wp.restapi.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * wp-restapi-obj
 * 
 * @author zhaoqi
 *
 */
public class Post implements Serializable {
    private static final long serialVersionUID = -2307798730479397484L;
    private int id;
    private String date;
    private String status;
    private String title;
    private String content;
    private String excerpt;
    private String comment_status;
    private String ping_status;
    private boolean sticky;
    private String jetpack_featured_media_url;

    private User author;
    private List<Category> categories;
    private List<Tag> tags;
    private Media featuredMedia;

    private String index; // 用户轮播这类的位置定位
    private Integer loaction; // 页面的定位
    private Integer authorId; // 作者id
    private Integer[] categoryIds; // 自身所有的分类id
    private Integer[] tagIds; // 自身所有的标签id
    private int wp_featuredmedia_id = 0; // 特色图片id
    private List<LinkedHashMap<String, Object>> metadata; // meta数据

    // 附加属性
    private MediaDetail thumbnailMediaDetail; // 默认的缩略图

    public Post() {
        categories = new ArrayList<Category>();
        tags = new ArrayList<Tag>();
        featuredMedia = new Media();
        metadata = new ArrayList<LinkedHashMap<String, Object>>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getExcerpt() {
        return excerpt;
    }

    public void setExcerpt(String excerpt) {
        this.excerpt = excerpt;
    }

    public String getComment_status() {
        return comment_status;
    }

    public void setComment_status(String comment_status) {
        this.comment_status = comment_status;
    }

    public String getPing_status() {
        return ping_status;
    }

    public void setPing_status(String ping_status) {
        this.ping_status = ping_status;
    }

    public boolean isSticky() {
        return sticky;
    }

    public void setSticky(boolean sticky) {
        this.sticky = sticky;
    }

    public String getJetpack_featured_media_url() {
        return jetpack_featured_media_url;
    }

    public void setJetpack_featured_media_url(String jetpack_featured_media_url) {
        this.jetpack_featured_media_url = jetpack_featured_media_url;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    public Media getFeaturedMedia() {
        return featuredMedia;
    }

    public void setFeaturedMedia(Media featuredMedia) {
        this.featuredMedia = featuredMedia;
    }

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    public Integer getLoaction() {
        return loaction;
    }

    public void setLoaction(Integer loaction) {
        this.loaction = loaction;
    }

    public Integer getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Integer authorId) {
        this.authorId = authorId;
    }

    public Integer[] getCategoryIds() {
        return categoryIds;
    }

    public void setCategoryIds(Integer[] categoryIds) {
        this.categoryIds = categoryIds;
    }

    public Integer[] getTagIds() {
        return tagIds;
    }

    public void setTagIds(Integer[] tagIds) {
        this.tagIds = tagIds;
    }

    public int getWp_featuredmedia_id() {
        return wp_featuredmedia_id;
    }

    public void setWp_featuredmedia_id(int wp_featuredmedia_id) {
        this.wp_featuredmedia_id = wp_featuredmedia_id;
    }

    public List<LinkedHashMap<String, Object>> getMetadata() {
        return metadata;
    }

    public void setMetadata(List<LinkedHashMap<String, Object>> metadata) {
        this.metadata = metadata;
    }

    public MediaDetail getThumbnailMediaDetail() {
        return thumbnailMediaDetail;
    }

    public void setThumbnailMediaDetail(MediaDetail thumbnailMediaDetail) {
        this.thumbnailMediaDetail = thumbnailMediaDetail;
    }

}

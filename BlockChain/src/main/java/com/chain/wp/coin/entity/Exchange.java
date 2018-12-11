package com.chain.wp.coin.entity;

import java.io.Serializable;

public class Exchange implements Serializable {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column cc_exchange.id
     *
     * @mbggenerated
     */
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column cc_exchange.exchangeid
     *
     * @mbggenerated
     */
    private Integer exchangeid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column cc_exchange.slug
     *
     * @mbggenerated
     */
    private String slug;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column cc_exchange.website
     *
     * @mbggenerated
     */
    private String website;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column cc_exchange.name
     *
     * @mbggenerated
     */
    private String name;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column cc_exchange.is_active
     *
     * @mbggenerated
     */
    private String isActive;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table cc_exchange
     *
     * @mbggenerated
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column cc_exchange.id
     *
     * @return the value of cc_exchange.id
     *
     * @mbggenerated
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column cc_exchange.id
     *
     * @param id the value for cc_exchange.id
     *
     * @mbggenerated
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column cc_exchange.exchangeid
     *
     * @return the value of cc_exchange.exchangeid
     *
     * @mbggenerated
     */
    public Integer getExchangeid() {
        return exchangeid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column cc_exchange.exchangeid
     *
     * @param exchangeid the value for cc_exchange.exchangeid
     *
     * @mbggenerated
     */
    public void setExchangeid(Integer exchangeid) {
        this.exchangeid = exchangeid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column cc_exchange.slug
     *
     * @return the value of cc_exchange.slug
     *
     * @mbggenerated
     */
    public String getSlug() {
        return slug;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column cc_exchange.slug
     *
     * @param slug the value for cc_exchange.slug
     *
     * @mbggenerated
     */
    public void setSlug(String slug) {
        this.slug = slug == null ? null : slug.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column cc_exchange.website
     *
     * @return the value of cc_exchange.website
     *
     * @mbggenerated
     */
    public String getWebsite() {
        return website;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column cc_exchange.website
     *
     * @param website the value for cc_exchange.website
     *
     * @mbggenerated
     */
    public void setWebsite(String website) {
        this.website = website == null ? null : website.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column cc_exchange.name
     *
     * @return the value of cc_exchange.name
     *
     * @mbggenerated
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column cc_exchange.name
     *
     * @param name the value for cc_exchange.name
     *
     * @mbggenerated
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column cc_exchange.is_active
     *
     * @return the value of cc_exchange.is_active
     *
     * @mbggenerated
     */
    public String getIsActive() {
        return isActive;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column cc_exchange.is_active
     *
     * @param isActive the value for cc_exchange.is_active
     *
     * @mbggenerated
     */
    public void setIsActive(String isActive) {
        this.isActive = isActive == null ? null : isActive.trim();
    }
}
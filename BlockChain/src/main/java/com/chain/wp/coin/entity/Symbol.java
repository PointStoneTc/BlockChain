package com.chain.wp.coin.entity;

import java.io.Serializable;

public class Symbol implements Serializable {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column cc_symbol.id
     *
     * @mbggenerated
     */
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column cc_symbol.symbol_id
     *
     * @mbggenerated
     */
    private String symbolId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column cc_symbol.exchange_id
     *
     * @mbggenerated
     */
    private String exchangeId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column cc_symbol.symbol_type
     *
     * @mbggenerated
     */
    private String symbolType;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column cc_symbol.asset_id_base
     *
     * @mbggenerated
     */
    private String assetIdBase;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column cc_symbol.asset_id_quote
     *
     * @mbggenerated
     */
    private String assetIdQuote;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table cc_symbol
     *
     * @mbggenerated
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column cc_symbol.id
     *
     * @return the value of cc_symbol.id
     *
     * @mbggenerated
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column cc_symbol.id
     *
     * @param id the value for cc_symbol.id
     *
     * @mbggenerated
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column cc_symbol.symbol_id
     *
     * @return the value of cc_symbol.symbol_id
     *
     * @mbggenerated
     */
    public String getSymbolId() {
        return symbolId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column cc_symbol.symbol_id
     *
     * @param symbolId the value for cc_symbol.symbol_id
     *
     * @mbggenerated
     */
    public void setSymbolId(String symbolId) {
        this.symbolId = symbolId == null ? null : symbolId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column cc_symbol.exchange_id
     *
     * @return the value of cc_symbol.exchange_id
     *
     * @mbggenerated
     */
    public String getExchangeId() {
        return exchangeId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column cc_symbol.exchange_id
     *
     * @param exchangeId the value for cc_symbol.exchange_id
     *
     * @mbggenerated
     */
    public void setExchangeId(String exchangeId) {
        this.exchangeId = exchangeId == null ? null : exchangeId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column cc_symbol.symbol_type
     *
     * @return the value of cc_symbol.symbol_type
     *
     * @mbggenerated
     */
    public String getSymbolType() {
        return symbolType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column cc_symbol.symbol_type
     *
     * @param symbolType the value for cc_symbol.symbol_type
     *
     * @mbggenerated
     */
    public void setSymbolType(String symbolType) {
        this.symbolType = symbolType == null ? null : symbolType.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column cc_symbol.asset_id_base
     *
     * @return the value of cc_symbol.asset_id_base
     *
     * @mbggenerated
     */
    public String getAssetIdBase() {
        return assetIdBase;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column cc_symbol.asset_id_base
     *
     * @param assetIdBase the value for cc_symbol.asset_id_base
     *
     * @mbggenerated
     */
    public void setAssetIdBase(String assetIdBase) {
        this.assetIdBase = assetIdBase == null ? null : assetIdBase.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column cc_symbol.asset_id_quote
     *
     * @return the value of cc_symbol.asset_id_quote
     *
     * @mbggenerated
     */
    public String getAssetIdQuote() {
        return assetIdQuote;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column cc_symbol.asset_id_quote
     *
     * @param assetIdQuote the value for cc_symbol.asset_id_quote
     *
     * @mbggenerated
     */
    public void setAssetIdQuote(String assetIdQuote) {
        this.assetIdQuote = assetIdQuote == null ? null : assetIdQuote.trim();
    }
}
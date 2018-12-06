package com.chain.wp.restapi.service;

public interface HomeServiceI {
    /**
     * 首页内容封装
     * 
     * @return
     * @throws Exception
     */
    public boolean home() throws Exception;

    /**
     * 金融通文章内容封装
     * 
     * @return
     * @throws Exception
     */
    public boolean financeDepart() throws Exception;

    /**
     * 文章右侧热门文章封装
     * 
     * @return
     * @throws Exception
     */
    public boolean rightPopular() throws Exception;
}

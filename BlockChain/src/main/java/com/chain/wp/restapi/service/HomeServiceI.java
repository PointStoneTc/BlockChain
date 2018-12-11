package com.chain.wp.restapi.service;

public interface HomeServiceI {
    /**
     * @Title 首页内容封装
     * 
     * @return
     * @throws Exception
     */
    public boolean home() throws Exception;

    /**
     * @Title 金融通文章内容封装
     * @Descript 解析格式为 date/-/title/-/href... 分隔符为'/-/'
     * 
     * @return
     * @throws Exception
     */
    public boolean financeDepart() throws Exception;

    /**
     * @Title 文章右侧热门文章封装
     * @Descript 解析格式为 id=title\n... 分隔符为'等号'
     * 
     * @return
     * @throws Exception
     */
    public boolean rightPopular() throws Exception;
}

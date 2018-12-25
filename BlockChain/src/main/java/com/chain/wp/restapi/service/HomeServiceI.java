package com.chain.wp.restapi.service;

import java.util.List;

import com.chain.wp.restapi.entity.Post;

public interface HomeServiceI {
    /**
     * @Title 首页内容封装
     * 
     * @return
     * @throws Exception
     */
    boolean home() throws Exception;

    /**
     * @Title 金融通文章内容封装
     * @Descript 解析格式为 date/-/title/-/href... 分隔符为'/-/'
     * 
     * @return
     * @throws Exception
     */
    boolean financeDepart() throws Exception;

    /**
     * @Title 文章右侧热门文章封装
     * @Descript 解析格式为 id=title\n... 分隔符为'等号'
     * 
     * @return
     * @throws Exception
     */
    boolean rightPopular() throws Exception;

    /**
     * @Title 文章下方推荐文章封装
     * 
     * @param cats
     * @parem postId
     * @return
     * @throws Exception
     */
    List<Post> ctRecommend(String cats, String postId) throws Exception;

    /**
     * @Title 查询文章关键字
     * 
     * @param key
     * @param page
     * @param num
     * @return
     * @throws Exception
     */
    String postQueryByKey(String key, int page, int num) throws Exception;
}

package com.chain.wp.restapi.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chain.redis.service.IRedisService;
import com.chain.util.HttpUtil;
import com.chain.wp.restapi.config.FinanceDepartViewConfigProperties;
import com.chain.wp.restapi.config.HomeViewConfigProperties;
import com.chain.wp.restapi.config.RightPopularViewConfigProperties;
import com.chain.wp.restapi.entity.Category;
import com.chain.wp.restapi.entity.Media;
import com.chain.wp.restapi.entity.MediaDetail;
import com.chain.wp.restapi.entity.Post;
import com.chain.wp.restapi.entity.Tag;
import com.chain.wp.restapi.entity.User;
import com.chain.wp.restapi.service.HomeServiceI;
import com.chain.wp.restapi.view.FinanceDepartView;
import com.chain.wp.restapi.view.HomeView;
import com.chain.wp.restapi.view.RightPopularView;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Service("homeService")
public class HomeServiceImpl implements HomeServiceI {
    private final static Logger logger = LoggerFactory.getLogger(HomeServiceImpl.class);
    final static String COMMA = ",";
    final static String Question = "?";
    final static String AND = "&";
    final static String EQUAL = "=";
    final static String DIAGONAL = "/";

    @Autowired
    private HomeViewConfigProperties homeViewConfigProperties;

    @Autowired
    private FinanceDepartViewConfigProperties financeDepartViewConfigProperties;

    @Autowired
    private RightPopularViewConfigProperties rightPopularViewConfigProperties;

    @Autowired
    private IRedisService redisService;

    @Autowired
    private HttpUtil httpUtil;

    @Override
    public boolean home() throws Exception {
        HomeView homeView = new HomeView();

        // 1.获取所有文章
        Map<Integer, Integer> categoryIdsMap_temp = new HashMap<Integer, Integer>();
        Map<Integer, Integer> tagIdsMap_temp = new HashMap<Integer, Integer>();
        Map<Integer, Integer> userIdsMap_temp = new HashMap<Integer, Integer>();
        List<Post> postList = new ArrayList<Post>();

        try {
            for (Map<String, String> map : homeViewConfigProperties.getListmap()) {
                if ("false".equals(map.get("active"))) // 只有活动的模块才能加载
                    continue;
                StringBuffer url = new StringBuffer(homeViewConfigProperties.getPostUrl()).append(Question);
                Iterator<Map.Entry<String, String>> entries = map.entrySet().iterator();
                while (entries.hasNext()) {
                    Map.Entry<String, String> entry = entries.next();
                    if ("active".equals(entry.getKey()) || "thumbnail".equals(entry.getKey()) || "desc".equals(entry.getKey()))
                        continue;
                    url.append(entry.getKey()).append(EQUAL).append(entry.getValue()).append(AND);
                }

                cutStringBuffer(url, AND); // 去掉最后一个'&'符号
                JSONArray arrayJson = JSONArray.fromObject(httpUtil.sendGet(url.toString()));
                System.out.println(url.toString());
                for (int i = 0; i < arrayJson.size(); i++) {
                    JSONObject obj = arrayJson.getJSONObject(i);
                    Post post = new Post();
                    post.setId(obj.getInt("id"));
                    post.setDate(obj.getString("date"));
                    post.setStatus(obj.getString("status"));
                    post.setTitle(obj.getJSONObject("title").getString("rendered"));
                    post.setContent(obj.getJSONObject("content").getString("rendered"));
                    post.setExcerpt(obj.getJSONObject("excerpt").getString("rendered"));
                    post.setComment_status(obj.getString("comment_status"));
                    post.setPing_status(obj.getString("status"));
                    post.setSticky(obj.getBoolean("sticky"));
                    post.setJetpack_featured_media_url("");

                    JSONArray metaArray = obj.getJSONObject("metadata").getJSONArray("_vc_post_settings");
                    if (metaArray != null && metaArray.size() > 0)
                        post.setIndex(metaArray.getString(0));

                    post.setAuthorId(Integer.valueOf(obj.getInt("author")));
                    userIdsMap_temp.put(post.getAuthorId(), post.getAuthorId()); // 存储所有的userId

                    JSONArray categoryArray = obj.getJSONArray("categories");
                    post.setCategoryIds(new Integer[categoryArray.size()]);
                    for (int x = 0; x < categoryArray.size(); x++) { // 存储所有的categoryId
                        Integer categoriesId = Integer.valueOf(categoryArray.getInt(x));
                        categoryIdsMap_temp.put(categoriesId, categoriesId);
                        post.getCategoryIds()[x] = categoriesId;
                    }
                    post.setLoaction(Integer.parseInt(map.get("categories")));

                    JSONArray tagsArray = obj.getJSONArray("tags");
                    post.setTagIds(new Integer[tagsArray.size()]);
                    for (int x = 0; x < tagsArray.size(); x++) { // 存储所有的tagId
                        Integer tagId = Integer.valueOf(tagsArray.getInt(x));
                        tagIdsMap_temp.put(tagId, tagId);
                        post.getTagIds()[x] = tagId;
                    }

                    postList.add(post);
                }
            }
        } catch (Exception e) {
            logger.error("请求wordpress-post服务错误:" + homeViewConfigProperties.getListmap(), e);
            throw new Exception(e);
        }

        // 2.获取所有Category信息
        Map<Integer, Category> categoryMap = new HashMap<Integer, Category>();
        try {
            categoryMap = transCategory(categoryIdsMap_temp);
            for (Integer key : categoryMap.keySet())
                homeView.getCaterories().add(categoryMap.get(key));
        } catch (Exception e) {
            logger.error("请求wordpress-category服务错误:" + categoryIdsMap_temp, e);
            throw new Exception(e);
        }

        // 3.获取所有tag信息
        Map<Integer, Tag> tagMap = new HashMap<Integer, Tag>();
        try {
            tagMap = transTag(tagIdsMap_temp);
            for (Integer key : tagMap.keySet())
                homeView.getTags().add(tagMap.get(key));
        } catch (Exception e) {
            logger.error("请求wordpress-tag服务错误:" + tagIdsMap_temp, e);
            throw new Exception(e);
        }

        // 4.获取所有user信息
        Map<Integer, User> userMap = new HashMap<Integer, User>();
        try {
            userMap = transUser(userIdsMap_temp);
            for (Integer key : userMap.keySet())
                homeView.getUsers().add(userMap.get(key));
        } catch (Exception e) {
            logger.error("请求wordpress-user服务错误:" + userIdsMap_temp, e);
            throw new Exception(e);
        }

        // 5.获取所有的media信息
        Map<Integer, Media> mediaMap = transMedia(postList);

        // 重新组装所有的post
        for (Post post : postList) {
            post.setAuthor(userMap.get(post.getAuthorId()));
            for (Integer key : post.getCategoryIds())
                post.getCategories().add(categoryMap.get(key));

            for (Integer key : post.getTagIds())
                post.getTags().add(tagMap.get(key));

            post.setFeaturedMedia(mediaMap.get(post.getId()));

            Integer location = post.getLoaction();
            if (!homeView.getPostMap().containsKey(location))
                homeView.getPostMap().put(location, new ArrayList<Post>());
            homeView.getPostMap().get(location).add(post);
            post.setThumbnailMediaDetail(getPostFeaturedmedia(homeViewConfigProperties.getSelfThumbnail(location), post.getFeaturedMedia()));
        }

        redisService.set("chain_homeView", homeView);
        return true;
    }


    /**
     * 组装分类信息
     * 
     * @param categoryIdsMap
     * @return
     * @throws Exception
     */
    private Map<Integer, Category> transCategory(Map<Integer, Integer> categoryIdsMap) throws Exception {
        StringBuffer categoryUrl = new StringBuffer(homeViewConfigProperties.getCategoryUrl()).append(Question).append("per_page").append(EQUAL).append(categoryIdsMap.size())
                .append(AND).append("include").append(EQUAL);
        Map<Integer, Category> map = new HashMap<Integer, Category>();
        for (Integer key : categoryIdsMap.keySet()) {
            categoryUrl.append(key).append(COMMA);
        }
        cutStringBuffer(categoryUrl, COMMA); // 去掉最后一个'&'符号

        JSONArray arrayJson = JSONArray.fromObject(httpUtil.sendGet(categoryUrl.toString()));

        for (int i = 0; i < arrayJson.size(); i++) {
            Category category = new Category();
            JSONObject obj = arrayJson.getJSONObject(i);
            int id = obj.getInt("id");
            category.setId(id);
            category.setCount(obj.getInt("count"));
            category.setDescription(obj.getString("description"));
            category.setName(obj.getString("name"));
            category.setSlug(obj.getString("slug"));
            category.setParent(obj.getInt("parent"));
            map.put(Integer.valueOf(id), category);
        }
        return map;
    }

    /**
     * 组装用户信息
     * 
     * @param userIdsMap
     * @return
     * @throws Exception
     */
    private Map<Integer, User> transUser(Map<Integer, Integer> userIdsMap) throws Exception {
        String[] avatar_urls = new String[] {"24", "48", "96"};
        StringBuffer userUrl = new StringBuffer(homeViewConfigProperties.getUserUrl()).append(Question).append("per_page").append(EQUAL).append(userIdsMap.size()).append(AND)
                .append("include").append(EQUAL);
        Map<Integer, User> map = new HashMap<Integer, User>();
        for (Integer key : userIdsMap.keySet()) {
            userUrl.append(key).append(COMMA);
        }
        cutStringBuffer(userUrl, COMMA); // 去掉最后一个'&'符号

        JSONArray arrayJson = JSONArray.fromObject(httpUtil.sendGet(userUrl.toString()));

        for (int i = 0; i < arrayJson.size(); i++) {
            User user = new User();
            JSONObject obj = arrayJson.getJSONObject(i);
            int id = obj.getInt("id");
            user.setId(id);
            user.setName(obj.getString("name"));
            user.setDescription(obj.getString("description"));
            for (String item : avatar_urls)
                user.getAvatar_urls().put(new Integer(item), obj.getJSONObject("avatar_urls").getString(item));
            map.put(Integer.valueOf(id), user);
        }
        return map;
    }

    /**
     * 组装标签信息
     * 
     * @param tagIdsMap
     * @return
     * @throws Exception
     */
    private Map<Integer, Tag> transTag(Map<Integer, Integer> tagIdsMap) throws Exception {
        StringBuffer tagUrl = new StringBuffer(homeViewConfigProperties.getTagUrl()).append(Question).append("per_page").append(EQUAL).append(tagIdsMap.size()).append(AND)
                .append("include").append(EQUAL);
        Map<Integer, Tag> map = new HashMap<Integer, Tag>();
        for (Integer key : tagIdsMap.keySet()) {
            tagUrl.append(key).append(COMMA);
        }
        cutStringBuffer(tagUrl, COMMA); // 去掉最后一个'&'符号

        JSONArray arrayJson = JSONArray.fromObject(httpUtil.sendGet(tagUrl.toString()));

        for (int i = 0; i < arrayJson.size(); i++) {
            Tag tag = new Tag();
            JSONObject obj = arrayJson.getJSONObject(i);
            int id = obj.getInt("id");
            tag.setId(id);
            tag.setCount(obj.getInt("count"));
            tag.setName(obj.getString("name"));
            tag.setDescription(obj.getString("description"));
            tag.setSlug(obj.getString("slug"));
            map.put(Integer.valueOf(id), tag);
        }
        return map;
    }

    private MediaDetail getPostFeaturedmedia(String thumbnail, Media media) {
        if (thumbnail == null || "".equals(thumbnail) || media == null)
            return null;

        double w = Double.parseDouble(thumbnail.split(COMMA)[0].trim());
        double h = Double.parseDouble(thumbnail.split(COMMA)[1].trim());
        double scale = new BigDecimal(w / h).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
        media.getMedia_details().sort((MediaDetail d1, MediaDetail d2) -> d1.getCompareValue().compareTo(d2.getCompareValue()));

        MediaDetail minDetail = media.getMedia_details().get(0);
        if (scale <= new BigDecimal(minDetail.getCompareValue().doubleValue()).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue())
            return minDetail;

        MediaDetail maxDetail = media.getMedia_details().get(media.getMedia_details().size() - 1);
        if (scale >= new BigDecimal(maxDetail.getCompareValue().doubleValue()).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue())
            return maxDetail;

        int middleIndex = (media.getMedia_details().size() - 1) / 2;
        MediaDetail middleDetail = media.getMedia_details().get(middleIndex);
        double middle_scale = new BigDecimal(middleDetail.getCompareValue().doubleValue()).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();

        // System.out.println(thumbnail);
        // for (MediaDetail d : media.getMedia_details())
        // System.out.println(d.getName() + ":" + d.getWidth() + "*" + d.getHeight() + " | " +
        // Double.valueOf(d.getWidth()) / Double.valueOf(d.getHeight()) + " | "
        // + (d.getCompareValue() - scale));

        for (int i = middle_scale - scale >= 0 ? 0 : middleIndex; i < media.getMedia_details().size() - middleIndex; i++) {
            MediaDetail currentMedia = media.getMedia_details().get(i);
            double curr = new BigDecimal(currentMedia.getCompareValue().doubleValue()).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
            if (curr - scale >= 0) {
                if (i == media.getMedia_details().size() - 1) {// 遍历到最后一个
                    return new MediaDetail();
                }

                MediaDetail nextMedia = media.getMedia_details().get(i + 1);
                double next = new BigDecimal(nextMedia.getCompareValue().doubleValue()).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();

                if (Math.abs(next - scale) > Math.abs(curr - scale))
                    new MediaDetail(currentMedia.getName(), currentMedia.getFile(), currentMedia.getWidth(), currentMedia.getHeight(), currentMedia.getMime_type(),
                            currentMedia.getSource_url());
                else
                    new MediaDetail(nextMedia.getName(), nextMedia.getFile(), nextMedia.getWidth(), nextMedia.getHeight(), nextMedia.getMime_type(), nextMedia.getSource_url());
            }

        }
        return null;
    }

    /**
     * 组装媒体信息
     * 
     * @param postList
     * @return
     * @throws Exception
     */
    private Map<Integer, Media> transMedia(List<Post> postList) throws Exception {
        String[] sizeNames = new String[] {"thumbnail", "medium", "medium_large", "large", "post-thumbnail", "hoverex-thumb-huge", "hoverex-thumb-big", "hoverex-thumb-med",
                "hoverex-thumb-med-small", "hoverex-thumb-med-avatar", "hoverex-thumb-tiny", "hoverex-thumb-masonry-big", "hoverex-thumb-masonry", "hoverex-thumb-magazine-extra",
                "hoverex-thumb-magazine-modern-big", "hoverex-thumb-magazine-modern-small", "hoverex-thumb-extra", "hoverex-thumb-extra-big", "trx_addons-thumb-small",
                "trx_addons-thumb-portrait", "trx_addons-thumb-avatar", "full"};
        StringBuffer tagUrl = new StringBuffer(homeViewConfigProperties.getMediaUrl()).append(Question).append("per_page").append(EQUAL).append(postList.size()).append(AND)
                .append("parent").append(EQUAL);
        Map<Integer, Media> map = new HashMap<Integer, Media>();
        for (Post item : postList) {
            tagUrl.append(item.getId()).append(COMMA);
        }
        cutStringBuffer(tagUrl, COMMA); // 去掉最后一个'&'符号

        JSONArray arrayJson = JSONArray.fromObject(httpUtil.sendGet(tagUrl.toString()));

        for (int i = 0; i < arrayJson.size(); i++) {
            Media media = new Media();
            JSONObject obj = arrayJson.getJSONObject(i);
            int id = obj.getInt("id");
            media.setId(id);
            media.setMedia_type(obj.getString("media_type"));
            media.setMime_type(obj.getString("mime_type"));

            JSONObject sizes = obj.getJSONObject("media_details").getJSONObject("sizes");
            List<MediaDetail> mediaDetailList = new ArrayList<MediaDetail>();
            for (String key : sizeNames) {
                MediaDetail detail = new MediaDetail();
                if (!sizes.containsKey(key))
                    continue;
                JSONObject img = sizes.getJSONObject(key);
                detail.setName(key);
                detail.setFile(img.getString("file"));
                detail.setWidth(img.getInt("width"));
                detail.setHeight(img.getInt("height"));
                detail.setMime_type(img.getString("mime_type"));
                detail.setSource_url(img.getString("source_url"));
                mediaDetailList.add(detail);
            }
            media.setMedia_details(mediaDetailList);
            map.put(Integer.valueOf(obj.getInt("post")), media);
        }
        return map;
    }

    @Override
    public boolean financeDepart() throws Exception {
        int postId = financeDepartViewConfigProperties.getId();
        FinanceDepartView financeDepartView = new FinanceDepartView();
        financeDepartView.setId(postId);
        try {
            StringBuffer url = new StringBuffer(homeViewConfigProperties.getPostUrl()).append(DIAGONAL).append(postId);
            JSONObject obj = JSONObject.fromObject(httpUtil.sendGet(url.toString()));
            String content = obj.getJSONObject("content").getString("rendered").replaceAll("<p>", "").replaceAll("</p>", "");

            for (String item : content.split("\n")) {
                String[] cx = item.split("/-/");
                financeDepartView.add(cx[0], cx[1], cx[2]);
            }

        } catch (

        Exception e) {
            logger.error("请求wordpress-post服务错误:" + homeViewConfigProperties.getListmap(), e);
            throw new Exception(e);
        }

        redisService.set("chain_financeDepartView", financeDepartView);
        return true;
    }

    @Override
    public boolean rightPopular() throws Exception {
        int postId = rightPopularViewConfigProperties.getId();
        RightPopularView rightPopularView = new RightPopularView();
        rightPopularView.setId(postId);
        try {
            StringBuffer url = new StringBuffer(homeViewConfigProperties.getPostUrl()).append(DIAGONAL).append(postId);
            JSONObject popularPost = JSONObject.fromObject(httpUtil.sendGet(url.toString()));
            String content = popularPost.getJSONObject("content").getString("rendered").replaceAll("<p>", "").replaceAll("</p>", "");

            url.setLength(0);
            url.append(homeViewConfigProperties.getPostUrl()).append(Question).append("include").append(EQUAL);
            for (String item : content.split("\n"))
                url.append(item.split(EQUAL)[0]).append(COMMA);
            cutStringBuffer(url, COMMA); // 去掉最后一个','符号

            JSONArray arrayJson = JSONArray.fromObject(httpUtil.sendGet(url.toString()));
            for (int i = 0; i < arrayJson.size(); i++) {
                JSONObject obj = arrayJson.getJSONObject(i);
                Post post = new Post();
                post.setId(obj.getInt("id"));
                post.setDate(obj.getString("date"));
                post.setStatus(obj.getString("status"));
                post.setTitle(obj.getJSONObject("title").getString("rendered"));
                post.setContent(obj.getJSONObject("content").getString("rendered"));
                post.setExcerpt(obj.getJSONObject("excerpt").getString("rendered"));
                post.setComment_status(obj.getString("comment_status"));
                post.setPing_status(obj.getString("status"));
                post.setSticky(obj.getBoolean("sticky"));
                post.setJetpack_featured_media_url("");

                JSONArray metaArray = obj.getJSONObject("metadata").getJSONArray("_vc_post_settings");
                if (metaArray != null && metaArray.size() > 0)
                    post.setIndex(metaArray.getString(0));

                rightPopularView.getList().add(post);
            }

            Map<Integer, Media> mediaMap = transMedia(rightPopularView.getList());

            for (Post post : rightPopularView.getList()) {
                post.setFeaturedMedia(mediaMap.get(post.getId()));
            }
        } catch (Exception e) {
            logger.error("请求wordpress-post服务错误:" + homeViewConfigProperties.getListmap(), e);
            throw new Exception(e);
        }

        redisService.set("chain_rightPopularView", rightPopularView);
        return true;

    }

    /**
     * 
     * @param sb
     * @param c
     */
    private void cutStringBuffer(StringBuffer sb, String c) {
        int index = sb.lastIndexOf(c);
        if (index == sb.length() - 1)
            sb.deleteCharAt(index);
    }
}

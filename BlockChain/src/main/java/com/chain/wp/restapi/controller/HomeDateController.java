package com.chain.wp.restapi.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.chain.redis.service.RedisServiceI;
import com.chain.wp.restapi.entity.CommentUserInfo;
import com.chain.wp.restapi.entity.Post;
import com.chain.wp.restapi.service.CommentUserInfoServiceI;
import com.chain.wp.restapi.service.HomeServiceI;
import com.chain.wp.restapi.view.FinanceDepartView;
import com.chain.wp.restapi.view.HomeView;
import com.chain.wp.restapi.view.RightPopularView;

@CrossOrigin(origins = "*", maxAge = 3600, methods = {RequestMethod.GET, RequestMethod.POST})
@RestController
@RequestMapping("/data")
public class HomeDateController {
    private final static Logger logger = LoggerFactory.getLogger(HomeDateController.class);
    @Autowired
    private RedisServiceI redisService;

    @Autowired
    private HomeServiceI homeService;

    @Autowired
    private CommentUserInfoServiceI commentUserInfoService;

    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public HomeView home(HttpServletResponse res) {
        HomeView homeView = (HomeView) redisService.get("chain_homeView");
        return homeView;
    }

    @RequestMapping(value = "/financeDepart", method = RequestMethod.GET)
    public FinanceDepartView financeDepart(HttpServletResponse res) {
        FinanceDepartView financeDepartView = (FinanceDepartView) redisService.get("chain_financeDepartView");
        return financeDepartView;
    }

    @RequestMapping(value = "/rightPopular", method = RequestMethod.GET)
    public RightPopularView RightPopular(HttpServletResponse res) {
        RightPopularView rightPopularView = (RightPopularView) redisService.get("chain_rightPopularView");
        return rightPopularView;
    }

    @RequestMapping(value = "/ctRecommend", method = RequestMethod.GET)
    public List<Post> ctRecommend(@RequestParam(value = "cats", required = true) String cats, @RequestParam(value = "postId", required = true) String postId) {
        List<Post> list;
        try {
            list = homeService.ctRecommend(cats, postId);
        } catch (Exception e) {
            return null;
        }
        return list;
    }

    @RequestMapping(value = "/addWpCommentUser", method = RequestMethod.POST)
    public boolean ctRecommend(CommentUserInfo user) {
        if (commentUserInfoService.selectOne(user).intValue() > 0)
            return false;

        commentUserInfoService.insert(user);
        return true;
    }
}

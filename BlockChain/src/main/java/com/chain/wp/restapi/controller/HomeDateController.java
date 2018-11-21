package com.chain.wp.restapi.controller;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.chain.redis.service.IRedisService;
import com.chain.wp.restapi.view.HomeView;

@CrossOrigin(origins = "*", maxAge = 3600, methods = {RequestMethod.GET, RequestMethod.POST})
@RestController
@RequestMapping("/data")
public class HomeDateController {
    private final static Logger logger = LoggerFactory.getLogger(HomeDateController.class);
    @Autowired
    private IRedisService redisService;

    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public HomeView home(HttpServletResponse res) {
        HomeView homeView = (HomeView) redisService.get("chain_homeView");
        return homeView;
    }
}

//package com.liuyang19900520.robotlife.web.controller;
//
//import com.liuyang19900520.robotlife.web.feign.BlogFeignClient2;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.client.RestTemplate;
//
///**
// * @program: robotlife-user-java
// * @description:
// * @author: LiuYang
// * @create: 2018-08-10 19:05
// **/
//@RestController
//public class TestController2 {
//
//    @Autowired
//    RestTemplate restTemplate;
//
//    @Autowired
//    private LoadBalancerClient loadBalancerClient;
//
//    @Autowired
//    BlogFeignClient2 blogFeignClient;
//
//
//
//    @GetMapping("/user/test2")
//    public Object delCookie() {
//        return blogFeignClient.test2();
//    }
//
//
//}

package com.liuyang19900520.robotlife.web.controller;

import com.liuyang19900520.robotlife.web.feign.UserFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @program: robotlife-user-java
 * @description:
 * @author: LiuYang
 * @create: 2018-08-10 19:05
 **/
@RestController
public class TestController {

    @Autowired
    RestTemplate restTemplate;

//    @Autowired
//    private LoadBalancerClient loadBalancerClient;

    @Autowired
    UserFeignClient blogFeignClient;

    @PostMapping("/user/test")
    public Object test(Integer pageNo, Integer rows) {

//        // Create the request body as a MultiValueMap
//        MultiValueMap<String, Integer> body = new LinkedMultiValueMap<String, Integer>();
//
//        body.add("pageNo", pageNo);
//        body.add("rows", rows);
//
//// Note the body object as first parameter!
//        HttpEntity<?> httpEntity = new HttpEntity<Object>(body);
//
////        return "asdf";
//        ServiceInstance serviceInstance = this.loadBalancerClient.choose("robotlife-blog");
//        System.out.println("===" + ":" + serviceInstance.getServiceId() + ":" + serviceInstance.getHost() + ":" + serviceInstance.getPort());

        return blogFeignClient.test(pageNo, rows);

    }


}

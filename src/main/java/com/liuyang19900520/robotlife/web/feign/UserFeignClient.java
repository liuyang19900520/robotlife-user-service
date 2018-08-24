package com.liuyang19900520.robotlife.web.feign;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "robotlife-blog", /*fallback = HystrixClientFallback.class, */fallbackFactory = HystrixClientFactory.class)
public interface UserFeignClient {
    @RequestMapping(value = "/blogs/page", method = RequestMethod.POST)
    Object test(@RequestParam("pageNo") Integer pageNo, @RequestParam("rows") Integer rows);
}

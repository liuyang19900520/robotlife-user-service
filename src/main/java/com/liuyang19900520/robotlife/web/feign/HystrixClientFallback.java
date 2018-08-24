package com.liuyang19900520.robotlife.web.feign;

import org.springframework.stereotype.Component;

@Component
public class HystrixClientFallback implements UserFeignClient {

  @Override
  public Object test(Integer pageNo, Integer rows) {
    return "abcd";
  }
}
//package com.liuyang19900520.robotlife.feign;
//
//
//import com.liuyang19900520.robotlife.config.FeignConfig1;
//import com.liuyang19900520.robotlife.config.FeignConfig2;
//import org.springframework.cloud.openfeign.FeignClient;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RequestParam;
//
//@FeignClient(name = "robotlife-blog", configuration = FeignConfig2.class)
//public interface BlogFeignClient2 {
////  @RequestMapping(value = "/simple/{id}", method = RequestMethod.GET)
////  public User findById(@PathVariable("id") Long id); // 两个坑：1. @GetMapping不支持   2. @PathVariable得设置value
////
////  @RequestMapping(value = "/user", method = RequestMethod.POST)
////  public User postUser(@RequestBody User user);
////
////  // 该请求不会成功，只要参数是复杂对象，即使指定了是GET方法，feign依然会以POST方法进行发送请求。可能是我没找到相应的注解或使用方法错误。
////  // 如勘误，请@lilizhou2008  eacdy0000@126.com
////  @RequestMapping(value = "/get-user", method = RequestMethod.GET)
////  public User getUser(User user);
//
//
//    @RequestMapping(value = "/topics", method = RequestMethod.GET)
//    Object test2();
//}
//

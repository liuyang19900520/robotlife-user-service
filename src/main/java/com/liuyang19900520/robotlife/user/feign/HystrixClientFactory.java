package com.liuyang19900520.robotlife.user.feign;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;


import feign.hystrix.FallbackFactory;

@Component
public class HystrixClientFactory implements FallbackFactory<UserFeignClient> {

    private static final Logger LOGGER = LoggerFactory.getLogger(HystrixClientFactory.class);

    @Override
    public UserFeignClient create(Throwable cause) {
        HystrixClientFactory.LOGGER.info("fallback; reason was: {}", cause.getMessage());
        return new UserFeignClientWithFactory() {

            @Override
            public Object test(Integer pageNo, Integer rows) {
                return "12345";
            }
        };
    }
}

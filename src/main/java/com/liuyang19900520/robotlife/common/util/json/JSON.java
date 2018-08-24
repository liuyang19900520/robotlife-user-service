package com.liuyang19900520.robotlife.common.util.json;


import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Repeatable(JSONS.class)
public @interface JSON {
    Class<?> type();
    String include() default "";
    String filter() default "";
}


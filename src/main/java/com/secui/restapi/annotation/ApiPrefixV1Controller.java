package com.secui.restapi.annotation;

import org.springframework.core.annotation.AliasFor;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Component
@RequestMapping("/api/v1/")
public @interface ApiPrefixV1Controller {
    @AliasFor(annotation = Component.class)
    String value() default "";
}

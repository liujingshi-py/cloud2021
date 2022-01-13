package com.atguigu.springcloud.service;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author liujs
 * @version 1.0
 * @date 2021/12/27 0027 21:58
 */

@Component
public class PaymentFallbackService implements PaymentHystrixService{

    @GetMapping("/payment/hystrix/ok/{id}")
    public String PaymentInfo_OK(@PathVariable("id") Integer id){
        return "----PaymentFallbackService---PaymentInfo_OK";
    };

    @GetMapping("/payment/hystrix/timeout/{id}")
    public String PaymentInfo_TimeOut(@PathVariable("id") Integer id){
        return "-----PaymentFallbackService-------PaymentInfo_TimeOut";
    };
}

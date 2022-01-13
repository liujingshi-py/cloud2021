package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.service.PaymentHystrixService;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author liujs
 * @version 1.0
 * @date 2021/12/24 0024 21:00
 */

@RestController
@Slf4j
@DefaultProperties(defaultFallback = "payment_Global_FallbackMethod")
public class OrderHystrixController {

    @Resource
    private PaymentHystrixService paymentHystrixService;

    @GetMapping("/consumer/payment/hystrix/ok/{id}")
    public String PaymentInfo_OK(@PathVariable("id") Integer id) {

        String result = paymentHystrixService.PaymentInfo_OK(id);
        return result;
    }


    @GetMapping("/consumer/payment/hystrix/timeout/{id}")
    @HystrixCommand
    /*@HystrixCommand(fallbackMethod = "PaymentInfo_TimeOutHandler", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "1000")
    })*/
    public String PaymentInfo_TimeOut(@PathVariable("id") Integer id) {

        int age = 10 / 0;
        String result = paymentHystrixService.PaymentInfo_TimeOut(id);
        return result;
    }

    public String PaymentInfo_TimeOutHandler(Integer id) {

        return "线程池： " + Thread.currentThread().getName() + " 80系统繁忙或系统出错，请稍后再试。 id=" + id + " o(╥﹏╥)o";
    }

    //全局fallback
    public String payment_Global_FallbackMethod(){
        return "Global 异常处理，请稍后再试";
    }
}

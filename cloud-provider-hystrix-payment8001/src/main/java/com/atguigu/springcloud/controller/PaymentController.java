package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author liujs
 * @version 1.0
 * @date 2021/12/23 0023 21:16
 */

@RestController
@Slf4j
public class PaymentController {

    @Resource
    private PaymentService paymentService;

    @Value("${server.port}")
    private String serverPort;

    @GetMapping("/payment/hystrix/ok/{id}")
    public String PaymentInfo_OK(@PathVariable("id") Integer id) {

        String result = paymentService.PaymentInfo_OK(id);
        return result;
    }

    @GetMapping("/payment/hystrix/timeout/{id}")
    public String PaymentInfo_TimeOut(@PathVariable("id") Integer id) {

        String result = paymentService.PaymentInfo_TimeOut(id);
        return result;
    }

    //服务熔断

    @GetMapping("/payment/circuit/{id}")
    public String paymentCircuitBreaker(@PathVariable("id") Integer id) {
        String s = paymentService.paymentCircuitBreaker(id);
        return s;
    }
}

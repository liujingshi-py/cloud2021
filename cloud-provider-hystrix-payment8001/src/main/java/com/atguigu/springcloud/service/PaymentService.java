package com.atguigu.springcloud.service;

import cn.hutool.core.util.IdUtil;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.cloud.commons.util.IdUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.concurrent.TimeUnit;

/**
 * @author liujs
 * @version 1.0
 * @date 2021/12/23 0023 21:09
 */

@Service
public class PaymentService {

    public String PaymentInfo_OK(Integer id) {
        return "线程池： " + Thread.currentThread().getName() + "   PaymentInfo_ok,id=" + id;
    }

    @HystrixCommand(fallbackMethod = "PaymentInfo_TimeOutHandler", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "5000")
    })
    public String PaymentInfo_TimeOut(Integer id) {
        int timeNum = 3;
//        int age = 10/0;
        try {
            TimeUnit.SECONDS.sleep(timeNum);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "线程池： " + Thread.currentThread().getName() + "  id=" + id + "耗时" + timeNum + "秒";
    }

    public String PaymentInfo_TimeOutHandler(Integer id) {

        return "线程池： " + Thread.currentThread().getName() + " 8001系统繁忙或系统出错，请稍后再试。 id=" + id + " o(╥﹏╥)o";
    }


    //服务熔断
    @HystrixCommand(fallbackMethod = "paymentCircuitBreaker_fallback", commandProperties = {
            @HystrixProperty(name = "circuitBreaker.enabled", value = "true"),
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"),//请求次数
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "10000"),//时间范围
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "60")//失败了达到后跳闸
    })
    public String paymentCircuitBreaker(@PathVariable("id") Integer id) {
        if (id < 0) {
            throw new RuntimeException("******id 不能是负数");
        }
        String serialNumber = IdUtil.fastSimpleUUID();
        return Thread.currentThread().getName() + "调用成功，流水号：" + serialNumber;
    }

    public String paymentCircuitBreaker_fallback(@PathVariable("id") Integer id) {
        return "ID不可以是负数```~~~id:" + id;
    }


}

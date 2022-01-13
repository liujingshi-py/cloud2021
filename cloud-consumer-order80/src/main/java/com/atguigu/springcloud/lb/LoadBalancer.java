package com.atguigu.springcloud.lb;

import org.springframework.cloud.client.ServiceInstance;

import java.util.List;

/**
 * @author liujs
 * @version 1.0
 * @date 2021/12/21 0021 22:26
 */
public interface LoadBalancer {
    ServiceInstance instance(List<ServiceInstance> serviceInstances);
}

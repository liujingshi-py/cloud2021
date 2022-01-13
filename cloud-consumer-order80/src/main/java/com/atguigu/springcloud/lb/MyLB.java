package com.atguigu.springcloud.lb;

import org.springframework.cloud.client.ServiceInstance;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author liujs
 * @version 1.0
 * @date 2021/12/21 0021 22:28
 */

@Component
public class MyLB implements LoadBalancer {
    private AtomicInteger atomicInteger = new AtomicInteger(0);

    public final int getAndIncrement() {
        int currunt;
        int next;
        do {
            currunt = this.atomicInteger.get();
            next = currunt >= 2147483647 ? 0 : currunt + 1;
        } while (!this.atomicInteger.compareAndSet(currunt, next));
        System.out.println("***第几次访问。次数next= " + next);
        return next;

    }

    @Override
    public ServiceInstance instance(List<ServiceInstance> serviceInstances) {
        int index = getAndIncrement() % serviceInstances.size();
        return serviceInstances.get(index);
    }
}

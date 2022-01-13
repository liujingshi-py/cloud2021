package com.atguigu.myrule;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.context.annotation.Configuration;


/**
 * @author liujs
 * @version 1.0
 * @date 2021/12/21 0021 21:54
 */

@Configuration
public class MySelfRule {

    public IRule myRule() {
        return new RandomRule();
    }
}

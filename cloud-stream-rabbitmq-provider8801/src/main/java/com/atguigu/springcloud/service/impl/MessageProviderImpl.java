package com.atguigu.springcloud.service.impl;

import com.atguigu.springcloud.service.IMessageProvider;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;

import javax.annotation.Resource;
import java.util.UUID;

/**
 * @author liujs
 * @version 1.0
 * @date 2022/1/9 0009 12:17
 */

@EnableBinding(Source.class)//定义消息推送管道
public class MessageProviderImpl implements IMessageProvider {

    @Resource
    private MessageChannel output; //消息管道

    @Override
    public String send() {

        String serial = UUID.randomUUID().toString();
        output.send(MessageBuilder.withPayload(serial).build());
        System.out.println("*****serial: " + serial);
        return serial;
    }
}

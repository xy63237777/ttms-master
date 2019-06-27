package com.thesevensky.ttms.ttmsprovidermovies19527.service.rabbitmq.impl;

import com.thesevensky.starter.properties.MasterProperties;
import com.thesevensky.ttms.ttmsprovidermovies19527.service.rabbitmq.HttpRabbitMqService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * @Author TheSevenSky
 * @Date: 2019/5/21 13:19
 * @Version 1.0
 */
@Service(value = "httpRabbitMqService")
public class HttpRabbitMqServiceImpl implements HttpRabbitMqService, InitializingBean {
    @Autowired
    private MasterProperties masterProperties = null;

    private String exchange = null;

    private String key = null;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public void afterPropertiesSet() throws Exception {
        exchange = masterProperties.getAmqp().getRabbit().getExchange();
        key = masterProperties.getAmqp().getRabbit().getRoutingKey();
    }

    @Autowired
    private RabbitTemplate rabbitTemplate = null;

    @Async
    @Override
    public void sendMessage(Object object) {
        logger.info("[ Rabbit MQ 发送消息] O::::::]==============> " + object);
        rabbitTemplate.convertAndSend(exchange, key, object);
    }



}

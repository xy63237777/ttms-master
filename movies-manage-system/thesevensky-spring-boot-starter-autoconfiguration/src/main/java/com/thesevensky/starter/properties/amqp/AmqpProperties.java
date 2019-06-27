package com.thesevensky.starter.properties.amqp;

/**
 * @Author TheSevenSky
 * @Date: 2019/5/21 13:21
 * @Version 1.0
 */
public class AmqpProperties {
    private RabbitMQProperties rabbit = new RabbitMQProperties();

    public RabbitMQProperties getRabbit() {
        return rabbit;
    }

    public void setRabbit(RabbitMQProperties rabbit) {
        this.rabbit = rabbit;
    }
}

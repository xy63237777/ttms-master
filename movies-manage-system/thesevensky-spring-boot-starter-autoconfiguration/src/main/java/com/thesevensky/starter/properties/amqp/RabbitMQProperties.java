package com.thesevensky.starter.properties.amqp;

/**
 * @Author TheSevenSky
 * @Date: 2019/5/21 13:21
 * @Version 1.0
 */
public class RabbitMQProperties {

    private String exchange = "ttms.direct";
    private String routingKey = "ttms-key";

    public String getExchange() {
        return exchange;
    }

    public void setExchange(String exchange) {
        this.exchange = exchange;
    }

    public String getRoutingKey() {
        return routingKey;
    }

    public void setRoutingKey(String routingKey) {
        this.routingKey = routingKey;
    }
}

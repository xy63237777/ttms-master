package com.thesevensky.ttms.ttmsserverconfig.master.service;

import org.springframework.amqp.core.Message;

/**
 * @Author TheSevenSky
 * @Date: 2019/5/21 17:33
 * @Version 1.0
 */
public interface HttpRabbitServerService {

    public void monitor(String str);


}

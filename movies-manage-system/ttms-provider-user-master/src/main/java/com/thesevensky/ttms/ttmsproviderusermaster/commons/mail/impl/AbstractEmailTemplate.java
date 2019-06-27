package com.thesevensky.ttms.ttmsproviderusermaster.commons.mail.impl;

import com.thesevensky.starter.properties.MasterProperties;
import com.thesevensky.ttms.ttmsproviderusermaster.commons.Generator;
import com.thesevensky.ttms.ttmsproviderusermaster.commons.mail.EmailTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Author TheSevenSky
 * @Date: 2019/6/8 20:02
 * @Version 1.0
 */
public abstract class AbstractEmailTemplate implements EmailTemplate {

    private Generator generator = null;

    private boolean idLogger = true;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    public AbstractEmailTemplate(){

    }

    public AbstractEmailTemplate(Generator generator) {
        this.generator = generator;
    }

    @Override
    public void send(String sendTo) {
        try {
            Object code = generator.generate();
            save(sendTo,code);
            sendMessage(sendTo,code);
        } catch (Exception e) {
            dontSave(sendTo, e);
        }
        logger.info("向用户" + sendTo + "的邮件发送完毕");
    }

    protected abstract void save(String sendTo, Object code);

    protected abstract void sendMessage(String sendTo, Object code);

    protected abstract void dontSave(String sendTo, Exception e);

    public Generator getGenerator() {
        return generator;
    }

    public void setGenerator(Generator generator) {
        this.generator = generator;
    }
}

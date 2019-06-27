package com.thesevensky.ttms.ttmsproviderusermaster.commons.mail.impl;

import com.thesevensky.starter.properties.MasterProperties;
import com.thesevensky.starter.properties.email.EmailProperties;
import com.thesevensky.ttms.moviesmanageapi.commons.exception.UserException;
import com.thesevensky.ttms.ttmsproviderusermaster.commons.Generator;
import com.thesevensky.ttms.ttmsproviderusermaster.commons.mail.EmailCodeGenerator;
import com.thesevensky.ttms.ttmsproviderusermaster.commons.mail.EmailTemplate;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;

import java.util.concurrent.TimeUnit;

/**
 * @Author TheSevenSky
 * @Date: 2019/6/8 18:56
 * @Version 1.0
 */

public class DefaultEmailTemplate extends AbstractEmailTemplate {

    @Autowired
    private MasterProperties masterProperties = null;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private StringRedisTemplate stringRedisTemplate = null;

    public DefaultEmailTemplate() {
        super();
        super.setGenerator(new EmailCodeGenerator(masterProperties));
    }

    public DefaultEmailTemplate(Generator generator) {
        super(generator);
    }

    @Autowired
    private JavaMailSender mailSender;

    @Override
    @Async
    public void send(String sendTo) {
        super.send(sendTo);
    }

    @Override
    protected void save(String sendTo, Object code) {
        stringRedisTemplate.opsForValue().set(sendTo,(String) code, masterProperties.getEmail().getMailTime(),
                TimeUnit.MILLISECONDS);
    }

    @Override
    protected void sendMessage(String sendTo, Object code) {
        if(!StringUtils.isNotEmpty(sendTo)) {
            logger.info("发送验证码失败 原因: 发送数据为空");
            return;
        }
        EmailProperties email = masterProperties.getEmail();
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(email.getSendFrom());
        message.setTo(sendTo);
        message.setSubject(email.getSubject());
        String format = String.format(email.getContent(), (Object[]) new String[]{sendTo,
                (String)code});
        logger.info("向用户 ---> " + sendTo + " 发送验证码 :  " + (String)code);
        message.setText(format);
        mailSender.send(message);
    }

    @Override
    protected void dontSave(String sendTo, Exception e) {
        logger.info(sendTo +  " 发送邮箱出错 错误是 " + e.getMessage() + "\r\n local --> " + e.getLocalizedMessage() );
    }

    @Override
    public void cmpCode(String sendTo, String code) throws UserException {
        if (!StringUtils.isNotEmpty(sendTo) || !StringUtils.isNotEmpty(code)) throw new UserException("非法访问");
        logger.info("用户 " + sendTo + " 验证验证码 " + code);
        String localCode = stringRedisTemplate.opsForValue().get(sendTo);
        if(localCode == null) throw new UserException("验证码已经过期");
        else if(!code.equals(localCode)) throw new UserException("验证码错误");
        logger.info("用户 --- > " + sendTo + " 验证成功");
        stringRedisTemplate.opsForValue().set(sendTo,code,5);
    }
}

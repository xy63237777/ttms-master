package com.thesevensky.starter.properties.email;

/**
 * @Author TheSevenSky
 * @Date: 2019/6/8 0:15
 * @Version 1.0
 */
public class EmailProperties {
    private String sendFrom = "13639558669@163.com";
    private String subject = "乐多影城验证码";
    private String content = "亲爱的用户 %s : 您好! \r\n \t\t 欢迎使用【乐多影城】服务,您的验证码是 : %s 有效时间为30分钟" +
            "\r\n \t\t\t感谢您的使用";
    private int codeLength = 6;
    private long mailTime = 1000 * 60 * 30;

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getSendFrom() {
        return sendFrom;
    }

    public void setSendFrom(String sendFrom) {
        this.sendFrom = sendFrom;
    }

    public int getCodeLength() {
        return codeLength;
    }

    public void setCodeLength(int codeLength) {
        this.codeLength = codeLength;
    }

    public long getMailTime() {
        return mailTime;
    }

    public void setMailTime(long mailTime) {
        this.mailTime = mailTime;
    }
}

package com.kingsoft.sendemail;

import java.util.UUID;

/**
 * Created by mjlinks on 15-3-11.
 */
public class Test {


    public static void main(String[] args){
        //这个类主要是设置邮件
        MailSenderInfo mailInfo = new MailSenderInfo();
        mailInfo.setMailServerHost("smtp.126.com");
        mailInfo.setMailServerPort("25");
        mailInfo.setValidate(true);
        mailInfo.setUserName("XXXXXXX@126.com");
        mailInfo.setPassword("XXXXXXX");//您的邮箱密码
        mailInfo.setFromAddress("XXXXXXXX@126.com");
        mailInfo.setToAddress("XXXXX@XXXXX.com");
        mailInfo.setSubject("test 标题");
        mailInfo.setContent("test 内容");
        //这个类主要来发送邮件
        JavaMailSender sms = new JavaMailSender();
//        sms.sendTextMail(mailInfo);//发送文体格式
        sms.sendHtmlMail(mailInfo);//发送html格式


    }

    public static String getUUID(){
        String s = UUID.randomUUID().toString();
        //去掉“-”符号
        return s.substring(0,8)+s.substring(9,13)+s.substring(14,18)+s.substring(19,23)+s.substring(24);
    }
}

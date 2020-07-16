package com.lyzzz.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Random;

/**
 * @location： freight\com.lyzzz.controller
 * @creatTime: 2020/7/7  18:24
 * @author: Administrator
 * @remark:
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext-mail.xml")
public class UserControllerTest {

    @Autowired
    private MailSender mailSender;
    @Autowired
    private SimpleMailMessage simpleMailMessage;

    @Test
    public void SingleMailSend (){
        simpleMailMessage.setSubject("你好");
        simpleMailMessage.setText("这个是一个通过Spring框架来发送邮件的小程序");
        simpleMailMessage.setTo("975900735@qq.com");
        mailSender.send(simpleMailMessage);
    }

    @Test
    public void test2(){
        int i = new Random().nextInt(10000);
        System.out.println(i);
    }


}

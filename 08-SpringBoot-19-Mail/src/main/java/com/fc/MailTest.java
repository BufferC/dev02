package com.fc;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.Date;

@SpringBootTest
public class MailTest {
    // 从容器中获取邮件发送对象
    @Autowired
    private JavaMailSender sender;

    @Test
    void testHtmlMail() {
        // 创建一个邮件对象
        MimeMessage mimeMessage = sender.createMimeMessage();

        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage);

        try {
            helper.setFrom("2937753364@qq.com");
            // 设置发送的时间（定时邮件）
            helper.setSentDate(new Date());

            helper.setTo(new String[] {
                    "3170549226@qq.com",
                    "3143350771@qq.com",
                    "2631994547@qq.com",
                    "3322432808@qq.com"
            });

            helper.setCc(new String[] {
                    "2831791259@qq.com",
                    "3044753348@qq.com"
            });

            helper.setBcc("2508993136@qq.com");

            helper.setSubject("重金求子");

            helper.setText(
                    "<img src='https://t10.baidu.com/it/u=1447337390,142630286&fm=30&app=106&fc=JPEG?w=312&h=208&s=978590414A313A157481CC150300E0C2' alt='图片'/><font align='center' color='red'>欧阳xx，28岁嫁夫港商，因夫无法生育，为继承家业，想寻健康男士与我共孕，通话谈好，飞你处见面首付定金50万，电话1383838382</font>"
                    , true);

            sender.send(mimeMessage);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    @Test
    void testSimple() {
        // 简单的邮件消息（信息）
        SimpleMailMessage message = new SimpleMailMessage();
        // 发送人
        message.setFrom("2937753364@qq.com");
        // 接收者
        message.setTo("3170549226@qq.com",
                "3143350771@qq.com",
                "2631994547@qq.com",
                "3322432808@qq.com");
        // 抄送人
        message.setCc("2831791259@qq.com",
                "3044753348@qq.com");
        // 秘密抄送，只有发送人和密抄者能够看到
        message.setBcc("1599862203@qq.com");
        // 邮件主题
        message.setSubject("您有一条新的邮件，请注意查收~");
        // 邮件内容
        message.setText("您已经成为失信人员，请速给我转账5000解除限制，否则XXX");
        // 发送邮件
        sender.send(message);
    }
}

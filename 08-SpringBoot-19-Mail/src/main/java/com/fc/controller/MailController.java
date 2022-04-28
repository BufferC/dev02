package com.fc.controller;

import com.fc.vo.MailVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@RestController
@RequestMapping("mail")
public class MailController {
    @Autowired
    private JavaMailSender sender;

    @PostMapping("send")
    public String send(MailVO mail, MultipartFile[] file) {

        MimeMessage mimeMessage = sender.createMimeMessage();
        try {
            // 注意，必须多传递一个true，代表允许接收附件
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);

            helper.setFrom(mail.getFrom());
            helper.setTo(mail.getTo());
            helper.setCc(mail.getCc());
            helper.setBcc(mail.getBcc());
            helper.setSubject(mail.getSubject());
            helper.setText(mail.getContent(), true);

            // 添加附件
            if (file.length > 0) {
                for (MultipartFile multipartFile : file) {
                    helper.addAttachment(multipartFile.getOriginalFilename(), multipartFile);
                }
            }

            // 发送
            sender.send(mimeMessage);
        } catch (MessagingException e) {
            e.printStackTrace();
        }


        return "发送成功";
    }
}

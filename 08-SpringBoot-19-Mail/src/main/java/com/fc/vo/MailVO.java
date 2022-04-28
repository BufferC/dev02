package com.fc.vo;

import lombok.Data;

@Data
public class MailVO {
    private String from;
    private String[] to;
    private String[] cc;
    private String[] bcc;
    private String subject;
    private String content;
}

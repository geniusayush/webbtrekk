package com.sherlockcodes.webbtrekk.entity;

public class Email {
    private String subject;
    private String body;
    private String to;

    private String attachment;

    public Email() {
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getAttatchment() {
        return attachment;
    }

    public void setAttatchment(String attatchment) {
        this.attachment = attatchment;
    }
}

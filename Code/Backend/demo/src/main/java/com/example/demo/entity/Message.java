package com.example.demo.entity;

import javax.persistence.*;

@Entity
@Table(name ="messages")
public class Message {


    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    @Column(name = "id", nullable = false, updatable = false)
    private Long id;

    @Column(name = "senderName")
    private String senderName;

    @Column(name = "senderEmail")
    private String senderEmail;

    @Column(name = "content")
    private String content;

    @Column(name = "status")
    private String status;

    public Message(Long id, String senderName, String senderEmail, String content, String status) {
        this.id = id;
        this.senderName = senderName;
        this.senderEmail = senderEmail;
        this.content = content;
        this.status = status;
    }

    public Message() {
    }

    public Long getId() {return id;}
    public void setId(Long id) {this.id = id;}

    public String getSenderName() {return senderName;}
    public void setSenderName(String senderName) {this.senderName = senderName;}

    public String getContent() {return content;}
    public void setContent(String content) {this.content = content;}

    public String getSenderEmail() {return senderEmail;}
    public void setSenderEmail(String senderEmail) {this.senderEmail = senderEmail;}

    public String getStatus() {return status;}
    public void setStatus(String status) {this.status = status;}
}

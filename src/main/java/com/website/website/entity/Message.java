package com.website.website.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "hire_me")
@Data
@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Message
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "message_id")
    private long messageId;

    @NotNull
    @Column(name = "email")
    private String email;

    @NotNull
    @Column(name = "message")
    private String message;

    public Message(String email, String message)
    {
        this.email = email;
        this.message = message;
    }
}

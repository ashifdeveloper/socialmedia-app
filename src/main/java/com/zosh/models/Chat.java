package com.zosh.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Chat {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String chatName;
    private String chatImage;
    @ManyToMany
    private List<User> users = new ArrayList<>();
    private LocalDateTime timeStamp;

    @OneToMany(mappedBy = "chat")
    private List<Message> messages = new ArrayList<>();

}

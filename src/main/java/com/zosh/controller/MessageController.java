package com.zosh.controller;

import com.zosh.models.Message;
import com.zosh.models.User;
import com.zosh.service.MessageService;
import com.zosh.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MessageController {
    @Autowired
    private MessageService messageService;
    @Autowired
    private UserService userService;

    @PostMapping("messages/chat/{chatId}")
    public Message createMessage(@RequestBody Message req, @RequestHeader("Authorization") String jwt, @PathVariable Integer chatId) throws Exception {
        User user = userService.findUserByJwt(jwt);
        Message message = messageService.createMessage(user, chatId, req);
        return message;

    }


    @GetMapping("messages/chat/{chatId}")
    public List<Message> findChatsMessages(@RequestHeader("Authorization") String jwt, @PathVariable Integer chatId) throws Exception {
        User user = userService.findUserByJwt(jwt);
        List<Message> messages = messageService.findChatsMessages(chatId);
        return messages;

    }
}


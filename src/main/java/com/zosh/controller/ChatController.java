package com.zosh.controller;

import com.zosh.config.request.CreateChatRequest;
import com.zosh.models.Chat;
import com.zosh.models.User;
import com.zosh.service.ChatService;
import com.zosh.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ChatController {
    @Autowired
    private ChatService chatService;
    @Autowired
    UserService userService;
    @PostMapping("/chats")
    public Chat createChat(@RequestHeader("Authorization") String jwt,@RequestBody CreateChatRequest req) throws Exception {
        User requser = userService.findUserByJwt(jwt);
        User user2 = userService.findUserById(req.getUserId());
        Chat chat = chatService.createChat(requser,user2);
        return chat;

    }


    @GetMapping("/chats")

    public List<Chat> findUsersChat(@RequestHeader("Authorization") String jwt){
       User user = userService.findUserByJwt(jwt);
        List<Chat> chats = chatService.findUsersChat(user.getId());
        return chats;

    }
}

package com.zosh.service;

import com.zosh.models.Chat;
import com.zosh.models.Message;
import com.zosh.models.User;
import com.zosh.repository.ChatRepository;
import com.zosh.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class MessageServiceImplementation implements MessageService {
    @Autowired
    private MessageRepository messageRepository;
    @Autowired
    private ChatService chatService;
    @Autowired
    ChatRepository chatRepository;
    @Override
    public Message createMessage(User user, Integer chatId, Message req) throws Exception {
        Chat chat = chatService.findChatById(chatId);//check msg exist or not

        Message message = new Message();
        message.setChat(chat);
        message.setContent(req.getContent());
        message.setImage(req.getImage());
        message.setUser(user);
        message.setTimeStamp(LocalDateTime.now());
        Message savedMessage = messageRepository.save(message);

        chat.getMessages().add(savedMessage);
        chatRepository.save(chat);
        return savedMessage;
    }

    @Override
    public List<Message> findChatsMessages(Integer chatId) throws Exception {
        Chat chat = chatService.findChatById(chatId);


        return messageRepository.findByChatId(chatId);
    }
}

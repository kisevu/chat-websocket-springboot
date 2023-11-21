package com.ameda.kevin.websocketchat.service;

import com.ameda.kevin.websocketchat.entity.ChatMessage;
import com.ameda.kevin.websocketchat.repo.ChatMessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ChatMessageService {
    private final ChatRoomService chatRoomService;
    private final ChatMessageRepository chatMessageRepository;

    public ChatMessage save(ChatMessage chatMessage){
        var chatId = chatRoomService.
                getChatRoomId(
                        chatMessage.getSenderId(),
                        chatMessage.getRecipientId(),
                        true).orElseThrow();
        chatMessage.setChatId(chatId);
       return chatMessageRepository.save(chatMessage);
    }
    public List<ChatMessage> findChatMessages(String senderId, String recipientId){
        var chatId = chatRoomService.getChatRoomId(senderId,recipientId,false);
        return chatId
                .map(chatMessageRepository::findByChatId).orElse(new ArrayList<>());
    }
}

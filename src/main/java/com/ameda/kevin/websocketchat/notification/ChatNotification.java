package com.ameda.kevin.websocketchat.notification;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ChatNotification {
    private String chatId;
    private String senderId;
    private String recipientId;
    private String content;
}

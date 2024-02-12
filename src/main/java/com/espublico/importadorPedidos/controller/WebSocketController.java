package com.espublico.importadorPedidos.controller;

import com.espublico.importadorPedidos.entity.Message;
import com.espublico.importadorPedidos.map.MessageMap;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class WebSocketController {

    private final MessageMap messageMap = new MessageMap();


    @MessageMapping("/status")
    @SendTo("/topic/messages")
    public Message sendMessage(Message message, @Header("simpSessionId") String sessionId) {
        this.messageMap.saveSession(sessionId, message.getName());
        System.out.println(sessionId);
        return message;

    }
}

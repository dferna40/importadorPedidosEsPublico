package com.espublico.importadorPedidos.websocket;

import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

public class MyWebSocketHandler extends TextWebSocketHandler {

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        // Aquí puedes definir cómo manejar los mensajes recibidos
        System.out.println("Message received: " + message.getPayload());
        // Por ejemplo, puedes hacer eco del mensaje recibido a todos los usuarios conectados
        session.sendMessage(new TextMessage("Echo: " + message.getPayload()));
    }
}

package com.espublico.importadorPedidos.config;

import com.espublico.importadorPedidos.map.MessageMap;
import org.springframework.context.event.EventListener;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

public class WebSocketEvents {

    private MessageMap messageMap;

    @EventListener
    public void onDisconnectEvent(SessionDisconnectEvent event) {
        String sessionId = event.getSessionId();
        this.messageMap.removeSession(sessionId);
    }
}

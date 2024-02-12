package com.espublico.importadorPedidos.map;

import java.util.HashMap;
import java.util.Map;

public class MessageMap {

    private Map<String, String> nameSession = new HashMap<>();

    public String getNameBySession(String session) {
        return this.nameSession.get(session);
    }
    public void saveSession(String sessionId, String name) {
        this.nameSession.put(sessionId, name);
    }
    public void removeSession(String sessionId) {
        this.nameSession.remove(sessionId);
    }
}

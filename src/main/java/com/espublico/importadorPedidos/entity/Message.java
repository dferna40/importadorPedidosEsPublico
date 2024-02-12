package com.espublico.importadorPedidos.entity;

public class Message {

    private String name;
    private String text;

    public Message(String name, String text) {
        super();
        this.name = name;
        this.text = text;
    }

    @Override
    public String toString() {
        return "Message [name=" + name + ", text=" + text + "]";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}

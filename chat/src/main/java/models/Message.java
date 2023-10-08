package models;

public class Message {
    private String name;
    private String content;

    public void setName(String name) {
        this.name = name;
    }

    public Message(String name, String content) {
        this.name = name;
        this.content=content;
    }

    public String getName() {
        return name;
    }
}

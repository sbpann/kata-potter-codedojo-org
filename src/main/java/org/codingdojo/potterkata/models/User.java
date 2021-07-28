package org.codingdojo.potterkata.models;

import java.util.UUID;

public class User {
    public String id;
    public String name;

    public User(String name) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}

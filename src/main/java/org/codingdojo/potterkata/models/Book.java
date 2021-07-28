package org.codingdojo.potterkata.models;

import java.util.UUID;

public class Book implements Item {
    public String id;
    public String name;
    public Double price;

    public Book(String id, String name, Double price) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.price = price;
    }


    public String getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public Double getPrice() {
        return this.price;
    }
}

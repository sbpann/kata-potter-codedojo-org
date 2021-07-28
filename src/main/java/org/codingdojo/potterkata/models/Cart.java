package org.codingdojo.potterkata.models;

import java.util.*;

public class Cart {
    public String id;
    public String userID;
    public ArrayList<CartItem> items;

    public Cart(String userID) {
        this.id = UUID.randomUUID().toString();
        this.userID = userID;
        this.items = new ArrayList<>();
    }

    public String getId() {
        return this.id;
    }

    public ArrayList<CartItem> getItems() {
        return this.items;
    }

    public Double getTotalPrice() {
        Double total = 0.0;
        for (CartItem cartItem: this.getItems()) {
            total = total + (cartItem.getNumber() * cartItem.getPricePerUnit());
        }
        return total;
    }

    public void addItem(Item item) {
        Optional<CartItem> addedCartItem = this.getItems().stream().filter(cartItem -> cartItem.getSKU().equals(item.getId())).findFirst();
        if (addedCartItem.isEmpty()) {
            this.getItems().add(new CartItem(item.getId(), item.getPrice()));
            return;
        }

        CartItem oldCartItem = addedCartItem.get();
        CartItem newCartItem = addedCartItem.get();
        oldCartItem.setNumber(oldCartItem.getNumber() + 1);
        this.getItems().set(this.getItems().indexOf(oldCartItem), newCartItem);
    }

    public void removeItem(Item item) {
        Optional<CartItem> addedCartItem = this.getItems().stream().filter(cartItem -> cartItem.getSKU().equals(item.getId())).findFirst();
        if (addedCartItem.isEmpty()) {
            return;
        }

        if (addedCartItem.get().getNumber().equals(1)) {
            this.getItems().remove(addedCartItem.get()); // guarantee unique
            return;
        }

        CartItem oldCartItem = addedCartItem.get();
        CartItem newCartItem = addedCartItem.get();
        oldCartItem.setNumber(oldCartItem.getNumber() - 1);
        this.getItems().set(this.getItems().indexOf(oldCartItem), newCartItem);
    }
}
